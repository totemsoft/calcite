package au.com.totemsoft.calcite.core.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.lang3.StringUtils;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchemaUtils {

    @SafeVarargs
    public static void init(String sql, Pair<Object, Map<String, String>>... targets) throws SQLException, ClassNotFoundException {
        Class.forName(org.apache.calcite.jdbc.Driver.class.getName());
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info);) {
            final CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            final SchemaPlus rootSchema = calciteConnection.getRootSchema();
            for (Pair<Object, Map<String, String>> target : targets) {
                final Object targetSchema = target.getKey();
                final Map<String, String> targetProps = target.getValue();
                final String name = targetProps.get("name");
                final Schema schema;
                if (targetSchema instanceof DataSource) {
                    final DataSource dataSource = (DataSource) targetSchema;
                    final String catalogName = StringUtils.trimToNull(targetProps.get("catalog"));
                    final String schemaName = StringUtils.trimToNull(targetProps.get("schema"));
                    log.info("{}: {}", name, schemaName);
                    schema = JdbcSchema.create(rootSchema, name, dataSource, catalogName, schemaName);
                } else {
                    schema = new ReflectiveSchema(targetSchema);
                }
                rootSchema.add(name, schema);
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
