package br.edu.ufcg.computacao.si1.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Utils {
	
	public static String userNameUsuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return usuarioLogado instanceof UserDetails ? ((UserDetails) usuarioLogado).getUsername()
				: usuarioLogado.toString();
	}

}
