package com.io.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty
	@Column(nullable = false)
	private String email;
	
	@NotEmpty
	@Column(nullable = false)
	private String matricula;
	
	@NotEmpty
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Boolean admin;
	
    public void setSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);
    }
	
}
