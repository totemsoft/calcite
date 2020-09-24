package au.com.totemsoft.calcite.core.schema;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.totemsoft.calcite.core.Application;
import au.com.totemsoft.calcite.core.Config;
import javafx.util.Pair;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {Application.class, Config.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("h2")
class JdbcH2SchemaTest {

    // H2
    private static final Map<String, String> PROPERTIES = Stream.of(
        new AbstractMap.SimpleEntry<>("name",    "hr"),
        new AbstractMap.SimpleEntry<>("catalog", ""),
        new AbstractMap.SimpleEntry<>("schema",  "")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private final String sql = 
        "SELECT"
        + " d.deptno deptno, max(e.empid) empid"
        + " FROM hr.employee e"
        + " JOIN hr.department d ON e.deptno = d.deptno"
        + " GROUP BY d.deptno"
        + " HAVING count(*) > 0"
        ;

    @Autowired
    private DataSource datasource;

    @Test // FIXME: Object 'employee' not found within 'hr'
    public void init() throws SQLException, ClassNotFoundException {
        SchemaUtils.init(sql,
            new Pair<Object, Map<String, String>>(datasource, PROPERTIES)
        );
    }

}
