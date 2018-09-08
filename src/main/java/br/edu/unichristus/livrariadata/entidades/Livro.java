package br.edu.unichristus.livrariadata.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_LIVRO")
@Data
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long livroID;

	private String titulo;

	private int numeroPaginas;

	private BigDecimal preco;
	
	// Por simplificação, definimos que cada livro só tem um autor 
	// Livro é o lado dominante, deve incluir seu autor
	@ManyToOne
	private Autor autor;

	public Livro(String titulo, int numeroPaginas, BigDecimal preco, Autor autor) {
		super();
		this.titulo = titulo;
		this.numeroPaginas = numeroPaginas;
		this.preco = preco;
		this.autor = autor;
	}

	public Livro() {

	}

	public Long getLivroID() {
		return livroID;
	}

	public void setLivroID(Long livroID) {
		this.livroID = livroID;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Autor getAutor() {
		return autor;
	}

	public String getAutorName() {
		return autor.getNome();
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Livro [livroID=" + livroID + ", titulo=" + titulo + ", numeroPaginas=" + numeroPaginas + ", preco="
				+ preco + ", autor= " + autor.getNome() + "]";
	}
}
