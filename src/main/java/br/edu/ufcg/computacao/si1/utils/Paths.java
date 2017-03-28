package br.edu.ufcg.computacao.si1.utils;

/**
 * Classe para declarar paths constantes, utilizados pelos controllers e segurança.
 * 
 * @author Thaynan Andrey
 * @author Giuseppe Mongiovi
 */
public class Paths {
	public Paths(){}

	//--------------------LOGIN E ROTAS GERAIS---------------------------//
	
	public static final String PATH_RAIZ = "/";
	public static final String PATH_LOGIN = "/login";
	public static final String PATH_LOGIN_ERRO = "/login?error";
	public static final String PATH_LOGOUT = "/logout";
	public static final String PATH_USUARIO = "/usuario";
	
	public static final String PATH_TODOS_CAMINHOS_USUARIO = PATH_USUARIO + "/**";
	
	
	//------------------------ANUNCIO_CTRL--------------------------------//
	
	public static final String PATH_CADASTRAR_ANUNCIO_USUARIO = PATH_USUARIO + "/cadastrar/anuncio";
	public static final String PATH_LISTAR_TODOS_ANUNCIOS = PATH_USUARIO + "/listar/anuncios";
	public static final String PATH_RETORNAR_USUARIO_LOGADO = PATH_USUARIO + "/usuarioLogado";
	public static final String PATH_LISTAR_ANUNCIOS_PARA_COMPRAR = PATH_USUARIO + "/listar/anuncios/comprar";
	public static final String PATH_DONO_DO_ANUNCIO_POR_ID = PATH_USUARIO + "/dono/anuncio/{id}";
	public static final String PATH_LISTAR_ANUNCIOS_DE_USUARIO_LOGADO = PATH_USUARIO + "/logado/anuncios";
	public static final String PATH_LISTAR_TIPOS_ANUNCIOS_USUARIO_LOGADO_PODE_CADASTRAR = PATH_USUARIO + "/anuncios/tipos/cadastrar";
	public static final String PATH_BUSCAR_ANUNCIO_POR_ID = PATH_USUARIO + "/anuncio/{id}";
	public static final String PATH_USUARIO_COMPRA_ANUNCIO = PATH_USUARIO + "/comprar/anuncio";
	public static final String PATH_USUARIO_EDITAR_ANUNCIO = PATH_USUARIO + "/editar/anuncio";
	public static final String PATH_USUARIO_DELETAR_ANUNCIO_POR_ID = PATH_USUARIO + "/deletar/anuncio/{id}";
	
	
	//-----------------------NOTIFICACAO_CTRL---------------------------------//
	
	public static final String PATH_CADASTRAR_NOTIFICACAO_USUARIO = PATH_USUARIO + "/cadastrar/notificacao";
	public static final String PATH_LISTAR_NOTIFICACOES = PATH_USUARIO + "/listar/notificacao";
	public static final String PATH_APAGAR_NOTIFICACAO_DE_USUARIO = PATH_USUARIO + "/deletar/notificacao/{id}";
	public static final String PATH_LISTAR_NOTIFICACOES_DE_USUARIO_LOGADO = PATH_USUARIO + "/listar/minhas/notificacao";

	
	//-----------------------USUARIO_CTRL------------------------------------//
	
	public static final String PATH_TODOS_USUARIOS_CADASTRADOS = PATH_USUARIO + "/todos/usuarios";
	public static final String PATH_OBTER_USUARIO_POR_ID = PATH_USUARIO + PATH_USUARIO + "/id/{id}";
	public static final String PATH_CADASTRO_DE_USUARIO = "/cadastrar-se";
	
	
	//-----------------------PAGINAS_DE_ERRO_CTRL---------------------------//
	
	private static final String PATH_ERRO = "/error";
	public static final String PATH_404 = "/404";
	public static final String PATH_403 = "/403";
	public static final String PATH_500 = "/500";
	
	public static final String PATH_ERRO_404 = PATH_ERRO + PATH_404;
	public static final String PATH_ERRO_403 = PATH_ERRO + PATH_403;
	public static final String PATH_ERRO_500 = PATH_ERRO + PATH_500;
	
}