/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe que dispara o jogo
  */

package projeto1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerenciador {
    
    public static void main(String[] args) {
        //variaveis
        String nome1;
        String nome2;
        int cor1 = 0;
        int cor2 = 0;
        int op = 0;
        
        //scanner
        Scanner scanner = new Scanner(System.in);
        
        //menu de opção
        System.out.println("=========================================");
        System.out.println("SEJA BEM-VINDO");
        System.out.println("=========================================");
        System.out.println("Escolha uma das opções: ");
        System.out.println("1- Começar novo jogo");
        System.out.println("2- Carregar jogo anterior");
        
        //lendo opcao do jogador entre comecar novo jogo e carregar um
        do{
            int j = 0;
            while(j == 0){
                try {      
                    op = scanner.nextInt();
                    if(op!=1 && op!=2){
                        System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                    }
                    j++;
                } catch (InputMismatchException e) { 
                    System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                    scanner.next();
                }
            }
        }while(op!=1 && op!=2);
        //se for 2 eu carrego um jogo
        if(op == 2){
            Jogo novoJogo = new Jogo();
            //carrega os dados do txt
            if(novoJogo.carregarJogo()){
                System.out.println("=========================================");
                //mostra o ultimo estado do jogo
                novoJogo.statusJogo();
                //continua executando o jogo
                novoJogo.executarJogo();
            }else{
                op = 1;
            }
        }        
        //se for 1, comeco um novo jogo, pegando informações do jogador, caso contrario carrego dados
        if(op == 1){
            //pega as informações do jogador 1
            do{
                System.out.println("Jogador 1, digite seu nome: ");
                nome1 = scanner.next();
                if(nome1.length() < 3){
                    System.out.println("O seu nome precisa ter pelo menos 3 caracteres");
                }
            }while(nome1.length() < 3);
            System.out.println("Agora escolha a cor das suas peças: ");
            System.out.println("1- Branca");
            System.out.println("2- Preta");
            do{
                int i = 0;
                while(i == 0){
                    try {      
                        cor1 = scanner.nextInt();
                        if(cor1!=1 && cor1!=2){
                            System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                        }
                        i++;
                    } catch (InputMismatchException e) { 
                        System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                        scanner.next();
                    }
                }
            }while(cor1!=1 && cor1!=2);
            //pega as informações do jogador 2
            do{
                System.out.println("Jogador 2, digite seu nome: ");
                nome2 = scanner.next();
                if(nome2.length() < 3){
                    System.out.println("O seu nome precisa ter pelo menos 3 caracteres");
                }else if(nome2.equals(nome1)){
                    System.out.println("O seu nome não pode ser igual ao do outro jogador");
                }
            }while(nome2.length() < 3 || nome2.equals(nome1));
            if(cor1 == 1){
                System.out.println("A cor das suas peças será Preta");
                cor2 = 2;
            }else if (cor1 == 2){
                System.out.println("A cor das suas peças será Branca");
                cor2 = 1;
            }
            //Cria um objeto para o jogo e inicia um novo jogo
            Jogo novoJogo = new Jogo(nome1, cor1, nome2, cor2); 
            novoJogo.boasVindas();
            novoJogo.executarJogo();
        }
    }
}
