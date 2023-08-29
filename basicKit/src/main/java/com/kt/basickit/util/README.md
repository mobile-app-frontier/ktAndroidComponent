# Utils

[Semantic Versioning](https://semver.org/) rule 을 따른 `Version` 객체 제공

- [Example](#example)
- [More](#more)

## Example

``` kotlin
// Code block

// initialize from String Version
val versionFromString = Version.createVersion("2.10.4-beta")

// initialize from major, minor, patch, preRelease Version
val appVersion = Version(major = 1, minor = 6, patch = 0, preRelease = null)
```