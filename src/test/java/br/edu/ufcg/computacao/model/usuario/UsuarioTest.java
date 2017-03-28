package br.edu.ufcg.computacao.model.usuario;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioEmprego;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioImovel;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioMovel;
import br.edu.ufcg.computacao.si1.model.enumerations.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;



/**
 * @author Caio Felipe
 * Created by Caio Felipe on 26/03/16.
 */
public class UsuarioTest {
	private Usuario usuario1,usuario2,usuario3;

    @Before
    public void setUp() {
    	usuario1 = new Usuario("Caio","caio@email.com","1234",UsuarioRoleEnum.USUARIO_FISICO);
    	usuario2 = new Usuario("Thaynan","thaynan@email.com","1234",UsuarioRoleEnum.USUARIO_JURIDICO);
    	usuario3 = new Usuario("Pepe","pepe@email.com","1234",UsuarioRoleEnum.USUARIO_FISICO);
    }
   
    @Test
    public void testModelClass(){
    	assertEquals("Caio",usuario1.getNome());
    	assertEquals("Thaynan",usuario2.getNome());
    	assertEquals("Pepe",usuario3.getNome());
    	
    	assertEquals("caio@email.com",usuario1.getEmail());
    	assertEquals("thaynan@email.com",usuario2.getEmail());
    	assertEquals("pepe@email.com",usuario3.getEmail());
    	
    	assertEquals(UsuarioRoleEnum.USUARIO_FISICO,usuario1.getRoleUsuario());
    	
    	
    	usuario1.setNome("Jose");
    	assertEquals("Jose",usuario1.getNome());



    }

}
