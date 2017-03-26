//package br.edu.ufcg.computacao.si1.service;
//
//import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
//import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioImovel;
//import br.edu.ufcg.computacao.si1.model.forms.AnuncioForm;
//import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
//import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//import static junit.framework.TestCase.*;
//
///**
// * Created by Marcus Oliveira on 28/12/16.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AnuncioServiceTest {
//
//    @Autowired
//    private AnuncioServiceImpl anuncioService;
//
//    @Autowired
//    private AnuncioRepository anuncioRepository;
//
//    private AnuncioForm anuncio1, anuncio2, anuncio3;
//
//    private Usuario dono1;
//
//    @Before
//    public void setUp() {
//    	long dateDefault =123;
//    	dono1 = new Usuario();
//    	lo
//        anuncio1 = new AnuncioForm();
//        anuncio1.setId(0l);
//        anuncio1.setTipo("movel");
//        anuncio1.setQuantia(10);
//        anuncio1.setTitulo("Anuncio movel");
//        anuncio2 =new AnuncioForm();
//        anuncio2.setId(0l);
//        anuncio2.setTipo("i");
//        anuncio2.setQuantia(10);
//        anuncio2.setTitulo("Anuncio imovel");
//        anuncio3 = new AnuncioForm();
//        anuncio3.setId(0l);
//        anuncio3.setTipo("emprego");
//        anuncio3.setQuantia(10);
//        anuncio3.setTitulo("Anuncio emprego");
//    }
//
//    @After
//    public void tearDown() {
//        anuncioRepository.deleteAll();
//    }
//
//    @Test
//    public void testInicializacao() {
//        assertNotNull("AnuncioService não foi instânciado corretamente", anuncioService);
//        assertTrue("AnuncioService não deveria conter nenhum item", anuncioService.obterTodasEntidadesCadastradas().isEmpty());
//    }
//
//
//    @Test
//    public void testCreateAd() {
//        Anuncio anuncio1FromDB = anuncioService.criarNovaEntidade(anuncio1);
//        Anuncio anuncio2FromDB = anuncioService.criarNovaEntidade(anuncio2);
//        Anuncio anuncio3FromDB = anuncioService.criarNovaEntidade(anuncio3);
//
//        assertNotNull(anuncio1FromDB);
//        assertNotNull(anuncio2FromDB);
//        assertNotNull(anuncio3FromDB);
//
//        assertTrue(anuncioRepository.exists(anuncio1FromDB.get_id()));
//        assertTrue(anuncioRepository.exists(anuncio2FromDB.get_id()));
//        assertTrue(anuncioRepository.exists(anuncio3FromDB.get_id()));
//
//        assertEquals(anuncio1FromDB, anuncio1);
//        assertEquals(anuncio2FromDB, anuncio2);
//        assertEquals(anuncio3FromDB, anuncio3);
//
//        assertEquals(anuncio1FromDB, anuncioService.obterEntidadePorId(anuncio1FromDB.get_id()).get());
//        assertEquals(anuncio2FromDB, anuncioService.obterEntidadePorId(anuncio2FromDB.get_id()).get());
//        assertEquals(anuncio3FromDB, anuncioService.obterEntidadePorId(anuncio3FromDB.get_id()).get());
//    }
//
//    @Test
//    public void testGetPorTipo() {
//
//        int QTDE_ANUNCIOS_ESPERADA = 1;
//
//        Anuncio anuncioMovel = anuncioService.criarNovaEntidade(anuncio1);
//        Anuncio anuncioImovel = anuncioService.criarNovaEntidade(anuncio2);
//        Anuncio anuncioEmprego = anuncioService.criarNovaEntidade(anuncio3);
//
//        assertNotNull(anuncioMovel);
//        assertNotNull(anuncioImovel);
//        assertNotNull(anuncioEmprego);
//
//        assertEquals(anuncioMovel.getTipo(), "movel");
//        assertEquals(anuncioImovel.getTipo(), "imovel");
//        assertEquals(anuncioEmprego.getTipo(), "emprego");
//
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterAnuncioPorTipo("movel").size());
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterAnuncioPorTipo("imovel").size());
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterAnuncioPorTipo("emprego").size());
//
//        assertTrue(anuncioService.obterAnuncioPorTipo("movel").contains(anuncioMovel));
//        assertTrue(anuncioService.obterAnuncioPorTipo("imovel").contains(anuncioImovel));
//        assertTrue(anuncioService.obterAnuncioPorTipo("emprego").contains(anuncioEmprego));
//    }
//
//    @Test
//    public void testGetAll() {
//        int QTDE_ANUNCIOS_ESPERADA = 3;
//
//        Anuncio anuncioMovel = anuncioService.criarNovaEntidade(anuncio1);
//        Anuncio anuncioImovel = anuncioService.criarNovaEntidade(anuncio2);
//        Anuncio anuncioEmprego = anuncioService.criarNovaEntidade(anuncio3);
//
//        assertNotNull(anuncioMovel);
//        assertNotNull(anuncioImovel);
//        assertNotNull(anuncioEmprego);
//
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterTodasEntidadesCadastradas().size());
//
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioMovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioImovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioEmprego));
//
//    }
//
//    @Test
//    public void testDelete() {
//        int QTDE_ANUNCIOS_ESPERADA = 3;
//
//        Anuncio anuncioMovel = anuncioService.criarNovaEntidade(anuncio1);
//        Anuncio anuncioImovel = anuncioService.criarNovaEntidade(anuncio2);
//        Anuncio anuncioEmprego = anuncioService.criarNovaEntidade(anuncio3);
//
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterTodasEntidadesCadastradas().size());
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioMovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioImovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioEmprego));
//
//        assertTrue(anuncioService.deletarEntidade(anuncioMovel.get_id()));
//        QTDE_ANUNCIOS_ESPERADA-=1;
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterTodasEntidadesCadastradas().size());
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioMovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioImovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioEmprego));
//
//        assertTrue(anuncioService.deletarEntidade(anuncioImovel.get_id()));
//        QTDE_ANUNCIOS_ESPERADA-=1;
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterTodasEntidadesCadastradas().size());
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioMovel));
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioImovel));
//        assertTrue(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioEmprego));
//
//        assertTrue(anuncioService.deletarEntidade(anuncioEmprego.get_id()));
//        QTDE_ANUNCIOS_ESPERADA-=1;
//        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.obterTodasEntidadesCadastradas().size());
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioMovel));
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioImovel));
//        assertFalse(anuncioService.obterTodasEntidadesCadastradas().contains(anuncioEmprego));
//
//        assertEquals(0, QTDE_ANUNCIOS_ESPERADA);
//
//        assertFalse(anuncioService.deletarEntidade(anuncioMovel.get_id()));
//        assertFalse(anuncioService.deletarEntidade(anuncioImovel.get_id()));
//        assertFalse(anuncioService.deletarEntidade(anuncioEmprego.get_id()));
//    }
//
//    @Test
//    public void testUpdate() {
//
//        String SUFIXO = " editado";
//
//        Anuncio anuncioMovel = anuncioService.criarNovaEntidade(anuncio1);
//        Anuncio anuncioImovel = anuncioService.criarNovaEntidade(anuncio2);
//        Anuncio anuncioEmprego = anuncioService.criarNovaEntidade(anuncio3);
//
//        assertEquals(anuncioMovel, anuncio1);
//        assertEquals(anuncioImovel, anuncio2);
//        assertEquals(anuncioEmprego, anuncio3);
//
//        //Update Titulo
//        anuncioMovel.setTitulo(anuncioMovel.getTitulo()+SUFIXO);
//        anuncioImovel.setTitulo(anuncioImovel.getTitulo()+SUFIXO);
//        anuncioEmprego.setTitulo(anuncioEmprego.getTitulo()+SUFIXO);
//
//        assertTrue(anuncioService.atualizarEntidade(anuncioMovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioImovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioEmprego));
//
//        assertEquals(anuncioMovel.getTitulo(), anuncioService.obterEntidadePorId(anuncioMovel.get_id()).get().getTitulo());
//        assertEquals(anuncioImovel.getTitulo(), anuncioService.obterEntidadePorId(anuncioImovel.get_id()).get().getTitulo());
//        assertEquals(anuncioEmprego.getTitulo(), anuncioService.obterEntidadePorId(anuncioEmprego.get_id()).get().getTitulo());
//
//        //Update preço
//        anuncioMovel.setQuantia(anuncioMovel.getQuantia()*2);
//        anuncioImovel.setQuantia(anuncioImovel.getQuantia()*2);
//        anuncioEmprego.setQuantia(anuncioEmprego.getQuantia()*2);
//
//        assertTrue(anuncioService.atualizarEntidade(anuncioMovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioImovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioEmprego));
//
//        assertEquals(anuncioMovel.getQuantia(), anuncioService.obterEntidadePorId(anuncioMovel.get_id()).get().getQuantia());
//        assertEquals(anuncioImovel.getQuantia(), anuncioService.obterEntidadePorId(anuncioImovel.get_id()).get().getQuantia());
//        assertEquals(anuncioEmprego.getQuantia(), anuncioService.obterEntidadePorId(anuncioEmprego.get_id()).get().getQuantia());
//
//        assertTrue(anuncioService.atualizarEntidade(anuncioMovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioImovel));
//        assertTrue(anuncioService.atualizarEntidade(anuncioEmprego));
//
//    }
//
//}
