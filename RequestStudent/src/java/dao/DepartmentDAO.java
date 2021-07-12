/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapper.DepartmentMapper;
import model.DepartmentModel;

/**
 *
 * @author haiva
 */
public class DepartmentDAO extends AbstractDAO<DepartmentModel>{
    public DepartmentModel getDepartment(int did){
        String sql = "SELECT *FROM Department WHERE did=?";
        return query(sql, new DepartmentMapper(), did).get(0);
    }
    public List<DepartmentModel> getDepartment(){
        String sql = "SELECT *FROM Department";
        return query(sql, new DepartmentMapper(), "");
    }
}
