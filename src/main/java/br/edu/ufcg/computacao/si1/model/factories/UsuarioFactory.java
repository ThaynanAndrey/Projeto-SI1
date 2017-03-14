package br.edu.ufcg.computacao.si1.model.factories;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.enumerations.UsuarioRoleEnum;

public class UsuarioFactory {
	
	public Usuario criarUsuario(String tipoDeUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario){

		Usuario usuario;
		
		if(tipoDeUsuario == UsuarioRoleEnum.USUARIO_FISICO.toString()){
			usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, UsuarioRoleEnum.valueOf(tipoDeUsuario));
		}
		
		else if(tipoDeUsuario == UsuarioRoleEnum.USUARIO_JURIDICO.toString()){
			usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, UsuarioRoleEnum.valueOf(tipoDeUsuario));
		}
		else {
			throw new RuntimeException();	
		}
		
		return usuario;
	}
}
