package com.dev.schooladmin.base.exception;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.http.HttpStatus;
import com.dev.schooladmin.base.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 拦截没有角色的异常
    @ExceptionHandler(Exception.class)
    public Result handlerNotRoleException(NotRoleException e,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色校验失败'{}'", requestURI, e.getMessage());
        return new Result().fail("无此角色，请联系管理员!",500);
    }

    /**
     * 认证失败 拦截：未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        // 打印堆栈，以供调试
        e.printStackTrace();
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',认证失败'{}',无法访问系统资源", requestURI, e.getMessage());
        return SaResult.get(HttpStatus.HTTP_UNAUTHORIZED, "认证失败，未登录，无法访问系统资源", null);
    }

    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return SaResult.get(HttpStatus.HTTP_FORBIDDEN, "没有访问权限，请联系管理员授权", null);

    }
}