package com.test.o2o.web.frontend;

import com.test.o2o.entity.LocalAuth;
import com.test.o2o.service.LocalAuthService;
import com.test.o2o.until.CodeUtil;
import com.test.o2o.until.HttpServletRequestUtil;
import com.test.o2o.until.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "frontend",method = {RequestMethod.GET,RequestMethod.POST})
public class CustomerAuthController {
    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/customerlogincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> CustomerLoginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        boolean needVerify = HttpServletRequestUtil.getBoolean(request,
                "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        if (userName != null && password != null) {
            //password = MD5.getMd5(password);
            LocalAuth localAuth = localAuthService.getLocalAuthPwdByUserName(userName);
            if (localAuth != null) {
                if (localAuth.getPersonInfo().getUserType() == 1) {
                    modelMap.put("success", true);
                    request.getSession().setAttribute("user",
                            localAuth.getPersonInfo());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "非顾客没有权限访问");
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }
}
