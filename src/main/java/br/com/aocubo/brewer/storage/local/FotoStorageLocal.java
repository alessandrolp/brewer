package br.com.aocubo.brewer.storage.local;

import br.com.aocubo.brewer.storage.FotoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Created by alessandro on 06/05/17.
 */
public class FotoStorageLocal implements FotoStorage{

    private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

    private Path local;

    private Path localTemporario;

    public FotoStorageLocal(){
        this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerfotos"));
    }

    public FotoStorageLocal(Path path){
        this.local = path;
        criarPastas();
    }

    private void criarPastas() {
        try {
            Files.createDirectories(this.local);
            this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
            Files.createDirectories(this.localTemporario);

            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("Pastas criadas para salvar fotos.");
                LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
                LOGGER.debug("Pasta temporaria: " + this.localTemporario.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro criando pasta para salvar foto", e);
        }
    }

    @Override
    public String salvarTemporariamente(MultipartFile[] files) {
        String novoNome = null;
        if(files != null && files.length > 0){
            MultipartFile arquivo = files[0];
            novoNome = renomearArquivo(arquivo.getOriginalFilename());
            try {
                arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + novoNome));
            } catch (IOException e) {
                throw new RuntimeException("erro salvando a foto na pasta temporaria", e);
            }
        }
        return novoNome;
    }

    private String renomearArquivo(String nomeOriginal){
        String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(String.format("Nome Original: %s , Novo Nome: %s", nomeOriginal, novoNome));
        }
        return novoNome;
    }

    @Override
    public byte[] recuperarFotoTemporaria(String nome) {
        try {
            return Files.readAllBytes(this.localTemporario.resolve(nome));
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo a foto temporaria", e);
        }
    }
}
