name := "Avatar"

version := "0.1"

scalaVersion := "2.13.9"

javaOptions += "-Dfile.encoding=UTF8"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M3",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1",
  "com.twitter" %% "util-core" % "21.11.0",
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test exclude ("junit", "junit-dep"),
  "org.apache.commons" % "commons-lang3" % "3.12.0",
  "com.ibm.icu" % "icu4j" % "54.1.1"
  // "marytts" % "marytts-lang" % "5.1.2",
  // "marytts" % "marytts-client" % "5.1.2",
  // "marytts" % "marytts-runtime" % "5.1.2",
  // "marytts" % "voice-cmu-slt-hsmm" % "5.1.2",
  // "marytts" % "voice-dfki-pavoque-neutral-hsmm" % "5.1",
  // "marytts" % "voice-istc-lucia-hsmm" % "5.1",
  // "marytts" % "voice-upmc-pierre-hsmm" % "5.1"
  // "com.eed3si9n" % "sbt-assembly" % "2.1.1"
)

// assembly / mainClass := Some("gui.Main") // le nom de la classe main
// assembly / assemblyJarName := "avatar.jar"
fork := true
