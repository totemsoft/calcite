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
@ActiveProfiles("mysql")
class JdbcMysqlSchemaTest {

    private final String sql = 
        "SELECT"
        + " d.deptno deptno, max(e.empid) empid"
        + " FROM hr.employee e"
        + " JOIN hr.department d ON e.deptno = d.deptno"
        + " GROUP BY d.deptno"
        + " HAVING count(*) > 0"
        ;

    @Autowired @Qualifier("mysqlDatasource")
    private DataSource datasource;

    @Test
    public void init() throws SQLException, ClassNotFoundException {
        SchemaUtils.init(sql,
            new Pair<Object, Map<String, String>>(datasource, HrSchema.PROPERTIES)
        );
    }

}
