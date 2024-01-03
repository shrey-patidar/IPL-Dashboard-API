package com.personalProjects.IPLDashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
    private Object errors;
}
