/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestModel;

/**
 *
 * @author haiva
 */
public class RequestMapper implements RowMapper<RequestModel> {

    @Override
    public RequestModel mapRow(ResultSet rs) {
        RequestModel model = new RequestModel();
        try {
            model.setRid(rs.getInt("rid"));
            model.setDid(rs.getInt("did"));
            model.setSid(rs.getInt("sid"));
            model.setUsername(rs.getString("username"));
            model.setDateCreated(rs.getDate("dateCreated"));
            model.setTitle(rs.getString("title"));
            model.setContent(rs.getString("content"));
            model.setCloseDate(rs.getDate("closeDate"));
            model.setState(rs.getBoolean("state"));
            model.setIsSolved(rs.getBoolean("isSolved"));
            model.setAttachedFile(rs.getString("attachedFile"));
            model.setSolution(rs.getString("solution"));
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(RequestMapper.class.getName()).log(Level.SEVERE, "SQL EXCEPTION ERROR", ex);
        }
        return null;
    }

}
