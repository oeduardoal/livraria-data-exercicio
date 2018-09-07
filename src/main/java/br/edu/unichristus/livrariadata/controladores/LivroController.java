package br.edu.unichristus.livrariadata.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroRepository repoLivro;
	

	@GetMapping("/todos")
	public List<Livro> buscarTodos() {
		return repoLivro.findAll();
	}

	@GetMapping("/{id}")
	public Livro buscarPeloID(@PathVariable Long id) {
		return repoLivro.findById(id).get();
	}
	
	@GetMapping("/precoMedio")
	public double obterPrecoMedio() {
		
		List<Livro> livro = repoLivro.findAll();
		
		double resposta = livro.stream()
		.mapToDouble(l -> l.getPreco().doubleValue()).average().getAsDouble();
		
		return resposta;
		
	}

	
	@GetMapping("/buscarPeloPais/{pais}")
	public List<String> buscarPeloPais(@PathVariable String pais) {
		
		List<Livro> livro = repoLivro.findAll();
		
		List<String> resposta = livro.stream()
		.filter(l -> l.getAutor().getPaisOrigem().equals(pais))
		.map( l -> l.getTitulo() )
		.collect(Collectors.toList());
		
		return resposta;
	}

	@PostMapping("/novo")
	public Livro salvar(@ModelAttribute Livro livro) {
		return repoLivro.save(livro);
	}

}
