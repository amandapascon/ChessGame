/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Dama, 
  *     responsável por verificar os movimentos realizados pela Dama 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Dama extends Peca{
    
    //construtor
    public Dama(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2655";
        }else if ('p'== this.cor){
            //preto
            this.representacao = "\u265B";
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
            //a rainha é uma junção do bispo e da torre
            if(Math.abs(ld-lo) == (Math.abs(cd-co)) || ((ld==lo) ||(cd==co))){
                return true;
            }   
        }
        
        return false;
    }
}
