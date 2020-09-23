package au.com.totemsoft.calcite.sql.rs;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/health"
    , consumes = {MediaType.APPLICATION_JSON_VALUE}
    , produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class HealthController implements HealthIndicator {

    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = "*")
    @Override
    public Health health() {
        return Health.up().build();
    }

}