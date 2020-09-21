package au.com.totemsoft.calcite.sql.schema;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@RunWith(SpringRunner.class)
class HrSchemaTest {

    @BeforeEach
    public void before() {
        
    }

    @Test
    void init() throws SQLException, ClassNotFoundException {
        Employee[] employee = new Employee[] {
            new Employee("10000001", "IT"),
            new Employee("10000001", "HR"),
            new Employee("10000002", "IT"),
        };
        Department[] department = new Department[] {
            new Department("IT"),
            new Department("HR"),
        };
        SchemaUtils.init("hr", new HrSchema(employee, department));
    }

}
