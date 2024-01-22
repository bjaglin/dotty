package dotty.tools.dotc
package core.tasty

import dotty.tools.tasty.TastyBuffer
import dotty.tools.tasty.TastyReader

import java.nio.charset.StandardCharsets
import scala.language.unsafeNulls

import core.Comments.Comment
import util.Spans.Span
import util.HashMap
import TastyBuffer.Addr

class CommentUnpickler(reader: TastyReader) {
  import reader.*

  private[tasty] lazy val comments: HashMap[Addr, Comment] = {
    val comments = new HashMap[Addr, Comment]
    while (!isAtEnd) {
      val addr = readAddr()
      val rawComment = readUtf8()
      val position = new Span(readLongInt())
      comments(addr) = Comment(position, rawComment)
    }
    comments
  }

  def commentAt(addr: Addr): Option[Comment] =
    comments.get(addr)
}
