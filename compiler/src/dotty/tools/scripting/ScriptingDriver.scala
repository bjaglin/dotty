package dotty.tools.scripting

import dotty.tools.dotc.Driver
import dotty.tools.dotc.core.Contexts
import dotty.tools.io.ClassPath
import dotty.tools.io.Directory
import dotty.tools.io.PlainDirectory

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.language.unsafeNulls

import Contexts.{ Context, ctx }
import Util._

class ScriptingDriver(compilerArgs: Array[String], scriptFile: File, scriptArgs: Array[String]) extends Driver:
  def compileAndRun(pack:(Path, Seq[Path], String) => Boolean = null): Option[Throwable] =
    val outDir = Files.createTempDirectory("scala3-scripting")
    outDir.toFile.deleteOnExit()
    setup(compilerArgs :+ scriptFile.getAbsolutePath, initCtx.fresh) match
      case Some((toCompile, rootCtx)) =>
        given Context = rootCtx.fresh.setSetting(rootCtx.settings.outputDir,
          new PlainDirectory(Directory(outDir)))

        if doCompile(newCompiler, toCompile).hasErrors then
          Some(ScriptingException("Errors encountered during compilation"))
        else
          try
            val classpath = s"${ctx.settings.classpath.value}${pathsep}${sys.props("java.class.path")}"
            val classpathEntries: Seq[Path] = ClassPath.expandPath(classpath, expandStar=true).map { Paths.get(_) }
            detectMainClassAndMethod(outDir, classpathEntries, scriptFile.toString) match
              case Right((mainClass, mainMethod)) =>
                val invokeMain: Boolean = Option(pack).map { func =>
                    func(outDir, classpathEntries, mainClass)
                  }.getOrElse(true)
                if invokeMain then mainMethod.invoke(null, scriptArgs)
                None
              case Left(ex) => Some(ex)
          catch
            case e: java.lang.reflect.InvocationTargetException =>
              Some(e.getCause)
          finally
            deleteFile(outDir.toFile)
      case None => None
  end compileAndRun

end ScriptingDriver

case class ScriptingException(msg: String) extends RuntimeException(msg)
