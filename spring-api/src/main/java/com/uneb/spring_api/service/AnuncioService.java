package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.repositories.AnuncioRepository;
import com.uneb.spring_api.repositories.ImagemRepository;
import com.uneb.spring_api.repositories.UserRepository;
import com.uneb.spring_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImagemRepository imagemRepository;
    @Transactional
    public Anuncio criarAnuncio(Anuncio anuncio) {
            // Verifica se a imagem está definida no anúncio
            if (anuncio.getImagem() != null) {
                    // Persiste a imagem, se não estiver persistida
                if (anuncio.getImagem().getId() == null) {
                    anuncio.setImagem(imagemRepository.save(anuncio.getImagem()));
                    }
                }
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

    public Optional<Anuncio> verAnuncio(Long id) {
        return anuncioRepository.findById(id);
    }

    public List<Anuncio> listarAnuncioByCategoryId(Long id) {
        List<Anuncio> todos = anuncioRepository.findAll();
        List<Anuncio> resposta = new ArrayList<>();
        for (Anuncio anuncio:todos) if(anuncio.getDepartamento().getId().equals(id)) resposta.add(anuncio);
        return resposta;
    }

    public List<Anuncio> getAnunciosByUser(User criador) {
        return anuncioRepository.findByCriador(criador);
    }
    public Void deletarAnuncio(Long id){
        anuncioRepository.deleteById(id);
        return null;
    }

    
    public void atualizarStatusAnuncio(Anuncio anuncio) {
        // Apenas atualiza o status do anúncio no banco de dados
        anuncioRepository.save(anuncio);
    }
}
