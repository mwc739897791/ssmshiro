package com.zking.ssm.controller;

import com.zking.ssm.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.zking.ssm.util.PasswordHelper.createCredentials;
import static com.zking.ssm.util.PasswordHelper.createSalt;

@Controller
@RequestMapping("/login")
public class LoginController {


    @RequestMapping("/dologin")
    public String dologin(){
        return "login";
    }



    /**
     * 登录
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/do")
    public String toLogin(HttpSession session, User user){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        String message="";
        try {
            subject.login(token);
            message="登陆成功";
        } catch (IncorrectCredentialsException e) {
            message="密码错误";
        }catch(UnknownAccountException e1){
            message="账号不存在";
        }
        if(subject.hasRole("管理员")){
            System.out.println("管理员");
            }else if(subject.hasRole("高级用户")){
            System.out.println("高级用户");
           }else{
            System.out.println("普通用户");
            }
        session.setAttribute("message",message);
        return "main";
    }

}
