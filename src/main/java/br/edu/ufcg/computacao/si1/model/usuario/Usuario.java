package br.edu.ufcg.computacao.si1.model.usuario;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.authority.AuthorityUtils;
import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.enumeration.AvaliacaoEnum;
import br.edu.ufcg.computacao.si1.model.enumeration.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/** Classe para objetos do tipo Usuario, onde serao contidos, valores e metodos para o mesmo.
 * @author Caio Felipe
 */
@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{

	@Id
	@Column(name="usuario_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="nome")
    private String nome;
    
	@Column(name="email", unique= true)
	@NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_EMAIL_VAZIO)
    @Email
    private String email;
	
	@Column(name="senha")
    private String senha;
    
    @Column
    @Enumerated(EnumType.STRING)
    private UsuarioRoleEnum role;
    
    @Column(name="saldo_devedor")
    private double saldoDevedor;
    
    @Column(name="saldo_credor")
    private double saldoCredor;
    
    @Column(name="avaliacao")
    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum avaliacao;
    
    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private List<Anuncio> anuncios;
    
    /**
     * Construtor default
     */
    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
        anuncios = new ArrayList<>();
    }
    
	/**
     * Construtor do objeto
     */
	public Usuario(String nome, String email, String senha, UsuarioRoleEnum tipoDeUsuario/*, double saldoDevedor, double saldoCredor, List<Anuncio> anuncios*/) {
    	super("default", "default", AuthorityUtils.createAuthorityList("USER"));
		//this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.role = tipoDeUsuario;
		this.anuncios = new ArrayList<>();
		//this.anuncios = anuncios;
		//this.saldoCredor = saldoCredor;
		//this.saldoDevedor = saldoDevedor;
	}
    
    /**
     * Metodo para retorno dos anuncios cadastrados pelo usuário
     * @return anuncios - anuncios pertencentes ao usuario.
     */
    public List<Anuncio> getAnuncios() {
		return anuncios;
	}
    
    /**
     * Metodo para alteracao dos anuncios cadastrados pelo usuário
     * @param List<Anuncio> anuncios - Novos anuncios do usuario.
     */
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	/**
	 * Metodo para retorno do id do usuário
	 * @return id - Id do usuario
	 */
	public Long getId() {
        return id;
    }
	
	/**
	 * Metodo para alteracao do id do usuário
	 * @param Long id - Novo id usuario.
	 */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Metodo para retorno do nome do usuário
     * @return nome - Nome do usuario.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo para alteracao do nome do usuário
     * @param String n - Nome do usuario.
     */
    public void setNome(String n) {
        this.nome = n;
    }
    
    /**
     * Metodo para retorno do email do usuário
     * @return email - Email do usuario
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Metodo para alteracao do email do usuário
     * @param String email - novo email do usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Metodo para retorno da senha do usuário
     * @return senha - senha do usuario
     */
    public String getSenha() {
        return senha;
    }
    
    /**
     * Metodo para alteracao da senha do usuário
     * @param String senha - nova senha do usuario
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Metodo para retorno do tipo do usuário
     * @return role - tipo do usuario
     */
    public UsuarioRoleEnum getRoleUsuario() {
        return role;
    }
    
    /**
     * Metodo para alteracao do tipo do usuário
     * @param UsuarioRoleEnunm role - novo tipo do usuario
     */
    public void setRoleUsuario(UsuarioRoleEnum role) {
        this.role = role;
    }
    
    /**
     * Metodo para retorno do saldo devedor do usuário
     * @return saldoDevedor - saldo devedor do usuario
     */
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	
	/**
	 * Metodo para alteracao do saldo devedor do usuário
	 * @param double saldoDevedor - novo saldo devedor do usuario
	 */
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	
	/**
	 * Metodo para retorno do saldo credor do usuário
	 * @return saldoCredor - saldo credor do usuario
	 */
	public double getSaldoCredor() {
		return saldoCredor;
	}
	
	/**
	 * Metodo para alteracao do saldo credor do usuário
	 * @param double saldoCredor - Novo saldo credor do usuario
	 */
	public void setSaldoCredor(double saldoCredor) {
		this.saldoCredor = saldoCredor;
	}
	/**
	 * Metodo para retorno da avaliacao do usuario.
	 * @return AvaliacaoEnum - Avaliacao do usuario.
	 */
	 public AvaliacaoEnum getAvaliacao() {
		 return avaliacao;
	}
	 /**
	  * Metodo para alteracao da avaliacao do usuario
	  * @param AvaliacaoEnum avaliacao - Avaliacao do usuario.
	  */
	 public void setAvaliacao(AvaliacaoEnum avaliacao) {
		 this.avaliacao = avaliacao;
	}
	 /**
	  * Metodo para retorno dos tipos de anuncios disponiveis de acordo com o tipo de usuario.
	  * @return String[] - Tipos de anuncios disponiveis para o usuario criar.
	  */
	public String[] getTiposDeAnunciosDisponiveis() {
		
		String[] tiposDeAnuncio;
		
		if(this.role.equals(UsuarioRoleEnum.USUARIO_FISICO))
			tiposDeAnuncio = StringsConstantes.TIPOS_DE_ANUNCIO_USUARIO_FISICO;
		else
			tiposDeAnuncio = StringsConstantes.TIPOS_DE_ANUNCIO_USUARIO_JURIDICO;
		
		return tiposDeAnuncio;
	}
}