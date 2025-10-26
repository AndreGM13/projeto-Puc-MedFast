import java.util.ArrayList;
import java.net.*;
import java.util.HashMap;

public class Servidor {
    static HashMap<String , Integer> estoque = new HashMap<String , Integer>();
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    public static boolean b;

    public Servidor(){//construtor
        try {
            serversocket = new ServerSocket(9600); // conexao com o socket
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.err.println("Nao criei o Server Socket...");
        }
        //teste generico do estoque de remedios
        estoque.put("r1" , 10);
        estoque.put("r2" , 15);
        estoque.put("r3" , 20);
    }

    public static void main(String args[]){
        new Servidor();
        Pedidos pedido;
        b = true;
        if (connect()) {
            do {
                pedido = (Pedidos) c.receive(client_socket);
                if(!b){
                    break;
                }
                if (estoque.containsKey(pedido.getNome())) {
                    if (estoque.get(pedido.getNome()) > pedido.getQuantidade()) {
                        estoque.replace(pedido.getNome(), estoque.get(pedido.getNome()) - pedido.getQuantidade());
                        System.out.println("pedido: " + pedido.getNome() + "\nquantidade: " + pedido.getQuantidade());
                        System.out.println("estoque: " + estoque.toString());
                    }
                    c.retorno(client_socket, true);
                } else {
                    c.retorno(client_socket, false);
                }
            }while(b);

            try {
                client_socket.close();
                serversocket.close();           // fase de desconexao
            } catch (Exception e) {
                System.err.println("Não encerrou a conexao corretamente" + e.getMessage());
            }

        }
    }


    static boolean connect() {
        boolean ret;
        try {
            client_socket = serversocket.accept();  // fase de conexao
            ret = true;
        } catch (Exception e) {
            System.err.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }

}
