package br.edu.ufcg.computacao.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;

/**
 * Interface que herda da JpaRepository, sendo responsável
 * por operções sobre Notificações no banco de dados
 * 
 * @author Thaynan Andrey
 * 
 */
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{
	
}
