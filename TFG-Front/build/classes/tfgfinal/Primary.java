package tfgfinal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.Native;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Primary implements Initializable{
    
    @FXML
    private ImageView ayuda;

    @FXML
    private Text textoBusquedaId;
    
    @FXML
    private TextField introducirId;
    
    @FXML
    private Pane background;

    @FXML
    private Pane cerra;
    
    @FXML
    private Pane cerraAyuda;

    @FXML
    private ImageView flechas;
    
    @FXML
    private Pane headerInicial;

    @FXML
    private Pane minimizar;

    @FXML
    private Pane panelAyuda;

    @FXML
    private Text textoOnline;
    
    @FXML
    private Pane xAyuda;
    
    @FXML
    private Pane panel2;
    
    @FXML
    private ImageView img,imgMundo;
    
    @FXML
    private Text namePersona;
    
    @FXML
    private Text textoGuardado;
    
    @FXML
    private Button botonBusqueda;
    
    @FXML
    private CheckBox DebugConsol;
   
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    Client61 client61; 

    /* Función para abrir el segundo panel */
    
    @FXML
    void abrirPanel2(MouseEvent event) {
        panel2.setVisible(true);
    }
    
    /* Función para abrir el panel del apartado de ayuda */
    
    @FXML
    void abrirAyuda(MouseEvent event) {
        panelAyuda.setOpacity(1);
        textoOnline.setText("BUSQUEDA ONLINE: Busca a un usuario aleatorio que esté buscando a la vez que tu.");
        cerraAyuda.setOpacity(1);
        botonBusqueda.setDisable(true);
        imgMundo.setOpacity(0);
    }
    
    /* Función para cerrar el panel del apartado de ayuda */
    
    @FXML
    void cerraAyuda(MouseEvent event) {
        panelAyuda.setOpacity(0);
        textoOnline.setText("");
        cerraAyuda.setOpacity(0);
        botonBusqueda.setDisable(false);
        imgMundo.setOpacity(1);
    }
    
    /* Función que se utiliza para poder arrastrar la ventana acutal, también se utiliza para quitarle los bordes predeterminados */
    
    @FXML
    void arrastrar(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setOpacity(0.85);
        node.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - event.getSceneX());
            stage.setY(e.getScreenY() - event.getSceneY());
        });
        node.setOnMouseReleased(e -> {
            stage.setOpacity(1);
        });
    }
    
    /* Función para cerrar la ventana actual*/
    
    @FXML
    void cerra(MouseEvent event) {
        System.exit(0);
    }
    
    /* Función para minimizar la pantalla */
    
    @FXML
    void minimizar(MouseEvent event) {
        
    }
    
    @FXML
    void initializeClient(MouseEvent event) throws IOException {
        
        client61 = Native.load("tfgfinal\\DLLClient61.dll", Client61.class);
        client61.initializeClientAsync();
          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carga.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   
        /* datos a pasar */
    
        CargaController cliente = loader.getController();
        cliente.setCliente(client61);
        if (DebugConsol.isSelected()){
            client61.runWithDebugConsole();
        }
        
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }
    
    /* Función para pasar a la pantalla de ajustes, pero en este caso no se cierra la anterior ventana simplemente se queda esperando */
    
    
    @FXML
    void ajustes(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajustes.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
 
    }
    
    String name;
    String imagenURL;
    
    public void datosJson() {
        
        try {
            String json = "{\"nmro_id\": 2,\"txto_protonmail\": \"b@protonmail.com\",\"txto_psswrd\": \"afd34cd672a4d020f6bf56313be07d7626e2a09941274f1f75f6ab7f3a9ebe86\",\"foto_perfil\": \"https://pbs.twimg.com/profile_images/1243666716472852480/FHAySN3t_400x400.jpg\",\"txto_nick\": \"Blacky\",\"cdgo_rango\": 8}";
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            
            name = jsonNode.get("txto_nick").asText();
            imagenURL = jsonNode.get("foto_perfil").asText();

            namePersona.setText(name);
            img.setImage(new Image(imagenURL));
            
            
        } catch (IOException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        datosJson();
    }
}
