package com.io.blog.dto;

import com.io.blog.model.Categoria;

import lombok.Data;

@Data
public class PostagemDTO {
    public Long id;

	public String matriculaAutor;

    public String titulo;

    public String tags;
    
    public String imagem;

    public Categoria categoria;
   
    public String corpoPostagem;
}
