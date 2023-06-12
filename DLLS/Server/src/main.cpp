#include "MultiThreadServer.hpp"

/**
 * Inicializar la configuración del servidor y ejecutar la escucha asíncrona.
*/
int main() {

    if (MultiThreadServer::initializeSettings(7205, 10)) {
            MultiThreadServer::runListening();
    }

    return 0;
}