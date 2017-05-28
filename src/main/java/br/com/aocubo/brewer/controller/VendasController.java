package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.Cervejas;
import br.com.aocubo.brewer.session.TabelaItensVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alessandro on 27/05/17.
 */

@Controller
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private Cervejas cervejaRepository;

    @Autowired
    private TabelaItensVenda tabelaItensVenda;

    @GetMapping("/nova")
    public ModelAndView nova(){
        ModelAndView mv = new ModelAndView("/venda/CadastroVenda");
        return mv;
    }

    @PostMapping("/item")
    public ModelAndView adicionarItem(Long idCerveja){
        Cerveja cerveja = cervejaRepository.findOne(idCerveja);
        tabelaItensVenda.adicionarItem(cerveja, 1);
        ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
        mv.addObject("itens", tabelaItensVenda.getItens());
        return mv;
    }

}
