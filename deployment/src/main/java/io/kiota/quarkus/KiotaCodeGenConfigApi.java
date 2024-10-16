package io.kiota.quarkus;

import static io.kiota.quarkus.KiotaCodeGenConfig.DEFAULT_CLIENT_NAME;
import static io.kiota.quarkus.KiotaCodeGenConfig.DEFAULT_CLIENT_PACKAGE;
import static io.kiota.quarkus.KiotaCodeGenConfig.DEFAULT_RELEASE_URL;
import static io.kiota.quarkus.KiotaCodeGenConfig.DEFAULT_TIMEOUT;

import java.util.List;
import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "quarkus.kiota")
@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
public interface KiotaCodeGenConfigApi {
    /**
     * Overrides the detected Operating System
     */
    @WithName("os")
    Optional<String> os();

    /**
     * Overrides the detected Architecture
     */
    @WithName("arch")
    Optional<String> arch();

    /**
     * The path to a kiota executable location to be used.
     * When set, the kiota version is not going to be checked/used.
     */
    @WithName("provided")
    Optional<String> provided();

    /**
     * The path to a kiota executable location to be used
     */
    @WithName("release.url")
    @WithDefault(DEFAULT_RELEASE_URL)
    Optional<String> releaseUrl();

    /**
     * The kiota version to be used.
     * If not provided we are going to try to resolve "latest" from the GitHub API.
     * Please, set this property in any production grade project.
     */
    @WithName("version")
    Optional<String> version();

    /**
     * The timeout to be used when running the kiota CLI.
     */
    @WithName("timeout")
    @WithDefault("" + DEFAULT_TIMEOUT)
    Optional<String> timeout();

    /**
     * Configuration resolved based on the OpenAPI description file name
     */
    @WithName("spec-name")
    Optional<SpecConfig> specName();

    @ConfigGroup
    public static interface SpecConfig {

        /**
         * The generated API client class name.
         */
        @WithName("class-name")
        @WithDefault(DEFAULT_CLIENT_NAME)
        Optional<String> className();

        /**
         * The generated API client package name.
         */
        @WithName("package-name")
        @WithDefault(DEFAULT_CLIENT_PACKAGE)
        Optional<String> packageName();

        /**
         * The glob expression to be used to identify the endpoints to be included in the generation.
         */
        @WithName("include-path")
        Optional<String> includePath();

        /**
         * The glob expression to be used to identify the endpoints to be excluded from the generation.
         */
        @WithName("exclude-path")
        Optional<String> excludePath();

        /**
         * ADVANCED:
         * The serializers to be used in the generated code.
         */
        @WithName("serializer")
        Optional<List<String>> serializer();

        /**
         * ADVANCED:
         * The deserializers to be used in the generated code.
         */
        @WithName("deserializer")
        Optional<List<String>> deserializer();
    }
}
