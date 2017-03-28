package br.edu.ufcg.computacao.model.notificacao;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
//import static junit.framework.TestCase.*;

import static junit.framework.TestCase.*;

/**
 * @author Caio Felipe
 * Created by Caio Felipe on 26/03/16.
 */
public class NotificacaoTest {
	Notificacao notificacao1,notificacao2,notificacao3;

	private Usuario dono1,dono2;
    @Before
    public void setUp() {
    	dono1 = new Usuario();
    	dono2 = new Usuario();
    	notificacao1 = new Notificacao("Notificacao",dono1,0l);
    	notificacao2 = new Notificacao("Notificacao2",dono2,10l);
    	notificacao3 = new Notificacao("Notificacao3",dono1,0l);

    }
   
    @Test
    public void testModelClass(){
    	assertEquals("Notificacao",notificacao1.getDescricao());
    	assertEquals("Notificacao2",notificacao2.getDescricao());
    	assertEquals("Notificacao3",notificacao3.getDescricao());
    	
    	notificacao1.setDescricao("novaNotificacao");
    	assertEquals("novaNotificacao",notificacao1.getDescricao());
    	
    }
	
}
