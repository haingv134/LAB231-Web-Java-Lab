/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.UserModel;
import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author haiva
 */
public class UserDAO {

    public boolean addUser(UserModel userModel) throws Exception {

        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "INSERT INTO dbo.users VALUES(?,?,?,?)";
            ps = cnn.prepareStatement(xSQL);
            ps.setString(1, userModel.getUsername());
            ps.setString(2, userModel.getPassword());
            ps.setString(3, userModel.getUsertype());
            ps.setString(4, userModel.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public UserModel getUser(String username, String password) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "SELECT *FROM dbo.users WHERE username=? AND password=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new UserModel(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("usertype"), rs.getString("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
        return null;
    }

//    public static void main(String[] args) {
//        UserDAO ud = new UserDAO();
//        try {
//            //System.out.println(ud.addUser(new UserModel(0, "baileyy", "1234", "student", "haivann200@gmail.com")));
//            UserModel umodel = ud.getUser("baileyy", "1234");
//            System.out.println(umodel.getEmail());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
