/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author haiva
 */
public class RequestModel {

    private int rid;
    private int sid;
    private int did;
    private String username;
    private Date dateCreated;
    private String title;
    private String content;
    private Date closeDate;
    private boolean state;
    private boolean isSolved;
    private String attachedFile;
    private String solution;
    private String strState;

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState;
    }
    
    //-----------
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    //------------
    public RequestModel() {
    }

    public RequestModel(int rid, int sid, int did, String username, Date dateCreated, String title, String content, Date closeDate, boolean state, boolean isSolved, String attachedFile, String solution, String departmentName) {
        this.rid = rid;
        this.sid = sid;
        this.did = did;
        this.username = username;
        this.dateCreated = dateCreated;
        this.title = title;
        this.content = content;
        this.closeDate = closeDate;
        this.state = state;
        this.isSolved = isSolved;
        this.attachedFile = attachedFile;
        this.solution = solution;
        this.departmentName = departmentName;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    
}
