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


public class LogInTest {
    
    public LogInTest() {
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
     * Testing del metodo pasarPantalla, de la clase LogIn.
     */
    @Test
    public void testPasarPantalla() throws Exception {
        System.out.println("pasarPantalla");
        MouseEvent event = null;
        LogIn instance = new LogIn();
        instance.pasarPantalla(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo arrastrar, de la clase LogIn.
     */
    @Test
    public void testArrastrar() {
        System.out.println("arrastrar");
        MouseEvent event = null;
        LogIn instance = new LogIn();
        instance.arrastrar(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo quitarError, de la clase LogIn.
     */
    @Test
    public void testQuitarError() {
        System.out.println("quitarError");
        MouseEvent event = null;
        LogIn instance = new LogIn();
        instance.quitarError(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo cerra, de la clase LogIn.
     */
    @Test
    public void testCerra() {
        System.out.println("cerra");
        MouseEvent event = null;
        LogIn instance = new LogIn();
        instance.cerra(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo minimizar, de la clase LogIn.
     */
    @Test
    public void testMinimizar() {
        System.out.println("minimizar");
        MouseEvent event = null;
        LogIn instance = new LogIn();
        instance.minimizar(event);
        fail("ERROR");
    }

    /**
     * Testing del metodo initialize, de la clase LogIn.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        LogIn instance = new LogIn();
        instance.initialize(location, resources);
        fail("ERROR");
    }
    
}
