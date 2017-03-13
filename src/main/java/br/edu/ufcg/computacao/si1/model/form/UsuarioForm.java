package br.edu.ufcg.computacao.si1.model.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioForm {
    
	private final int qtdMinimaCaracteresNome = 2;
	private final int qtdMaximaCaracteresNome = 10;
	private final int qtdMinimaCaracteresSenha = 4;
	private final int qtdMaximaCaracteresSenha = 16;
	
	@NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_NOME_NULO)
    @NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_NOME_VAZIO)
    @Size(min = qtdMinimaCaracteresNome, max = qtdMaximaCaracteresNome, message = StringsConstantes.MESAGEM_DE_ERRO_QTD_CARACTERES_NOME)
    private String nome;
    
	@NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_EMAIL_VAZIO)
    @Email
    private String email;
	
    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_SENHA_NULA)
    @NotEmpty
    @Size(min = qtdMinimaCaracteresSenha, max = qtdMaximaCaracteresSenha, message = StringsConstantes.MESAGEM_DE_ERRO_SENHA_QTD_CARACTERES)
    private String senha;

    @NotNull
    private Integer role;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}