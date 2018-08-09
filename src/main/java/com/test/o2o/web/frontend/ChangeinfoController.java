package com.test.o2o.web.frontend;


import com.test.o2o.entity.LocalAuth;
import com.test.o2o.service.LocalAuthService;
import com.test.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ChangeinfoController {

    @Autowired
    private LocalAuthService localAuthService;
    @Autowired
    private PersonInfoService personInfoService;

    @RequestMapping(value = "/changeinfo")
    private String changeinfo(){return "/frontend/changeinfo";}

    @RequestMapping(value = "/changecount", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> changecount(@RequestParam(value = "oldcount") String oldcount,@RequestParam(value = "newcount") String newcount){
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int flag = localAuthService.changeLocalAuthId(oldcount, newcount);
            if (flag == 1)
                modelMap.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
        }
            return modelMap;
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changepwd(@RequestParam(value = "oldpwd") String oldpwd, @RequestParam(value = "newpwd") String newpwd,@RequestParam(value = "count") String count) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int flag = localAuthService.changePassword(oldpwd,newpwd,count);
            if (flag == 1)
                modelMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }


    @RequestMapping(value = "/changename", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changename(@RequestParam(value = "oldname") String oldname, @RequestParam(value = "newname") String newname) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int flag1 = localAuthService.changeUsername(oldname, newname);
            if (flag1 == 1) {
                int flag2 = personInfoService.changeName(oldname, newname);
                if (flag2 == 1)
                    modelMap.put("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }

    @RequestMapping(value = "/changegender", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changegender(@RequestParam(value = "oldgender") String oldgender, @RequestParam(value = "newgender") String newgender,@RequestParam(value = "name") String name) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int flag = personInfoService.changeGender(oldgender,newgender,name);
            if (flag == 1)
                    modelMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }

    @RequestMapping(value = "/changeemail", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeemail(@RequestParam(value = "oldemail") String oldemail, @RequestParam(value = "newemail") String newemail,@RequestParam(value = "name") String name) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int flag = personInfoService.changeEmail(oldemail,newemail,name);
            if (flag == 1)
                modelMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }
}
