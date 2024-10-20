
package javabrabo;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Brenda
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox<String> ComboBoxpartida;
    @FXML
    private ComboBox<String> ComboBoxdestino;
    @FXML
    private Label lblResultado;
    @FXML
    private Label lblLitros;
    @FXML
    private TextField txtKM; 
    @FXML
    private  Button btnButton;
    @FXML
    private AnchorPane Secund_Window;
    
      private DecimalFormat estado= new DecimalFormat("#.00");
              
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
                String partida = ComboBoxpartida.getValue();
                String destino = ComboBoxdestino.getValue();

        if (partida != null && destino != null &&!partida.equals(destino)) {
            Travel viagem = new Travel();
            viagem.setPartida(partida);
            viagem.setDestino(destino);
            // Defina valores para KML e RSL para a demonstração, você pode pegar isso de outra entrada
           try {
                int kml = Integer.parseInt(txtKM.getText());
                 viagem.setKml(kml);
                
            } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para KML");
                
                
                return;
            }
            double litros = viagem.LitrosGastos();
            double kml = viagem.DistanciaAB();
           
            lblResultado.setText(estado.format(kml) + " km");
            lblLitros.setText(estado.format(litros) + " L");
            
        } else {
             JOptionPane.showMessageDialog(null, "Por favor, selecione uma cidade de partida e uma cidade de destino. ATENÇÃO NÃO PODE SER IGUAIS");
              
      }
    
}
    
   @FXML
    void Hyperlink(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link clicked");
    Desktop.getDesktop().browse(new URI( "Https://wa.me/qr/J4VJEMJP6VMFA1"));
}
    
   @FXML
    void Button(ActionEvent event) {
      
          
        try {
           
       FXMLLoader fxml = new FXMLLoader(getClass().getResource("FXML.fxml"));
        Parent root =  fxml.load();
            Stage stage = new Stage();
         
            stage.setTitle("Secund Window");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
       }
       
    }

  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         for (String cidade : Travel.getCidades().keySet()) {
            ComboBoxpartida.getItems().add(cidade);
            ComboBoxdestino.getItems().add(cidade);
        }
    }    
    
}
