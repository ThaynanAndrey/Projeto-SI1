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

    public AvaliacaoEnum getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoEnum avaliacao) {
		this.avaliacao = avaliacao;
	}

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
     * Retorna os anuncios cadastrados pelo usuário
     * @return
     */
    public List<Anuncio> getAnuncios() {
		return anuncios;
	}
    
    /**
     * Define os anuncios cadastrados pelo usuário
     * @param anuncios
     */
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	/**
	 * Retorna o id do usuário
	 * @return
	 */
	public Long getId() {
        return id;
    }
	
	/**
	 * Define o id do usuário
	 * @param id
	 */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retorna o nome do usuário
     * @return
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Define o nome do usuário
     * @param n
     */
    public void setNome(String n) {
        this.nome = n;
    }
    
    /**
     * Retorna o email do usuário
     * @return
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Define o email do usuário
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Retorna a senha do usuário
     * @return
     */
    public String getSenha() {
        return senha;
    }
    
    /**
     * Define a senha do usuário
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Retorna o tipo do usuário
     * @return
     */
    public UsuarioRoleEnum getRoleUsuario() {
        return role;
    }
    
    /**
     * Define o tipo do usuário
     * @param roleUsuario
     */
    public void setRoleUsuario(UsuarioRoleEnum roleUsuario) {
        this.role = roleUsuario;
    }
    
    /**
     * Retorna o saldo devedor do usuário
     * @return
     */
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	
	/**
	 * Define o saldo devedor do usuário
	 * @param saldoDevedor
	 */
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	
	/**
	 * Retorna o saldo credor do usuário
	 * @return
	 */
	public double getSaldoCredor() {
		return saldoCredor;
	}
	
	/**
	 * Define o saldo credor do usuário
	 * @param saldoCredor
	 */
	public void setSaldoCredor(double saldoCredor) {
		this.saldoCredor = saldoCredor;
	}
	
	public String[] getTiposDeAnunciosDisponiveis() {
		
		String[] tiposDeAnuncio;
		
		if(this.role.equals(UsuarioRoleEnum.USUARIO_FISICO))
			tiposDeAnuncio = StringsConstantes.TIPOS_DE_ANUNCIO_USUARIO_FISICO;
		else
			tiposDeAnuncio = StringsConstantes.TIPOS_DE_ANUNCIO_USUARIO_JURIDICO;
		
		return tiposDeAnuncio;
	}
}