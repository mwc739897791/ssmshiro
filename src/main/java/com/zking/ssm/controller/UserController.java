package com.zking.ssm.controller;

import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.zking.ssm.util.PasswordHelper.createCredentials;
import static com.zking.ssm.util.PasswordHelper.createSalt;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService ius;

    /**
     * 注册界面跳转
     *
     * @return
     */
    @RequestMapping("/doregister")
    public String doregister() {
        return "register";
    }


    /**
     * 注册界面跳转
     *
     * @return
     */
    @RequestMapping("/dodel")
    public String del() {
        return "del";
    }

    @RequestMapping("del")
    @ResponseBody
    public Map<String, Object> delete(HttpSession session, User user) {
        String message = "";
        int code = 0;
        int i = ius.deleteByPrimaryKey(user.getUserid());
        if (i > 0) {
            message = "成功";
            code = 1;
        } else {
            message = "失败";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", message);
        return map;
    }

    @RequestMapping("register")
    public String register(User user) {
        String salt = createSalt();
        String credentials = createCredentials(user.getPassword(), salt);
        user.setPassword(credentials);
        user.setSalt(salt);
        int insert = ius.insert(user);
        if (insert > 0) {
            return "/ssmshiro/login/do/main";
        } else
            throw new RuntimeException("增加失败");
    }
}
