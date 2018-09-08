package br.edu.unichristus.livrariadata;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import antlr.collections.List;
import br.edu.unichristus.livrariadata.controladores.AutorController;
import br.edu.unichristus.livrariadata.controladores.LivroController;
import br.edu.unichristus.livrariadata.entidades.Autor;
import br.edu.unichristus.livrariadata.entidades.Livro;
import br.edu.unichristus.livrariadata.repositorios.AutorRepository;
import br.edu.unichristus.livrariadata.repositorios.LivroRepository;

@SpringBootApplication
public class LivrariaDataApplication implements CommandLineRunner {
	
	@Autowired
	private LivroRepository repoLivros;
	
	@Autowired
	private AutorRepository repoAutores;
	
	@Autowired
	private AutorController autorController;
	
	@Autowired
	private LivroController livroController;
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaDataApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// limpa os repositórios
		repoLivros.deleteAll();
		repoAutores.deleteAll();
		// salva alguns livros e autores
		Autor stephenking = new Autor("Stephen King", "Estados Unidos");
		Autor gillianflynn = new Autor("Gillian Flynn", "Estados Unidos");
		Autor jorgeamado = new Autor("Jorge Amado", "Brasil");
		Autor gracilianoramos = new Autor("Graciliano Ramos", "Brasil");
		Autor conandoyle = new Autor("Arthur Conan Doyle", "Inglaterra");
		
		repoAutores.save(stephenking);
		repoAutores.save(gillianflynn);
		repoAutores.save(jorgeamado);
		repoAutores.save(gracilianoramos);
		repoAutores.save(conandoyle);
		
		repoLivros.save(new Livro("It a Coisa", 200, new BigDecimal(72.90), stephenking));
		repoLivros.save(new Livro("A Torre Negra - O Pistoleiro", 123, new BigDecimal(37.90), stephenking));
		repoLivros.save(new Livro("Objetos Cortantes", 490, new BigDecimal(26.31), gillianflynn));
		repoLivros.save(new Livro("Capitães da Areia", 500, new BigDecimal(35.90), jorgeamado));
		repoLivros.save(new Livro("Dona Flor e Seus Dois Maridos", 350, new BigDecimal(78.50), jorgeamado));
		repoLivros.save(new Livro("Tieta do Agreste", 600, new BigDecimal(90.00), jorgeamado));
		repoLivros.save(new Livro("Vidas Secas", 850, new BigDecimal(190.00), gracilianoramos));
		repoLivros.save(new Livro("Memórias do Cárcere", 320, new BigDecimal(55.00), gracilianoramos));
		repoLivros.save(new Livro("O Mundo Perdido", 110, new BigDecimal(25.00), conandoyle));
		repoLivros.save(new Livro("As Aventuras de Sherlock Holmes", 420, new BigDecimal(124.00), conandoyle));
		repoLivros.save(new Livro("O Cão dos Baskervilles", 210, new BigDecimal(92.00), conandoyle));
		
		// obtêm todos os livros
		System.out.println("obtendo os livros...");
		System.out.println("-------------------------");
		repoLivros.findAll().stream().forEach(System.out::println);
		System.out.println();
		
		// EXERCÍCIO
		
		// 2 - Livros dos autores de um determinado país (somente os títulos de cada livro)
		System.out.println("LIVROS DE AUTORES BRASILEIROS:");
		livroController.buscarPeloPais("Brasil").forEach(System.out::println);
		
		// 3 - Preço médio dos livros cadastrados
		System.out.println();
		System.out.print("PREÇO MÉDIO DOS LIVROS: ");
		System.out.println(livroController.obterPrecoMedio());
		
		
		// 4 - Listar os NOMES dos autores de um determinado país
		System.out.println();
		System.out.println("AUTORES DOS ESTADOS UNIDOS:");
		autorController.buscarPais("Brasil").forEach(System.out::println);
		
		// 6 - Listar autores com seus livros - Listagem contendo nome de cada autor autor e o título dos seus livros
		System.out.println();
		System.out.println("LISTA DE AUTORES COM SEUS RESPECTIVOS LIVROS:");
		
		Map<String, java.util.List<Livro>> agrupadosPorAutor = livroController.buscarTodos().stream().collect(Collectors.groupingBy(Livro::getAutorName));
		
		agrupadosPorAutor.forEach(
				(autor, livros) -> 
				{
					System.out.println(autor);
					livros.forEach(
							l -> System.out.printf("     %s%n", l.getTitulo()));
				}
		);
		
		
	}
}
