package tfgfinal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

public class Chat {

    @FXML
    private ImageView img;

    @FXML
    private Text namePersona;

    @FXML
    private TextField mensajeParaEnviar;

    @FXML
    private Text textito;

    @FXML
    private ScrollPane scrollPane;

    String datos = "";

    Client61 client61;

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
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enviarMensaje(MouseEvent event) {
        if (mensajeParaEnviar.getText() != "") {
            String mensaje = mensajeParaEnviar.getText();
            datos = datos + "\n" + name + ": " + "\n" + mensaje;
            textito.setText(datos);
            client61.sendBuffer(mensajeParaEnviar.getText());
            mensajeParaEnviar.setText("");
            scrollPane.setVvalue(1.0);
        }
    }

    @FXML
    void enviar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (mensajeParaEnviar.getText() != "") {
                /*chat.getItems().add("Mustielo:");*/
                String mensaje = mensajeParaEnviar.getText();
                datos = datos + "\n" + name + ": " + "\n" + mensaje;
                textito.setText(datos);
                client61.sendBuffer(mensajeParaEnviar.getText());
                mensajeParaEnviar.setText("");
                scrollPane.setVvalue(1.0);
            }
        }
    }

    String nameChat;
    String imgChat;

    void recibir() {
        new Thread() {
            @Override
            public void run() {
                int contador = 1;
                if (contador == 1) {
                    String mensaje = client61.recvBuffer();
                    nameChat = mensaje;
                    namePersona.setText(nameChat);
                    contador++;
                }

                while (true) {
                    String mensaje = client61.recvBuffer();
                System.out.println(mensaje);
                datos = datos + "\n" + nameChat + ": " + "\n" + mensaje;
                textito.setText(datos);
                scrollPane.setVvalue(1.0);
            }
        }
    }
    .start();
}

/* Función para minimizar la pantalla */
@FXML
    void minimizar(MouseEvent event) {

    }

    void setCliente(Client61 client) {
        client61 = client;
    }

    String protonmail;

    void setProtonmail(String proton) {
        protonmail = proton;

        datosJson();

        int contador = 1;
        if (contador == 1) {
            client61.sendBuffer(name);
            contador++;
        }
        recibir();
        
    }

    String name;
    String imagenURL;

    public void datosJson() {

        try {

            URL url = new URL("http://82.213.237.186:65000/getUser/protonmail/" + protonmail);
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

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(String.valueOf(informationstring));

            name = jsonNode.get("txto_nick").asText();
            imagenURL = jsonNode.get("foto_perfil").asText();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
