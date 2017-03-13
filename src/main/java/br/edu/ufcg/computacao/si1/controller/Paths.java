package br.edu.ufcg.computacao.si1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class Paths {
	public Paths(){}
	
	public static final String cadastrarAnuncioUsuarioPath = "/user/cadastrar/anuncio";
	public static final String listarAnunciosDeUsuarioPath = "/user/listar/anuncios";
	public static final String cadastrarAnuncioCompanhiaPath = "/company/cadastrar/anuncio";
	public static final String listarAnunciosDeCompanhiaPath = "/company/listar/anuncios";
	public static final String retornarUsuarioLogado = "/usuarioLogado";

	public static final String cadastroDeUsuarioPath = "/cadastrar-se";
	
	public static final String naoEncontradoErroPath = "/404";
	public static final String proibidoErroPath = "/403";
	public static final String erroInternoDeServidorPath = "/500";

	public static final String indexPrincipalPath = "/";
	public static final String loginPath = "/login";
	public static final String indexUsuarioPath = "/user";
	public static final String indexCompanhiaPath = "/company";
	
}
