package project.controller;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class Response <T> {
    private int error_code;
    private String error_message;
    private T data;
}