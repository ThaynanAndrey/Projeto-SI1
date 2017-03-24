package br.edu.ufcg.computacao.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;

/**
 * Interface que herda da JpaRepository, sendo responsável
 * por operções sobre Anúncios no banco de dados
 * 
 * @author Thaynan Andrey
 * 
 */
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}