/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tfgfinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class ChatTest {
    
    public ChatTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /*
        Se excluyen los metodos de enviar y recibir 
        ya que estan en constante escucha y no devolver ningun valor
    */
    
    /**
     * Testing del metodo minimizar, de la clase Chat.
     */
    @Test
    public void testMinimizar() {
        System.out.println("minimizar");
        MouseEvent event = null;
        Chat instance = new Chat();
        instance.minimizar(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo setCliente, de la clase Chat.
     */
    @Test
    public void testSetCliente() {
        System.out.println("setCliente");
        Client61 client = null;
        Chat instance = new Chat();
        instance.setCliente(client);
        fail("ERROR");
    }

    /**
     * Testing del metodo datosJson, de la clase Chat.
     */
    @Test
    public void testDatosJson() {
        System.out.println("datosJson");
        Chat instance = new Chat();
        instance.datosJson();
        fail("ERROR");
    }

    
}
