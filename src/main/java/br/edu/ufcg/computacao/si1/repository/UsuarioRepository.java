package br.edu.ufcg.computacao.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Usuario findByEmail(String email);
}
