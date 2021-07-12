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
import model.StudentModel;

/**
 *
 * @author haiva
 */
public class StudentMapper implements RowMapper<StudentModel>{

    @Override
    public StudentModel mapRow(ResultSet rs) {
        StudentModel model = new StudentModel();
        try {
            model.setName(rs.getString("name"));
            model.setSid(rs.getInt("sid"));
            model.setStudentId(rs.getString("studentId"));
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(StudentMapper.class.getName()).log(Level.SEVERE, "SQLEXEPTION", ex);
        }
        return null;
    }
}
