package br.com.aocubo.brewer.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by alessandro on 19/05/17.
 */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String autenticar(@AuthenticationPrincipal User user){
        if(user != null) {
            return "redirect:/cervejas";
        }
        return "Login";
    }

}
