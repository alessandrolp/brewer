package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.controller.page.PageWrapper;
import br.com.aocubo.brewer.model.Cidade;
import br.com.aocubo.brewer.repository.Cidades;
import br.com.aocubo.brewer.repository.filter.CidadeFilter;
import br.com.aocubo.brewer.service.CidadeService;
import br.com.aocubo.brewer.service.EstadoService;
import br.com.aocubo.brewer.service.exception.CidadeCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by alessandro on 25/03/17.
 */

@Controller
@RequestMapping("/cidades")
public class CidadesController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private Cidades cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    @RequestMapping("/novo")
    public ModelAndView novo(Cidade cidade) {
        ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
        mv.addObject("estados", estadoService.buscarTodos());
        return mv;
    }

    @Cacheable(value = "cidades", key = "#idEstado")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Cidade> buscaPorIdEstado(@RequestParam(name = "estado", defaultValue = "-1") Long idEstado){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
        }
        return cidadeService.buscaPorIdEstado(idEstado);
    }

    @PostMapping("/novo")
    @CacheEvict(value = "cidades", key = "#cidade.estado.id", condition = "#cidade.temEstado()")
    public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return novo(cidade);
        }
        try {
            cidadeService.salvar(cidade);
        } catch (CidadeCadastradaException e){
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return novo(cidade);
        }
        attributes.addFlashAttribute("mensagem", "Cidade Salva com sucesso!");
        return new ModelAndView("redirect:/cidades/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
        PageWrapper<Cidade> paginaCidades = new PageWrapper<>(cidadeRepository.filtrar(cidadeFilter, pageable), httpServletRequest);
        mv.addObject("estados", estadoService.buscarTodos());
        mv.addObject("pagina", paginaCidades);
        return mv;
    }

}
