/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.QuestionModel;
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
public class QuestionDAO {

    // add question and correct index corresponding to.
    public boolean addQuestion(QuestionModel questionModel, int[] answerindex) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "INSERT INTO dbo.questions VALUES(?,?,?,?,?,?,GETDATE())";
            ps = cnn.prepareStatement(xSQL);
            ps.setString(1, questionModel.getQuestion());
            ps.setString(2, questionModel.getAnswer1());
            ps.setString(3, questionModel.getAnswer2());
            ps.setString(4, questionModel.getAnswer3());
            ps.setString(5, questionModel.getAnswer4());
            ps.setString(6, questionModel.getUsername());
            ps.execute();
            ps.clearBatch();
            //
            ps = cnn.prepareStatement("SELECT TOP 1 * FROM dbo.questions ORDER BY questionid DESC");
            rs = ps.executeQuery();
            rs.next();
            new AnswerSheetDAO().addAnswerSheet(rs.getInt("questionid"), answerindex);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public List<QuestionModel> getQuestion(String amount, String username) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<QuestionModel> questionList = new ArrayList<>();

        try {
            cnn = db.connection;
            String xSQL = "SELECT " + amount + " FROM dbo.questions WHERE username = ?";
            ps = cnn.prepareStatement(xSQL);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                questionList.add(new QuestionModel(rs.getInt("questionid"), rs.getString("question"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4"), rs.getString("username"), rs.getString("datecreated")));
            }
            return questionList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public QuestionModel getQuestion(int questionid, String username) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "SELECT *FROM dbo.questions WHERE questionid=? AND username=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setInt(1, questionid);
            ps.setString(2, username);
            rs = ps.executeQuery();
            rs.next();
            return (new QuestionModel(rs.getInt("questionid"), rs.getString("question"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4"), rs.getString("username"), rs.getString("datecreated")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public boolean updateQuestion(QuestionModel qModel, int[] answerindex) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AnswerSheetDAO asDAO = new AnswerSheetDAO();
        try {
            cnn = db.connection;
            String xSQL = "UPDATE dbo.questions SET question=?,answer1=?, answer2=?, answer3=?, answer4=? WHERE questionid=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setString(1, qModel.getQuestion());
            ps.setString(2, qModel.getAnswer1());
            ps.setString(3, qModel.getAnswer2());
            ps.setString(4, qModel.getAnswer3());
            ps.setString(5, qModel.getAnswer4());
            ps.setInt(6, qModel.getQuestionid());
            ps.execute();
            ps.clearBatch();
            //
            asDAO.deleteAnswerSheet(qModel.getQuestionid());
            asDAO.addAnswerSheet(qModel.getQuestionid(), answerindex);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public boolean deleteQuestion(int questionid) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AnswerSheetDAO asDAO = new AnswerSheetDAO();
        try {
            cnn = db.connection;
            String xSQL = "DELETE FROM dbo.questions WHERE questionid=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setInt(1, questionid);
            ps.execute();
            asDAO.deleteAnswerSheet(questionid);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

//
//    public static void main(String[] args) {
//        try {
//            QuestionDAO qd = new QuestionDAO();
//            int[] inde = {1, 2};
//            QuestionModel qm = new QuestionModel(0, "Who im i like?", "thao", "thw", "hang", "thuy", "baileyy", "");
//            System.out.println("add question: " + qd.addQuestion(qm, inde));
//            System.out.println(qd.getQuestion("*", "baileyy").get(0).getQuestion());
//            System.out.println(qd.getQuestion(9, "baileyy").getQuestion());
//        } catch (Exception e) {
//        }
//    }
}
