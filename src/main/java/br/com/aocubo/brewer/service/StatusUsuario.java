package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.repository.Usuarios;

/**
 * Created by alessandro on 20/05/17.
 */
public enum StatusUsuario {

    ATIVAR {
        @Override
        public void executar(Long[] codigos, Usuarios usuarioRepository) {
            usuarioRepository.findByIdIn(codigos).forEach(u -> u.setAtivo(true));
        }
    },
    DESATIVAR {
        @Override
        public void executar(Long[] codigos, Usuarios usuarioRepository) {
            usuarioRepository.findByIdIn(codigos).forEach(u -> u.setAtivo(false));
        }
    };

    public abstract void executar(Long[] codigos, Usuarios usuarioRepository);
}
