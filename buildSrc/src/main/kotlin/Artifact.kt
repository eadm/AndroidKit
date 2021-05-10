data class Artifact(
    val group: String,
    val version: String,
    val name: String
) {
    val libraryName = "$group:$name:$version"
}