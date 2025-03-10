package ${package}.utils;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class DateTimeProvider {

    public OffsetDateTime now() {
        return OffsetDateTime.now();
    }
}
