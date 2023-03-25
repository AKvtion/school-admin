package com.dev.schooladmin.base.exception;

public class PasswordErrorException extends ServiceException {
    public PasswordErrorException() {
        super(10400, "密码错误");
    }
}