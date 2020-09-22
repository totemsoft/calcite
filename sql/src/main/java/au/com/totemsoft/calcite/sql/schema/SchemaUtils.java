package au.com.totemsoft.calcite.sql.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchemaUtils {

    public static void init(Object target) throws SQLException, ClassNotFoundException {
        Class.forName(org.apache.calcite.jdbc.Driver.class.getName());
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info);) {
            final CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            final SchemaPlus rootSchema = calciteConnection.getRootSchema();
            final Schema schema;
            if (target instanceof DataSource) {
                schema = JdbcSchema.create(rootSchema, "hr", (DataSource) target, null, "hrdb");
            } else {
                schema = new ReflectiveSchema(target);
            }
            rootSchema.add("hr", schema);
            execute(calciteConnection, schema);
        }
    }

    public static void execute(CalciteConnection calciteConnection, Schema schema) throws SQLException {
        final String sql = 
            "SELECT"
            + " d.deptno deptno, max(e.empid) empid"
            + " FROM hr.employee e"
            + " JOIN hr.department d ON e.deptno = d.deptno"
            + " GROUP BY d.deptno"
            + " HAVING count(*) > 0"
            ;
        try (Statement statement = calciteConnection.createStatement();) {
            try (ResultSet rs = statement.executeQuery(sql);) {
                while (rs.next()) {
                    log.info("{}, {}", rs.getString("deptno"), rs.getString("empid"));
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        }
    }

}
