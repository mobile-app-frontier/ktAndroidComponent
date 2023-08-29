package com.kt.basickit.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * [Semantic Versioning](https://semver.org/)
 *
 * 지원 하는 버전 format: "$major.$minor.$patch(-$preRelease)"
 * ex) 1.6.1 or 2.10.4-beta
 *
 * <em>[Version] 비교 시에는 [preRelease] 는 확인 하지 않는다. 지원 하지 않는 format 인 경우 Version(0,0,0,null) 를 return 한다.</em>
 *
 * @property major version when you mamke incompatible API changes
 * @property minor version when you add functionality in a backward compatible manner
 * @property patch version when you make backward compatible bug fixes
 * @property preRelease
 * Identifiers MUST comprise only ASCII alphanumerics and hyphens [0-9A-Za-z-]. Identifiers MUST NOT be empty.
 * Numeric identifiers MUST NOT include leading zeroes.
 * Pre-release versions have a lower precedence than the associated normal version.
 * A pre-release version indicates that the version is unstable and might not satisfy the intended compatibility
 * requirements as denoted by its associated normal version.
 * Examples: 1.0.0-alpha, 1.0.0-alpha.1, 1.0.0-0.3.7, 1.0.0-x.7.z.92, 1.0.0-x-y-z.
 */
@Parcelize
public data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val preRelease: String?
) : Comparable<Version>, Parcelable {
    companion object {
        /**
         * Semantic Versioning rule 를 따르는 [String] 버전 정보로 [Version] create
         *
         * @param versionName Semantic Versioning rule 를 따르는 String
         * @return [Version] 을 return. Semantic Versioning rule 를 따르지 않을 경우, Version(0,0,0,null) 을 return.
         */
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

    /**
     * [major] -> [minor] -> [patch] 순으로 비교.
     * 주의! [preRelease] 는 비교시 사용 되지 않는다.
     *
     * @param other
     * @return
     */
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

        return 0
    }
}