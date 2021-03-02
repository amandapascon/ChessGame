/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Bispo, 
  *     responsável por verificar os movimentos realizados pelo Bispo 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Bispo extends Peca{
    
    //construtor
    public Bispo(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2657";
        }else if ('p'== this.cor){
            //preto
            this.representacao = "\u265D";
        } 
    }
    
    //metodos
    @Override
    public String desenho(){
        return this.representacao;
    }
    
    @Override
    public boolean checaMovimento(int lo, int co, int ld, int cd){
        if(lo>=0 && lo<=7 && ld>=0 && ld<=7 && co>=0 && co<=7 && cd>=0 && cd<=7){
            //andar quantas casas quiser na diagonal
            if(Math.abs(ld-lo) == (Math.abs(cd-co))){
                return true;
            }
        }
        return false;
    }
}
