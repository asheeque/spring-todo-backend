package com.asheeque.springboot.ToDo.dto.request;


import jakarta.validation.constraints.NotBlank;

public class UpdateSubTaskStatusRequest {

    @NotBlank
    private Long subtaskId;

    @NotBlank
    private boolean newStatus;

    // Add getters and setters for both fields
    public Long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(Long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public UpdateSubTaskStatusRequest(Long subtaskId, boolean newStatus) {
        this.subtaskId = subtaskId;
        this.newStatus = newStatus;
    }
}


