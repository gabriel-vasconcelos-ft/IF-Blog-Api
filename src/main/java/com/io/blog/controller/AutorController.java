package com.io.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.io.blog.dto.AuthenticationDTO;
import com.io.blog.dto.AutorDTO;
import com.io.blog.model.Autor;
import com.io.blog.service.AutorService;
import com.io.blog.service.SessaoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService serviceAutor;
	
	@Autowired
	private SessaoService serviceSessao;

	@PostMapping("/salvar")
	public String salvar(@RequestBody Autor autor) {
		try {
			autor.setAdmin(false);
			serviceAutor.cadastrarAutor(autor);
			return "{\"status\":\"Usuário cadastrado com sucesso\"}";
		} catch (Exception e) {
			return "{\"status\":\"Erro no cadastro\"}";
		}

	}

	@PostMapping("/login")
	public AuthenticationDTO login(@RequestBody AutorDTO autor) {
		Autor autorEncontrado = serviceAutor.buscarAutorPorMatricula(autor.getMatricula());
		
		if (autorEncontrado != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(autor.getSenha(), autorEncontrado.getSenha())) {
				String token = UUID.randomUUID().toString();
				serviceSessao.criarSessao(autorEncontrado, token);
				AuthenticationDTO logar = new AuthenticationDTO();
				logar.setNome(autorEncontrado.getNome());
				logar.setMatricula(autorEncontrado.getMatricula());
				logar.setToken(token);
				System.out.println(logar);
				return logar;
			}
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/auth")
	public String hasAuthorization(@RequestBody AuthenticationDTO auth) {
		boolean res = serviceSessao.hasAuthorization(auth);
		return "{\"auth\":\""+ res +"\"}";
	}
		

}
