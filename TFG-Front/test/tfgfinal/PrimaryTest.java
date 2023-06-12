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
public class PrimaryTest {
    
    public PrimaryTest() {
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
     * Testing del metodo abrirPanel2, de la clase Primary.
     */
    @Test
    public void testAbrirPanel2() {
        System.out.println("abrirPanel2");
        MouseEvent event = null;
        Primary instance = new Primary();
        instance.abrirPanel2(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo abrirAyuda, de la clase Primary.
     */
    @Test
    public void testAbrirAyuda() {
        System.out.println("abrirAyuda");
        MouseEvent event = null;
        Primary instance = new Primary();
        instance.abrirAyuda(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo cerraAyuda method, de la clase Primary.
     */
    @Test
    public void testCerraAyuda() {
        System.out.println("cerraAyuda");
        MouseEvent event = null;
        Primary instance = new Primary();
        instance.cerraAyuda(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo initializeClient method, de la clase Primary.
     */
    @Test
    public void testInitializeClient() throws Exception {
        System.out.println("initializeClient");
        MouseEvent event = null;
        Primary instance = new Primary();
        instance.initializeClient(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo ajustes method, de la clase Primary.
     */
    @Test
    public void testAjustes() throws Exception {
        System.out.println("ajustes");
        MouseEvent event = null;
        Primary instance = new Primary();
        instance.ajustes(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo datosJson method, de la clase Primary.
     */
    @Test
    public void testDatosJson() {
        System.out.println("datosJson");
        Primary instance = new Primary();
        instance.datosJson();
        fail("ERROR");
    }

    
}
