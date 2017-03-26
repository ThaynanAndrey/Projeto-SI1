package br.edu.ufcg.computacao.model.anuncio;

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
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;



/**
 * Created by Caio Felipe on 26/03/16.
 */
public class AnuncioTest {

	private Anuncio anuncio1,anuncio2,anuncio3;

	private Usuario dono1,dono2;
    @Before
    public void setUp() {
    	dono1 = new Usuario();
    	dono2 = new Usuario();
    	anuncio1 = new AnuncioMovel("AnuncioMovel",100d,dono1,0l,30);
    	anuncio2 = new AnuncioImovel("AnuncioImovel",50d,dono2,10l,60);
    	anuncio3 = new AnuncioEmprego("AnuncioEmprego",150d,dono1,0l,90);

    }
   
    @Test
    public void testModelClass(){
    		assertEquals("AnuncioMovel",anuncio1.getTitulo());
    		assertEquals("AnuncioImovel",anuncio2.getTitulo());
    		assertEquals("AnuncioEmprego",anuncio3.getTitulo());
    		
    		assertEquals(30,anuncio1.getDiasDeVidaUtil());
    		assertEquals(60,anuncio2.getDiasDeVidaUtil());
    		assertEquals(90,anuncio3.getDiasDeVidaUtil());

    		
    		

    	
    }
	
}
