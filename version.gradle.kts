val snapshot = true  //set it to false when it is a release workflow

allprojects {
  var ver = "1.0.0"
  val release = findProperty("otel.release")
  if (release != null) {
    ver += "-" + release
  }
  if (snapshot) {
    ver += "-SNAPSHOT"
  }
  version = ver
}
