package au.com.totemsoft.calcite.sql.rs;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://stormpath.com/blog/jax-rs-vs-spring-rest-endpoints
 * https://stackoverflow.com/questions/20007164/spring-mvc-rest-is-not-jax-rs-compliant-does-it-matter
 * https://stackoverflow.com/questions/42944777/difference-between-jax-rs-and-spring-rest
 * @author vchibaev (Valeri CHIBAEV)
 */
@RestController
@RequestMapping(
    value = "/health"
    , consumes = {"application/json"} // MediaType.APPLICATION_JSON
    , produces = {"application/json"} // MediaType.APPLICATION_JSON
)
public class HealthController implements HealthIndicator {

    //private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

    /* (non-Javadoc)
     * @see org.springframework.boot.actuate.health.HealthIndicator#health()
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = "*"/*MediaType.MEDIA_TYPE_WILDCARD*/)
    @Override
    public Health health() {
        return Health.up().build();
    }

}