package br.edu.ufcg.computacao.si1.service;


import br.edu.ufcg.computacao.si1.model.forms.UsuarioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static junit.framework.TestCase.*;

/**
 * Created by Caio Felipe on 26/03/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioForm usuarioForm1, usuarioForm2, usuarioForm3;

    @Before
    public void setUp() {

        usuarioForm1 = new UsuarioForm();
        usuarioForm1.setEmail("caio@email.com");
        usuarioForm1.setNome("Caio");
        usuarioForm1.setRole(1);
        usuarioForm1.setSenha("caio");
        
        usuarioForm2 = new UsuarioForm();
        usuarioForm2.setEmail("pepe@email.com");
        usuarioForm2.setNome("Pepe");
        usuarioForm2.setRole(2);
        usuarioForm2.setSenha("pepe");
        
        usuarioForm3 = new UsuarioForm();
        usuarioForm3.setEmail("thaynan@email.com");
        usuarioForm3.setNome("thaynan");
        usuarioForm3.setRole(2);
        usuarioForm3.setSenha("thaynan");
    }

    @After
    public void tearDown() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void testInicializacao() {
        assertNotNull("AnuncioService não foi instânciado corretamente", usuarioService);
        assertTrue("AnuncioService não deveria conter nenhum item", usuarioService.obterTodasEntidadesCadastradas().isEmpty());
    }


    @Test
    public void testCreateAd() {
        Usuario usuario1FromDB = usuarioService.criarNovaEntidade(usuarioForm1);
        Usuario usuario2FromDB = usuarioService.criarNovaEntidade(usuarioForm2);
        Usuario usuario3FromDB = usuarioService.criarNovaEntidade(usuarioForm3);

        assertNotNull(usuario1FromDB);
        assertNotNull(usuario2FromDB);
        assertNotNull(usuario3FromDB);

        assertTrue(usuarioRepository.exists(usuario1FromDB.getId()));
        assertTrue(usuarioRepository.exists(usuario2FromDB.getId()));
        assertTrue(usuarioRepository.exists(usuario3FromDB.getId()));

        assertEquals(usuario1FromDB.getEmail(), usuarioForm1.getEmail());
        assertEquals(usuario2FromDB.getEmail(), usuarioForm2.getEmail());
        assertEquals(usuario3FromDB.getEmail(), usuarioForm3.getEmail());
        
        assertEquals(usuario1FromDB.getNome(), usuarioForm1.getNome());
        assertEquals(usuario2FromDB.getNome(), usuarioForm2.getNome());
        assertEquals(usuario3FromDB.getNome(), usuarioForm3.getNome());
        
        assertEquals(usuario1FromDB, usuarioService.obterEntidadePorId(usuario1FromDB.getId()).get());
        assertEquals(usuario2FromDB, usuarioService.obterEntidadePorId(usuario2FromDB.getId()).get());
        assertEquals(usuario3FromDB, usuarioService.obterEntidadePorId(usuario3FromDB.getId()).get());
    }
    @Test
    public void testGetAll() {
        int QTDE_USUARIOS_ESPERADA = 3;

        Usuario usuario1FromDB = usuarioService.criarNovaEntidade(usuarioForm1);
        Usuario usuario2FromDB = usuarioService.criarNovaEntidade(usuarioForm2);
        Usuario usuario3FromDB = usuarioService.criarNovaEntidade(usuarioForm3);


        assertNotNull(usuario1FromDB);
        assertNotNull(usuario2FromDB);
        assertNotNull(usuario3FromDB);

        assertEquals(QTDE_USUARIOS_ESPERADA, usuarioService.obterTodasEntidadesCadastradas().size());

        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario1FromDB));
        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario2FromDB));
        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario3FromDB));

    }
    @Test
    public void testDelete() {
        int QTDE_USUARIOS_ESPERADA = 3;

        Usuario usuario1FromDB = usuarioService.criarNovaEntidade(usuarioForm1);
        Usuario usuario2FromDB = usuarioService.criarNovaEntidade(usuarioForm2);
        Usuario usuario3FromDB = usuarioService.criarNovaEntidade(usuarioForm3);

        assertEquals(QTDE_USUARIOS_ESPERADA, usuarioService.obterTodasEntidadesCadastradas().size());
        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario1FromDB));
        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario2FromDB));
        assertTrue(usuarioService.obterTodasEntidadesCadastradas().contains(usuario3FromDB));

        assertTrue(usuarioService.deletarEntidade(usuario1FromDB.getId()));
        QTDE_USUARIOS_ESPERADA-=1;
        assertEquals(QTDE_USUARIOS_ESPERADA, usuarioService.obterTodasEntidadesCadastradas().size());


        assertTrue(usuarioService.deletarEntidade(usuario2FromDB.getId()));
        QTDE_USUARIOS_ESPERADA-=1;
        assertEquals(QTDE_USUARIOS_ESPERADA, usuarioService.obterTodasEntidadesCadastradas().size());

        assertTrue(usuarioService.deletarEntidade(usuario3FromDB.getId()));
        QTDE_USUARIOS_ESPERADA-=1;
        assertEquals(QTDE_USUARIOS_ESPERADA, usuarioService.obterTodasEntidadesCadastradas().size());
        assertEquals(0, QTDE_USUARIOS_ESPERADA);

        assertFalse(usuarioService.deletarEntidade(usuario1FromDB.getId()));
        assertFalse(usuarioService.deletarEntidade(usuario2FromDB.getId()));
        assertFalse(usuarioService.deletarEntidade(usuario3FromDB.getId()));
    }
    @Test
    public void testUpdate() {

        String SUFIXO = " editado";

        Usuario usuario1FromDB = usuarioService.criarNovaEntidade(usuarioForm1);
        Usuario usuario2FromDB = usuarioService.criarNovaEntidade(usuarioForm2);
        Usuario usuario3FromDB = usuarioService.criarNovaEntidade(usuarioForm3);

        assertEquals(usuario1FromDB.getEmail(), usuarioForm1.getEmail());
        assertEquals(usuario2FromDB.getEmail(), usuarioForm2.getEmail());
        assertEquals(usuario3FromDB.getEmail(), usuarioForm3.getEmail());

        //Update Nome
        usuario1FromDB.setNome(usuario1FromDB.getNome()+SUFIXO);
        usuario2FromDB.setNome(usuario1FromDB.getNome()+SUFIXO);
        usuario3FromDB.setNome(usuario1FromDB.getNome()+SUFIXO);

        assertTrue(usuarioService.atualizarEntidade(usuario1FromDB));
        assertTrue(usuarioService.atualizarEntidade(usuario2FromDB));
        assertTrue(usuarioService.atualizarEntidade(usuario3FromDB));

        assertEquals(usuario1FromDB.getNome(), usuarioService.obterEntidadePorId(usuario1FromDB.getId()).get().getNome());
        assertEquals(usuario2FromDB.getNome(), usuarioService.obterEntidadePorId(usuario2FromDB.getId()).get().getNome());
        assertEquals(usuario3FromDB.getNome(), usuarioService.obterEntidadePorId(usuario3FromDB.getId()).get().getNome());

    }
}
