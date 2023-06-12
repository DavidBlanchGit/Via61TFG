#pragma once
#include "plusaes.hpp"

using namespace std;
using namespace plusaes;

class AESHandler {
public:
    static void setKey(char key[17]);
    static string encryptCBC(string rawData, bool control);
    static string decryptCBC(string rawData, bool control);
    static void setEncryptSize(unsigned int size);

    static vector<unsigned char> key;

private:

    static unsigned long encryptedSize;
    static const unsigned char iv[16];
};
