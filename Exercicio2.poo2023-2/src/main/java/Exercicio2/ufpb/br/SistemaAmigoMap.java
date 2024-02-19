package Exercicio2.ufpb.br;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class SistemaAmigoMap {

    private Map<String, Mensagem> mensagens;
    private Map<String, Amigo> amigos;
    public SistemaAmigoMap(){
        this.mensagens = new HashMap<>();
        this.amigos = new HashMap<>();
    }

    public void cadastrarAmigo(String nomeAmigo, String emailAmigo){
        Amigo a = new Amigo(nomeAmigo, emailAmigo);
        if(this.amigos.containsKey(a.getEmail())){
            System.out.println("Esse amigo já existe cadastrado");
        }else{
            this.amigos.put(a.getEmail(), a);
        }
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
        if(this.amigos.containsKey(emailDaPessoa)){
            Amigo a = this.amigos.get(emailDaPessoa);
            a.setEmailAmigoSorteado(emailAmigoSorteado);
        }else{
            throw new AmigoInexistenteException("Email não encontrado");
        }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
        for(Amigo a: amigos.values()){
            if(a.getEmail().equals(emailDaPessoa)){
                if(a.getEmailAmigoSorteado() == null){
                    throw new AmigoNaoSorteadoException("Não possui amigo secreto, tente cadastrar");
                }else{
                    return a.getEmailAmigoSorteado();
                }
            }
        }
        throw new AmigoInexistenteException("Email inexistente, tente novamente ou cadastre seu amigo secreto!");
    }

    public Amigo pesquisarAmigo(String emailAmigo) throws AmigoInexistenteException{
        Amigo amigo = new Amigo("","");
        boolean achou = false;
        for (Amigo a: amigos.values()) {
            if(a.getEmail().equals(emailAmigo)){
                amigo.setNome(a.getNome());
                amigo.setEmail(a.getEmail());
                achou = true;
            }
        }
        if(!achou){
            throw new AmigoInexistenteException("Amigo nao encontrado");
        }
        return amigo;
    }
    public List<Mensagem> pesquisaMensagensAnonimas(){
        List<Mensagem> msgAnonima = new ArrayList<>();
        for(Mensagem m: mensagens.values()){
            if(m.ehAnonima()){
                msgAnonima.add(m);
            }
        }
        return msgAnonima;
    }

    public List<Mensagem> pesquisaTodasAsMensagens(){
        List<Mensagem> todasMensagens = new LinkedList<>();
        for(Mensagem m: mensagens.values()) {
            if (m != null) {
                todasMensagens.add(m);
            }
        }
        return todasMensagens;
    }//Fim do metodo
    public void enviaMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima){
        mensagens.put(emailRemetente, new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, anonima ));
    }
    public void enviaMensagemParaTodos(String texto, String emailRemetente, boolean anonima){
        mensagens.put(emailRemetente, new MensagemParaTodos(texto, emailRemetente, anonima));
    }

}
