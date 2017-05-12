package br.com.aocubo.brewer.controller;

import br.com.aocubo.brewer.controller.page.PageWrapper;
import br.com.aocubo.brewer.model.Cliente;
import br.com.aocubo.brewer.model.TipoPessoa;
import br.com.aocubo.brewer.repository.Clientes;
import br.com.aocubo.brewer.repository.filter.ClienteFilter;
import br.com.aocubo.brewer.service.ClienteService;
import br.com.aocubo.brewer.service.EstadoService;
import br.com.aocubo.brewer.service.exception.CpfCnpjClienteCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by alessandro on 25/03/17.
 */

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Clientes clienteRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(Cliente cliente){
        ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
        mv.addObject("tipoPessoas", TipoPessoa.values());
        mv.addObject("estados",estadoService.buscarTodos());
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return novo(cliente);
        }
        try {
            clienteService.salvar(cliente);
        } catch (CpfCnpjClienteCadastradoException e){
            result.rejectValue("cpfCnpj", e.getMessage(), e.getMessage());
            return novo(cliente);
        }
        attributes.addFlashAttribute("mensagem", "Cliente Salvo com sucesso!");
        return new ModelAndView("redirect:/clientes/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
        ModelAndView mv = new ModelAndView("/cliente/PesquisaCliente");
        PageWrapper<Cliente> paginaClientes = new PageWrapper<>(clienteRepository.filtrar(clienteFilter, pageable), httpServletRequest);
        mv.addObject("pagina", paginaClientes);
        return mv;
    }

}
