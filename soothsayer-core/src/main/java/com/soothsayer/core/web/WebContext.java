package com.soothsayer.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class WebContext {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    public HttpSession getSession() {
        return request.getSession();
    }

    public HttpSession getNewSession() {
        return request.getSession(true);
    }

    public void redirect(String redirectUrl) throws IOException {
        response.sendRedirect(redirectUrl);
    }

}
