description = "OpenTelemetry Opamp Protobufs"

plugins {
    `java-library`
}

tasks {
    withType<JavaCompile>().configureEach {
        with(options) {
            release.set(8)

            //disable deprecation warnings for the protobuf module
            compilerArgs.add("-Xlint:-deprecation")

            encoding = "UTF-8"
        }
    }
}

val protobufVersion = "3.19.4"

dependencies {
    implementation("com.google.protobuf:protobuf-java:$protobufVersion")
}
