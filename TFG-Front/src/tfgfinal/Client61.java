package tfgfinal;

import com.sun.jna.Library;

public interface Client61 extends Library{
    
    boolean userConnected();
    void initializeClientAsync();
    void sendBuffer(String buffer);
    String recvBuffer();
    String validateDll();
    void runWithDebugConsole();
    
}
