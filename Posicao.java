/**
  * Nome: Amanda Carnio Pascon
  * RA: 770981
  * Descrição da Classe: Classe responsavel por cada uma das 64 posições existentes no tabuleiro
  *     Ela tem uma coordenada, uma cor e pode ter uma peca ocupando-a
  */

package projeto1;

public class Posicao {
    private char cor;
    private int linha;
    private char coluna;
    private boolean ocupada;    
    private Peca peca; 
    
    //construtor    
    public Posicao(int linha, char coluna, String cor){
        this.linha = linha;
        this.coluna = coluna;
        if("preto".equals(cor)){
            this.cor = '\u2B1B';
        }else if("branco".equals(cor)){
            this.cor = '\u2B1C';
        }
        this.ocupada = false;
    }

    //getters e setters
    public int getLinha() {
        return  linha;
    }
    
    public String getLinhaString() {
        return ""+linha;
    }

    public char getColuna() {
        return coluna;
    }

    public boolean getOcupada() {
        return ocupada;
    }
    
    
    public Peca getPeca() {
        return peca;
    }
    
    public void setPeca(Peca peca){
        this.peca = peca;
    }
    
    public char getCor() {
        return cor;
    }
    
    public String getCorString() {
        if(this.cor == '\u2B1B'){
            return "Preto";
        }
        return "Branco";
    }
    
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }
}
