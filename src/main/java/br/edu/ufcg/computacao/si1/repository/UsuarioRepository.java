package br.edu.ufcg.computacao.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Interface que herda da JpaRepository, sendo responsável
 * por operções sobre Usuários no banco de dados
 * 
 * @author Thaynan Andrey
 * 
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	/**
	 * Busca-se por um usuário armazenado no banco de dados que possui determinado email. 
	 * @param email: email do usuário.
	 * @return Usuario: usuario encontrado.
	 */
    Usuario findByEmail(String email);
}