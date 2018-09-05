package br.edu.unichristus.livrariadata.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.livrariadata.entidades.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
