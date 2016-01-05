name := "play-elasticplugin"

version := "0.2.0"

organization := "com.github.unterstein"

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Spring releases" at "http://repo.springsource.org/release",
  "Spring milestones" at "http://repo.spring.io/milestone"

// This line below can cause "uri has authority component" errors.   
//"Local Maven" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
)


scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.4.4",
  "com.typesafe.play" %% "play-java" % "2.4.4",

  // spring data stuff
//  "org.springframework" % "spring-context" % "4.1.8.RELEASE",
  "org.springframework.data" % "spring-data-elasticsearch" % "1.3.1.RELEASE",

  // elastic stuff
  // "org.elasticsearch" % "elasticsearch" % "2.1.0", // TODO UPGRADE !
  "org.elasticsearch" % "elasticsearch" % "1.7.3",

  // Inject stuff
  "javax.inject" % "javax.inject" % "1"
)

publishTo <<= version {
  case v if v.trim.endsWith("SNAPSHOT") => Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
  case _ => Some(Resolver.file("Github Pages",  new File("../unterstein.github.io/repo")))
}
