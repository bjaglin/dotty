package dotty
package tools
package scripting

import org.junit.AfterClass
import org.junit.Ignore
import org.junit.Test

import java.io.File
import java.nio.file.Path
import scala.language.unsafeNulls

import vulpix.TestConfiguration
import ScriptTestEnv._

/** Test java command line generated by bin/scala and bin/scalac */

class ClasspathTests:
  /*
   * Test disabled (temporarily).
   * verify classpath reported by called script.
   * This need to be reconceptualized.
   * System property "java.class.path" does not necessarily contain the actual runtime path,
   * So this test can fail even when the classpath is correct.
   */
  @Ignore
  @Test def hashbangClasspathVerifyTest = {
    // only interested in classpath test scripts
    val testScriptName = "classpathReport.sc"
    val testScript = scripts("/scripting").find { _.getName.matches(testScriptName) } match
      case None => sys.error(s"test script not found: ${testScriptName}")
      case Some(file) => file

    val relpath = testScript.toPath.relpath.norm
    printf("===> hashbangClasspathVerifyTest for script [%s]\n", relpath)
    printf("bash is [%s]\n", bashExe)

    if packBinScalaExists then
      val bashCmdline = s"SCALA_OPTS= $relpath"
      val cmd = Array(bashExe, "-c", bashCmdline)

      cmd.foreach { printf("[%s]\n", _) }

      // classpathReport.sc is expected to produce two lines:
      // cwd: <current-working-directory-seen-by-the-script>
      // classpath: <classpath-seen-by-the-script>

      val scriptOutput: Seq[String] = exec(cmd*)
      val scriptCwd: String = findTaggedLine("cwd", scriptOutput) // the value tagged "cwd: "
      printf("script ran in directory [%s]\n", scriptCwd)
      val scriptCp = findTaggedLine("classpath", scriptOutput) // the value tagged "classpath: "

      // convert scriptCp to a list of files
      val hashbangJars: List[File] = scriptCp.split(psep).map { _.toFile }.toList
      val hashbangClasspathJars = hashbangJars.map { _.name }.sorted.distinct // get jar basenames, remove duplicates
      val packlibDir = s"$scriptCwd/$packLibDir" // classpathReport.sc specifies a wildcard classpath in this directory
      val packlibJars: List[File] = listJars(packlibDir) // classpath entries expected to have been reported by the script

      printf("%d jar files in dist/target/pack/lib\n", packlibJars.size)
      printf("%d test script jars in classpath\n", hashbangClasspathJars.size)

      val (diff: Set[File], msg: String) = if (packlibJars.size > hashbangClasspathJars.size) {
        (packlibJars.toSet -- hashbangJars.toSet , "only in packlib classpath")
      } else {
        (hashbangJars.toSet -- packlibJars.toSet , "only in hashbang classpath")
      }
      // verify that the script hasbang classpath setting was effective at supplementing the classpath
      // (a minimal subset of jars below dist/target/pack/lib are always be in the classpath)
      val missingClasspathEntries = if hashbangClasspathJars.size != packlibJars.size then
        printf("packlib dir [%s]\n", packlibDir)
        printf("hashbangClasspathJars: %s\n", hashbangJars.map { _.relpath.norm }.mkString("\n ", "\n ", ""))
        printf("# %s\n", msg)
        diff.foreach { (f: File) => printf(" %s\n", f.relpath.norm) }
      else
        Set.empty[String]

      assert(hashbangClasspathJars.size == packlibJars.size)
  }
  /*
   * verify classpath is unglobbed by MainGenericRunner.
   */
  @Test def unglobClasspathVerifyTest = {
    val testScriptName = "unglobClasspath.sc"
    val testScript = scripts("/scripting").find { _.name.matches(testScriptName) } match
      case None => sys.error(s"test script not found: ${testScriptName}")
      case Some(file) => file

    val relpath = testScript.toPath.relpath.norm
    printf("===> unglobClasspathVerifyTest for script [%s]\n", relpath)
    printf("bash is [%s]\n", bashExe)

    if packBinScalaExists then
      val bashCmdline = s"set +x ; SCALA_OPTS= $relpath"
      val cmd = Array(bashExe, "-c", bashCmdline)

      cmd.foreach { printf("[%s]\n", _) }

      // test script reports the classpath it sees
      val scriptOutput = exec(cmd*)
      val scriptCp = findTaggedLine("unglobbed classpath", scriptOutput)
      printf("%s\n", scriptCp)
      val classpathJars = scriptCp.split(psep).map { _.getName }.sorted.distinct
      //classpathJars.foreach { printf("%s\n", _) }
      assert(classpathJars.size > 1)
  }

