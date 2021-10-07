/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackjackFX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BetScreenController implements Initializable {

    @FXML
    private Label bankAmountLabel;
    @FXML
    private Button enterBetButton;
    @FXML
    private TextField betField;
    @FXML
    private Label messageLabel;
    @FXML
    private Label playerbankLabel;
    @FXML
    private Button savegameButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("Player count at start of betscreen: "+Game.getPlayercount());
        if (!checkGameOver()) {
            Game.loadHands();
            Game.resetHandLoop();
            
            updateLabels();
            
        }
                
        
        
    }    
    
    @FXML
    private void enterBetButtonPress(ActionEvent event) {
        if (checkBet()) {
            Game.handLoop().enterBet(Integer.parseInt(betField.getText()));
            betField.clear();
            Game.iterateHandLoopCounter();
            //
            if (Game.handLoop()==null) {
                switchToInGameScreen();
            }else updateLabels();
        } 
        
        
    }

    @FXML
    private void betFieldTyped(KeyEvent event) {
        checkBet();
    }
    
    private boolean checkGameOver(){

        if (Game.checkGameOver()) {
            playerbankLabel.setVisible(false);
            bankAmountLabel.setVisible(false);
            enterBetButton.setVisible(false);
            betField.setVisible(false);
            savegameButton.setVisible(false);
            playerbankLabel.setVisible(false);
            messageLabel.setText("Game over.");
            return true;
                    
        }
        else return false;
        
}
    
    private boolean checkBet(){
        //System.out.println(Game.handLoop().getPlayer().getName());
        try {Integer bet = Integer.parseInt(betField.getText());
            if (!Game.checkEnoughForBet(bet)) {
                messageLabel.setText("Not enough for bet.");
            }
            else{
                messageLabel.setText("Enter bet for "+Game.handLoop().getPlayer());
                return true;
            }
        } catch (Exception e) {
            messageLabel.setText("Enter whole numbers for bet.");
        }
        return false;
    
    
    
    }
    
    private void updateLabels(){
        messageLabel.setText("Enter bet for "+Game.handLoop().getPlayer().getName());
        bankAmountLabel.setText(String.valueOf(Game.handLoop().getPlayer().getBank()));
    
    
    }
    
    //checks for reload of deck
    private void switchToInGameScreen(){
        
        Game.getDeck().checkReloadDeck();
        
        
        Stage stage = (Stage) enterBetButton.getScene().getWindow();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("InGame.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    
    }

    @FXML
    private void handleSaveGameButton(ActionEvent event) {
        String filename  = "savegame.bin";
        ObjectOutputStream toFile = null;
        try {
            
            
            FileOutputStream fos = new FileOutputStream(filename);
            toFile= new ObjectOutputStream(fos);
            toFile.writeObject(Game.getDeck());
            toFile.writeObject(Game.getPlayers());
            messageLabel.setText("Saved game.");
            
        } catch (FileNotFoundException e) {
            
            messageLabel.setText("file not found");
        } catch (IOException ex) {
            Logger.getLogger(BetScreenController.class.getName()).log(Level.SEVERE, null, ex);
            
            messageLabel.setText("io exception 1");
        }
        
        
        
        
        
        
        
    }

    @FXML
    private void handleMainMenuButton(ActionEvent event) {
        Stage stage = (Stage) enterBetButton.getScene().getWindow();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
}
