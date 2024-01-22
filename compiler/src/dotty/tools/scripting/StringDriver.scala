package dotty.tools.scripting

import dotty.tools.dotc.Driver
import dotty.tools.dotc.core.Contexts
import dotty.tools.dotc.util.SourceFile
import dotty.tools.io.ClassPath
import dotty.tools.io.Directory
import dotty.tools.io.PlainDirectory

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.language.unsafeNulls

import Contexts.{ Context, ctx }
import Util.*

class StringDriver(compilerArgs: Array[String], scalaSource: String) extends Driver:
  override def sourcesRequired: Boolean = false

  def compileAndRun(classpath: List[String] = Nil): Option[Throwable] =
    val outDir = Files.createTempDirectory("scala3-expression")
    outDir.toFile.deleteOnExit()

    setup(compilerArgs, initCtx.fresh) match
      case Some((toCompile, rootCtx)) =>
        given Context = rootCtx.fresh.setSetting(rootCtx.settings.outputDir, new PlainDirectory(Directory(outDir)))

        val compiler = newCompiler

        val source = SourceFile.virtual("expression", scalaSource)
        compiler.newRun.compileSources(List(source))

        val output = ctx.settings.outputDir.value
        if ctx.reporter.hasErrors then
          Some(StringDriverException("Errors encountered during compilation"))
        else
          try
            val classpath = s"${ctx.settings.classpath.value}${pathsep}${sys.props("java.class.path")}"
            val classpathEntries: Seq[Path] = ClassPath.expandPath(classpath, expandStar=true).map { Paths.get(_) }
            sys.props("java.class.path") = classpathEntries.map(_.toString).mkString(pathsep)
            detectMainClassAndMethod(outDir, classpathEntries, scalaSource) match
              case Right((mainClass, mainMethod)) =>
                mainMethod.invoke(null, Array.empty[String])
                None
              case Left(ex) => Some(ex)
          catch
            case e: java.lang.reflect.InvocationTargetException =>
              Some(e.getCause)
          finally
            deleteFile(outDir.toFile)
      case None => None
  end compileAndRun

end StringDriver

case class StringDriverException(msg: String) extends RuntimeException(msg)
