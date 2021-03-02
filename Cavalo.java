/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Cavalo, 
  *     responsável por verificar os movimentos realizados pelo Cavalo 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Cavalo extends Peca{
        
    //construtor
    public Cavalo(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2658";
        }else if ('p'==this.cor){
            //preto
            this.representacao = "\u265E";
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
            //cavalo anda em L para qualquer direção
            if((ld==lo+2 && cd==co+1) || (ld==lo+1 && cd==co+2) || 
               (ld==lo-1 && cd==co+2) || (ld==lo-2 && cd==co+1) || 
               (ld==lo-2 && cd==co-1) || (ld==lo-1 && cd==co-2) || 
               (ld==lo+1 && cd==co-2) || (ld==lo+2 && cd==co-1)){
                return true;
            } 
        }
        return false;
    }
}
