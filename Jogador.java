/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe responsavel pela criação do jogador
  *     Guarda o nome do jogador criado, e o conjunto de pecas da cor de sua escolha
  *     Alem de saber quais pecas estao em jogo e quais estao capturadas
  */

package projeto1;

public class Jogador {
    
    private String nome;
    private Peca[] pecas = new Peca[16];
    protected char corPeca;

    public Jogador(String nome, int cor, Peca peca[]) {
        this.nome = nome;
        if(cor == 1){
            this.corPeca = 'b';
        }else if (cor == 2){
            this.corPeca = 'p';
        }
        if(this.corPeca == 'b'){
            //pecas brancas
            this.atribuirPecas(peca);
        }else if(this.corPeca == 'p'){
            //pecas pretas
            this.atribuirPecas(peca);
        }
    }  

    public Peca[] getPecas() {
        return pecas;
    }

    public String getNome() {
        return nome;
    }

    public char getCorPeca() {
        return corPeca;
    }
    
    //metodo para exibir em tela as pecas capturadas do jogador
    public void pecasCapturadas(){
        System.out.print("Peças capturadas:");
        for(int i=0; i<16; i++){
            if(this.pecas[i].getCapturado()){
                System.out.print(this.pecas[i].getRepresentacao()+" ");
            }
        }
    }
    
    //metodo para ver as pecas ainda ativas no jogo
    public void pecasAtivas(){
        System.out.print("Peças ativas:");
        for(int i=0; i<16; i++){
            if(this.pecas[i].getCapturado() == false){
                System.out.print(this.pecas[i].getRepresentacao()+" ");
            }
        }
    }
    
    //metodo para definir o conjunto de 16 pecas do jogador, dependendo da cor escolhida
    public void atribuirPecas(Peca pecas[]){
        int p=16;
        if(this.corPeca == 'b'){
            //pecas brancas
            for(int i=0; i<16; i++){
                this.pecas[i]= pecas[i];
                p++;
            }
        }else if(this.corPeca == 'p'){
            //pecas pretas
            while(p<32){
                for(int i=0; i<16; i++){
                    this.pecas[i]= pecas[p];
                    p++;
                }
            }
        }
    }
}
