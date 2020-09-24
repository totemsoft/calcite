package au.com.totemsoft.calcite.sql.schema;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.totemsoft.calcite.sql.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
//@ActiveProfiles("test")
class JdbcPostgresSchemaTest {

    private final String sql = 
        "SELECT"
        + " s.empid empid, s.salary salary"
        + " FROM sal.salary s"
        ;

    @Autowired
    private DataSource datasource;

    @Test
    public void init() throws SQLException, ClassNotFoundException {
        SchemaUtils.init(sql, datasource);
    }

}
