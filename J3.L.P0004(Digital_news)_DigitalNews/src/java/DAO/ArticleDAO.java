package DAO;

import Model.ArticleModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dal.DBContext;
import java.text.SimpleDateFormat;

/**
 *
 * @author haiva
 */
public class ArticleDAO {

    public ArticleModel getArticle(int id) throws Exception {
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "SELECT *FROM dbo.Article WHERE id=?";
            ps = cnn.prepareStatement(xSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            return (new ArticleModel(rs.getInt("id"), rs.getString("title"), rs.getString("image"), rs.getString("content"), rs.getDate("date"), rs.getString("author")));
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public ArrayList<ArticleModel> getTopArtical(int amount) throws Exception {
        ArrayList<ArticleModel> data = new ArrayList<>();
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            String xSQL = "SELECT TOP "+ amount+" *FROM dbo.Article ORDER BY date DESC";
            ps = cnn.prepareStatement(xSQL);
            //ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new ArticleModel(rs.getInt("id"), rs.getString("title"), rs.getString("image"), rs.getString("content"), rs.getDate("date"), rs.getString("author")));
            }
            return data;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }

    public ArrayList<ArticleModel> searchArtical(String keyword) throws Exception {
        ArrayList<ArticleModel> data = new ArrayList<>();
        DBContext db = new DBContext();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = db.connection;
            System.out.println("ok");
            String xSQL = "SELECT *FROM dbo.Article  WHERE title LIKE '%" + keyword + "%'";
            ps = cnn.prepareStatement(xSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new ArticleModel(rs.getInt("id"), rs.getString("title"), rs.getString("image"), rs.getString("content"), rs.getDate("date"), rs.getString("author")));
            }
            return data;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(cnn, rs, ps);
        }
    }
//    public static void main(String[] args) {
//        ArticleDAO a = new ArticleDAO();
//        try {
//            SimpleDateFormat sdfm = new SimpleDateFormat("dd MMM yyyy - HH:mmaa");
//            String d = sdfm.format(a.getArticle(1).getDate());
//            System.out.println(d);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
//    }
}
