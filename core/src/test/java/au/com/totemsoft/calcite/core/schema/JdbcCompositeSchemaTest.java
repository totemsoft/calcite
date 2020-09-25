package au.com.totemsoft.calcite.core.schema;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@ActiveProfiles({"mysql", "postgres"})
class JdbcCompositeSchemaTest {

    private final String sql = 
        "SELECT"
        + " d.deptno deptno, max(e.empid) empid, max(s.salary) salary"
        + " FROM hr.department d"
        + " JOIN hr.employee e ON d.deptno = e.deptno"
        + " JOIN pr.salary s ON e.empid = s.empid"
        + " GROUP BY d.deptno"
        + " HAVING count(*) > 0"
        ;

    @Autowired @Qualifier("mysqlDatasource")
    private DataSource mysqlDatasource;

    @Autowired @Qualifier("postgresDatasource")
    private DataSource postgresDatasource;

    @Test
    public void init() throws SQLException {
        SchemaUtils.init(sql,
            new Pair<Object, Map<String, String>>(mysqlDatasource, HrSchema.PROPERTIES),
            new Pair<Object, Map<String, String>>(postgresDatasource, PayrollSchema.PROPERTIES)
        );
    }

}
