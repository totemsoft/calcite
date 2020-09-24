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

    public static void init(String sql, Object... targets) throws SQLException, ClassNotFoundException {
        Class.forName(org.apache.calcite.jdbc.Driver.class.getName());
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info);) {
            final CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            final SchemaPlus rootSchema = calciteConnection.getRootSchema();
            for (Object target : targets) {
                final Schema schema;
                if (target instanceof DataSource) {
                    final DataSource dataSource = (DataSource) target;
                    final String name = "hr";
                    final String catalogName = null;
                    final String schemaName = "hrdb";
                    schema = JdbcSchema.create(rootSchema, name, dataSource, catalogName, schemaName);
                } else {
                    schema = new ReflectiveSchema(target);
                }
                rootSchema.add("hr", schema);
            }
            execute(sql, calciteConnection);
        }
    }

    public static void execute(String sql, CalciteConnection calciteConnection) throws SQLException {
        try (Statement statement = calciteConnection.createStatement();) {
            try (ResultSet rs = statement.executeQuery(sql);) {
                while (rs.next()) {
                    final int count = rs.getMetaData().getColumnCount();
                    if (count == 2) {
                        log.info("{}, {}", rs.getObject(1), rs.getObject(2));
                    } else {
                        log.info("{}", rs.getObject(1));
                    }
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        }
    }

}
