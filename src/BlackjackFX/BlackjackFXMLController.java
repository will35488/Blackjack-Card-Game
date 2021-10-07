/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//source for card images: http://acbl.mybigcommerce.com/52-playing-cards/

package BlackjackFX;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BlackjackFXMLController implements Initializable {
    
   
    private TextField textFieldForBet;
    @FXML
    private Button dealButton;
    @FXML
    private Button hitButton;
    @FXML
    private Button stayButton;
    @FXML
    private Button doubleDownButton;
    @FXML
    private TextArea textArea;
    private HBox dealerHbox;
    private HBox hbox0;
    private HBox hbox1;
    @FXML
    private VBox vbox;
    @FXML
    private Label bankTitle;
    private ImageView dealerFirstCard;
    private int gamestate;
    @FXML
    private Button ContinueButton;
    @FXML
    private Label playerLabel;
    @FXML
    private Label countLabel;
    private int cardsize;
    
    private Label theLabel;
    @FXML
    private Label bank;
    @FXML
    private Label betAmount;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cardsize=Game.getCardSize();
        countLabel.setText(String.valueOf(Game.getDeck().getCardcount())); 
        ContinueButton.setVisible(false);
        Game.resetHandLoop();
        
        updateLabels();
        
        addHandsToVbox();
        
        
    }

    @FXML
    private void dealButtonPress(ActionEvent event) {
        dealButton.setVisible(false);
        addPlayerLabels();
        dealTableOneCard();
        
        dealTableOneCard();
        
        Game.checkNatural();
        
        updateTextArea();
        if (Game.isRoundOver()) {
            updateLabels();
            endRound();
        }
        else{
            Game.resetHandLoop();
            gamestate=1;
            
            updateLabels();
        }
    }



     
    
    
    

    @FXML
    private void handleHit(ActionEvent event) {
        hit();
        
        
    }

    @FXML
    private void handleStay(ActionEvent event) {
        stay();
    }

    @FXML
    private void handleDoubleDown(ActionEvent event) {
        doubleDown();
    }

    


    
    
    private void dealTableOneCard(){
        Game.resetHandLoop();
        
        
        while (Game.handLoop()!=null) {                
            
            drawCards(Game.handLoop().dealSingleCard());
            
            Game.iterateHandLoopCounter();    
            if (Game.handLoop()==null) {
                
                drawDealerCards(Game.getDealer().dealSingleCard());
                
            }
        }
        
     countLabel.setText(String.valueOf(Game.getDeck().getCardcount()));       
         
    
    
    }
    
    private void addPlayerLabels(){
        Game.resetHandLoop();
        Label dealer = new Label("Dealer");
        Game.getDealer().getHbox().setAlignment(Pos.CENTER_LEFT);
        Game.getDealer().getHbox().setSpacing(5);
        Game.getDealer().getHbox().getChildren().add(dealer);
        while (Game.handLoop()!=null) {            
            
            Label playername = new Label(Game.handLoop().getPlayer().getName());
            Game.handLoop().getHbox().getChildren().add(playername);
            Game.handLoop().getHbox().setSpacing(5);
            Game.handLoop().getHbox().setAlignment(Pos.CENTER_LEFT);
            Game.iterateHandLoopCounter();
        }
        
    }
    
    private void drawCards(Card card){
       
        
        
        
        String imagename = card.toString();
                
        Image cardimage = new Image("file:images\\"+imagename+".jpg");
        ImageView cardView= new ImageView(cardimage);
        
        cardView.setPreserveRatio(true);
        cardView.setFitHeight(cardsize);
        Game.handLoop().getHbox().getChildren().add(cardView);
       
    }
    
    
    

    private void doubleDown(){
        if (gamestate==1&&Game.handLoop().checkDoubleDown()) {
            drawCards(Game.handLoop().dealSingleCard());
            countLabel.setText(String.valueOf(Game.getDeck().getCardcount())); 
            Game.handLoop().doubleDown();
            Game.iterateHandLoopCounter();
            if (Game.handLoop()!=null) {
                updateLabels();
            
            }
            else{
                endHittingRound();
        }
        }
        else message.setText("Cannot double down.");
        
        
    }
    
    
    private void hit(){

        
        
        if (gamestate==1&&Game.handLoop()!=null&&Game.handLoop().checkNoMoreHits()==false) {
            drawCards(Game.handLoop().dealSingleCard());
            countLabel.setText(String.valueOf(Game.getDeck().getCardcount())); 
            
            if (Game.checkRemoveHandAfterHit()) {
                updateTextArea();
                updateLabels();
                if (Game.isRoundOver()) {
                    endRound();
                } 
                else if (Game.handLoop()==null) {
                    endHittingRound();
                }
            }
            else {
                ///Game.iterateHandLoopCounter();
                //updateLabels();
            
            }
            
        }
        else{
            message.setText("Cannot hit.");
            }
        
        
        
        
    
    
    
    }
    
    private void stay(){
        
        if (gamestate==1&&Game.handLoop()!=null) {
            Game.iterateHandLoopCounter();
                 
            if (Game.handLoop()==null) {
                endHittingRound();
            
         }
            else{
                updateLabels();
                
            }
    
        }
        else{
            message.setText("Cannot stay.");
            
        }
    }

    private void dealerTurn(){
        flipDealerCard();
        while (Game.checkDealCardsToDealer()) {            
            drawDealerCards(Game.getDealer().dealSingleCard());
            countLabel.setText(String.valueOf(Game.getDeck().getCardcount())); 
        }
        
    
    
    }
    
    private void endHittingRound(){
        gamestate=0;
        dealerTurn();
        Game.determineWinners();
        updateTextArea();
        endRound();
    
    
    
    
    }
    
    private void endRound(){
        

        
        
        gamestate=0;
        flipDealerCard();
        textArea.appendText("\nRound over.");
        textArea.appendText(Game.checkRemovePlayers());
        
        ContinueButton.setVisible(true);
        
        
        
    }
    
    @FXML
    private void handleContinueButton(ActionEvent event) {
        switchToBettingScreen();
    }
    
    private void switchToBettingScreen(){
        
        
        Stage stage = (Stage) hitButton.getScene().getWindow();
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
    


    
    

    
    private void drawDealerCards(Card card){
        if (Game.getDealer().getCardlist().size()==1) {
             Image cardback = new Image("file:images\\back.jpg");
             dealerFirstCard = new ImageView(cardback);
             dealerFirstCard.setPreserveRatio(true);
            dealerFirstCard.setFitHeight(cardsize);
            Game.getDealer().getHbox().getChildren().add(dealerFirstCard);
        }
        else{
            String imagename = card.toString();
                    //handLoop().getCardlist().get(cardindex).toString();
            Image cardimage = new Image("file:images\\"+imagename+".jpg");
            ImageView cardView= new ImageView(cardimage);




            cardView.setPreserveRatio(true);
            cardView.setFitHeight(cardsize);



            Game.getDealer().getHbox().getChildren().add(cardView);
        }
    
    
    
    
    }
    
    private void flipDealerCard(){
        String imagename = Game.getDealer().getCardlist().get(0).toString();
        Image cardimage = new Image("file:images\\"+imagename+".jpg");
        dealerFirstCard.setImage(cardimage);
    
    
    }
    
    
    
    private void updateLabels(){
        if (Game.handLoop()!=null) {
            message.setText("");
            playerLabel.setText(Game.handLoop().getPlayer().getName());
            bank.setText(String.valueOf(Game.handLoop().getPlayer().getBank()));
            betAmount.setText(String.valueOf(Game.handLoop().getBet()));
        }
        else{
            message.setText("");
            playerLabel.setText("");
            bank.setText("");
            betAmount.setText("");
        
        }
        
        
    
    }
    
    private void updateTextArea(){
        String update = Game.getResultstringbuilder();
        textArea.appendText(update);
        
    }
    
    private void addHandsToVbox(){
        Game.resetHandLoop();
        vbox.getChildren().add(Game.getDealer().getHbox());
        for (int i = 0; i < Game.getPlayercount(); i++) {
            vbox.getChildren().add(Game.handLoop().getHbox());
            Game.iterateHandLoopCounter();
        }


}


    
}
