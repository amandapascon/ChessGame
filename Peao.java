/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe específica da peça Peao, 
  *     responsável por verificar os movimentos realizados pelo Peao 
  *     e também por guardar a representação da peca em tela
  */

package projeto1;

public class Peao extends Peca{
    
    //construtor
    public Peao(char cor) {
        super(cor);
        if ('b' == this.cor){
            //branco
            this.representacao = "\u2659";
        }else if ('p'== this.cor){
            //preto
            this.representacao = "\u265F";
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
            if (this.cor=='p'){
                //primeiro movimento permitindo andar duas casas peao preto
                if(lo==6 && (ld==lo-2 && cd==co)){
                    return true;
                //movimento para frente ou para as diagonais
                }else if((ld==lo-1 && cd==co) || ((ld==lo-1 && cd==co-1) || (ld==lo-1 && cd==co+1))){
                    return true;
                }
                return false;
            }else{
                //primeiro movimento permitindo andar duas casas peao branco
                if(lo==1 && (ld==lo+2 && cd==co)){
                    return true;
                //movimento para frente ou para as diagonais
                }else if((ld==lo+1 && cd==co) || ((ld==lo+1 && cd==co-1) || (ld==lo+1 && cd==co+1))){
                    return true;
                }
                return false;
            }    
        }else{
            return false;
        }
    }
}
