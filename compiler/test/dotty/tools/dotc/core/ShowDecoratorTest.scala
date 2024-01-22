package dotty.tools
package dotc
package core

import org.junit.Assert.*
import org.junit.Test

import Contexts.*
import Decorators.*
import Denotations.*
import SymDenotations.*
import Symbols.*
import Types.*
import printing.Formatting.Show

class ShowDecoratorTest extends DottyTest:
  import ShowDecoratorTest.*

  @Test def t1 = assertEquals("... (cannot display due to FooException boom) ...", Foo().tryToShow)
end ShowDecoratorTest

object ShowDecoratorTest:
  import printing.*, Texts.*
  class FooException extends Exception("boom")
  case class Foo() extends Showable:
    def toText(printer: Printer): Text = throw new FooException
