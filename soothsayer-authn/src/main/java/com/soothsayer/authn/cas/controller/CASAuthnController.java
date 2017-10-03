package com.soothsayer.authn.cas.controller;

import com.soothsayer.authn.cas.controller.exception.CASAppNotRegisterException;
import com.soothsayer.authn.params.Constants;
import com.soothsayer.core.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Slf4j
@Controller
public class CASAuthnController {

    @Autowired
    private WebContext context;



    @GetMapping("/cas/login")
    public void casLogin(@RequestParam String service, @RequestParam(required = false) String params) throws Exception {
        //TODO: 验证service是否存在
        if (false) {
            log.info("CAS应用未注册");
            throw new CASAppNotRegisterException();
        }

        service = URLEncoder.encode(service, Constants.UTF8);
        //TODO: 判断当前用户是否已登录
        this.redirectToLogin(service, params);
    }

    private void redirectToLogin(String service, String params) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("/?fromUrl=%s", service));
        if (!StringUtils.isEmpty(params)) {
            sb.append(String.format("&params=%s", URLEncoder.encode(params, Constants.UTF8)));
        }
        context.redirect(sb.toString());
    }

}
