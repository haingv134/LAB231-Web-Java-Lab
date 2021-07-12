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
import model.AdminModel;

/**
 *
 * @author haiva
 */
public class AdminMapper implements RowMapper<AdminModel> {
    
    @Override
    public AdminModel mapRow(ResultSet rs) {
        AdminModel model = new AdminModel();
        try {
            model.setUsername(rs.getString("username"));
            model.setPassword(rs.getString("password"));
            model.setName(rs.getString("name"));
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(AdminMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
