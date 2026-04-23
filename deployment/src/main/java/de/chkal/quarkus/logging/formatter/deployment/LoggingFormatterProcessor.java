package de.chkal.quarkus.logging.formatter.deployment;

import de.chkal.quarkus.logging.formatter.runtime.LoggingFormatterRecorder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.LogConsoleFormatBuildItem;

class LoggingFormatterProcessor {

  @BuildStep
  @Record(ExecutionTime.RUNTIME_INIT)
  public LogConsoleFormatBuildItem setUpConsoleFormatter( LoggingFormatterRecorder recorder ) {
    return new LogConsoleFormatBuildItem( recorder.initializeConsoleFormatterLogging() );
  }

  // TODO: Add other formatters as well (file, syslog, etc.)

}
