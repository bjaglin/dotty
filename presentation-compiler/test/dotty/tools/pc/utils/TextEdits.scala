package dotty.tools.pc.utils

import dotty.tools.pc.utils.TestExtensions._
import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.TextEdit

import scala.jdk.CollectionConverters._
import scala.language.unsafeNulls
import scala.meta.internal.mtags.CommonMtagsEnrichments._

object TextEdits:
  def applyEdits(text: String, edits: List[TextEdit]): String =
    if (edits.isEmpty) text
    else {
      val positions: List[(TextEdit, Range)] = edits
        .map(edit => (edit, Option(edit.getRange)))
        .collect { case (edit, Some(range)) =>
          edit -> range
        }
        .sortBy((_, range) =>
          (range.getStart.getLine, range.getStart.getCharacter)
        )
      var curr = 0
      val out = new java.lang.StringBuilder()
      positions.foreach { case (edit, pos) =>
        out.append(text, curr, pos.getStart.getOffset(text))
        out.append(edit.getNewText())
        curr = pos.getEnd.getOffset(text)
      }
      out.append(text, curr, text.length)
      out.toString
    }

  def applyEdits(text: String, item: CompletionItem): String =
    val edits = item.getLeftTextEdit().toList ++
      Option(item.getAdditionalTextEdits).toList.flatMap(_.asScala)
    applyEdits(text, edits)
