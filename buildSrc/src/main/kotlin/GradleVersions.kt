import groovy.lang.GString
import org.gradle.api.plugins.ExtraPropertiesExtension

private val nonStableVersionMarker = listOf("alpha", "beta", "rc", "m")

fun isStableVersion(version: String): Boolean =
    !nonStableVersionMarker.any { version.contains(it, ignoreCase = true) }

fun ExtraPropertiesExtension.library(name: String): GString =
    (get("libraries") as Map<String, Any>)[name] as GString
