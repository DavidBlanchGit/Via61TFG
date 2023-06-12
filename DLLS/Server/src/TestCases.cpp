#define CONFIG_CATCH_MAIN
#include <catch2/catch_all.hpp>

#include "MultiThreadServer.hpp"

/**
 * Se excluyen las siguientes funciones:
 * - "getCurrentDateTime" debido a que es meramente visual e informativa.
 * - "runListening" debido a que es una función que está pensada para estar siempre en ejecución para aceptar 
 * las conexiones entrantes, sin devolver ningún valor.
 * - "recvClientContextAsync" debido a que este método solo se llama si todo lo anterior funciona correctamente,
 * y en caso de desconexión del algún cliente en mitad del contexto del hilo, simplemente el método termina.
 * - "clientsHandlerThread" lo mismo que la anterior. Esta función no da lugar a fallo.
*/

/**
 * En caso de que se produzca un error la función devolverá falso. El error puede ser debido a la introducción
 * de un puerto inválido o a un error del sistema operativo (probablemente de reuso de puerto).
 * Puertos válidos (1 - 65535). 
*/
TEST_CASE("Testing Initialize Settings") 
{
    MultiThreadServer server; 
    REQUIRE(server.initializeSettings(7205, 10));
}

/**
 * En caso de que los dos clientes disponibles en el vector sean conexiones válidas la función devolverá true.
 * En caso contrario devolverá false.
*/
TEST_CASE("Testing Validate Two Clients On Hold") 
{
    MultiThreadServer server; 
    REQUIRE(server.validateTwoClientsOnHold());
}
