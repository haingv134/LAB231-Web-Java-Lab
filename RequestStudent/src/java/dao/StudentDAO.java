/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import mapper.StudentMapper;
import model.StudentModel;

/**
 *
 * @author haiva
 */
public class StudentDAO extends AbstractDAO<StudentModel> {

    public StudentModel getStudent(int sid) {
        String sql = "SELECT *FROM Student WHERE [sid]=?";
        return query(sql, new StudentMapper(), sid).get(0);
    }
}
