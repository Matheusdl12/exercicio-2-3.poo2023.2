package Exercicio2.ufpb.br;

import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String [] args){
        SistemaAmigo sistema = new SistemaAmigo();
        sistema.cadastraAmigo("José", "jose@email.com");
        sistema.cadastraAmigo("Maria","maria@email.com");
        try{
            sistema.configuraAmigoSecretoDe("jose@email.com","maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com","jose@email.com");
            sistema.enviaMensagemParaAlguem("Oi José","maria@email.com","jose@email.com",true);
            sistema.enviaMensagemParaTodos("Oi povo","maria@email.com",true);
            List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
            for(Mensagem m: mensagensAnonimas){
                System.out.println(m.getTextoCompletoAExebir());
            }
            String emailAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@email.com");
            if(emailAmigoDeJose.equals("maria@email.com")){
                System.out.println("OK");
            }
        }catch(AmigoInexistenteException e){
            System.out.println("Exceção estranha foi lançada");
            e.printStackTrace();
        }catch (AmigoNaoSorteadoException e){
            System.out.println("Exceção estranha foi lançada");
            e.printStackTrace();
        }
    }
}
