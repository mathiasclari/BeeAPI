package net.acticraft.plugin.api.beeapi.mysql;

import java.sql.*;
import java.text.SimpleDateFormat;

public class BeeMySQL {

    public Connection connection;

    String host;
    String database;
    String username;
    String password;

    public BeeMySQL(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean CanConnect() {
        if(GetConnection() != null) {
            return true;
        }
        return false;
    }

    public static String mysql_real_escape_string(String str) {
        if (str == null) {
            return "";
        }

        if (str.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]","").length() < 1) {
            return str;
        }

        String clean_string = str;
        clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\");
        clean_string = clean_string.replaceAll("\\n","\\\\n");
        clean_string = clean_string.replaceAll("\\r", "\\\\r");
        clean_string = clean_string.replaceAll("\\t", "\\\\t");
        clean_string = clean_string.replaceAll("\\00", "\\\\0");
        clean_string = clean_string.replaceAll("'", "\\\\'");
        clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
        return clean_string;
    }

    public static String mysql_real_date_string(Date date) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public Connection GetConnection() {
        if (connection != null) {
            try {
                if(connection.isClosed() || !connection.isValid(28800)) {
                    //Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false&serverTimezone=UTC&tcpKeepAlive=true&autoReconnect=true", username, password);
                }
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false&serverTimezone=UTC&tcpKeepAlive=true&autoReconnect=true", username, password);
            return connection;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Update(Connection connection, String command) {
        if (command == null) {
            return;
        }
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(command);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
