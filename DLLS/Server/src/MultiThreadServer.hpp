
/**
 * Objeto servidor que manejará la conexiones entre los clientes.
*/

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <vector>
#include <iostream>
#include <cerrno>
#include <cstring>
#include <unistd.h>
#include <pthread.h>
#include <tuple>
#include "Client.hpp"

using namespace std;

#define BUFFER_SIZE 4096

class MultiThreadServer {

public:

	/**
	 * Inicializamos el socket con las estructuras necesarias para su funcionamiento.
	 * Estableciendo un máximo de clientes y el puerto que se pondrá a la escucha.
	 * La ip no hace falta establecerla ya que será la 0.0.0.0.
	*/
	static bool initializeSettings(unsigned short port, unsigned int maxClients);

	/**
	 * Poner a la espera para aceptar clientes.
	 * Se aceptarán dos sockets por cada cliente conectado, uno para la salida y otro para
	 * la entrada de datos. Una vez aceptados los dos sockets estos se guardan en un objeto 
	 * Cliente, que a su vez está almacenado en un vector (clientsOnHold). Después de esto
	 * si "validateTwoClientsOnHold" devuelve true se llamara a "clientsHandlerThread" y 
	 * el vector se limpiará para seguir administrando conexiones posteriores.
	*/
	static void runListening();

	/**
	 * Si el vector clientsOnHold tiene almacenados dos clientes se envía el mensaje a los
	 * dos para que inicien la conexión. Comprobando que el primer cliente guardado en el vector
	 * no haya cancelado su conexión, en caso de que sí, se eliminará.
	 * Por su naturaleza la función tendría que ser de carácter privado pero se crea pública para el testing.
	*/
	static bool validateTwoClientsOnHold();

private:

	static unsigned short int port;
	static int sockfd;
	static struct sockaddr_in serverAddr, clientAddr;
	static socklen_t clientAddrLen;
	static vector<Client*> clientsOnHold;

	/**
	 * Mediante los dos clientes pasados como parámetro se creará se creará un thread
	 * para cada uno respectivamente mediante el método "recvClientContextAsync".
	*/
	static void clientsHandlerThread(Client* clientContext, Client* clientContext2);

	/**
	 * Obtenemos la fecha actual con precisiñon de segundos.
	*/
	static string getCurrentDateTime();

	/**
	 * La funcion de este metodo será recibir una mensaje desde el primer cliente
	 * y redirigirlo al segundo y viceversa. Es el núcleo principal del servidor.
	*/
	static void* recvClientContextAsync(void* arg);
};
