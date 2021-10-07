
package BlackjackFX;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.HBox;

/**
 *
 * @author User
 */
public class Game implements Serializable{
    private static int playercount;
    private static int handloopcounter;
    private static ArrayList<Hand> hands;
    private static ArrayList<Player> players;
    private static Hand dealer;
    private static Shoe deck;
    private static StringBuilder resultstringbuilder;
    
    public Game(){
//        playercount=0;
//        handloopcounter=0;
//        hands = new ArrayList<Hand>();
//        players = new ArrayList<Player>();
//        deck = new Deck();
//        dealer = new Hand(deck);
        
    
    }
    
    public static void initialize(){
        playercount=0;
        handloopcounter=0;
        hands = new ArrayList<Hand>();
        players = new ArrayList<Player>();
        deck = new Shoe();
        dealer = new Hand(deck);
        resultstringbuilder = new StringBuilder();
    
    
    }
    
    public static boolean checkEnoughForBet(Integer betamount){
        if (betamount>handLoop().getPlayer().getBank()){
            return false;
        
        
        }
        else return true;

    }
    
    public int checkHandDone(Hand hand){
        if (hand.isOver21()) {
            return 1;
        }
        else if (hand.isTwentyone()){
            return 0;
        
        }
        else return -1;

    }
    
    //returns 4 for blackjack, 2 for tie, 1 for player loses, 0 for continue
    public static void checkNatural(){
        resetHandLoop();
        while (handLoop()!=null) {            
            
        
        
            if (handLoop().getHighestHandValue()==21&&dealer.getHighestHandValue()==21) {
                removeHand(2);
                
            }
            else if (handLoop().getHighestHandValue()==21&&dealer.getHighestHandValue()!=21) {
                removeHand(4);
                
            } 
            else if (handLoop().getHighestHandValue()!=21&&dealer.getHighestHandValue()==21) {
                removeHand(1);
                
            } 
            else iterateHandLoopCounter();
            //else return 0;
        }
        
    }
    
    //4 blackjack, 3 win, 2 tie, 1 lose
    //calls settlebet, and resultstring
    private static void removeHand(int handresult){
        if (handresult==4) {
            //textArea.appendText("\n"+handLoop().getPlayer()+" wins, blackjack!");
            settleBet(handresult);
            resultString(handresult);
            hands.remove(handloopcounter);
        } else if (handresult==3) {
            //textArea.appendText("\n"+handLoop().getPlayer()+" wins!");
            settleBet( handresult);
            resultString(handresult);
            hands.remove(handloopcounter);
        } else if (handresult==2) {
            //textArea.appendText("\n"+handLoop().getPlayer()+" ties!");
            settleBet(handresult);
            resultString(handresult);
            hands.remove(handloopcounter);
        } else if (handresult==1) {
            //textArea.appendText("\n"+handLoop().getPlayer()+" loses!");
            settleBet( handresult);
            resultString(handresult);
            hands.remove(handloopcounter);
        }
        
        
        
        
    
    }
    
    public static void settleBet(int handresult){
        if (handresult==4) {
            double addtobank =(handLoop().getBet()*1.5)+handLoop().getBet();
            double oldbank = handLoop().getPlayer().getBank();
            Double newbank = addtobank+oldbank;
            double roundednewbank = Math.rint(newbank);
            handLoop().getPlayer().setBank(roundednewbank);
            //hand.addToBank(win+hand.getBet());
        }
        if (handresult==3) {
            double addtobank =handLoop().getBet()*2;
            double oldbank = handLoop().getPlayer().getBank();
            double newbank = addtobank+oldbank;
            handLoop().getPlayer().setBank(newbank);
        }
        if (handresult==2) {
            double addtobank =handLoop().getBet();
            double oldbank = handLoop().getPlayer().getBank();
            double newbank = addtobank+oldbank;
            handLoop().getPlayer().setBank(newbank);
        }
        
    
    
    
    }
    
    private static void resultString(int handresult){
        String returnstring = "";
        switch (handresult){
            case 4:
                returnstring = "\n"+handLoop().getPlayer()+" wins, blackjack!";
                resultstringbuilder.append(returnstring);
                break;
            //return  returnstring;
            case 3:
                returnstring = "\n"+handLoop().getPlayer()+" wins!";
                resultstringbuilder.append(returnstring);
                break;
                //return returnstring;
            case 2:
                returnstring = "\n"+handLoop().getPlayer()+" ties!";
                resultstringbuilder.append(returnstring);
                break;
                //return returnstring;
            case 1:
                returnstring = "\n"+handLoop().getPlayer()+" loses!";
                resultstringbuilder.append(returnstring);
                break;
                //return returnstring;
        
        
        }
        //return returnstring;
    
    
    
    }
    

    
    public static void determineWinners(){
        handloopcounter=0;
        
        for (int i = 0; i < hands.size(); ) {
            if (handLoop().getHighestHandValue()==dealer.getHighestHandValue()) {
                removeHand(2);
            }
            else if (handLoop().getHighestHandValue()>dealer.getHighestHandValue()) {
                removeHand(3);
            }
            else if (handLoop().getHighestHandValue()<dealer.getHighestHandValue()) {
                removeHand(1);
            }
        }
        
        
        
        
        
    
    
    
    }
    
    
    public static boolean checkRemoveHandAfterHit(){
        if (handLoop().isOver21()) {
            removeHand(1);
            //System.out.println("Removed hand after hit");
            return true;
        }
        else return false;
    }
    
    public void dealerTurn(Hand dealer){
        while ((dealer.getHighestHandValue()<17)&&(dealer.getHighestHandValue()>=0)) {            
            dealer.dealSingleCard();
        }
    
    
    
    }
    
    public void doubleDown(Hand hand){
        
    
    
    }
    
    public static void createPlayers(){
        for (int i = 0; i < playercount; i++) {
            int playernumber = i+1;
            String playername = "Player "+playernumber;
            players.add(new Player(playername));
            //System.out.println("Player added.");
            //System.out.println("Hand list size"+hands.size());
        }
    
        loadHands();
    
    
    }
    
    public static boolean checkGameOver(){
        if (players.isEmpty()) {
            return true;
        }
        return false;
    
    
    
    }
    
    public static String checkRemovePlayers(){
        StringBuilder returnstring = new StringBuilder();
        
        for (int i = 0; i < players.size(); ) {
            if (players.get(i).getBank()<1) {
                
                returnstring.append("\n"+players.get(i).getName()+" out of chips, removed from game.");
                players.remove(i);
            }
            else i++;
        }
        return returnstring.toString();
        
    }
    
    public static void loadHands(){
        if (!hands.isEmpty()) {
            hands.clear();
        }
        playercount=players.size();
        
        dealer=null;
        dealer = new Hand(deck);
        //vbox.getChildren().clear();
        //vbox.getChildren().add(dealer.getHbox());
        for (int i = 0; i < players.size(); i++) {
      
            
            //System.out.println("hboxnumber in reloadhands: "+hboxnumber);
            
            hands.add(i, new Hand(players.get(i), deck));
            //System.out.println("Hand added");
            //vbox.getChildren().add(newbox);
            //System.out.println("Hand list size"+hands.size());
        }
    
    }
    
    
        public static Hand handLoop(){
        
        if (handloopcounter<hands.size()) {
            Hand returnhand =hands.get(handloopcounter);
        
        return returnhand;
        } else{
            return null;
        }
        
        
    
    
    }

    public static  void setPlayercount(int playercount) {
        Game.playercount = playercount;
    }

    public static void setHandloopcounter(int handloopcounter) {
        Game.handloopcounter = handloopcounter;
    }
    
    public static void iterateHandLoopCounter(){
        handloopcounter++;
    
    
    }
    
    public static void resetHandLoop(){
        handloopcounter=0;
    
    }    
    
    
    public static boolean isRoundOver(){
        if (hands.isEmpty()) {
            return true;
        }
        else return false;
    
    }

    public static int getPlayercount() {
        return playercount;
    }

    public static ArrayList<Hand> getHands() {
        return hands;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static Hand getDealer() {
        return dealer;
    }

    public static String getResultstringbuilder() {
        String returnstring = resultstringbuilder.toString();
        resultstringbuilder= new StringBuilder();
        return returnstring;
    }

    public static int getHandloopcounter() {
        return handloopcounter;
    }
    

    
    public static boolean checkDealCardsToDealer(){
        if ((dealer.getHighestHandValue()<17)&&(dealer.getHighestHandValue()>=0)) {
            return true;
        }
        else return false;
    
    
    }

    public static Shoe getDeck() {
        return deck;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Game.players = players;
    }

    public static void setDeck(Shoe deck) {
        Game.deck = deck;
    }
     
    public static int getCardSize(){
        int playernumber = players.size();
        switch (playernumber){
            case 0:
                return 100;
                
            case 1:
                return 150;
                
            case 2:
                return 140;
                
            case 3:
                return 120;
                
            case 4:
                return 110;
                
        
        
        
        }
    
    return 100;
    
    }
    
      
}
