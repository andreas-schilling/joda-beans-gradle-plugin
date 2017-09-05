Joda-Beans plugin for Gradle
----------------------------
[![Maven Central](https://img.shields.io/maven-central/v/org.kiirun/joda-beans-gradle-plugin.svg)]()
[![Bintray](https://api.bintray.com/packages/andreas-schilling/maven/org.kiirun%3Ajoda-beans-gradle-plugin/images/download.svg) ](https://bintray.com/andreas-schilling/maven/org.kiirun%3Ajoda-beans-gradle-plugin/_latestVersion)
[![Apache 2.0](https://img.shields.io/github/license/andreas-schilling/joda-beans-gradle-plugin.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This project provides a [Gradle](https://www.gradle.org/) plugin
for [Joda-Beans](https://github.com/JodaOrg/joda-beans).
This is basically a copy of the [Joda-Beans Maven Plugin](https://github.com/JodaOrg/joda-beans-maven-plugin)

### Usage in your gradle build

This section outlines the changes required to your gradle build script.

#### Dependency

The plugin is available from JCenter and Maven Central.
To use it, declare a buildscript dependency to the plugin and a compile or api dependency to Joda-Beans (any version >= 1.2):

```
buildscript {
  ...
  
  dependencies {
      ...
      classpath 'org.kiirun:joda-beans-gradle-plugin:1.0.4'
  }
}

dependencies {
    compile 'org.joda:joda-beans:1.8'
    ...
}
```

Please note, that for versions of the plugin prior to 1.0.3, an explicit buildscript dependency to Joda-Beans is needed.
Then you can simply use the plugin with:

```
apply plugin: 'joda-beans'
```

### Tasks

The plugin provides two tasks - `jodaValidate` and `jodaGenerate`.

#### JodaValidate

The `jodaValidate` task is used to check that no Joda-Beans need regeneration.
It can be used from the command line as `gradle jodaValidate`.

This task has the following optional configuration items:
- `sourceDir` - where to look for bean classes. The default is autodetected frmo the `sourceSet` definition.
- `indent` - as per the command line, the amount of indentation used,
either the word "tab", or a number, such as "2" or "4". Default is "4".
- `prefix` - as per the command line, the prefix used by fields. Default is "".
- `verbose` - as per the command line, a number from "0" (quiet) to "3" (verbose).
- `recursive` - whether the source directory should be parsed recursively. Default is `true`.
- `strict` - whether the validate task should fail if beans need regenerating. Default is `false`.
- `config` - the configuration file to use for bean generation. Can be `guava` or `jdk6`, default is `guava`

#### JodaGenerate

The `jodaGenerate` task is used to generate or regenerate any Joda-Beans in the project source directory.
It can be used from the command line as `gradle jodaGenerate`.

This task has the same configuration as the `jodaValidate` task.

### Configuration

If your project is in default maven layout and you're fine with the default configuration of
this plugin, no configuration is needed at all.

If you want to customize the settings, your properties might look like this:

```
jodabeans {
    sourceDir = file('src/main/java') // this default is autodetected from your sourceSet definition
    indent = 4                        // this is the default
    verbose = 2
    prefix = "_"
    recursive = true                  // this is the default
    strict = false                    // this is the default
    config = 'guava'                  // this is the default, can also be 'jdk6'
}
```

The `strict` mode fails the validation step if files have been changed and a new generate step is needed.

### Example project

You can find a small example of the usage of both this plugin and Joda-Beans in general [here](https://github.com/andreas-schilling/joda-beans-test)