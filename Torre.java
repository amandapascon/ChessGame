/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Torre, 
  *     responsável por verificar os movimentos realizados pela Torre 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Torre extends Peca{   
    
    //construtor
    public Torre(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2656";
        }else if ('p'== this.cor){
            //preto
            this.representacao = "\u265C";
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
            //torre anda quantas casas quiser verticalmente ou horizontalmente
            if((ld==lo) || (cd==co)){
                return true;
            }    
        }
        return false;
    }
}
