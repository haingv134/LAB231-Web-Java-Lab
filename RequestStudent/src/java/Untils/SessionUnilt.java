/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Untils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author haiva
 */
public class SessionUnilt {

    public void pushValue(HttpServletRequest request, String key, String value) {
        request.getSession().setAttribute(key, value);
    }

    public String getValue(HttpServletRequest request, String key) {
        return String.valueOf(request.getSession().getAttribute(key));
    }
    public void remove(HttpServletRequest request, String attribute){
        request.getSession().removeAttribute(attribute);
    }
}
