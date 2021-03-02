/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe repsonsavel pela manutencao do tabuleiro, é essa classe que
  *     garante que um movimento é válido, chamando outros metodos de outras classe e tambem verifica se 
  *     a peça em questao está livre
  *     Essa classe imprime o tabuleiro em tela e faz as checagens de adequação ao movimento solicitado
  *     Ela é quem constroi o tabuleiro, formado de 64 Posições
  */

package projeto1;

public class Tabuleiro {
    private static final int TAMANHO = 8;
    private Posicao[][] tabuleiro = new Posicao[TAMANHO][TAMANHO];
    
    //construtor
    public Tabuleiro() {
        //criando as 64 Posiões (tabuleiro)
        for(int i=0; i<TAMANHO; i++){
            for(int k=0; k<TAMANHO; k++){
                this.criarPosicao(i, k);
            }
        }
    }

    public Posicao[][] getTabuleiro() {
        return tabuleiro;
    }
    
    //metodo para imprimir o tabuleiro no estado atual em tela
    public void imprimirTabuleiro(){
        //colunas
        System.out.print(" ");
        for (char coluna='A'; coluna<='H'; coluna++){
            System.out.print(" "+(coluna));            
        }
        System.out.println();        
        //linhas                
        for (int l=TAMANHO-1; l>=0; l--) {            
            System.out.print(l+1);            
            //posicoes do tabuleiro
            for (int c=0; c<TAMANHO; c++) {
                if(this.tabuleiro[l][c].getOcupada()){
                    System.out.print(" "+ this.tabuleiro[l][c].getPeca().representacao);
                }else{
                    System.out.print(" "+ this.tabuleiro[l][c].getCor());
                }
            }               
            System.out.println();
        }  
        //colunas
        System.out.print(" ");
        for (char coluna='A'; coluna<='H'; coluna++){
            System.out.print(" "+(coluna));            
        }
        System.out.println();
    }
    
    //metodo que cria as posições com sua respectiva cor
    private void criarPosicao(int i, int k){
        //conversão para char
        int temp = k+65;
        char k_char = (char)temp;
        if((i%2==0 && k%2==0) || (i%2!=0 && k%2!=0)){
            //preto
            this.tabuleiro[i][k] = new Posicao(i+1,k_char,"preto");
        }else{
            //branco
            this.tabuleiro[i][k] = new Posicao(i+1,k_char,"branco");
        } 
    }
    
    //metodo que coloca uma peca em uma posicao
    public void ocuparPosicao(int i, int k, Peca peca){
        //colocando a peca na posicao
        this.tabuleiro[i][k].setPeca(peca);
        //falando que a posicao esta ocupada
        this.tabuleiro[i][k].setOcupada(true);
    }
    
    //metodo que desocupa uma posição
    public void desocuparPosicao(int i, int k){
        //falando que a posição está desocupada
        this.tabuleiro[i][k].setOcupada(false);
        this.tabuleiro[i][k].setPeca(null);
    }
    
    //metodo para configuração inicial do tabuleiro
    public void posicaoInicial(Peca peca[]){
        int p = 0;
        while (p<32){
            //Pecas brancas
            for(int i=0; i<2; i++){
                for(int k=0; k<8; k++){
                    this.ocuparPosicao(i, k, peca[p]);
                    p++;
                }
            }
            //Pecas pretas
            for(int i=7; i>5; i--){
                for(int k=0; k<8; k++){
                    this.ocuparPosicao(i, k, peca[p]);
                    p++;
                }
            }
        }
    }
    
    public boolean checaMovimento(int lo, int co, int ld, int cd){
        if(lo>=0 && lo<=7 && ld>=0 && ld<=7 && co>=0 && co<=7 && cd>=0 && cd<=7){
            //tentar se mover para o mesmo lugar
            if ((ld==lo) && (cd==co)){
                return false;
            }
            //só entra no if se estiver dentro das especificções do tabuleiro 8x8
            if((lo>=0 && lo<8) && (co>=0 && co<8) && (ld>=0 && ld<8) && (cd>=0 && cd<8)){
                //chamar o checa movimento especifico da peca que esta na posicao de origem do movimento
                if(this.tabuleiro[lo][co].getPeca().checaMovimento(lo, co, ld, cd)){
                    //verifcando se tem pecas no caminho
                    if(checaCaminho(lo, co, ld, cd) == false){
                        return false;
                    }
                    //se tiver peca na posição destino
                    if(casaOcupada(lo, co, ld, cd) == false){
                        return false;
                    }
                    return true;               
                }
            }    
        }
        return false;
    }
    
    private boolean checaCaminho(int lo, int co, int ld, int cd){
        //variaveis        
        boolean horizontal = false;
        boolean vertical = false;
        boolean diagonal = false;
        int i=lo;
        int j=co;
        String representação = this.tabuleiro[lo][co].getPeca().getRepresentacao();
 
        //se nao for nem cavalo nem rei preciso ver se nao tem peca no caminho do movimento
        if(representação!="\u2658" && representação!="\u265E" &&
                representação!="\u2654" && representação!="\u265a"){
            //movimento na diagonal
            if(Math.abs(ld-lo) == (Math.abs(cd-co))){
                diagonal = true;                           
            }else if(cd==co){
            //movimento na vertical
                vertical = true;
            }else if(ld==lo){
            //movimento na horizontal
                horizontal = true;
            }
            while(i!=ld || j!=cd){
                if((diagonal || vertical) && lo<ld ){
                    i++;
                }
                if((diagonal || vertical) && lo>ld ){
                    i--;
                }
                if((diagonal || horizontal) && co<cd ){
                    j++;
                }
                if((diagonal || horizontal) && co>cd ){
                    j--;
                }
                if(this.tabuleiro[i][j].getOcupada() && (i!=ld || j!=cd) ){
                    return false;
                }
            }
        }
        return true;
    }
    
    //metodo que verifica se a peca na posicao final é do oponente ou no proprio jogador
    private boolean casaOcupada(int lo, int co, int ld, int cd){
        if(this.tabuleiro[ld][cd].getOcupada()){
            //se a peca for sua
            if(this.tabuleiro[ld][cd].getPeca().getCor() == this.tabuleiro[lo][co].getPeca().getCor()){
                return false;
            }else{
                //se for peca do oponente
                return true;
            }
        }
        //se for um peao e tentou mover na diagonal sem peca la, invalido
        String representação = this.tabuleiro[lo][co].getPeca().getRepresentacao();
        if(representação == "\u2659" || representação == "\u265F"){
            if((ld==lo+1 && cd==co-1) || (ld==lo+1 && cd==co+1) || (ld==lo-1 && cd==co-1) || (ld==lo-1 && cd==co+1)){
                return false;
            }
        }
        return true;
    }
    
    //metodo que encontra o rei
    public int[] posicaoRei(char cor){
        int posicao[] = new int[2];
        if(cor == 'b'){
            for(int i=0; i<TAMANHO; i++){
                for(int k=0; k<TAMANHO; k++){
                    //rei branco
                    if(this.tabuleiro[i][k].getOcupada()){
                        if(this.tabuleiro[i][k].getPeca().getRepresentacao() == "\u2654"){
                            posicao[0]=i;
                            posicao[1]=k;
                            return posicao;
                        }
                    }
                }
            }
        }else if(cor == 'p'){
            for(int i=0; i<TAMANHO; i++){
                for(int k=0; k<TAMANHO; k++){
                    //rei preto
                    if(this.tabuleiro[i][k].getOcupada()){
                        if(this.tabuleiro[i][k].getPeca().getRepresentacao() == "\u265a"){
                            posicao[0]=i;
                            posicao[1]=k;
                            return posicao;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    //metodo para verificar se o rei esta em xeque
    public boolean emXeque(char cor){
        int rl = this.posicaoRei(cor)[0];
        int rc = this.posicaoRei(cor)[1];
        for(int i=0; i<TAMANHO; i++){
            for(int k=0; k<TAMANHO; k++){
                if(this.tabuleiro[i][k].getOcupada()){
                    //se for um movimento valido, significa que o rei pode ser comido, logo esta em xeque
                    if(this.checaMovimento(i, k, rl, rc)){
                        return true;
                    }                 
                }
            }
        }
        return false;
    }
    
    //metodo para verificar se o rei esta em xeque mate
    public boolean emXequeMate(char cor){
        boolean ocupadaAntes = false;
        Peca peca = null;
        //percorro todo o tabuleiro vendo as posicões ocupadas
        for(int i=0; i<TAMANHO; i++){
            for(int k=0; k<TAMANHO; k++){
                //se a peca for da mesma cor que o rei em xeque, podemos tentar achar algum movimento para indicar que aquele xeque nao é mate
                if(this.tabuleiro[i][k].getOcupada() && this.tabuleiro[i][k].getPeca().getCor() == cor){
                    //movo aquela peca para todos os caminhos possiveis
                    for(int j=0; j<TAMANHO; j++){
                        for(int p=0; p<TAMANHO; p++){
                            if(this.checaMovimento(i, k, j, p)){
                                if(this.tabuleiro[j][p].getOcupada()){
                                    peca = this.tabuleiro[j][p].getPeca();
                                    ocupadaAntes = true;
                                }else{
                                    ocupadaAntes = false;
                                }
                                this.ocuparPosicao(j, p, this.tabuleiro[i][k].getPeca());
                                this.desocuparPosicao(i, k);

                                //se executar esse movimento anualar o xeque, ele nao é mate
                                if(!this.emXeque(cor)){
                                    this.ocuparPosicao(i, k, this.tabuleiro[j][p].getPeca());
                                    this.desocuparPosicao(j, p);
                                    if(ocupadaAntes){
                                        this.ocuparPosicao(j, p, peca);
                                    }
                                    return false;
                                //caso contrario, desfaco as alterações para teste e o xeque "continua"    
                                }else{
                                    this.ocuparPosicao(i, k, this.tabuleiro[j][p].getPeca());
                                    this.desocuparPosicao(j, p);
                                    if(ocupadaAntes){
                                        this.ocuparPosicao(j, p, peca);
                                    }
                                }
                            }
                        }
                    }
                }            
            }
        }
        return true;
    }
}
    






	