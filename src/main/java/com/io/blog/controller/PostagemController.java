package com.io.blog.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.io.blog.dto.PostagemDTO;
import com.io.blog.model.Autor;
import com.io.blog.model.Postagem;
import com.io.blog.service.AutorService;
import com.io.blog.service.PostagemService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/post")
public class PostagemController {

    private final PostagemService postagemService;
    private final AutorService autorService;

   
    public PostagemController(PostagemService postagemService, AutorService autorService) {
        this.postagemService = postagemService;
        this.autorService = autorService;
    }
    
    
	
   @GetMapping
   public ResponseEntity<Page<Postagem>> findAll(@RequestParam(defaultValue  = "0") int page, @RequestParam(defaultValue  = "25") int size) {
	   
	   return new ResponseEntity<>(postagemService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
	
   	}

    @GetMapping("/{autorId}")
    public String exibirPostagens(@PathVariable String matricula, Model model) {
        Autor autor = autorService.buscarAutorPorMatricula(matricula);
        if (autor != null) {
            model.addAttribute("autor", autor);
            model.addAttribute("postagens", postagemService.buscarPostagensPorAutor(autor));
            return "postagens";
        } else {
            return "error";
        }
    }

    @PostMapping
    public String salvarPostagem(@RequestBody PostagemDTO postagem) {
    	    	
    	try {
        	Postagem post = new Postagem();
        	Autor autor = autorService.buscarAutorPorMatricula(postagem.getMatriculaAutor());
        	post.setAutor(autor);
        	post.setCategoria(postagem.getCategoria());
        	post.setCorpoPostagem(postagem.getCorpoPostagem());    	
        	post.setImagem(postagem.getImagem());
        	HashSet<String> hTags = new HashSet<String>();
        	Arrays.asList(postagem.getTags().split(",")).stream().forEach(tag -> {
        		hTags.add(tag);
        	});
        	post.setTags(hTags);
        	post.setTitulo(postagem.getTitulo());
        	postagemService.salvarPostagem(post);
    		
			return "{\"status\":\"Postagem salva com sucesso\"}";
		} catch (Exception e) {
			return "{\"status\":\"Erro na postagem\"}";
		}

    	
    }
    
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<Postagem>> buscarPorMatricula(@PathVariable("matricula") String matricula) {
        return new ResponseEntity<>(postagemService.buscarPorMatricula(matricula), HttpStatus.OK);
    }
    
    @GetMapping("/exibir/{id}")
    public ResponseEntity<Optional<Postagem>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postagemService.findById(id), HttpStatus.OK);
    }

}


