/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author haiva
 */
public class AnswerSheetModel {

    private int questionid;
    private int answerindex;

    public AnswerSheetModel(int questionid, int answerindex) {
        this.questionid = questionid;
        this.answerindex = answerindex;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getAnswerindex() {
        return answerindex;
    }

    public void setAnswerindex(int answerindex) {
        this.answerindex = answerindex;
    }

}
