Joda-Beans plugin for Gradle
----------------------------

This project provides a [Gradle](https://www.gradle.org/) plugin
for [Joda-Beans](https://github.com/JodaOrg/joda-beans).
This is basically a copy of the [Joda-Beans Maven Plugin](https://github.com/JodaOrg/joda-beans-maven-plugin)


### Tasks

This plugin provides two tasks - `jodaValidate` and `jodaGenerate`.

#### JodaValidate

The `jodaValidate` task is used to check that no Joda-Beans need regeneration.
It can be used from the command line as `gradle jodaValidate`.

This task has the following optional configuration items:
- `indent` - as per the command line, the amount of indentation used,
either the word "tab", or a number, such as "2" or "4". Default is "4".
- `prefix` - as per the command line, the prefix used by fields. Default is "".
- `verbose` - as per the command line, a number from "0" (quiet) to "3" (verbose).
- `recursive` - whether the source directory should be parsed recursively. Default is "true".
- `strict` - whether the validate task should fail if beans need regenerating. Default is "false".

#### JodaGenerate

The `jodaGenerate` task is used to generate or regenerate any Joda-Beans in the project source directory.
It can be used from the command line as `gradle jodaGenerate`.

This task has the same configuration as the `jodaValidate` task.

### Usage in your gradle build

This section outlines the changes required to your gradle build script.

#### Dependency

First, declare a buildscript dependency to both the plugin and Joda Beans:

```
buildscript {
  ...
  
  dependencies {
      ...
      classpath 'org.joda:joda-beans:1.2'
      classpath 'org.kiirun:joda-beans-plugin:1.0.2'
  }
}
```

Then you can simply use the plugin with:

```
apply plugin: 'joda-beans'
```

#### Configuration

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
}
```

The `strict` mode fails the validation step if files have been changed and a new generate step is needed.
