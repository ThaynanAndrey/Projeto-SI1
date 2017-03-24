package br.edu.ufcg.computacao.si1.seguranca;

import br.edu.ufcg.computacao.si1.model.enumerations.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Constantes;
import br.edu.ufcg.computacao.si1.utils.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                    .antMatchers(Paths.PATH_RAIZ,Paths.PATH_CADASTRO_DE_USUARIO).permitAll()
                    .antMatchers(Paths.PATH_TODOS_CAMINHOS_USUARIO).hasAnyAuthority(UsuarioRoleEnum.USUARIO_FISICO.toString(), UsuarioRoleEnum.USUARIO_JURIDICO.toString())
                    .anyRequest().authenticated().and()
                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository())	
               .and()
            .formLogin()
                    .loginPage(Paths.PATH_LOGIN).permitAll()
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureUrl(Paths.PATH_LOGIN_ERRO)
                .and()
            .logout()
                    .logoutUrl(Paths.PATH_LOGOUT)
                    .deleteCookies(Constantes.PRECO_RELEMBRAR_USUARIO,"JSESSIONID")
                    .logoutSuccessUrl(Paths.PATH_LOGIN).permitAll()
                .and()
                    .rememberMe();
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
    	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    	  repository.setHeaderName("X-XSRF-TOKEN");
    	  return repository;
    }

    /**
     * TODO colocar para o login ser feito por dados consultados no banco de dados
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email as username,senha as password, true as enabled from tb_usuario where email=?")
                .authoritiesByUsernameQuery(
                        "select email as username, role from tb_usuario where email=?");
    }

    @Bean
    protected UserDetailsService userDetailsService(){
    	
        return new UserDetailsService() {
            @Autowired
            UsuarioServiceImpl usuarioService;
                    
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario usuario = usuarioService.getByEmail(email).get();
                if(usuario != null){
                    return new User(usuario.getEmail(), usuario.getSenha(), true, true, true, true,
                            AuthorityUtils.createAuthorityList(usuario.getRoleUsuario().toString()));
                }else {
                    throw new UsernameNotFoundException(Constantes.MESAGEM_DE_ERRO_USUARIO_NAO_LOCALIZADO + usuario);
                }
            }
        };
    }
}