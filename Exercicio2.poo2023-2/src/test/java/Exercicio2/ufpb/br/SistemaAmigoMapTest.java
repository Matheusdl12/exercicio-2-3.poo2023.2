package Exercicio2.ufpb.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaAmigoMapTest {
    SistemaAmigo sistema;

    @BeforeEach
    void setUp()  {
        this.sistema = new SistemaAmigo();
    }


    @Test
    void testSistemaAmigo() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class, ()-> sistema.pesquisaAmigo("ayla@teste"));
    }

    @Test
    void testPesquisaECadastraAmigo() {
        try {
            sistema.pesquisaAmigo("ayla@teste.com");
            fail("Deveria falhar pois não existe ainda");
        } catch (AmigoInexistenteException e) {
            //Ok
        }
        try {
            sistema.cadastraAmigo("ayla", "ayla@teste.com");
            Amigo a = sistema.pesquisaAmigo("ayla@teste.com");
            assertEquals("ayla", a.getNome());
            assertEquals("ayla@teste.com", a.getEmail());
        } catch (AmigoInexistenteException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void testEnviarMensagemParaTodos() throws AmigoInexistenteException {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviaMensagemParaTodos("texto", "ayla@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertTrue(mensagensAchadas.size()==1);
        assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("ayla@dcx.ufpb.br"));
    }

    @Test
    void testEnviarMensagemParaAlguem() throws AmigoInexistenteException {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviaMensagemParaAlguem("texto", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, mensagensAchadas.size());
        assertTrue(mensagensAchadas.get(0) instanceof MensagemParaAlguem);
        assertTrue(mensagensAchadas.get(0).getTexto().equals("texto"));
    }
    @Test
    void testPesquisaMensagensAnonimas() throws AmigoInexistenteException {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviaMensagemParaAlguem("texto 1", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
        sistema.enviaMensagemParaAlguem("texto 2", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaMensagensAnonimas().size()==1);
    }

    @Test
    void testPesquisaTodasAsMensagens() throws AmigoInexistenteException {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviaMensagemParaAlguem("texto 1", "ayla@dcx.ufpb.br", "rodrigor@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==1);
        sistema.enviaMensagemParaAlguem("texto 2", "ayla@dcx.ufpb.br", "rodrigor@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaTodasAsMensagens().size()==2);
    }
    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
        assertThrows(AmigoInexistenteException.class,
                ()-> sistema.pesquisaAmigoSecretoDe("ayla@dcx.ufpb.br"));
        try {
            sistema.cadastraAmigo("Ayla", "ayla@dcx.ufpb.br");
            sistema.cadastraAmigo("Ana", "ana@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("ayla@dcx.ufpb.br", "ana@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("ana@dcx.ufpb.br", "ayla@dcx.ufpb.br");
            assertEquals("ana@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("ayla@dcx.ufpb.br"));
            assertEquals("ayla@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("ana@dcx.ufpb.br"));
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }

}
