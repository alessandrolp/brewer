package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.controller.page.PageWrapper;
import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.model.Origem;
import br.com.aocubo.brewer.model.Sabor;
import br.com.aocubo.brewer.repository.Cervejas;
import br.com.aocubo.brewer.repository.Estilos;
import br.com.aocubo.brewer.repository.filter.CervejaFilter;
import br.com.aocubo.brewer.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {

    @Autowired
    private Estilos estilos;

    @Autowired
    private CervejaService cervejaService;

    @Autowired
    private Cervejas cervejaRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(Cerveja cerveja) {
        ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
        mv.addObject("estilos", estilos.findAll());
        mv.addObject("sabores", Sabor.values());
        mv.addObject("origens", Origem.values());
        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return novo(cerveja);
        }
        cervejaService.salvar(cerveja);
        attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
        return new ModelAndView("redirect:/cervejas/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
        mv.addObject("estilos", estilos.findAll());
        mv.addObject("sabores", Sabor.values());
        mv.addObject("origens", Origem.values());
        PageWrapper<Cerveja> paginaCervejas = new PageWrapper<>(cervejaRepository.filtrar(cervejaFilter, pageable), httpServletRequest);
        mv.addObject("pagina", paginaCervejas);
        return mv;
    }

}