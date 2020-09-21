package au.com.totemsoft.calcite.sql.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;

public class SchemaUtils {

    public static void init(String schemaName, Object targetSchema) throws SQLException, ClassNotFoundException {
        Class.forName(org.apache.calcite.jdbc.Driver.class.getName());
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info);) {
            CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            SchemaPlus rootSchema = calciteConnection.getRootSchema();
            Schema schema = new ReflectiveSchema(targetSchema);
            rootSchema.add(schemaName, schema);
            try (Statement statement = calciteConnection.createStatement();) {
                final String sql = 
                    "SELECT"
                    + " d.deptno deptno, max(e.empid) empid"
                    + " FROM hr.employee e"
                    + " JOIN hr.department d ON e.deptno = d.deptno"
                    + " GROUP BY d.deptno"
                    + " HAVING count(*) > 0"
                    ;
                try (ResultSet rs = statement.executeQuery(sql);) {
                    while (rs.next()) {
                        System.out.println(rs.getString("deptno") + ", " + rs.getString("empid"));
                    }
                } catch (SQLException e) {
                    System.err.print(e);
                    throw e;
                }
            }
        }
    }

}
