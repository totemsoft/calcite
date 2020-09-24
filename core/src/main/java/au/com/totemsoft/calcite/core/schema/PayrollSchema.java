package au.com.totemsoft.calcite.core.schema;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import au.com.totemsoft.calcite.core.schema.salary.Salary;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
public class PayrollSchema {

    // PostgreSQL
    public static final Map<String, String> PROPERTIES = Stream.of(
        new AbstractMap.SimpleEntry<>("name",    "pr"),
        new AbstractMap.SimpleEntry<>("catalog", ""),
        new AbstractMap.SimpleEntry<>("schema",  "")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public final Salary[] salary;

}
