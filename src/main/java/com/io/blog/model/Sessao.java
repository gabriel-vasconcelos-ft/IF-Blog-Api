package com.io.blog.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Sessao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(nullable = false)
	public String token;

	@OneToOne(optional = false)
	public Autor autor;

	@Column(nullable = true)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	public LocalDateTime expDate;
}
