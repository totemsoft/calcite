package au.com.totemsoft.calcite.core.schema;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import au.com.totemsoft.calcite.core.schema.hr.Department;
import au.com.totemsoft.calcite.core.schema.hr.Employee;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
public class HrSchema {

    // MySQL
    public static final Map<String, String> PROPERTIES = Stream.of(
        new AbstractMap.SimpleEntry<>("name",    "hr"),
        new AbstractMap.SimpleEntry<>("catalog", ""),
        new AbstractMap.SimpleEntry<>("schema",  "hrdb")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public final Employee[] employee;

    public final Department[] department;

}
