/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.AnswerSheetModel;
import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haiva
 */
public class AnswerSheetDAO {

    public boolean addAnswerSheet(int questionid, int[] answerindex) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "INSERT INTO dbo.answersheet VALUES(?,?)";
            ps = cnn.prepareStatement(xSQL);
            for (int index : answerindex) {
                ps.setInt(1, questionid);
                ps.setInt(2, index);
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public List<AnswerSheetModel> getAnswerSheet(int questionid) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnswerSheetModel> list = new ArrayList<>();

        try {
            cnn = db.connection;
            String xSQL = "SELECT *FROM dbo.answersheet WHERE questionid=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setInt(1, questionid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AnswerSheetModel(questionid, rs.getInt("answerindex")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public boolean deleteAnswerSheet(int questionid) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            ps = cnn.prepareStatement("DELETE dbo.answersheet WHERE questionid=?");
            ps.setInt(1, questionid);
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(new AnswerSheetDAO().getAnswerSheet(9).get(0).getAnswerindex());
//        } catch (Exception e) {
//        }
//    }
}
