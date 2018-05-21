package com.resi.common.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LoginUserResponse {
    private String sessionId;

    @Override
    public String toString() {
        return "ClassPojo [loginUserResponse = " + sessionId + "]";
    }
}