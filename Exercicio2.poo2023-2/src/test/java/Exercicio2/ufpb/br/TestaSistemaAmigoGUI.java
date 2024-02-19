package Exercicio2.ufpb.br;

import java.util.Scanner;

public class TestaSistemaAmigoGUI {
    public static void main(String[]arg) throws AmigoInexistenteException{
        Scanner sc = new Scanner(System.in);
        SistemaAmigo sistema = new SistemaAmigo();
        System.out.println("Qual a quantidade de participantes? ");
        int qtAmigos = Integer.parseInt(sc.nextLine());
        for(int k=0; k< qtAmigos; k++){
            System.out.println("Digite nome do amigo: ");
            String nome = sc.nextLine();
            System.out.println("Digite o e-mail: ");
            String email = sc.nextLine();
            sistema.cadastraAmigo(nome, email);
        }

        for(int k=0; k<qtAmigos; k++){
            System.out.println("Digite o email da pessoa que vai sotear um amigo: ");
            String email = sc.nextLine();
            System.out.println("Digite e-mail do amigo sorteado dessa pessoa: ");
            String emailSortado = sc.nextLine();

            sistema.configuraAmigoSecretoDe(email, emailSortado);
        }
        System.out.println("Deseja enviar uma mensagem para todos? ");
        String resposta = sc.nextLine();
        if(resposta.equals("sim")){
            System.out.println("Texto: ");
            String texto = sc.nextLine();
            System.out.println("Email rementente: ");
            String emailRemetente = sc.nextLine();
            System.out.println("Envia como anonima? ");
            String anoni = sc.nextLine();
            boolean anonima = false;
            if(anoni.equals("sim")){
                anonima = true;
            }
            sistema.enviaMensagemParaTodos(texto, emailRemetente, anonima);
        }

    }
}

