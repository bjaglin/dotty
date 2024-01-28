package dotty.tools.pc.base

import dotty.tools.dotc.util.SourceFile
import dotty.tools.dotc.util.SourcePosition
import dotty.tools.dotc.util.Spans.Span
import dotty.tools.pc.utils.MtagsEnrichments.toLsp
import dotty.tools.pc.utils.TextEdits
import org.eclipse.lsp4j as l
import org.eclipse.lsp4j.TextEdit

import java.nio.file.Paths
import scala.language.unsafeNulls
import scala.meta.internal.metals.CompilerOffsetParams
import scala.meta.pc.OffsetParams

abstract class BasePcDefinitionSuite extends BasePCSuite:

  def definitions(offsetParams: OffsetParams): List[l.Location]

  def check(original: String): Unit =
    val filename = "A.scala"
    val uri = Paths.get(filename).toUri

    val (_, offset) = params(original.removeRanges, filename)
    val cleanedCode = original.removeRanges.removePos

    val offsetRange = new SourcePosition(
      SourceFile.virtual(filename, cleanedCode),
      Span(offset)
    ).toLsp

    val locs = definitions(
      CompilerOffsetParams(uri, cleanedCode, offset)
    )
    val edits = locs.flatMap { location =>
      if (location.getUri() == uri.toString) {
        List(
          new TextEdit(
            new l.Range(
              location.getRange().getStart(),
              location.getRange().getStart()
            ),
            "<<"
          ),
          new TextEdit(
            new l.Range(
              location.getRange().getEnd(),
              location.getRange().getEnd()
            ),
            ">>"
          )
        )
      } else {
        val filename = location.getUri()
        val comment = s"/*$filename*/"
        List(new TextEdit(offsetRange, comment))
      }
    }
    val obtained = TextEdits.applyEdits(cleanedCode, edits)
    val expected = original.removePos

    assertNoDiff(expected, obtained)
