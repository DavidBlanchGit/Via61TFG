#pragma once
#include <winSock2.h>
#include <windows.h>
#include <ws2tcpip.h>
#include <iostream>
#include <string>
#include <time.h>
#include "AESHandler.h"

#pragma comment(lib, "ws2_32.lib")
#pragma warning(disable: 4996)

#define BUFFER_SIZE 4096

using namespace std;

class Client {

public:

	/*
		* Intentamos la conexión con el servidor en bucle hasta que esta sea aceptada por le mismo.
	*/
	static DWORD WINAPI runConnectionToServer(void* Param);

	/*
		* Enviamos el buffer que ha sido rebicido por parámetro al servidor al servidor.
	*/
	void sendBuffer(const char* buffer);

	/*
		* Recibimos el buffer enviado por el servidor.
	*/
	const char* recvBuffer();

	/*
		* En caso de llamar a esta función se ejecutará una consola complementaria que mostrará información
		* de debug en tiempo de ejecución.
	*/
	void runWithDebugConsole(); 

	/*
		* Variable que tomará el valor TRUE cuando se haya encontrado otro cliente para la conexión.
	*/
	static bool userConnected;

private:

	static WSADATA wsaData, wsaData1;
	static SOCKET socketIn, socketOut;
	static struct sockaddr_in serverAddr, serverAddr1;

	/*
		* LLamará al método initialize socket.
	*/
	static bool tryConnection(USHORT port, string ipAddr);

	/*
		* Obtenemos la fecha actual, con precisión de segundos.
	*/
	static string getCurrentDateTime();

	/*
		* Inicializamos el socket con los datos obtenidos por parámetro.
	*/
	static bool initializeSocket(SOCKET& sockfd, USHORT port, string ipAddr, WSADATA& wsaData, sockaddr_in& serverAddr);

	/*
		* Inicializamos el cifrado AES de 256 bit que se utilizará en el envío y recibo de mensajes.
	*/
	static void initializeAES256Key();
};