package tfgfinal;

import com.sun.jna.platform.unix.X11;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
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
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

public class Ajustes {

    @FXML
    private Pane background;

    @FXML
    private Pane cerra;

    @FXML
    private Pane headerInicial;

    @FXML
    private TextField imagenPerfil;

    @FXML
    private TextField nombrePerfil;

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    String IdApi;

    void setId(String id) {
        IdApi = id;
    }

    @FXML
    void guardarAjustes(MouseEvent event) {
        imagenPerfil.getText();
        nombrePerfil.getText();

        if (nombrePerfil.getText() != "") {
            try {
                // Crear instancia de HttpClient
                CloseableHttpClient httpClient = HttpClients.createDefault();

                // URL de la API y el recurso que deseas actualizar
                String url = "http://82.213.237.186:65000/changeUser/name/" + IdApi;

                // Crear solicitud PUT
                HttpPut httpPut = new HttpPut(url);

                // Crear el cuerpo de la solicitud PATCH
                String jsonBody = "{ \"txto_nick\": \"" + nombrePerfil.getText() + "\" }";
                httpPut.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

                // Enviar la solicitud PATCH y obtener la respuesta
                HttpResponse response = httpClient.execute(httpPut);

                // Cerrar el HttpClient
                httpClient.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (imagenPerfil.getText() != "") {
            
            try {
                // Crear instancia de HttpClient
                CloseableHttpClient httpClient = HttpClients.createDefault();

                // URL de la API y el recurso que deseas actualizar
                String url = "http://82.213.237.186:65000/changeUser/foto/" + IdApi;

                // Crear solicitud PUT
                HttpPut httpPut = new HttpPut(url);

                // Crear el cuerpo de la solicitud PATCH
                String jsonBody = "{ \"foto_perfil\": \"" + imagenPerfil.getText() + "\" }";
                httpPut.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

                // Enviar la solicitud PATCH y obtener la respuesta
                HttpResponse response = httpClient.execute(httpPut);

                // Cerrar el HttpClient
                httpClient.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
           
    }

    /* Función para cerrar la ventana actual*/
    @FXML
    void cerra(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Primary id = loader.getController();
            id.setId(IdApi);
            
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

    /* Función para minimizar la pantalla */
    @FXML
    void minimizar(MouseEvent event) {

    }

}
