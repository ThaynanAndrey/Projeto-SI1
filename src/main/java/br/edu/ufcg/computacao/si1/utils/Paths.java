package br.edu.ufcg.computacao.si1.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe para alocacao de paths, ou urls, como constantes.
 */
public class Paths {
	public Paths(){}
	
	public static final String PATH_CADASTRAR_ANUNCIO_USUARIO = "/user/cadastrar/anuncio";
	public static final String PATH_LISTAR_ANUNCIOS_DE_USUARIO = "/user/listar/anuncios";
	public static final String PATH_RETORNAR_USUARIO_LOGADO = "/usuarioLogado";
	public static final String cadastrarAnuncioCompanhiaPath = "/company/cadastrar/anuncio";
	public static final String listarAnunciosDeCompanhiaPath = "/company/listar/anuncios";

	public static final String PATH_CADASTRO_DE_USUARIO = "/cadastrar-se";
	
	public static final String PATH_NAO_ENCONTRADO_ERRO = "/404";
	public static final String PATH_PROIBIDO_ERRO = "/403";
	public static final String PATH_ERRO_INTERNO_DE_SERVIDOR = "/500";

	public static final String PATH_INDEX_PRINCIPAL = "/";
	public static final String PATH_LOGIN = "/login";
	public static final String PATH_INDEX_USUARIO = "/user";
	public static final String PATH_INDEX_COMPANHIA = "/company";
	
	private static final String ERRO = "error";
	
	public static final String PATH_404 = "/404";
	public static final String PATH_403 = "/403";
	public static final String PATH_500 = "/500";
	
	public static final String PATH_ERRO_404 = ERRO+PATH_404;
	public static final String PATH_ERRO_403 = ERRO+PATH_403;
	public static final String PATH_ERRO_500 = ERRO+PATH_500;
	
}
