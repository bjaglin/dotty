package dotty.tools
package dotc
package transform
package patmat

import org.junit

import core.*
import Annotations.*
import Contexts.*
import Decorators.*
import Flags.*
import Names.*
import StdNames.*
import Symbols.*
import Types.*
import ast.*
import tpd.*
import vulpix.TestConfiguration
import TestConfiguration.basicClasspath
import junit.Test
import junit.Assert.*

class SpaceEngineTest:
  import SpaceEngine.*

  @Test def isSubspaceTest1: Unit = inCompilerContext(basicClasspath) {
    // Testing the property of `isSubspace` that:
    // isSubspace(a, b)  <=>  simplify(simplify(a) - simplify(a)) == Empty
    // Previously there were no simplify calls,
    // and this is a counter-example,
    // for which you need either to simplify(b) or simplify the minus result.

    val tp      = defn.ConsType.appliedTo(defn.AnyType)
    val unappTp = requiredMethod("scala.collection.immutable.::.unapply").termRef
    val params  = List(Empty, Typ(tp))

    val a = Prod(tp, unappTp, params)
    val b = Empty

    val res1 = a.isSubspace(b)

    val a2   = a.simplify
    val b2   = b.simplify
    val rem1 = minus(a2, b2)
    val rem2 = rem1.simplify
    val res2 = rem2 == Empty

    assertEquals(
      i"""|isSubspace:
          |
          |isSubspace(a, b) = $res1
          |
          |Should be equivalent to:
          |simplify(simplify(a) - simplify(b)) == Empty
          |simplify(a2          - b2)          == Empty
          |simplify(rem1)                      == Empty
          |rem2                                == Empty
          |
          |a    = $a
          |b    = $b
          |a2   = $a2
          |b2   = $b2
          |rem1 = $rem1
          |rem2 = $rem2
          |
          |""".stripMargin, res1, res2)
  }
