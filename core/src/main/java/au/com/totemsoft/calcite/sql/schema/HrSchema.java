package au.com.totemsoft.calcite.sql.schema;

import au.com.totemsoft.calcite.sql.schema.hr.Department;
import au.com.totemsoft.calcite.sql.schema.hr.Employee;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
public class HrSchema {

    public final Employee[] employee;

    public final Department[] department;

}
