package dotty.tools.dotc.core.tasty

import dotty.tools.dotc.Driver
import dotty.tools.dotc.Main
import dotty.tools.dotc.ast.tpd
import dotty.tools.dotc.ast.tpd.TreeOps
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Decorators.toTermName
import dotty.tools.dotc.core.Decorators.toTypeName
import dotty.tools.dotc.core.Mode
import dotty.tools.dotc.core.Names.Name
import dotty.tools.dotc.decompiler
import dotty.tools.dotc.interfaces.Diagnostic.ERROR
import dotty.tools.dotc.reporting.TestReporter
import dotty.tools.io.Directory
import dotty.tools.io.File
import dotty.tools.io.JarArchive
import dotty.tools.io.Path
import dotty.tools.vulpix.TestConfiguration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.{File => JFile}
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import scala.language.unsafeNulls
import scala.sys.process._

class PathPicklingTest {

  @Test def test(): Unit = {
    val out = JFile("out/testPathPickling")
    val cwd = JFile("").getAbsolutePath()
    delete(out)
    out.mkdir()

    locally {
      val ignorantProcessLogger = ProcessLogger(_ => ())
      val options = TestConfiguration.defaultOptions
        .and("-d", s"$out/out.jar")
        .and("-sourceroot", "tests/pos")
        .and(s"$cwd/tests/pos/i10430/lib.scala", s"$cwd/tests/pos/i10430/app.scala")
      val reporter = TestReporter.reporter(System.out, logLevel = ERROR)
      val rep = Main.process(options.all, reporter)
      assertFalse("Compilation failed.", rep.hasErrors)
    }

    val printedTasty =
      val sb = new StringBuffer
      val jar = JarArchive.open(Path(s"$out/out.jar"), create = false)
      try
        for file <- jar.iterator() if file.name.endsWith(".tasty") do
          sb.append(TastyPrinter.showContents(file.toByteArray, noColor = true))
      finally jar.close()
      sb.toString()

    assertTrue(printedTasty.contains(": i10430/lib.scala"))
    assertTrue(printedTasty.contains("[i10430/lib.scala]"))
    assertFalse(printedTasty.contains(": i10430\\lib.scala"))
    assertFalse(printedTasty.contains("[i10430\\lib.scala]"))

    assertTrue(printedTasty.contains(": i10430/app.scala"))
    assertTrue(printedTasty.contains("[i10430/app.scala]"))
    assertFalse(printedTasty.contains(": i10430\\app.scala"))
    assertFalse(printedTasty.contains("[i10430\\app.scala]"))
  }

  private def delete(file: JFile): Unit = {
    if (file.isDirectory) file.listFiles.foreach(delete)
    try Files.delete(file.toPath)
    catch {
      case _: NoSuchFileException => // already deleted, everything's fine
    }
  }
}
