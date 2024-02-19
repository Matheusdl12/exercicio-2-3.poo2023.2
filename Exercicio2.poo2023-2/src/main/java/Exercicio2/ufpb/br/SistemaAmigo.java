package Exercicio2.ufpb.br;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo(){
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        Amigo amigo = new Amigo("","");
        boolean achou = false;
        for(Amigo a: amigos){
            if(a.getEmail().equals(emailAmigo)){
                amigo.setNome(a.getNome());
                amigo.setEmail(a.getEmail());
                achou = true;
            }
        }
        if(!achou){
            throw new AmigoInexistenteException("Amigo não encontrado");
        }return amigo;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo){
        Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
        amigos.add(amigo);
    }

    public List<Mensagem> pesquisaMensagensAnonimas(){
        List<Mensagem> MensagensAnonimas = new ArrayList<>();
        for (Mensagem mensagens : mensagens){
            if(mensagens.ehAnonima()){
                MensagensAnonimas.add(mensagens);
            }
        }return MensagensAnonimas;
    }

    public List<Mensagem> pesquisaTodasAsMensagens(){
        return this.mensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
        boolean configurou = false;
        for(Amigo amigos: amigos){
            if(amigos.getEmail().equalsIgnoreCase(emailDaPessoa)){
                amigos.setEmailAmigoSorteado(emailAmigoSorteado);
                configurou = true;
                break;
            }
        }
        if(!configurou){
            throw new AmigoInexistenteException("Email Inexistente");
        }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
        for(Amigo amigos: amigos){
            if(amigos.getEmail().equals(emailDaPessoa)){
                if(amigos.getEmailAmigoSorteado() != null){
                    return amigos.getEmailAmigoSorteado();
                }else{
                    throw new AmigoNaoSorteadoException("Não possui amigo sorteado");
                }
            }
        }
        throw  new AmigoInexistenteException("Email inexistente. Tente novamente ou cadastre uma pessoa");
    }

    public void enviaMensagemParaAlguem(String texto,String emailRemetente, String emailDestinatario, boolean anonima){
        mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, anonima));
    }

    public void enviaMensagemParaTodos(String texto, String emailRemetente, boolean anonima){
        mensagens.add(new MensagemParaTodos(texto, emailRemetente,anonima));
    }


}
