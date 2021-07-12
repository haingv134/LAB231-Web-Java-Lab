/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapper.AdminMapper;
import model.AdminModel;

/**
 *
 * @author haiva
 */
public class AdminDAO extends AbstractDAO<AdminModel>{
    
    public List<AdminModel> getAdmin(String username, String password){
        String sql = "SELECT *FROM Admin WHERE username=? AND password=?";
        return query(sql, new AdminMapper(), username, password);
    }

}
