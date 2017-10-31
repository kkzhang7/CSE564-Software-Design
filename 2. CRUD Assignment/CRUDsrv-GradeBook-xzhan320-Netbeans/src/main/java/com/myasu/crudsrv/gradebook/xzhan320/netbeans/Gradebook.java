/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.crudsrv.gradebook.xzhan320.netbeans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xzhan
 */
@XmlRootElement
@XmlType(propOrder={
    "id",
    "studentId",
    "gradingItem",
    "grade",
    "gradingComment"})
public class Gradebook {
    
    private static final Logger LOG = LoggerFactory.getLogger(Gradebook.class);
    
    private String studentId;
    private String gradingItem;
    private double grade;
    private String gradingComment;
    private int id;

    public Gradebook() {
        LOG.info("Creating a Gradebook object");
    }

    public String getStudentId() {
        return studentId;
    }

    @XmlElement(name = "studentId")
    public void setStudentId(String studentId) {
        LOG.info("Setting the studentId to {}", studentId);
        
        this.studentId = studentId;
        
        LOG.debug("The updated gradebook = {}", this);
    }

    public String getGradingItem() {
        return gradingItem;
    }

    @XmlElement(name = "gradingItem")
    public void setGradingItem(String gradingItem) {
        LOG.info("Setting the gradingItem to {}", gradingItem);
        
        this.gradingItem = gradingItem;
        
        LOG.debug("The updated gradebook = {}", this);
    }

    public double getGrade() {
        return grade;
    }

    @XmlElement(name = "grade")
    public void setGrade(double grade) {
        LOG.info("Setting the grade to {}", grade);
        
        this.grade = grade;
        
        LOG.debug("The updated gradebook = {}", this);
    }

    public String getGradingComment() {
        return gradingComment;
    }

    @XmlElement(name = "gradingComment")
    public void setGradingComment(String gradingComment) {
        LOG.info("Setting the gradingComment to {}", gradingComment);
        
        this.gradingComment = gradingComment;
        
        LOG.debug("The updated gradebook = {}", this);
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        LOG.info("Setting the record Id to {}", id);
        
        this.id = id;
        
        LOG.debug("The updated gradebook = {}", this);
    }

    @Override
    public String toString() {
        return "Gradebook{" + "recordId=" + id + ", studentId=" + studentId + ", gradingItem=" + gradingItem + ", grade=" + grade + ", comment=" + gradingComment +"}";
    }

}
