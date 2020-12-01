package com.baizhi.hh.controller;

import com.baizhi.hh.entity.Admin;
import com.baizhi.hh.service.AdminService;
import com.baizhi.hh.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    /**
     * 后台
     * 登陆
     */
    @RequestMapping("login")
    public String login(Admin admin, String enCode, HttpSession session) {
        String ren = null;
        Admin name = adminService.findName(admin.getUsername());
        String code = (String) session.getAttribute("code");
        if (enCode.equals(code)) {
            if (name != null && name.getPassword().equals(admin.getPassword())) {
                session.setAttribute("admin", name);
                ren = "redirect:/main/main.jsp";
            } else {
                ren = "redirect:/login.jsp";
            }
        } else {
            ren = ("验证码有误");
        }
        return ren;
    }

    /**
     * 后台
     * 退出
     */
    @RequestMapping("del")
    public String del(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }


    /**
     * 验证码
     */
    @RequestMapping("caretImg")
    public void caretImg(HttpSession session, HttpServletResponse response) {
        try {
//        生成随机数
            String code = ImageCodeUtil.getSecurityCode();
            log.debug("当前验证码为:" + code);
//        生成图片
            BufferedImage bufferedImage = ImageCodeUtil.createImage(code);
//        输出流
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "gif", outputStream);
            session.setAttribute("code", code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
