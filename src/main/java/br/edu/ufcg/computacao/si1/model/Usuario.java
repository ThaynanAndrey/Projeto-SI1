package br.edu.ufcg.computacao.si1.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.LinkedList;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column
    private String nome;
    
	@Column(unique= true)
	@NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_EMAIL_VAZIO)
    @Email
    private String email;
	
	@Column
    private String senha;
    
    @Column
    private String role;

    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public Usuario(String nome, String email, String senha, String role) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getR() {
        return role;
    }

    public void setR(String r) {
        this.role = r;
    }
}