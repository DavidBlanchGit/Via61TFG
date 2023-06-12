package tfgfinal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class LogIn implements Initializable {

    @FXML
    private Pane background;

    @FXML
    private Pane cerra;

    @FXML
    private Pane headerInicial;

    @FXML
    private Pane minimizar;

    @FXML
    private TextField contraseñaPerfil;

    @FXML
    private TextField protonmailPerfil;

    @FXML
    private Text textoError;

    private Parent root;
    private Stage stage;
    private Scene scene;

    protected String imgPerfil;
    protected String nomPerfil;

    private static String getSHA256Hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    String pass;


    /*Función para pasar de pantalla (Del Log-In a la selección de función) se pasa la información introducida com el nombre y foto de perfil*/
    @FXML
    void pasarPantalla(MouseEvent event) throws IOException {

        try {

            URL url = new URL("http://82.213.237.186:65000/getUser/protonmail/" + protonmailPerfil.getText());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responsecode = con.getResponseCode();

            StringBuilder informationstring = new StringBuilder();
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                informationstring.append(sc.nextLine());
            }
            sc.close();
            JSONObject dataObject = new JSONObject(String.valueOf(informationstring));
            System.out.println(informationstring);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (getSHA256Hash(contraseñaPerfil.getText()).equals(pass)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.show();

            Stage stage = (Stage) background.getScene().getWindow();
            stage.close();
        } else {
            textoError.setText("La contraseña es incorrecta");
        }
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

    @FXML
    void quitarError(MouseEvent event) {
        textoError.setText("");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
