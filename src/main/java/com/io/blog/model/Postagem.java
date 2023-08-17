package com.io.blog.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column(nullable = false)
    private String titulo;

    @ElementCollection
    @CollectionTable(name = "postagem_tags")
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @Column(nullable = true)
    private String imagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String corpoPostagem;

    public Postagem() {
    }

    public Postagem(Autor autor, String titulo, Set<String> tags, String imagem, Categoria categoria, String corpoPostagem) {
        this.autor = autor;
        this.titulo = titulo;
        this.tags = tags;
        this.imagem = imagem;
        this.categoria = categoria;
        this.corpoPostagem = corpoPostagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCorpoPostagem() {
        return corpoPostagem;
    }

    public void setCorpoPostagem(String corpoPostagem) {
        this.corpoPostagem = corpoPostagem;
    }
}


