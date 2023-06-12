
/**
 * Objeto cliente en el que guardaremos el socket de entrada de datos y el de salida.
*/

#include <string>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

using namespace std;

class Client {
public:

	int socketContextIn, socketContextOut;

	Client(int socketContextIn, int socketContextOut) {
		this->socketContextIn = socketContextIn;
		this->socketContextOut = socketContextOut;
	}
};

