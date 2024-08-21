package ua.nure.englishcards.configuration;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up metrics in the application.
 * Provides beans for aspects that automatically record timing and counting metrics.
 */
@Configuration
public class MetricConfiguration {

  /**
   * Creates a {@link TimedAspect} bean that records timing metrics
   * for methods annotated with {@code @Timed}.
   *
   * @param meterRegistry the registry to which metrics will be recorded
   * @return a new {@link TimedAspect} instance
   */
  @Bean
  public TimedAspect timedAspect(MeterRegistry meterRegistry) {
    return new TimedAspect(meterRegistry);
  }

  /**
   * Creates a {@link CountedAspect} bean that records counting metrics
   * for methods annotated with {@code @Counted}.
   *
   * @param meterRegistry the registry to which metrics will be recorded
   * @return a new {@link CountedAspect} instance
   */
  @Bean
  public CountedAspect countedAspect(MeterRegistry meterRegistry) {
    return new CountedAspect(meterRegistry);
  }
}
