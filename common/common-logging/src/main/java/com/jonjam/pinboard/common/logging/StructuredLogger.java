package com.jonjam.pinboard.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonjam.pinboard.common.objectmodel.ObjectMapperBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructuredLogger {

  private static final ObjectMapper MAPPER = ObjectMapperBuilder.build();
  private static final StructuredLogger LOG = StructuredLogger.getLogger(StructuredLogger.class);

  private final Logger logger;

  private StructuredLogger(final Logger logger) {
    this.logger = logger;
  }

  public static StructuredLogger getLogger(final Class clazz) {
    return new StructuredLogger(LogManager.getLogger(clazz));
  }

  public LogEntryBuilder debug() {
    return new LogEntryBuilder(logger, Level.DEBUG);
  }

  public LogEntryBuilder info() {
    return new LogEntryBuilder(logger, Level.INFO);
  }

  public LogEntryBuilder warn() {
    return new LogEntryBuilder(logger, Level.WARN);
  }

  public LogEntryBuilder error() {
    return new LogEntryBuilder(logger, Level.ERROR);
  }

  public static class LogEntryBuilder {
    private final Logger logger;
    private final Level logLevel;
    private final Map<String, Object> data;

    LogEntryBuilder(
        final Logger logger,
        final Level logLevel) {
      this.logger = logger;
      this.logLevel = logLevel;
      this.data = new LinkedHashMap<>();
    }

    public LogEntryBuilder withData(final String key, final Object value) {

      data.put(key, value);

      return this;
    }

    public LogEntryBuilder withData(final Map<String, ?> map) {
      map.forEach(this::withData);

      return this;
    }

    public void write(final String message) {
      // Using lambdas for lazy logging. See: https://logging.apache.org/log4j/2.x/manual/api.html#Java_8_lambda_support_for_lazy_logging
      logger.log(logLevel, () -> buildMessageWithData(message));
    }

    public void write(final Throwable error) {
      // Using lambdas for lazy logging. See: https://logging.apache.org/log4j/2.x/manual/api.html#Java_8_lambda_support_for_lazy_logging
      logger.log(logLevel, () -> buildMessageWithData(null), error);
    }

    public void write(final String message, final Throwable error) {
      // Using lambdas for lazy logging. See: https://logging.apache.org/log4j/2.x/manual/api.html#Java_8_lambda_support_for_lazy_logging
      logger.log(logLevel, () -> buildMessageWithData(message), error);
    }

    String buildMessageWithData(String message) {

      if (StringUtils.isEmpty(message)) {
        message = StringUtils.EMPTY;
      }

      try {
        final StringBuilder output = new StringBuilder(message);

        if (!this.data.isEmpty()) {
          output.append(" :: ")
              .append(MAPPER.writeValueAsString(this.data));
        }

        return output.toString();
      } catch (final JsonProcessingException exception) {
        LOG.error()
            .withData("data", this.data.toString())
            .write("Unable to serialise additional log attributes.");

        return message;
      }
    }
  }
}
