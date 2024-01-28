package dotty
package tools
package dotc

import dotty.tools.io.Directory
import org.junit.Test

import java.io.*
import java.io.File
import java.lang.System.lineSeparator as EOL
import java.nio.charset.StandardCharsets
import java.nio.file.Path as JPath
import scala.io.Source
import scala.language.unsafeNulls
import scala.util.Using

import vulpix.FileDiff
import vulpix.TestConfiguration
import vulpix.TestConfiguration
import reporting.TestReporter
import interfaces.Diagnostic.INFO

class PrintingTest {

  def options(phase: String, flags: List[String]) =
    List(s"-Xprint:$phase", "-color:never", "-classpath", TestConfiguration.basicClasspath) ::: flags

  private def compileFile(path: JPath, phase: String): Boolean = {
    val baseFilePath  = path.toString.stripSuffix(".scala")
    val checkFilePath = baseFilePath + ".check"
    val flagsFilePath = baseFilePath + ".flags"
    val byteStream    = new ByteArrayOutputStream()
    val reporter = TestReporter.reporter(new PrintStream(byteStream), INFO)
    val flags =
      if (!(new File(flagsFilePath)).exists) Nil
      else Using(Source.fromFile(flagsFilePath, StandardCharsets.UTF_8.name))(_.getLines().toList).get

    try {
      Main.process((path.toString :: options(phase, flags)).toArray, reporter, null)
    } catch {
      case e: Throwable =>
        println(s"Compile $path exception:")
        e.printStackTrace()
    }

    val actualLines = byteStream.toString(StandardCharsets.UTF_8.name).linesIterator
    FileDiff.checkAndDumpOrUpdate(path.toString, actualLines.toIndexedSeq, checkFilePath)
  }

  def testIn(testsDir: String, phase: String) =
    val res = Directory(testsDir).list.toList
      .filter(f => f.extension == "scala")
      .map { f => compileFile(f.jpath, phase) }

    val failed = res.filter(!_)

    val msg = s"Pass: ${res.length - failed.length}, Failed: ${failed.length}"

    assert(failed.length == 0, msg)

    println(msg)

  end testIn

  @Test
  def printing: Unit = testIn("tests/printing", "typer")

  @Test
  def untypedPrinting: Unit = testIn("tests/printing/untyped", "parser")

  @Test
  def transformedPrinting: Unit = testIn("tests/printing/transformed", "repeatableAnnotations")
}
