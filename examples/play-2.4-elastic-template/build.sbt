name := "play-elasticplugin-examples"

version := "1.0-SNAPSHOT"

lazy val plugin = RootProject(file("../.."))
lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn(plugin % "compile->compile;test->test")

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Spring releases" at "http://repo.springsource.org/release",
  "Spring milestones" at "http://repo.spring.io/milestone",
  "Spring snapshots" at "http://repo.spring.io/snapshot",
  "Local Maven" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.github.unterstein" %% "play-elasticplugin" % "0.4.0-SNAPSHOT",
  "org.webjars" % "bootstrap" % "3.2.0"

)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
