/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackjackFX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MainScreenController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private ChoiceBox<String> playerChoiceBox;
    @FXML
    private Button newGameButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playerChoiceBox.getItems().add("1 Player");
        playerChoiceBox.getItems().add("2 Player");
        playerChoiceBox.getItems().add("3 Player");
        playerChoiceBox.getItems().add("4 Player");
        Game.initialize();
        messageLabel.setText("");
    }    

    @FXML
    private void newGameButtonPress(ActionEvent event) {
        String playernumber = playerChoiceBox.getValue();
        
        if (playernumber==null) {
            messageLabel.setText("Please choose number of players.");
        }
        else
        switch (playernumber){
            case "1 Player":
                Game.setPlayercount(1);
                createPlayers();
                break;
            case "2 Player":
                Game.setPlayercount(2);
                createPlayers();
                break;
            case "3 Player":
               Game.setPlayercount(3);
                createPlayers();
                break;
            case "4 Player":
                Game.setPlayercount(4);
                createPlayers();
                break;
            
        
        
        
        }
        
       // if (playerChoiceBox.getValue()) {
            
       // }
    }

    @FXML
    private void exitButtonPress(ActionEvent event) {
        System.exit(0);
    }
    
    //creates players and calls switchscreen
    private void createPlayers(){
        Game.createPlayers();
        switchBettingScreen();
    
    }
    
    

    
    private void switchBettingScreen() {
       // System.out.println("Player count at end of mainscreen: "+Game.getPlayercount());
        
        
        Stage stage = (Stage) newGameButton.getScene().getWindow();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("BetScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void handleLoadGameButton(ActionEvent event) {
        ObjectInputStream fromfile = null;
        String filename  = "savegame.bin";
        
        
        try {
            FileInputStream fis = new FileInputStream(filename);
            fromfile = new ObjectInputStream(fis);
            Game.setDeck((Shoe)fromfile.readObject());
            Game.setPlayers((ArrayList<Player>)fromfile.readObject());
            switchBettingScreen();
            
            //deckfromfile = (Deck)fromfile.readObject();
            //playerfromfile = (Player)fromfile.readObject();
        } catch (FileNotFoundException e) {
            messageLabel.setText("No previous game to load.");
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            messageLabel.setText("IOException");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            messageLabel.setText("No previous game to load.");
        }
        
    }
    
    
}
