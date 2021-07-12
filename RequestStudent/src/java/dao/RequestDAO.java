/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapper.RequestMapper;
import model.RequestModel;

/**
 *
 * @author haiva
 */
public class RequestDAO extends AbstractDAO<RequestModel> {

    public RequestModel getRequest(int rid) {
        String sql = "SELECT *FROM Request WHERE rid=?";
        return query(sql, new RequestMapper(), rid).get(0);
    }

    public List<RequestModel> getAllRequest() {
        String sql = "SELECT *FROM Request";
        return query(sql, new RequestMapper(), "");
    }

    public void updaterequest(RequestModel model) {
        String sql = "UPDATE Request SET closeDate=GETDATE(), username=?, State=?, isSolved=?, solution=? WHERE rid=?";
        update(sql, model.getUsername(), model.isState(), model.isIsSolved(), model.getSolution(), model.getRid());
    }

    public List<RequestModel> getRequestbyDID(int did) {
        String sql = "SELECT *FROM Request WHERE did=?";
        return query(sql, new RequestMapper(), did);
    }
}
