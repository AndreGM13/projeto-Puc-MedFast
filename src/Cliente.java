import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static Conexao c;
    static Socket socket;

    public Cliente() {
        try {
            socket = new Socket("localhost", 9600); // fase de conexao
        } catch (Exception e) {
            System.err.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]) {
        boolean valor = false;
        Scanner entrada = new Scanner(System.in);
        Pedidos pedido;
        boolean sair = false;
        new Cliente();
        do {
                System.out.println("insira o nome do remedio: ");
                String nome = entrada.nextLine();

                System.out.println("Insira a quantidade desejada: ");
                int qtd = entrada.nextInt();

                if(qtd == -1){
                    sair = true;
                    Servidor.b = false;
                    break;
                }
                entrada.nextLine();



                pedido = new Pedidos(nome, qtd);

                c.send(socket, pedido);

                valor = c.recebeRetorno(socket);
                if (!valor) {
                    System.out.println("nao existe esse remedio");
                }
        }while(!sair);
        entrada.close();
        try {
            socket.close();                 // fase de desconexao
        } catch (IOException e) {
            System.err.println("Nao encerrou a conexao corretamente" + e.getMessage());
        }
    }
}
