package com.io.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.blog.dto.AuthenticationDTO;
import com.io.blog.model.Autor;
import com.io.blog.model.Sessao;
import com.io.blog.repository.SessaoRepository;

@Service
public class SessaoService {
	@Autowired
	SessaoRepository repository;
	
	public void criarSessao(Autor autor, String token) {
		repository.removeAllSessionByAutorId(autor.getId());
		Sessao sessao = new Sessao();
		sessao.setAutor(autor);
		//LocalDateTime.from(new Date().toInstant()).plusMinutes(1)
		//sessao.setExpDate(LocalDateTime.from(new Date().toInstant()));
		sessao.setToken(token);
		repository.save(sessao);
	}

	public boolean hasAuthorization(AuthenticationDTO auth) {
		List<Sessao> sessoes = repository.findAllSessaoByToken(auth.getToken());
		 if(!sessoes.isEmpty()) {
			 return true;
		 } else {
			 return false;
		 }
		
	}
}
