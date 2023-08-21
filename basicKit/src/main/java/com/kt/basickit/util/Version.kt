package com.kt.basickit.util

public data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val preRelease: String?
) : Comparable<Version> {
    companion object {
        fun createVersion(versionName: String) : Version {
            val splitParts = versionName.split("-")
            val versionParts = splitParts[0].split(".")

            return if (versionParts.size >= 3) {
                val major = versionParts[0].toIntOrNull() ?: 0
                val minor = versionParts[1].toIntOrNull() ?: 0
                val patch = versionParts[2].toIntOrNull() ?: 0

                val preRelease = if (splitParts.size > 1) splitParts[1] else null

                Version(major, minor, patch, preRelease)
            } else {
                Version(0, 0, 0, null)
            }
        }
    }

    override fun compareTo(other: Version): Int {
        if (major != other.major) {
            return major.compareTo(other.major)
        }
        if (minor != other.minor) {
            return minor.compareTo(other.minor)
        }
        if (patch != other.patch) {
            return patch.compareTo(other.patch)
        }
//        if (preRelease != other.preRelease) {
//            if (preRelease == null) return 1
//            if (other.preRelease == null) return -1
//            return preRelease.compareTo(other.preRelease)
//        }
        return 0
    }
}