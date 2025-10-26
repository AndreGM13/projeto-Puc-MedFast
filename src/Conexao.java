import java.io.*;
import java.net.Socket;

public class Conexao {

    public static void send(Socket socket, Object o) {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(o);
        } catch (Exception e) {
            System.err.println("Excecao no OutputStream");
        }
    }

    public static Object receive(Socket socket) {
        ObjectInputStream in;
        Object recebido = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            recebido = in.readObject();
        } catch (Exception e) {
            System.err.println("Excecao no InputStream: " + e);
        }
        return recebido;
    }

    public static void retorno(Socket socket, boolean x){
        DataOutputStream out;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeBoolean(x);
        } catch (Exception e) {
            System.err.println("Excecao no OutputStream");
        }
    }

    public static boolean recebeRetorno(Socket socket){
        DataInputStream in;
        boolean valor = false;
        try{
            in = new DataInputStream(socket.getInputStream());
            valor = in.readBoolean();
        }catch (Exception e){
            System.err.println("Excecao no InputStream: " + e);
        }
        return valor;
    }
}