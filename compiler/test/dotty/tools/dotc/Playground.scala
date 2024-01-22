package dotty.tools.dotc

import dotty.tools.vulpix._
import org.junit.Ignore
import org.junit.Test

@Ignore class Playground:
  import TestConfiguration._
  import CompilationTests._

  @Test def example: Unit =
    implicit val testGroup: TestGroup = TestGroup("playground")
    compileFile("tests/playground/example.scala", defaultOptions).checkCompile()
