

/*#define CONFIG_CATCH_MAIN
#include <catch2/catch_all.hpp>

#include "Client.h"

Client* client;

/**
 * Se excluyen las siguientes funciones:
 * - "getCurrentDateTime" debido a que es meramente visual e informativa.
 * - "runListening" debido a que es una funci�n que est� pensada para estar siempre en ejecuci�n para aceptar
 * las conexiones entrantes, sin devolver ning�n valor.
 * - "recvClientContextAsync" debido a que este m�todo solo se llama si todo lo anterior funciona correctamente,
 * y en caso de desconexi�n del alg�n cliente en mitad del contexto del hilo, simplemente el m�todo termina.
 * - "clientsHandlerThread" lo mismo que la anterior. Esta funci�n no da lugar a fallo.
*/

/**
 * 
*/
/*TEST_CASE("Testing Try Connection")
{
    REQUIRE(client->tryConnection(7205, "82.213.237.186"));
}*/

/**
 * En caso de que los dos clientes disponibles en el vector sean conexiones v�lidas la funci�n devolver� true.
 * En caso contrario devolver� false.
*/
/*TEST_CASE("Testing Validate Two Clients On Hold")
{
    REQUIRE(server.validateTwoClientsOnHold());
}*/
