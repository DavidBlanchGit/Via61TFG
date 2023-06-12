package tfgfinal;

import com.sun.jna.Native;
import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CargaController implements Initializable {

    @FXML
    private Pane background;

    @FXML
    private Pane cerra;

    @FXML
    private Pane headerInicial;
    
    @FXML
    private Button listo;
    
    Client61 client; 
    private Parent root;
    private Stage stage;
    private Scene scene;
    
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
    
    @FXML
    void cerra(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }

    
    void pasar(){
        new Thread() {
            @Override
            public void run(){
                while(!client.userConnected()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Primary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                conectado();
            }
        }.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pasar();
    }    

    void setCliente(Client61 client61) {
        client=client61;
    }
    
    String proton;
    
    void setProton(String protonmail){
        proton=protonmail;
    }
    
    void conectado(){
        listo.setOpacity(1);
        listo.setDisable(false);
    }
    
    @FXML
    void abrirChat(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
        Chat cliente = loader.getController();
        cliente.setCliente(client);
        cliente.setProtonmail(proton);
        
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
        
        Stage stage = (Stage) background.getScene().getWindow();
        stage.close();
    }
    
}
