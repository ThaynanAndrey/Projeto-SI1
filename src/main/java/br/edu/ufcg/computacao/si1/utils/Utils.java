package br.edu.ufcg.computacao.si1.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Classe auxiliar com implementacao de metodos estaticos para utilizacao no projeto.
 * 
 * @author Thaynan Andrey
 *
 */
public class Utils {
	
	/**
	 * Metodo que retorna o nome do usuario logado
	 * @return String - Nome do usuario logado
	 */
	public static String userNameUsuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return usuarioLogado instanceof UserDetails ? ((UserDetails) usuarioLogado).getUsername()
				: usuarioLogado.toString();
	}

}
