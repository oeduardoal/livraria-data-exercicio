package br.edu.unichristus.livrariadata.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.livrariadata.entidades.Autor;
import br.edu.unichristus.livrariadata.entidades.Livro;
import br.edu.unichristus.livrariadata.repositorios.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorRepository repoAutor;

	@GetMapping("/buscarPais/{pais}")
	public List<String> buscarPais(@PathVariable String pais) {
		
		List<Autor> autores = repoAutor.findAll();
		
		List<String> resposta = autores.stream()
		.filter(l -> l.getPaisOrigem().equals(pais))
		.map( l -> l.getNome() )
		.collect(Collectors.toList());
		
		return resposta;
		
	}
}
