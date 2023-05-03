package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class ORM {
    static ORM object;
    static Connection connection;
    static Statement statement;

    private ORM() throws SQLException {
        if (connection == null) {
        	String separator = System.getProperty("file.separator");
        	String propPath = System.getProperty("wtp.deploy")
        			.concat(separator).concat("shopwithmvc")
        			.concat(separator).concat("WEB-INF")
        			.concat(separator).concat("configs")
        			.concat(separator).concat("db.xml");
        	
        	// propPath for local tests
//        	String propPath = "/home/esta/eclipse-workspace/shopwithmvc/src/main/java/orm/db.xml";
        	
            Properties databaseProperties = PropertiesFactory.createProperties(propPath);
            String url = "jdbc:mysql://" + databaseProperties.getProperty("server") + "/" + databaseProperties.getProperty("db");
            try {
            	Class.forName(databaseProperties.getProperty("driver"));
                DriverManager.setLoginTimeout(Integer.parseInt(databaseProperties.getProperty("connection_timeout")));
                connection = DriverManager.getConnection(url, databaseProperties.getProperty("login"), databaseProperties.getProperty("password"));
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println("No connect to BD. " + e.getMessage());
            }
        }
    }

    public static ORM getInstance() throws SQLException {
    	if (object == null) {
            object = new ORM();
        }
        return object;
    }

    public ResultSet select(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
    
    public ResultSet select(@NonNull String table, String[] fields, @NonNull String where) throws SQLException {
    	String selectFields = "";
        if (fields == null || fields.length == 0) {
            selectFields += "*";
        } else {
            for (int i = 0; i < fields.length; i++) {
                selectFields += fields[i] + (i < fields.length - 1 ? "," : "");
            }
        }
        String sql = "SELECT " + selectFields + " FROM " + table + " " + where;
        //System.out.println(sql);
        return connection.createStatement().executeQuery(sql);
    }
    
    public int insert(@NonNull String table, @NonNull HashMap<String, String> values) throws SQLException {
        String sql = "INSERT INTO " + table, columns = "", sqlValues = "";
        if (!values.isEmpty()) {
            var i = 0;
            for (var item : values.entrySet()) {
                columns += "`" + item.getKey() + "`" + (i < values.size() - 1 ? "," : "");
                sqlValues += "'" + item.getValue() + "'" + (i < values.size() - 1 ? "," : "");
                i++;
            }
            sql += "(" + columns + ") VALUES(" + sqlValues + ")";

        }
        return statement.executeUpdate(sql);
    }

    public int update(@NonNull String table, @NonNull HashMap<String,String> values, @NonNull String where) throws SQLException {
        String sql = "UPDATE " + table + " SET ";
        if (!values.isEmpty()) {
            int i = 1;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                sql = sql.concat(entry.getKey()).concat(" = ")
                        .concat(entry.getValue())
                        .concat(i < values.size() ? "," : "");
                i++;
            }
            sql = sql.concat(" ").concat(where);
        } else {
            return 0;
        }
        return statement.executeUpdate(sql);
    }

}
