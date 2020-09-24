package au.com.totemsoft.calcite.core.schema;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.totemsoft.calcite.core.schema.hr.Department;
import au.com.totemsoft.calcite.core.schema.hr.Employee;
import javafx.util.Pair;

@RunWith(SpringRunner.class)
@SpringBootTest(
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

    private HrSchema target;

    @BeforeEach
    public void before() {
        Employee[] employee = new Employee[] {
            new Employee("10000001", "IT"),
            new Employee("10000001", "HR"),
            new Employee("10000002", "IT"),
        };
        Department[] department = new Department[] {
            new Department("IT"),
            new Department("HR"),
        };
        this.target = new HrSchema(employee, department);
    }

    @Test
    public void init() throws SQLException, ClassNotFoundException {
        SchemaUtils.init(sql,
            new Pair<Object, Map<String, String>>(target, HrSchema.PROPERTIES)
        );
    }

}
