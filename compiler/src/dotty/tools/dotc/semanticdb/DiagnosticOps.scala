package dotty.tools.dotc.semanticdb

import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.interfaces.Diagnostic.ERROR
import dotty.tools.dotc.interfaces.Diagnostic.INFO
import dotty.tools.dotc.interfaces.Diagnostic.WARNING
import dotty.tools.dotc.reporting.Diagnostic
import dotty.tools.dotc.semanticdb as s

import scala.annotation.internal.sharable

object DiagnosticOps:
  @sharable private val asciiColorCodes = "\u001B\\[[;\\d]*m".r
  extension (d: Diagnostic)
    def toSemanticDiagnostic: s.Diagnostic =
      val severity = d.level match
        case ERROR => s.Diagnostic.Severity.ERROR
        case WARNING => s.Diagnostic.Severity.WARNING
        case INFO => s.Diagnostic.Severity.INFORMATION
        case _ => s.Diagnostic.Severity.INFORMATION
      val msg = asciiColorCodes.replaceAllIn(d.msg.message, m => "")
      s.Diagnostic(
        range = Scala3.range(d.pos.span, d.pos.source),
        severity = severity,
        message = msg
      )
