package br.com.aocubo.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by alessandro on 20/05/17.
 */

@Controller
public class ErrosController {

    @GetMapping("/404")
    public String paginaNaoEncontrada(){
        return "404";
    }

    @GetMapping("/500")
    public String erroServidor(){
        return "500";
    }

}
