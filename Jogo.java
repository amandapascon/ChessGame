/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: A classe Jogo é quem gerencia o jogo em si
  *     Possui 2 jogadores e um tabuleiro e as 32 pecas disponiveis, o necessario para um jogo acontecer
  *     Essa classe tambem é a responsavel por saber o estdo do jogo, por exemplo, caso ocorra xeque, a classe deve saber
  */

package projeto1;

import java.util.ArrayList;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jogo {
    private static final int TAMANHO = 8;
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Jogador[] jogadores = new Jogador[2]; 
    private Peca[] pecas = new Peca[32];
    private boolean xeque;
    private boolean xequeMate;
    private boolean semSairXeque;
    private boolean inicio = true;
    private boolean fim = false;
    private char reiXeque = ' ';
    private Jogador vez;
    boolean movInvalido = false;

    public Jogo(String n1, int cor1, String n2, int cor2) {
        this.atribuirPecas();
        this.atribuirJogadores(n1, cor1, n2, cor2);
        //montagem das pecas
        this.tabuleiro.posicaoInicial(pecas);
        //jogador com as pecas brancas comeca
        this.vez = this.jogadores[0];
        //arquivo de jogadas
        this.criarArquivo();
    }   
    
    public Jogo(){
        this.atribuirPecas();
    }
    
    public void setFim(boolean fim) {
        this.fim = fim;
    }
    
    private void atribuirJogadores(String n1, int cor1, String n2, int cor2){
        //jogadores
        if(cor1 == 1){
            this.jogadores[0]= new Jogador(n1, cor1, pecas);
            this.jogadores[1]= new Jogador(n2, cor2, pecas);
        }else if(cor2 == 1){
            this.jogadores[0]= new Jogador(n2, cor2, pecas);
            this.jogadores[1]= new Jogador(n1, cor1, pecas);
        }
    }    
    
    //metodo para criar as pecas
    private void atribuirPecas(){
        //pecas brancas
        this.pecas[0] = new Torre('b');
        this.pecas[1] = new Cavalo('b');
        this.pecas[2] = new Bispo('b');
        this.pecas[3] = new Dama('b');
        this.pecas[4] = new Rei('b');
        this.pecas[5] = new Bispo('b');
        this.pecas[6] = new Cavalo('b');
        this.pecas[7] = new Torre('b');
        for(int i=8; i<16; i++){
            this.pecas[i] = new Peao('b'); 
        }            
        //pecas pretas
        this.pecas[16] = new Torre('p');
        this.pecas[17] = new Cavalo('p');
        this.pecas[18] = new Bispo('p');
        this.pecas[19] = new Dama('p');
        this.pecas[20] = new Rei('p');
        this.pecas[21] = new Bispo('p');
        this.pecas[22] = new Cavalo('p');
        this.pecas[23] = new Torre('p'); 
        for(int i=24; i<32; i++){
            this.pecas[i] = new Peao('p'); 
        }
    }
    
    //funcao para exibir pecas ativas e capturadas e o tabuleiro com as pecas nas posicoes atuais
    public void statusJogo(){
        //exibe dados do jogador 1
        System.out.println(this.jogadores[0].getNome());
        this.jogadores[0].pecasAtivas();
        System.out.println("");
        this.jogadores[0].pecasCapturadas();
        System.out.println("");
        this.divisaoTexto();
        //exibe dados do jogador 2
        System.out.println(this.jogadores[1].getNome());
        this.jogadores[1].pecasAtivas();
        System.out.println("");
        this.jogadores[1].pecasCapturadas();
        System.out.println("");
        this.divisaoTexto();
        //exibe o tabuleiro em tela
        this.tabuleiro.imprimirTabuleiro();
        this.divisaoTexto();
    }
    
    //metodo que faz a checagem do movimento em nivel do tabuleiro
    private boolean movimento(int lo, int co, int ld, int cd){
        if(lo>=0 && lo<=7 && ld>=0 && ld<=7 && co>=0 && co<=7 && cd>=0 && cd<=7){
            if(tabuleiro.checaMovimento(lo, co, ld, cd)){
                return true;
            }    
        }
        return false;
    }
    
    //metodo que exibe ====... em tela
    private void divisaoTexto(){
        System.out.println("=========================================");
    }
    
    //mensagem de boas vindas
    public void boasVindas(){
        this.inicio = false;
        this.divisaoTexto();
        System.out.println("COMEÇANDO NOVO JOGO");
        this.divisaoTexto();
        this.statusJogo();
    }
    
    //exibe de quem é a vez
    private void vezJogador(){
        System.out.println(this.vez.getNome()+", é sua vez!");
    }
    
    //exibe mensagem de movimento invalido
    private void movInvallido(){
        System.out.println("Tentativa de Movimento INVÁLIDO");
        this.divisaoTexto();
    }
    
    //exibe mensagem de xeque
    private void xeque(){
        String cor = "";
        if(this.reiXeque == 'p'){
            cor = "Preto";
        }else if(this.reiXeque == 'b'){
            cor = "Branco";
        }
        System.out.println("Rei "+cor+" em XEQUE");
        this.divisaoTexto();
    }
    
    //metodo que exibe mensagem de xeque mate
    private void xequeMate(){
        this.fim = true;
        this.tabuleiro.imprimirTabuleiro();
        this.divisaoTexto();
        System.out.println("XEQUE MATE");
    }
    
    //metodo responsavel por tentar fazer a jogada do jogador que tem a vez
    private void fazerJogada(){
        Scanner scanner = new Scanner(System.in);
        if(this.xequeMate){
            this.xequeMate();
        }else if(this.xeque && this.movInvalido){
            this.movInvallido();
        }else if(this.xeque){
            //se o rei esta em xeque exibe em tela
            this.xeque();
        }else if(this.movInvalido){
            //se o movimento for invalido exibe em tela
            this.movInvallido();
        }
        //se nao tiver em xeque mate permite o proximo movimento
        if(!this.xequeMate){
            this.vezJogador();
            
            boolean invalido = false;
            
            int lo=0;
            char co_char=0;
            int ld=0;
            char cd_char=0;
            int co=0;
            int cd=0;
              
            boolean ok = false;
            
            while(!ok){
                //le as coordenadas da entrada
                System.out.println("Insira a linha e a coluna de origem de movimento separados por espaço:");
                int i =0;
                do{
                    try{
                        lo = scanner.nextInt();   
                        co_char = scanner.next().charAt(0);
                        i++;
                    }catch(Exception e){
                        if(!invalido){
                            System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                            invalido = true;
                        }
                        scanner.next();
                    }
                }while(i==0);
                //le as coordenadas da saida
                System.out.println("Insira a linha e a coluna de destino de movimento separados por espaço:");
                i=0;
                invalido = false;
                do{
                    try{
                        ld = scanner.nextInt();
                        cd_char = scanner.next().charAt(0);
                        i++;
                    }catch(Exception e){
                        if(!invalido){
                            System.out.println("Você não digitou uma entrada válida, tente novamente: ");
                            invalido = true;
                        }
                        scanner.next();
                    }
                }while(i==0);
                //armazena nas variaveis
                co = co_char-65;
                cd = cd_char-65;
                lo = lo-1;
                ld = ld-1;  
                //faz as checagens
                if(lo>=0 && lo<=7 && ld>=0 && ld<=7 && co>=0 && co<=7 && cd>=0 && cd<=7){
                    ok = true;
                }else{
                    this.divisaoTexto();
                    System.out.println("Você não digitou uma entrada válida");
                    System.out.println("Tente novamente: ");
                    this.divisaoTexto();
                }
            }
            
            
            boolean ocupadaAntes = false;
            Peca peca = null;

            //tenta fazer a jogada
            //se posicao está vazia             
            if(!this.tabuleiro.getTabuleiro()[lo][co].getOcupada()){
                movInvalido = true;
            }else{
                if(this.tabuleiro.getTabuleiro()[lo][co].getPeca().getCor() != this.vez.getCorPeca()){
                    movInvalido = true;
                }else{
                    if(this.movimento(lo, co, ld, cd)){
                        //se onde vou tentar mover estiver ocupado, preciso guardar a peca que estava la
                        if(this.tabuleiro.getTabuleiro()[ld][cd].getOcupada()){
                            peca = this.tabuleiro.getTabuleiro()[ld][cd].getPeca();
                            peca.setCapturado(true);
                            ocupadaAntes = true;
                        }else{
                            ocupadaAntes = false;
                        }
                        //se o movimento for valido verifico se ele corrige o estado de xeque caso estivesse em xeque
                        this.tabuleiro.ocuparPosicao(ld, cd, this.tabuleiro.getTabuleiro()[lo][co].getPeca());
                        this.tabuleiro.desocuparPosicao(lo, co);
                        //se o movimento for valido verifico se ele coloca algum rei em xeque
                        if(this.tabuleiro.emXeque('b')){
                            if(this.vez.corPeca == 'b'){
                                this.movInvalido = true;
                            }else
                            //se ja tiver em xeque, quer dizer que o mov n é valido, pois ele nao tirou o rei do xeque
                            if(!this.xeque){
                                this.xeque = true;
                            }else{
                                this.semSairXeque = true;
                            }
                            this.reiXeque = 'b';

                            if(this.tabuleiro.emXequeMate('b')){
                                this.xequeMate = true;
                            }
                        }else if(this.tabuleiro.emXeque('p')) {
                            if(this.vez.corPeca == 'p'){
                                this.movInvalido = true;
                            }
                            //se ja tiver em xeque, quer dizer que o mov n é valido, pois ele nao tirou o rei do xeque
                            if(!this.xeque){
                                this.xeque = true;
                                this.semSairXeque = false;
                            }else{
                                this.semSairXeque = true;
                            }
                            this.reiXeque = 'p';

                            if(this.tabuleiro.emXequeMate('p')){
                                this.xequeMate = true;
                            }
                        }else {
                            if(this.semSairXeque){
                                this.semSairXeque = false;
                            }
                            this.xeque = false;
                        }
                        if(!this.xeque){
                            this.reiXeque = ' ';
                        }
                        //se saiu do xeque, passo a vez, caso contrario movimento nao foi valido
                        if((!this.semSairXeque || !this.xeque) && this.reiXeque != this.vez.corPeca){
                            this.passarVez();
                            movInvalido = false;
                            peca = null;
                        }else{
                            this.tabuleiro.ocuparPosicao(lo, co, this.tabuleiro.getTabuleiro()[ld][cd].getPeca());
                            this.tabuleiro.desocuparPosicao(ld, cd);
                            if(ocupadaAntes){
                                this.tabuleiro.ocuparPosicao(ld, cd, peca);
                                this.tabuleiro.getTabuleiro()[ld][cd].getPeca().setCapturado(false);
                            }
                            movInvalido = true;
                        }
                        //se validou o movimento salvo ele
                        this.salvarJogada(lo, co, ld, cd);

                    }else{
                        movInvalido = true;
                    }
                }
            }
        }
        this.divisaoTexto();
    }
    
    //metodo sobrecarregado responsavel por fazer jogadas carregadas
    private void fazerJogada(int lo, int co, int ld, int cd){
        //se nao tiver em xeque mate permite o proximo movimento
        if(!this.xequeMate){
            boolean invalido = false;
            boolean ok = false;
            
            boolean ocupadaAntes = false;
            Peca peca = null;

            //tenta fazer a jogada
            //se posicao está vazia             
            if(!this.tabuleiro.getTabuleiro()[lo][co].getOcupada()){
                movInvalido = true;
            }else{
                if(this.tabuleiro.getTabuleiro()[lo][co].getPeca().getCor() != this.vez.getCorPeca()){
                    movInvalido = true;
                }else{
                    if(this.movimento(lo, co, ld, cd)){
                        //se onde vou tentar mover estiver ocupado, preciso guardar a peca que estava la
                        if(this.tabuleiro.getTabuleiro()[ld][cd].getOcupada()){
                            peca = this.tabuleiro.getTabuleiro()[ld][cd].getPeca();
                            peca.setCapturado(true);
                            ocupadaAntes = true;
                        }else{
                            ocupadaAntes = false;
                        }
                        //se o movimento for valido verifico se ele corrige o estado de xeque caso estivesse em xeque
                        this.tabuleiro.ocuparPosicao(ld, cd, this.tabuleiro.getTabuleiro()[lo][co].getPeca());
                        this.tabuleiro.desocuparPosicao(lo, co);
                        //se o movimento for valido verifico se ele coloca algum rei em xeque
                        if(this.tabuleiro.emXeque('b')){
                            if(this.vez.corPeca == 'b'){
                                this.movInvalido = true;
                            }else
                            //se ja tiver em xeque, quer dizer que o mov n é valido, pois ele nao tirou o rei do xeque
                            if(!this.xeque){
                                this.xeque = true;
                            }else{
                                this.semSairXeque = true;
                            }
                            this.reiXeque = 'b';

                            if(this.tabuleiro.emXequeMate('b')){
                                this.xequeMate = true;
                            }
                        }else if(this.tabuleiro.emXeque('p')) {
                            if(this.vez.corPeca == 'p'){
                                this.movInvalido = true;
                            }
                            //se ja tiver em xeque, quer dizer que o mov n é valido, pois ele nao tirou o rei do xeque
                            if(!this.xeque){
                                this.xeque = true;
                                this.semSairXeque = false;
                            }else{
                                this.semSairXeque = true;
                            }
                            this.reiXeque = 'p';

                            if(this.tabuleiro.emXequeMate('p')){
                                this.xequeMate = true;
                            }
                        }else {
                            if(this.semSairXeque){
                                this.semSairXeque = false;
                            }
                            this.xeque = false;
                        }
                        if(!this.xeque){
                            this.reiXeque = ' ';
                        }
                        //se saiu do xeque, passo a vez, caso contrario movimento nao foi valido
                        if((!this.semSairXeque || !this.xeque) && this.reiXeque != this.vez.corPeca){
                            this.passarVez();
                            movInvalido = false;
                            peca = null;
                        }else{
                            this.tabuleiro.ocuparPosicao(lo, co, this.tabuleiro.getTabuleiro()[ld][cd].getPeca());
                            this.tabuleiro.desocuparPosicao(ld, cd);
                            if(ocupadaAntes){
                                this.tabuleiro.ocuparPosicao(ld, cd, peca);
                                this.tabuleiro.getTabuleiro()[ld][cd].getPeca().setCapturado(false);
                            }
                            movInvalido = true;
                        }
                        //se validou o movimento salvo ele
                        this.salvarJogada(lo, co, ld, cd);

                    }else{
                        movInvalido = true;
                    }
                }
            }
        }
    }
    
    //metodo para passar a vez
    private void passarVez(){
        if(this.vez.getNome().equals(this.jogadores[0].getNome())){
            this.vez = this.jogadores[1];
        }else{
            this.vez = this.jogadores[0];
        }
    }
    
    //metodo que executa o jogo ate que seja o final
    public void executarJogo(){
        int op = 0;
        Scanner scanner = new Scanner(System.in);
        
        while(this.fim == false){
            System.out.println("Quer sair? ");
            System.out.println("1- Sim");
            System.out.println("2- Não");
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
            
            //se quer sair
            if(op == 1){
                System.out.println("Quer salvar antes de sair? ");
                System.out.println("1- Sim");
                System.out.println("2- Não");
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
                if(op == 1){
                    if(this.gravaJogo()){
                        this.fim = true;
                    }else{
                        System.out.println("Ocorreu um erro ao tentar salvar seu jogo!");
                    }
                }else{
                    this.fim = true;
                }
            }else{
                this.fazerJogada();
                if(!this.xequeMate){
                    this.statusJogo();
                }
            }
        }
    }
    
    //metodo para savar um jogo
    private boolean gravaJogo(){
        Scanner scanner = new Scanner(System.in);
        //nome para salvar o arquivo
        System.out.println("Digite o nome que deseja salvar o jogo: ");
        String nome = null;
        nome = scanner.next();                
        try {
            FileWriter gravarArquivo = new FileWriter(nome+".txt");
            //salvando os jogadores
            for(int j=0; j<2; j++){
                gravarArquivo.write(this.jogadores[j].getNome());
                gravarArquivo.write("\n");
                gravarArquivo.write(this.jogadores[j].getCorPeca());
                gravarArquivo.write("\n");
            }
            //gravando as jogadas
            try{
                File arquivo = new File("jogadas.txt");
                Scanner lerArquivo = new Scanner(arquivo);
                while(lerArquivo.hasNextLine()){
                    gravarArquivo.write(lerArquivo.nextLine());
                    gravarArquivo.write("\n");
                }
            }catch(FileNotFoundException e){
                System.out.println("Ocorreu um erro ");
            }
            gravarArquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro e não foi gravar seu jogo.");
        }
        return true;
    }
    
    //metodo para carregar jogo anterior salvo
    public boolean carregarJogo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do jogo que deseja continuar: ");
        String nome = null;
        nome = scanner.next();  
        ArrayList<String> dados = new ArrayList<String>();
        int i = 0;
        try{
            File arquivo = new File(nome+".txt");
            Scanner lerArquivo = new Scanner(arquivo);
            while(lerArquivo.hasNextLine()){
                dados.add(lerArquivo.nextLine());
                i++;
            }
        }catch(FileNotFoundException e){
            this.divisaoTexto();
            System.out.println("Ocorreu um erro ao tentar carregar seu jogo");
            System.out.println("Um novo jogo será iniciado!");
            this.divisaoTexto();
            return false;
        }
        //atribuir jogadores
        int c1 = 0;
        int c2 = 0;
        if("b".equals(dados.get(1))){
            c1=1;
        }else{
            c1=2;
        }
        if("b".equals(dados.get(3))){
            c2=1;
        }else{
            c2=2;
        }
        this.atribuirJogadores(dados.get(0), c1, dados.get(2), c2);
        //montagem das pecas
        this.tabuleiro.posicaoInicial(pecas);
        //jogador com as pecas brancas comeca
        this.vez = this.jogadores[0];
        //arquivo de jogadas
        this.criarArquivo();
        //executa as jogadas
        for(int j=4; j<dados.size(); j+=4){
            if(j<dados.size()){
                this.fazerJogada(Integer.parseInt(dados.get(j)), Integer.parseInt(dados.get(j+1)), 
                        Integer.parseInt(dados.get(j+2)), Integer.parseInt(dados.get(j+3)));
            }                
        }
              
        this.fim = false;
        return true;
    }
    
    private void criarArquivo(){
        try {
            FileWriter gravarArq = new FileWriter("jogadas.txt");
            gravarArq.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro");
        }
    }
    
    private void salvarJogada(int lo, int co, int ld, int cd){
        try {
            FileWriter gravarArq = new FileWriter("jogadas.txt",true);
            gravarArq.write(lo+"\n");
            gravarArq.write(co+"\n");
            gravarArq.write(ld+"\n");
            gravarArq.write(cd+"\n");
            gravarArq.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro e não foi gravar seu jogo.");
        }
    }
}
