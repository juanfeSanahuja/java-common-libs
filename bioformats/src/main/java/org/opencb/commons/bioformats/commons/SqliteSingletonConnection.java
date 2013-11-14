package org.opencb.commons.bioformats.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: aaleman
 * Date: 10/24/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SqliteSingletonConnection {

    private static Connection con = null;
    private static String dbName;

    public SqliteSingletonConnection(String dbName) {
        this.dbName = dbName;
    }

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:" + dbName);
                con.setAutoCommit(false);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

            }
        }
        return con;
    }
}