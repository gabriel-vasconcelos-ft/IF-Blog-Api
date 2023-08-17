package com.io.blog.service;

import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;
import com.io.blog.repository.PostagemRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;

    @Autowired
    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }
    
    public Page<Postagem> findAll(Pageable pageable) {
    	return this.postagemRepository.findAll(pageable);
    }

    public void salvarPostagem(Postagem postagem) {
        postagemRepository.save(postagem);
    }

    public List<Postagem> buscarPostagensPorAutor(Autor autor) {
        return postagemRepository.findByAutor(autor);
    }
    
    public List<Postagem> buscarPorMatricula(String matricula) {
        return postagemRepository.buscarPorMatricula(matricula);
    }
    public Optional<Postagem> findById(Long id) {
        return postagemRepository.findById(id);
    }

}

