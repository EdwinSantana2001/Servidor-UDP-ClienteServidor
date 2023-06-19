import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *En caso de analigia el puerto vacio seria una casa, La direccion seria el InetAddress
 * y el  puerto seria el puerto seria el portal de las residencias
 **/
public class Cliente {
    public static void main(String[] args) {

        final int PUERTO_SERVIDOR = 25565;
        byte[] buffer = new byte[1024];//Esto es un arreglo de bytes


        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");//Este localhost le da la direccion de usuario al servidor
            DatagramSocket socketUDP = new DatagramSocket();//Se crea sin puerto, esto significa que te va a asignar un puerto propio

            String ms = "¡Hola mundo desde el cliente!";
            buffer = ms.getBytes();//Se Escribe el mensaje

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length,direccionServidor,PUERTO_SERVIDOR);//Se le da la direccion del servidor para enviar el mensaje
            socketUDP.send(pregunta);
            System.out.println("Envio datagrama");

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);// El servidor mandaria la confirmacion de conexion desde el servidor (Server->Cliente)
            socketUDP.receive(peticion);
            System.out.println("Recibo peticion");

            ms =new String(peticion.getData());
            System.out.println(ms);

            socketUDP.close();//Cierra la conexión
        } catch (SocketException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}