package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.repository.Grupos;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;
import br.com.aocubo.brewer.service.UsuarioService;
import br.com.aocubo.brewer.service.exception.SenhaObrigatoriaException;
import br.com.aocubo.brewer.service.exception.EmailCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * Created by alessandro on 25/03/17.
 */

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Grupos grupoRepository;

    @RequestMapping(value = "/novo")
    public ModelAndView novo(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
        mv.addObject("grupos", grupoRepository.findAll());
        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return novo(usuario);
        }
        try {
            usuarioService.salvar(usuario);
        } catch (EmailCadastradoException e){
            result.rejectValue("email", e.getMessage(), e.getMessage());
            return novo(usuario);
        } catch (SenhaObrigatoriaException e){
            result.rejectValue("senha", e.getMessage(), e.getMessage());
            return novo(usuario);
        }
        attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");
        return new ModelAndView("redirect:/usuarios/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(UsuarioFilter usuarioFilter){
        ModelAndView mv = new ModelAndView("usuario/PesquisaUsuario");
        mv.addObject("usuarios", usuarioService.filtrar(usuarioFilter));
        mv.addObject("grupos", grupoRepository.findAll());
        return mv;
    }

}
