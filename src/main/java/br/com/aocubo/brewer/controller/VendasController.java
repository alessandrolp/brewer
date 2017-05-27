package br.com.aocubo.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alessandro on 27/05/17.
 */

@Controller
@RequestMapping("/vendas")
public class VendasController {

    @GetMapping("/nova")
    public ModelAndView nova(){
        ModelAndView mv = new ModelAndView("/venda/CadastroVenda");
        return mv;
    }

}
