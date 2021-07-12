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
public class StudentModel {
    private int sid;
    private String name;
    private String studentId;

    public StudentModel() {
    }

    public StudentModel(int sid, String name, String studentId) {
        this.sid = sid;
        this.name = name;
        this.studentId = studentId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
