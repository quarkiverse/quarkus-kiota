# Quarkus - Kiota Extension

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
[![Build](<https://img.shields.io/github/actions/workflow/status/quarkiverse/quarkus-kiota/build.yml?branch=main&logo=GitHub&style=flat-square>)](https://github.com/quarkiverse/quarkus-kiota/actions?query=workflow%3ABuild)
[![Maven Central](https://img.shields.io/maven-central/v/io.quarkiverse.kiota/quarkus-kiota.svg?label=Maven%20Central&style=flat-square)](https://search.maven.org/artifact/io.quarkiverse.kiota/quarkus-kiota)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)


Quarkus' extension for generation of client SDKs based on OpenAPI specification files.

This extension is based on the [Kiota](https://github.com/microsoft/kiota).

**Want to contribute? Great!** We try to make it easy, and all contributions, even the smaller ones, are more than welcome. This includes bug reports, fixes, documentation, examples... But first, read [this page](CONTRIBUTING.md).

## Getting Started

If you have a supersonic, subatomic [Quarkus](https://quarkus.io/) project you can use this extension to generate code with Kiota:

```xml
<dependency>
  <groupId>io.quarkiverse.kiota</groupId>
  <artifactId>quarkus-kiota</artifactId>
  <version>VERSION</version>
</dependency>
```

remember to enable the code generation in the `quarkus-maven-plugin` configuration, if not already present, add `<goal>generate-code</goal>`:

```xml
<plugin>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-maven-plugin</artifactId>
  <executions>
    <execution>
      <goals>
        <goal>build</goal>
        <goal>generate-code</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

now you can drop any Open API specification in the `src/<scope>/openapi` folder and configure the extension as usual with Quarkus configuration.
We highly encourage you to pin `quarkus.kiota.version` to a specific version instead of relying on the "latest detection" built-in mechanism for production code.

| config | description |
|---|---|
| quarkus.kiota.os | Override the detected Operating System |
| quarkus.kiota.arch | Override the detected Architecture |
| quarkus.kiota.provided | Specify the path to an available Kiota CLI to be used |
| quarkus.kiota.release.url | Define an alternative URL to be used to download the Kiota CLI |
| quarkus.kiota.version | Define a specific Kiota version to be used |
| quarkus.kiota.timeout | Global timeout over the execution of the Kiota CLI |

To fine tune the generation you can define additional properties after the Open API spec file name:

| config | description |
|---|---|
| quarkus.kiota.< filename >.class-name | Specify the name for the generated client class |
| quarkus.kiota.< filename >.package-name | Specify the name of the package for the generated sources |
| quarkus.kiota.< filename >.include-path | Glob expression to identify the endpoint to be included in the generation |
| quarkus.kiota.< filename >.exclude-path | Glob expression to identify the endpoint to be excluded in the generation |
| quarkus.kiota.< filename >.serializer | Overwrite the serializers for the generation |
| quarkus.kiota.< filename >.deserializer | Overwrite the deserializers for the generation |

Using the extension, by default, the Json serializer and deserializer will be based on Jackson instead of the official one based on Gson.


> If you want to improve the docs, please feel free to contribute editing the docs in [Docs](https://github.com/quarkiverse/quarkus-kiota/tree/main/docs/modules/ROOT). But first, read [this page](CONTRIBUTING.md).

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/andreaTP"><img src="https://avatars.githubusercontent.com/u/5792097?v=4?s=100" width="100px;" alt="Andrea Peruffo"/><br /><sub><b>Andrea Peruffo</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-kiota/commits?author=andreaTP" title="Code">💻</a> <a href="#maintenance-andreaTP" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://gastaldi.wordpress.com"><img src="https://avatars.githubusercontent.com/u/54133?v=4?s=100" width="100px;" alt="George Gastaldi"/><br /><sub><b>George Gastaldi</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-kiota/commits?author=gastaldi" title="Code">💻</a> <a href="#maintenance-gastaldi" title="Maintenance">🚧</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
