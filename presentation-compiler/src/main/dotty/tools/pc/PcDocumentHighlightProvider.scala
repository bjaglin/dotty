package dotty.tools.pc

import dotty.tools.dotc.ast.tpd._
import dotty.tools.dotc.core.Symbols._
import dotty.tools.dotc.interactive.InteractiveDriver
import dotty.tools.dotc.util.SourcePosition
import dotty.tools.pc.utils.MtagsEnrichments._
import org.eclipse.lsp4j.DocumentHighlight
import org.eclipse.lsp4j.DocumentHighlightKind

import scala.meta.pc.OffsetParams

final class PcDocumentHighlightProvider(
    driver: InteractiveDriver,
    params: OffsetParams
) extends PcCollector[DocumentHighlight](driver, params):

  def collect(
      parent: Option[Tree]
  )(
      tree: Tree | EndMarker,
      toAdjust: SourcePosition,
      sym: Option[Symbol]
  ): DocumentHighlight =
    val (pos, _) = toAdjust.adjust(text)
    tree match
      case _: NamedDefTree =>
        DocumentHighlight(pos.toLsp, DocumentHighlightKind.Write)
      case _ => DocumentHighlight(pos.toLsp, DocumentHighlightKind.Read)

  def highlights: List[DocumentHighlight] =
    result().distinctBy(_.getRange())
end PcDocumentHighlightProvider
