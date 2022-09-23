import com.google.protobuf.gradle.*

plugins {
    `java-library`
    id("com.google.protobuf")
}

val protobufVersion = "3.19.4"

dependencies {
    api("com.google.protobuf:protobuf-java:$protobufVersion")
}

protobuf {
    generatedFilesBaseDir = "$projectDir/../proto/src"
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
}

sourceSets {
    main {
        proto {
            srcDir("../opamp-spec/proto")
        }
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}
