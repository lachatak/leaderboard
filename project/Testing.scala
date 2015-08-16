import sbt._
import sbt.Keys._

object Testing {

  import Configs._

  lazy val testAll = TaskKey[Unit]("test-all")

  private lazy val testSettings = Seq(
    fork in Test := false,
    parallelExecution in Test := false
  )

  private lazy val itSettings = inConfig(IntegrationTest)(Defaults.testSettings) ++ Seq(
    fork in IntegrationTest := false,
    parallelExecution in IntegrationTest := false,
    scalaSource in IntegrationTest := baseDirectory.value / "src/it/scala",
    resourceDirectory in IntegrationTest := baseDirectory.value / "src/e2e/resources"
  )

  private lazy val e2eSettings = inConfig(EndToEndTest)(Defaults.testSettings) ++ Seq(
    fork in EndToEndTest := false,
    parallelExecution in EndToEndTest := false,
    scalaSource in EndToEndTest := baseDirectory.value / "src/e2e/scala",
    resourceDirectory in EndToEndTest := baseDirectory.value / "src/e2e/resources"
  )

  lazy val settings = testSettings ++ itSettings ++ e2eSettings ++ Seq(
    testAll := (),
    testAll <<= testAll.dependsOn(test in EndToEndTest),
    testAll <<= testAll.dependsOn(test in IntegrationTest),
    testAll <<= testAll.dependsOn(test in Test)
  )
}
