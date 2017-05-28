package br.com.aocubo.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by alessandro on 06/05/17.
 */

public interface FotoStorage {

    String salvarTemporariamente(MultipartFile[] files);

    byte[] recuperarFotoTemporaria(String nome);

    void salvar(String foto);

    byte[] recuperar(String foto);

}
