package Exercicio2.ufpb.br;

public class MensagemParaTodos extends Mensagem{

    public MensagemParaTodos (String texto, String emailRemetente, boolean anonima){
        super(texto, emailRemetente, anonima);
    }

    public String getTextoCompletoAExibir(){
        return getTexto();
    }
}
