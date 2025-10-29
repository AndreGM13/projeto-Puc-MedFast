import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static Conexao c;
    static Socket socket;
    static String nomeCliente;

    public Cliente() {
        try {
            socket = new Socket("localhost", 9600); // fase de conexao
        } catch (Exception e) {
            System.err.println("Nao consegui resolver o host...");
        }
    }
    private static void menu(){
        int opc;

        Scanner entrada = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("PUC - MedFast");
            System.out.println("1- Cadastrar Usuário: ");
            System.out.println("2- Enviar Pedido: ");
            System.out.println("3- Sair");

            opc = entrada.nextInt();
            switch(opc){
                case 1:
                    cadastro();
                    break;
                case 2:
                    pedido();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("opcao incorreta\n");
            }
        }while(opc != 3);

        entrada.close();
    }

    private static void cadastro(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira seu nome: ");
        nomeCliente = entrada.nextLine();
    }

    private static void pedido(){
        boolean valor;
        Scanner entrada = new Scanner(System.in);
        Pedidos pedido;
        boolean sair = false;

        System.out.println("insira o nome do remedio: ");
        String nome = entrada.nextLine();

        System.out.println("Insira a quantidade desejada: ");
        int qtd = entrada.nextInt();

        entrada.nextLine();

        pedido = new Pedidos(nome, qtd, nomeCliente);

        c.send(socket, pedido);

        valor = c.recebeRetorno(socket);
        if (!valor) {
            System.out.println("nao existe esse remedio");
        }
    }
    public static void main(String args[]) {
        new Cliente();
        menu();
        try {
            socket.close();                 // fase de desconexao
        } catch (IOException e) {
            System.err.println("Nao encerrou a conexao corretamente" + e.getMessage());
        }
    }
}
