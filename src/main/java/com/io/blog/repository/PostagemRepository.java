package com.io.blog.repository;

import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	Page<Postagem> findAll(Pageable pageablle);
	
    List<Postagem> findByAutor(Autor autor);
    
    @Query("SELECT p FROM Postagem p WHERE p.autor.matricula = :matricula")
    List<Postagem> buscarPorMatricula(@Param("matricula") String matricula);
}

