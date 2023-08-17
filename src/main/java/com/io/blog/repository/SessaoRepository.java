package com.io.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.blog.model.Sessao;

import jakarta.transaction.Transactional;


public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	@Transactional
	void removeAllSessionByAutorId(Long autorId);

	List<Sessao> findAllSessaoByToken(String token);

}
