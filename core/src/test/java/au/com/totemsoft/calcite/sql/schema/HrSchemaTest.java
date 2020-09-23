package au.com.totemsoft.calcite.sql.schema;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.totemsoft.calcite.sql.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class HrSchemaTest {

    @Autowired
    private DataSource datasource;

    @Test
    public void init_ReflectiveSchema() throws SQLException, ClassNotFoundException {
        Employee[] employee = new Employee[] {
            new Employee("10000001", "IT"),
            new Employee("10000001", "HR"),
            new Employee("10000002", "IT"),
        };
        Department[] department = new Department[] {
            new Department("IT"),
            new Department("HR"),
        };
        HrSchema target = new HrSchema(employee, department);
        SchemaUtils.init(target);
    }

    //@Test // FIXME: Object 'employee' not found within 'hr'
    public void init_JdbcSchemaH2() throws SQLException, ClassNotFoundException {
        SchemaUtils.init(datasource);
    }

}
