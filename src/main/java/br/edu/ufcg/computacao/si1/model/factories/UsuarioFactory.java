package br.edu.ufcg.computacao.si1.model.factories;

import br.edu.ufcg.computacao.si1.model.enumerations.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/** Classe para objeto do tipo UsuarioFactory, que implementa o factory method do objeto Usuario.
 * 
 * @author Caio Felipe
 * @author Thaynan Andrey
 */
public class UsuarioFactory {
	
	/** Metodo factory que cria e retorna um objeto do tipo Usuario recebendo as informacoes necessarias para criacao.
	 * @param String tipoDeUsuario -Tipo do usuario
	 * @param String nomeUsuario - Nome do usuario
	 * @param String emailUsuario - Email do usuario
	 * @param String senhaUsuario - Senha do usuario
	 * @return Usuario - Usuario criado pelo factory method.
	 */
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
