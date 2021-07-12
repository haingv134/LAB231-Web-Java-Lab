/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.RowMapper;

/**
 *
 * @author haiva
 */
public class AbstractDAO<T> {

    public List<T> query(String sql, RowMapper<T> rowmapper, Object... parameters) {
        List<T> list = new ArrayList<>();
        DBContext dbContext = new DBContext();
        Connection cnn = dbContext.connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cnn.prepareStatement(sql);
            // set parameter
            setParameters(ps, parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rowmapper.mapRow(rs));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                dbContext.closeConnection(cnn, rs, ps);
            } catch (Exception er) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, er.getMessage(), er);
            }
        }
        return null;
    }

    // return the id in IDENTITY(1,1) (auto_increase) 
    public int insert(String sql, Object... parameters) {
        DBContext dbContext = new DBContext();
        Connection cnn = dbContext.connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn.setAutoCommit(false);
            ps = cnn.prepareStatement(sql);
            setParameters(ps, parameters);
            ps.execute();
            int id = -1;
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            cnn.commit();
            return id;
        } catch (SQLException ex) {
            try {
                if (cnn != null) {
                    cnn.rollback();
                }
            } catch (SQLException erb) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, "ROLL BACK FAILED", erb);
            }
        } finally {
            try {
                dbContext.closeConnection(cnn, rs, ps);
            } catch (Exception er) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, "SQLEXCEPTION IN CLOSING ELEMENTS", er);
            }
        }
        return -1;
    }

    public void update(String sql, Object... parameters) {
        DBContext dbContext = new DBContext();
        Connection cnn = dbContext.connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cnn.prepareStatement(sql);
            setParameters(ps, parameters);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                dbContext.closeConnection(cnn, rs, ps);
            } catch (Exception er) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, er.getMessage(), er);
            }
        }
    }

    private void setParameters(PreparedStatement ps, Object... parameters) {
        try {
            for (int index = 0; index < parameters.length; index++) {
                Object parameter = parameters[index];
                if (parameter instanceof Long) {
                    ps.setLong(index+1, (Long) parameter);
                } else if (parameter instanceof Integer) {
                    ps.setInt(index+1, (Integer) parameter);
                } else if (parameter instanceof String) {
                    ps.setString(index+1, (String) parameter);
                } else if (parameter instanceof Date) {
                    ps.setDate(index+1, (Date) parameter);
                } else if (parameter instanceof Double) {
                    ps.setDouble(index+1, (Double) parameter);
                } else if (parameter instanceof Boolean) {
                    ps.setBoolean(index+1, (Boolean) parameter);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
