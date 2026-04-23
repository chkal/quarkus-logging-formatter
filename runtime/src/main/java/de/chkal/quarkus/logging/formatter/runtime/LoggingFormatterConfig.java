package de.chkal.quarkus.logging.formatter.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigDocSection;
import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "quarkus.log")
public interface LoggingFormatterConfig {

  /**
   * Console logging.
   */
  @ConfigDocSection
  @WithName("console.formatter")
  JsonConfig consoleFormatter();

  @ConfigGroup
  interface JsonConfig {

    /**
     * Name of a class implementing @{@link java.util.logging.Formatter}.
     */
    Optional<String> className();

  }

}



