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
import model.DepartmentModel;

/**
 *
 * @author haiva
 */
public class DepartmentMapper implements RowMapper<DepartmentModel>{

    @Override
    public DepartmentModel mapRow(ResultSet rs) {
        DepartmentModel model = new DepartmentModel();
        try {
            model.setDid(rs.getInt("did"));
            model.setName(rs.getString("name"));
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentMapper.class.getName()).log(Level.SEVERE, "SQL EXCEPTION ERROR: ", ex);
        }
        return null;
    }
}
