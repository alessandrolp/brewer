package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.controller.page.PageWrapper;
import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.repository.Grupos;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;
import br.com.aocubo.brewer.service.StatusUsuario;
import br.com.aocubo.brewer.service.UsuarioService;
import br.com.aocubo.brewer.service.exception.EmailCadastradoException;
import br.com.aocubo.brewer.service.exception.SenhaObrigatoriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView("usuario/PesquisaUsuario");
        mv.addObject("grupos", grupoRepository.findAll());
        PageWrapper<Usuario> paginaUsuarios = new PageWrapper<>(usuarioService.filtrar(usuarioFilter, pageable), httpServletRequest);
        mv.addObject("pagina", paginaUsuarios);
        return mv;
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario){
        usuarioService.alterarStatus(codigos, statusUsuario);
    }

}
