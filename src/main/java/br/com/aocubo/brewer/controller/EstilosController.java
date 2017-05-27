package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.controller.page.PageWrapper;
import br.com.aocubo.brewer.model.Estilo;
import br.com.aocubo.brewer.repository.Estilos;
import br.com.aocubo.brewer.repository.filter.EstiloFilter;
import br.com.aocubo.brewer.service.EstiloService;
import br.com.aocubo.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by alessandro on 25/03/17.
 */

@Controller
@RequestMapping("/estilos")
public class EstilosController {

    @Autowired
    private EstiloService estiloService;

    @Autowired
    private Estilos estilos;

    @RequestMapping("/novo")
    public ModelAndView novo(Estilo estilo) {
        return new ModelAndView("estilo/CadastroEstilo");
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return novo(estilo);
        }

        try {
            estiloService.salvar(estilo);
        } catch (NomeEstiloJaCadastradoException e) {
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return novo(estilo);
        }

        attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
        return new ModelAndView("redirect:/estilos/novo");
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
        }
        estilo = estiloService.salvar(estilo);
        return ResponseEntity.ok(estilo);
    }

    @GetMapping
    public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");
        PageWrapper<Estilo> paginaEstilos = new PageWrapper<>(estilos.filtrar(estiloFilter, pageable), httpServletRequest);
        mv.addObject("pagina", paginaEstilos);
        return mv;
    }


}
