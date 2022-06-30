package com.grapedata.hibernateelementcollectioninvestigation.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestionResponseId implements Serializable {

    private String userId;
    private String taskId;

    public QuestionResponseId(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public QuestionResponseId() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
