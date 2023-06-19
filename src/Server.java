import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Datagramas:es una recopilación autónoma e independiente de datos. Estos datos contienen
 * información suficiente para ser enrutados desde el ordenador de origen al de destino sin
 * depender de intercambios anteriores entre el origen, el destino y la red de transporte».
 *
 * Consejo: imaginar los paquetes como si fuesen correos yo te envio un paquete y tu me envias otro ambos con informacion
 * Lo que entiendo en esa analogia es que basicamente es una conversacion yo espero y tu hablas y viseversa
 * */

public class Server {
    public static void main(String[] args) {

        final int PUERTO = 25565;
        byte[] buffer = new byte[1024];//Esto es un arreglo de bytes

        try {
            //DatagramPacket que es no más que los  datagramas que vamos a enviar y recibir
            System.out.println("Iniciado el server UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);//DatagramSocket es realmente el socket

            //para crear paquete que va a contener el bufer con la longitud del propio buffer
            // No permite recibir más de la longitud de byte que ponemos en el buffer
            /*El while(true) basicamente se usa para que el vservidor siempre se mantrenga escuchando y no termine proceso estamos siempre recibiendo el mensaje*/
            while(true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(peticion);//Recibe peticion de Datagrama cuando el usuario se conecte con nosotros parecido al input del TCP
                System.out.println("Recibida la informacion del cliente");

                String ms = new String(peticion.getData());//Apartir del buffer que recibe hace un String
                System.out.println(ms);//Muestra por consola el mensaje del usuario y muestra el string del buffer

                int puertoCliente = peticion.getPort();//Cuando viene el paquete me da la direccion y el puerto del cliente
                /**
                 * InetAddress: proporciona la funcionalidad básica para obtener la dirección IP de un ordenador conectado a una red local o a internet.
                 * */
                InetAddress direccion  = peticion.getAddress();//Da la direccion basicamente dice donde está la persona que me ha contactado

                ms = "¡Hola mundo desde el servidor!";
                buffer = ms.getBytes();

                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length,direccion,puertoCliente);//respuesta de el seridor al cliente adicional se le agrega a la respuesta la direccion y puerto proporcionada por el cliente
                System.out.println("Enviada informacion al cliente");
                socketUDP.send(respuesta);//se le manda la respuesta del server al cliente

            }

        } catch (SocketException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
