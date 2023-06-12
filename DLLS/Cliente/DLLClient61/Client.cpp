#include "Client.h"

SOCKET Client::socketIn, Client::socketOut;
sockaddr_in Client::serverAddr, Client::serverAddr1;
WSADATA Client::wsaData, Client::wsaData1;
bool Client::userConnected = FALSE;

bool Client::tryConnection(USHORT port, string ipAddr) {

    return initializeSocket(socketOut, port, ipAddr, wsaData, serverAddr) && initializeSocket(socketIn, port, ipAddr,
        wsaData1, serverAddr1);
}

bool Client::initializeSocket(SOCKET& sockfd, USHORT port, string ipAddr, WSADATA& wsaData, sockaddr_in& serverAddr) {

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        WSACleanup();
        return FALSE;
    }

    sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (sockfd == INVALID_SOCKET) {
        closesocket(sockfd);
        WSACleanup();
        return FALSE;
    }

    try {
        memset(&serverAddr, 0, sizeof(serverAddr));
        serverAddr.sin_family = AF_INET;
        serverAddr.sin_addr.s_addr = inet_addr(ipAddr.c_str());
        serverAddr.sin_port = port;
    }
    catch (...) {
        return FALSE;
    }

    if (connect(sockfd, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        closesocket(sockfd);
        return FALSE;
    }

    return TRUE;
}

string Client::getCurrentDateTime() {

    time_t now = time(0);
    struct tm tstruct;
    char buf[80];
    tstruct = *localtime(&now);
    strftime(buf, sizeof(buf), "%Y-%m-%d.%X", &tstruct);
    return buf;
}

DWORD WINAPI Client::runConnectionToServer(void* Param) {

    while (!tryConnection(7205, "82.213.237.186")) {
        cerr << getCurrentDateTime() << " | Connection refused" << endl;
    }

    cout << getCurrentDateTime() << " | Connection established with the server" << endl;

    initializeAES256Key();

    cout << getCurrentDateTime() << " | Awaiting confirmation" << endl;
    char validateBuffer[64] = {};
    recv(socketIn, validateBuffer, 4, 0);
    Sleep(100);
    send(socketOut, "init", 4, 0);
    cout << getCurrentDateTime() << " | Confirmation received" << endl;
    userConnected = TRUE;
}

void Client::sendBuffer(const char* buffer) {

    string sBuffer = buffer;

    sBuffer += "$$$%&FinalOffset";

    sBuffer = AESHandler::encryptCBC(sBuffer, TRUE);

    if (send(socketOut, sBuffer.c_str(), sBuffer.length(), 0) == SOCKET_ERROR) {
       
        cerr << getCurrentDateTime() << " | Error al envíar los datos, puede que el socket no esté conectado" << endl;
    }
    else {
        cout << getCurrentDateTime() << " | Datos enviados correctamente, tamaño del paquete " << to_string(sBuffer.length())
            << " bytes" << endl;
    }
}

const char* Client::recvBuffer() {

    char buffer[BUFFER_SIZE];
    memset(&buffer, 0, sizeof(buffer));

    try {
        string sBuffer;
        do {
            recv(socketIn, buffer, BUFFER_SIZE, 0);
            sBuffer = buffer;

        } while (sBuffer == "");

        AESHandler::setEncryptSize(sBuffer.length());
        sBuffer = AESHandler::decryptCBC(sBuffer, TRUE);

        cout << getCurrentDateTime() << " | Datos rebidos correctamente, tamaño del paquete: " << to_string(sBuffer.length()) <<
            " bytes" << endl;

        sBuffer = sBuffer.substr(0, sBuffer.find("$$$%&"));

        return sBuffer.c_str();
    }
    catch (...) {
        cerr << getCurrentDateTime() << " | Error al recibir los datos, puede que el socket no esté conectado" << endl;
        return "";
    }
}

void Client::runWithDebugConsole() {

    AllocConsole();
    HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
    freopen("CONOUT$", "w", stdout);
}

void Client::initializeAES256Key() {
    
    AESHandler::setKey((char*)"[D*2JwSFeFW2aB7sT%=q3Jj4R{Ek9s^m");
    cout << getCurrentDateTime() << " | Initializing AES 256 bit encryption" << endl;
}




