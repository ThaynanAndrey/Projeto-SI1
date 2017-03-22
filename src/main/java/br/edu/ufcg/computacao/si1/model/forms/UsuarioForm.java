package br.edu.ufcg.computacao.si1.model.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Classe para objetos do tipo UsuarioForm, representando um formulario do usuario,
 *  onde serao contidos, valores e metodos para o mesmo.
 * @author Caio Felipe
 */
public class UsuarioForm {
	
    @NotNull(message = "O nome não pode ser nulo.")
    @NotEmpty(message = "O nome não pode ser vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;
    
    @NotEmpty(message = "O email não pode ser vazio.")
    @Email
    private String email;
    
    @NotNull(message = "A senha não pode ser nula.")
    @NotEmpty
    @Size(min = 4, max = 16, message = "A senha deve ter entre 4 e 16 caracteres.")
    private String senha;
    
    @NotNull
    private Integer role;

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
     * @param String email - email do usuario.
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
     * @param String senha - senha do usuario
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Metodo para retorno do tipo do usuário
     * @return role - tipo do usuario
     */
    public Integer getRole() {
        return role;
    }
    
    /**
     * Metodo para alteracao do tipo do usuário
     * @param Integer role - tipo do usuario
     */
    public void setRole(Integer role) {
        this.role = role;
    }
}
