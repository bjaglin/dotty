package dotty
package tools
package dotc

import org.junit.AfterClass
import org.junit.Test

import java.io.File as JFile
import scala.concurrent.duration.*
import scala.language.unsafeNulls

import reporting.TestReporter
import vulpix.*

class FromTastyTests {
  import TestConfiguration._
  import FromTastyTests._

  @Test def posTestFromTasty: Unit = {
    // Can be reproduced with
    // > sbt
    // > scalac -Ythrough-tasty -Ycheck:all <source>

    implicit val testGroup: TestGroup = TestGroup("posTestFromTasty")
    compileTastyInDir(s"tests${JFile.separator}pos", defaultOptions,
      fromTastyFilter = FileFilter.exclude(TestSources.posFromTastyBlacklisted)
    ).checkCompile()
  }

  @Test def runTestFromTasty: Unit = {
    // Can be reproduced with
    // > sbt
    // > scalac -Ythrough-tasty -Ycheck:all <source>
    // > scala Test

    implicit val testGroup: TestGroup = TestGroup("runTestFromTasty")
    compileTastyInDir(s"tests${JFile.separator}run", defaultOptions,
      fromTastyFilter = FileFilter.exclude(TestSources.runFromTastyBlacklisted)
    ).checkRuns()
  }
}

object FromTastyTests extends ParallelTesting {
  // Test suite configuration --------------------------------------------------

  def maxDuration = 30.seconds
  def numberOfSlaves = Runtime.getRuntime().availableProcessors()
  def safeMode = Properties.testsSafeMode
  def isInteractive = SummaryReport.isInteractive
  def testFilter = Properties.testsFilter
  def updateCheckFiles: Boolean = Properties.testsUpdateCheckfile
  def failedTests = TestReporter.lastRunFailedTests

  implicit val summaryReport: SummaryReporting = new SummaryReport
  @AfterClass def tearDown(): Unit = {
    super.cleanup()
    summaryReport.echoSummary()
  }
}
