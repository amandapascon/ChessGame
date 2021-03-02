/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe abstrata que é mãe de todas as classes de Pecas específicas
  *     Ou seja, possui metodos e atributos que sao comuns a todas as classes filhas (Bispo, Cavalo, Dama, Peao, Rei e Torre)
  */

package projeto1;

public abstract class Peca {
    
    //atributos
    protected String representacao;
    protected char cor;
    protected boolean capturado;

    //construtor    
    public Peca(char cor) {
        if(cor=='p' || cor=='b'){
            this.cor = cor;
        }
        this.capturado = false;
    }

    public char getCor() {
        return cor;
    }
    
    public String getRepresentacao() {
        return representacao;
    }

    public boolean getCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }
    
    //metodos abstratos
    public abstract String desenho();
    
    public abstract boolean checaMovimento(int lo, int co, int ld, int cd);
    
}
