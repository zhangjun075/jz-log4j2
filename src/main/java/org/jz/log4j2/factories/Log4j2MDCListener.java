package org.jz.log4j2.factories;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.bind.PropertySourceUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.PropertySources;
import org.springframework.util.ClassUtils;

import java.util.Map;

/**
 * Lookup properties of Spring
 *
 * @author 张均
 * @since 2016/11/28
 */
public class Log4j2MDCListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    private static final String LOGGING_PATH_KEY = LogFile.PATH_PROPERTY;
    private static final String DEFAULT_LOGGING_PATH = "logs";

    private static final String FILE_NAME_KEY = "spring.application.name";
    private static final String DEFAULT_FILE_NAME = "spring";

    private static final String ROOT_LOGGER_LEVEL_KEY = "logging.level.root";
    private static final String DEFAULT_ROOT_LOGGER_LEVEL = "info";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        String className = "org.apache.logging.log4j.ThreadContext";
        if (ClassUtils.isPresent(className, event.getSpringApplication().getClassLoader())) {
            PropertySources propertySources = event.getEnvironment().getPropertySources();
            Map<String, Object> properties = PropertySourceUtils.getSubProperties(propertySources, null);
            properties.forEach((k, v) -> {
                if (v != null) {
                    ThreadContext.put(k, v.toString());
                }
            });
            if (!ThreadContext.containsKey(LOGGING_PATH_KEY)) {
                ThreadContext.put(LOGGING_PATH_KEY, DEFAULT_LOGGING_PATH);
            }
            if (!ThreadContext.containsKey(FILE_NAME_KEY)) {
                ThreadContext.put(FILE_NAME_KEY, DEFAULT_FILE_NAME);
            }
            if (!ThreadContext.containsKey(ROOT_LOGGER_LEVEL_KEY)) {
                ThreadContext.put(ROOT_LOGGER_LEVEL_KEY, DEFAULT_ROOT_LOGGER_LEVEL);
            }
        }
    }

    @Override
    public int getOrder() {
        return LoggingApplicationListener.DEFAULT_ORDER - 1;
    }
}
