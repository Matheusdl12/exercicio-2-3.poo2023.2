package Exercicio2.ufpb.br;

public class MensagemParaAlguem extends Mensagem{

    private String emaildestintario;

    public MensagemParaAlguem (String texto,String emailRemetente, String emaildestintario, boolean anonima){
        super(texto, emailRemetente, anonima);
        this.emaildestintario = emaildestintario;
    }

    public String getEmaildestintario() {
        return emaildestintario;
    }

    public void setEmaildestintario(String emaildestintario) {
        this.emaildestintario = emaildestintario;
    }

    public String getTextoCompletoAExibir(){
        return "Mensagem de: " +getEmailRemetente()+ "para" +emaildestintario+ "Texto: " +getTexto();
    }
}
