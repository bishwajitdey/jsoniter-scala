val `jsoniter-scala-examples` = project.in(file("."))
  .enablePlugins(NativeImagePlugin)
  .settings(
    scalaVersion := "2.13.6",
    scalacOptions ++= Seq("-Xmacro-settings:print-codecs"),
    crossScalaVersions := Seq("2.13.6", "2.12.14", "2.11.12"),
    Compile / mainClass := Some("com.github.plokhotnyuk.jsoniter_scala.examples.Example01"),
    assembly / mainClass := Some("com.github.plokhotnyuk.jsoniter_scala.examples.Example01"),
    libraryDependencies ++= Seq(
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "latest.integration",
      // Use the "provided" scope instead when the "compile-internal" scope is not supported
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "latest.integration" % "compile-internal"
    )
  )
