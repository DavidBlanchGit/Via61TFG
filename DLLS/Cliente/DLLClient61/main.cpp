#include "Client.h"

using namespace std;

Client* client;

/*
    * Funciones que componen la dll. Estas funciones serán las que se utilicen desde el código de JavaFX.
*/

extern "C" __declspec(dllexport) void initializeClientAsync() {

    client = new Client;
    CreateThread(NULL, 0, client->runConnectionToServer, NULL, 0, NULL);
}

extern "C" __declspec(dllexport) void sendBuffer(const char* buffer) {
 
    client->sendBuffer(buffer);
}

extern "C" __declspec(dllexport) const char* recvBuffer() {
    
    return client->recvBuffer();
}

extern "C" __declspec(dllexport) const char* validateDll() {

    return "The client dll was loaded successfully";
}

extern "C" __declspec(dllexport) void runWithDebugConsole() {

    return client->runWithDebugConsole();
}

extern "C" __declspec(dllexport) bool userConnected() {

    return Client::userConnected;
}