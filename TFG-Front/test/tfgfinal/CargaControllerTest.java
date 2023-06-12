/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tfgfinal;

import java.net.URL;
import java.util.ResourceBundle;
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
public class CargaControllerTest {
    
    public CargaControllerTest() {
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

    /**
     * Testing del metodo pasar, de la clase CargaController.
     */
    @Test
    public void testPasar() {
        System.out.println("pasar");
        CargaController instance = new CargaController();
        instance.pasar();
        fail("ERROR");
    }

    /**
     * Testing del metodo initialize, de la clase CargaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        CargaController instance = new CargaController();
        instance.initialize(url, rb);
        fail("ERROR");
    }

    /**
     * Testing del metodo setCliente, de la clase CargaController.
     */
    @Test
    public void testSetCliente() {
        System.out.println("setCliente");
        Client61 client61 = null;
        CargaController instance = new CargaController();
        instance.setCliente(client61);
        fail("ERROR");
    }

    /**
     * Testing del metodo conectado, de la clase CargaController.
     */
    @Test
    public void testConectado() {
        System.out.println("conectado");
        CargaController instance = new CargaController();
        instance.conectado();
        fail("ERROR");
    }

    /**
     * Testing del metodo abrirChat, de la clase CargaController.
     */
    @Test
    public void testAbrirChat() throws Exception {
        System.out.println("abrirChat");
        MouseEvent event = null;
        CargaController instance = new CargaController();
        instance.abrirChat(event);
        fail("ERROR");
    }
    
}
