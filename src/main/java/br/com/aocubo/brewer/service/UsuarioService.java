package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.repository.Usuarios;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;
import br.com.aocubo.brewer.service.exception.EmailCadastradoException;
import br.com.aocubo.brewer.service.exception.SenhaObrigatoriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by alessandro on 07/05/17.
 */

@Service
public class UsuarioService {

    @Autowired
    private Usuarios usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void salvar(Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioExistente.isPresent()){
            throw new EmailCadastradoException("E-mail já cadastrado");
        }
        if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())){
            throw new SenhaObrigatoriaException("Senha é obrigatória para novo usuário");
        }
        if(usuario.isNovo()){
            usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
            usuario.setConfirmacaoSenha(usuario.getSenha());
        }
        usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> filtrar(UsuarioFilter usuarioFilter){
        return usuarioRepository.filtrar(usuarioFilter);
    }

    public void deletar(Long id){
        usuarioRepository.delete(usuarioRepository.findOne(id));
    }

}
