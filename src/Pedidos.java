import java.io.Serializable;

public class Pedidos implements Serializable {
    int quantidade;
    String remedio;
    String nome_cliente;
    public Pedidos(String s, int qtd , String n){
        quantidade = qtd;
        remedio = s;
        nome_cliente = n;
    }

    public String getRemedio(){
        return remedio;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public String getNomeCliente(){
        return nome_cliente;
    }
    public String toString(){
        return "\nCliente: " + nome_cliente + "\nRemédio: " + remedio + "\nQuantidade: "+ quantidade;
    }


}
