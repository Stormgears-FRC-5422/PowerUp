[PowerUp](../index.md) / [org.stormgears.utils](index.md) / [fixPermissions](./fix-permissions.md)

# fixPermissions

`fun fixPermissions(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Invokes a shell script provided by the build system with the [setuid](https://en.wikipedia.org/wiki/Setuid) flag
set that fixes permissions of the config.properties file, in case it was uploaded as the admin user.

