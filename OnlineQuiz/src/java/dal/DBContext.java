/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    public Connection connection;

    public DBContext() {
        try {
            //Change the username password and url to connect your own database
            String username = "sa";
            String password = "27799";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection(Connection cnn, ResultSet rs, PreparedStatement ps) throws Exception{
        if (cnn != null && !cnn.isClosed()) {
            cnn.close();
        }
        if (ps!=null && !ps.isClosed()){
            ps.close();
        }
        if (rs!=null && !rs.isClosed()){
            rs.close();
        }
    }
}
