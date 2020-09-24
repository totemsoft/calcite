package au.com.totemsoft.calcite.sql.schema;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.totemsoft.calcite.sql.Application;
import au.com.totemsoft.calcite.sql.schema.hr.Department;
import au.com.totemsoft.calcite.sql.schema.hr.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class ReflectiveSchemaTest {

    private final String sql = 
        "SELECT"
        + " d.deptno deptno, max(e.empid) empid"
        + " FROM hr.employee e"
        + " JOIN hr.department d ON e.deptno = d.deptno"
        + " GROUP BY d.deptno"
        + " HAVING count(*) > 0"
        ;

    @Test
    public void init() throws SQLException, ClassNotFoundException {
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
        SchemaUtils.init(sql, target);
    }

}
