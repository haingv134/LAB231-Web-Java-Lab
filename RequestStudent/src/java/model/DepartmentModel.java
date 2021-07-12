/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author haiva
 */
public class DepartmentModel {

    private int did;
    private String name;
    private int requestToday;
    private int requestLast2days;

    public int getRequestToday() {
        return requestToday;
    }

    public void setRequestToday(int requestToday) {
        this.requestToday = requestToday;
    }

    public int getRequestLast2days() {
        return requestLast2days;
    }

    public void setRequestLast2days(int requestLast2days) {
        this.requestLast2days = requestLast2days;
    }

    public DepartmentModel() {
    }

    public DepartmentModel(int did, String name) {
        this.did = did;
        this.name = name;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
