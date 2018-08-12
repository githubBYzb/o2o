package com.test.o2o.web.frontend;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.test.o2o.entity.LocalAuth;
import com.test.o2o.entity.PersonInfo;
import com.test.o2o.service.LocalAuthService;
import com.test.o2o.service.PersonInfoService;
import com.test.o2o.until.HttpServletRequestUtil;
import com.test.o2o.until.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/frontend")
public class LoginController {
	@Autowired
	private LocalAuthService localAuthService;
	@Autowired
	private PersonInfoService personInfoService;

	/*@RequestMapping(value = "/logincheck", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private Map<String, Object> loginCheck(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		request.setCharacterEncoding("utf-8");
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		if (userName != null && password != null) {
			//password = MD5.getMd5(password);
			LocalAuth localAuth = localAuthService.getLocalAuthPwdByUserName(userName);
			if (localAuth.getPassword() == password) {
				if (localAuth.getPersonInfo().getUserType() ==3) {
					modelMap.put("success", true);
					request.getSession().setAttribute("user",localAuth.getPersonInfo());
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "非管理员没有权限访问");
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}*/


	@RequestMapping(value = "/logincheck", method = {RequestMethod.POST})
	@ResponseBody
	private Map<String, Object> logincheck(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password,HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		LocalAuth localAuth = localAuthService.getLocalAuthPwdByUserName(userName);
		PersonInfo personInfo = personInfoService.getPersonInfoById(Long.valueOf(localAuth.getUserId()));
		//System.out.println(personInfo.getUserType());
		try {
			if (localAuth.getPassword().equals(password) ) {
				modelMap.put("success", true);
				modelMap.put("userType",personInfo.getUserType());
				modelMap.put("yourusername",localAuth.getLocalAuthId());
				modelMap.put("yourpwd",localAuth.getPassword());
				modelMap.put("yourname",personInfo.getName());
				modelMap.put("yourgender",personInfo.getGender());
				modelMap.put("youremail",personInfo.getEmail());
				request.getSession().setAttribute("user",localAuth.getLocalAuthId());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "密码错误");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return modelMap;
	}

	@RequestMapping(value = "/login")
	private String login() {
		return "frontend/login";
	}
}
