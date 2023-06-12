#include "MultiThreadServer.hpp"

unsigned short int MultiThreadServer::port;
int MultiThreadServer::sockfd;
struct sockaddr_in MultiThreadServer::serverAddr, MultiThreadServer::clientAddr;
socklen_t MultiThreadServer::clientAddrLen;
vector<Client*> MultiThreadServer::clientsOnHold;

string MultiThreadServer::getCurrentDateTime() {

    time_t now = time(0);
    struct tm tstruct;
    char buf[80];
    tstruct = *localtime(&now);
    strftime(buf, sizeof(buf), "%Y-%m-%d.%X", &tstruct);
    return buf;
}

bool MultiThreadServer::initializeSettings(unsigned short port, unsigned int maxClients) {

    sockfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (sockfd == -1) {
        cerr << "Error al crear el socket del servidor [Error code]:" << strerror(errno) << endl;
        close(sockfd);
        return false;
    }
    cout << "Socket del servidor creado correctamente" << endl;

    try {
        memset(&serverAddr, 0, sizeof(serverAddr));
        serverAddr.sin_family = AF_INET;
        serverAddr.sin_addr.s_addr = INADDR_ANY;
        serverAddr.sin_port = port;
        clientAddrLen = sizeof(clientAddr);
    }
    catch (...) {
        cerr << "Error al asignar ip y puerto a la estructura del socket [Error code]:" << strerror(errno) << endl;
        return false;
    }

    if (::bind(sockfd, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == -1) {
        cerr << "Error al enlazar el socket [Error code]:" << strerror(errno) << endl;
        close(sockfd);
        return false;
    }
    cout << "Socket enlazado correctamente" << endl;

    if (listen(sockfd, maxClients) == -1) {
        cerr << "Error al poner el socket a la escucha [Error code]:" << strerror(errno) << endl;
        close(sockfd);
        return false;
    }
    cout << "Socket puesto a la escucha" << endl;
    return true;
}

void MultiThreadServer::runListening() {

    while (true) {

        int newClientSocketIn = accept(sockfd, (struct sockaddr*)&clientAddr, &clientAddrLen);
        int newClientSocketOut = accept(sockfd, (struct sockaddr*)&clientAddr, &clientAddrLen);

        if ((newClientSocketIn | newClientSocketOut) == -1) {
            cerr << "Error al aceptar la conexi�n entrante [Error code]:" << strerror(errno) << endl;
            continue;
        }

        clientsOnHold.push_back(new Client(newClientSocketIn, newClientSocketOut));

        cout << getCurrentDateTime() << " | New client connected" << endl;

        if (validateTwoClientsOnHold()) {

            clientsHandlerThread(clientsOnHold[clientsOnHold.size() - 2], clientsOnHold[clientsOnHold.size() - 1]);
            clientsOnHold.clear();
        }
    }
}

bool MultiThreadServer::validateTwoClientsOnHold() {

    if (clientsOnHold.size() == 2) {

        char buffer[64] = {};

        if ((send(clientsOnHold[0]->socketContextOut, "init", 4, 0) != 0) && recv(clientsOnHold[0]->socketContextIn, buffer, 4, 0) > 0) {
            send(clientsOnHold[1]->socketContextOut, "init", 4, 0);
            recv(clientsOnHold[1]->socketContextIn, buffer, 4, 0);
            return true;
        }
        cout << getCurrentDateTime() << " | Unable to create clients thread context" << endl;
        clientsOnHold.erase(clientsOnHold.begin());
    }
    return false;
}

void* MultiThreadServer::recvClientContextAsync(void* arg) {

    char buffer[BUFFER_SIZE] = { 0 };

    tuple<Client*, Client*, bool>* params = static_cast<std::tuple<Client*, Client*, bool>*>(arg);

    while (true) {

        memset(&buffer, 0, sizeof(buffer));

        if (get<2>(*params)) {
            if (recv(get<0>(*params)->socketContextIn, buffer, sizeof(buffer), 0) != 0) {
                send(get<1>(*params)->socketContextOut, buffer, sizeof(buffer), 0);
            }
            else {
                /*
                    Mensaje a modo de clave que serviría para notificar a un cliente de la desconexión del otro.
                    No se llegó a hacer por falta de tiempo.
                */
                //send(get<1>(*params)->socketContextOut, "2Jt4V9k6N7y8Xp3D1E5qR7gA4cF2hS9j", 32, 0);
                break;
            }
        }
        else {
            if (recv(get<1>(*params)->socketContextIn, buffer, sizeof(buffer), 0) != 0) {
                send(get<0>(*params)->socketContextOut, buffer, sizeof(buffer), 0);
            }
            else {
                //send(get<0>(*params)->socketContextOut, "2Jt4V9k6N7y8Xp3D1E5qR7gA4cF2hS9j", 32, 0);
                break;
            }
        }
    }
}

void MultiThreadServer::clientsHandlerThread(Client* clientContext, Client* clientContext2) {

    pthread_t thread, thread2;

    auto params = new tuple<Client*, Client*, bool>(clientContext, clientContext2, true);
    auto params2 = new tuple<Client*, Client*, bool>(clientContext, clientContext2, false);

    int result = pthread_create(&thread, nullptr, recvClientContextAsync, static_cast<void*>(params));
    int result2 = pthread_create(&thread2, nullptr, recvClientContextAsync, static_cast<void*>(params2));

    if (result != 0 || result2 != 0) {
        cerr << "Error al crear un contexto de hilo para los clientes" << strerror(errno) << endl;
    }
    else {
        cout << getCurrentDateTime() << " | New clients context chat created" << endl;
    }

    //thread recvClientContextAsyncThread([this, clientContext, clientContext2] {recvClientContextAsync(clientContext, clientContext2);});
    //thread recvClientContextAsyncThread2([this, clientContext, clientContext2] {recvClientContextAsync2(clientContext, clientContext2);});

    pthread_detach(thread);
    pthread_detach(thread2);
}


