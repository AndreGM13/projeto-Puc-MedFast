import java.io.Serializable;

public class Pedidos implements Serializable {
    int quantidade;
    String nome;
    public Pedidos(String s, int qtd){
        quantidade = qtd;
        nome = s;
    }

    public String getNome(){
        return nome;
    }
    public int getQuantidade(){
        return quantidade;
    }
}
