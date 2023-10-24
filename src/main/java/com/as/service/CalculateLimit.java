package com.as.service;

public class CalculateLimit {
    public int calculateLimit(int pageNo) {
        return (pageNo - 1) * 5;
    }
}
