package dotty.tools.pc.completions

import dotty.tools.dotc.util.SourcePosition

import scala.meta.pc.PresentationCompilerConfig

object MultilineCommentCompletion:

  def contribute(config: PresentationCompilerConfig): List[CompletionValue] =
    val newText = if config.isCompletionSnippetsEnabled() then " $0 */" else " */"
    List(CompletionValue.document("/* */", newText, "Multiline Comment"))

  def isMultilineCommentCompletion(pos: SourcePosition, text: String): Boolean =
    pos.point >= 2 &&
      text.charAt(pos.point - 2) == '/' &&
      text.charAt(pos.point - 1) == '*'
