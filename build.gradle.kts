plugins {
  id("otel.java-conventions")
  id("java-library")
  id("maven-publish")
}

repositories {
  mavenCentral()
  mavenLocal()
}

dependencies {
  implementation(project(":proto"))
}

group = "io.opentelemetry"
version = "1.0.0"

publishing {
  publications.create<MavenPublication>("lib") {
    from(components["java"])
  }
  repositories.maven("/tmp/opamp-java")
}

tasks.jar {
  from(sourceSets.main.get().output)
  dependsOn(configurations.runtimeClasspath)
  from({
    configurations.runtimeClasspath.get().filter { it.name.equals("proto.jar") }.map { zipTree(it) }
  })
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
