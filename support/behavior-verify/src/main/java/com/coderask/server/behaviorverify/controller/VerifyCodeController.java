package com.coderask.server.behaviorverify.controller;

import com.coderask.server.behaviorverify.model.VerifyCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 行为验证：验证码
 */
@RestController
public class VerifyCodeController {

    @GetMapping("/api/auth/verifycode")
    public void verifyCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("verify_code", text);
        vc.output(image, resp.getOutputStream());
    }
}
