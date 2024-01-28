package dotty.tools.pc.base

import dotty.tools.pc.utils.TextEdits
import org.eclipse.{lsp4j => l}

import java.net.URI
import scala.language.unsafeNulls
import scala.meta.internal.jdk.CollectionConverters._
import scala.meta.internal.metals.CompilerOffsetParams
import scala.meta.internal.metals.CompilerRangeParams

class BaseExtractMethodSuite extends BaseCodeActionSuite:
  def checkEdit(
      original: String,
      expected: String
  ): Unit =
    val (edits, code) = getAutoImplement(original)
    val obtained = TextEdits.applyEdits(code, edits)
    assertNoDiff(expected, obtained)

  def getAutoImplement(
      original: String,
      filename: String = "file:/A.scala"
  ): (List[l.TextEdit], String) =
    val withoutExtractionPos = original.replace("@@", "")
    val onlyRangeClose = withoutExtractionPos
      .replace("<<", "")
    val code = onlyRangeClose.replace(">>", "")
    val extractionPos = CompilerOffsetParams(
      URI.create(filename),
      code,
      original.indexOf("@@"),
      cancelToken
    )
    val rangeParams = CompilerRangeParams(
      URI.create(filename),
      code,
      withoutExtractionPos.indexOf("<<"),
      onlyRangeClose.indexOf(">>"),
      cancelToken
    )
    val result = presentationCompiler
      .extractMethod(
        rangeParams,
        extractionPos
      )
      .get()
    (result.asScala.toList, code)
