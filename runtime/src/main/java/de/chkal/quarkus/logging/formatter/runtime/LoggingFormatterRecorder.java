package de.chkal.quarkus.logging.formatter.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Formatter;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class LoggingFormatterRecorder {

  private final RuntimeValue<LoggingFormatterConfig> runtimeConfig;

  public LoggingFormatterRecorder( final RuntimeValue<LoggingFormatterConfig> runtimeConfig ) {
    this.runtimeConfig = runtimeConfig;
  }

  public RuntimeValue<Optional<Formatter>> initializeConsoleFormatterLogging() {
    return getFormatter( runtimeConfig.getValue().consoleFormatter().className().orElse( null ) );
  }

  private RuntimeValue<Optional<Formatter>> getFormatter( String className ) {

    if( className == null || className.isBlank() ) {
      return new RuntimeValue<>( Optional.empty() );
    }

    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      Class<?> clazz = classLoader.loadClass( className );
      Formatter formatter = (Formatter) clazz.getDeclaredConstructor().newInstance();
      return new RuntimeValue<>( Optional.of( formatter ) );
    }
    catch( ClassNotFoundException e ) {
      throw new IllegalArgumentException( "Formatter class not found: " + className, e );
    }
    catch( InvocationTargetException e ) {
      throw new IllegalStateException( "Formatter class could not be instantiated", e.getCause() );
    }
    catch( InstantiationException | IllegalAccessException e ) {
      throw new IllegalStateException( "Formatter class could not be instantiated", e );
    }
    catch( NoSuchMethodException e ) {
      throw new IllegalStateException( "Formatter class does not have a public no-arg constructor", e );
    }

  }

}
