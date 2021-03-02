/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Rei, 
  *     responsável por verificar os movimentos realizados pelo Rei 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Rei extends Peca{
    
    //construtor
    public Rei(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2654";
        }else if ('p'== this.cor){
            //preto
            this.representacao = "\u265a";
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
            //rei pode andar um casa para qualquer direção (8 possíveis movimentos)
            if( (ld==lo-1 && cd==co-1) || (ld==lo-1 && cd==co) || 
                (ld==lo-1 && cd==co+1) || (ld==lo && cd==co-1) ||
                (ld==lo+1 && cd==co-1) || (ld==lo+1 && cd==co) || 
                (ld==lo+1 && cd==co+1) || (ld==lo && cd==co+1)){
                return true;
            }       
        }
        return false;
    }
}
