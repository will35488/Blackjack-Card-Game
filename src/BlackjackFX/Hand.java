
package BlackjackFX;





import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 *
 * @author User
 */
public class Hand implements Serializable{
    
    private ArrayList<Card> cardlist; 
    private ArrayList<Integer> handvalueslist; 
    private ArrayList<Integer> handcomparelist;
    
    private int handvalue;
    
    private boolean is21;
    private boolean over21;
    private Player player;
    private double bet;
    private boolean nomoreturns;
    private Shoe deck;
    private int hboxnumber;
    private HBox hbox;
    


    
    public Hand(Player player, Shoe deck){
        cardlist =  new ArrayList();
        handcomparelist=new ArrayList<>();
        handvalueslist=new ArrayList<>();
        over21 = false;
        this.player=player;
       // updateHandValueList();
        //removeOver21HandValueList();
       // fillInHandCompareList();
        nomoreturns=false;
         
        this.deck=deck;
        this.hbox=new HBox();
        hbox.setPadding(new Insets(5));
        //hbox=new HBox();
    }
    public Hand(Shoe deck){
        cardlist =  new ArrayList();
        handcomparelist=new ArrayList<>();
        handvalueslist=new ArrayList<>();
        over21 = false;
        nomoreturns=false;
        this.deck=deck;
        player=new Player("default player");
        hboxnumber=0;
        hbox=new HBox();
        hbox.setPadding(new Insets(5));
    }
    
    public Card dealSingleCard(){
        
        
        Card newcard = deck.getRandomCard();
        cardlist.add(newcard);
        //System.out.println("\nSingle card dealt: "+ newcard);
        //System.out.println(player.getName()+" hand values: "+handvalueslist);
        updateHandValueList(newcard);
        //System.out.println(player.getName()+" hand values: "+handvalueslist);
        return newcard;
        
    }
    

    
    
    
    public void dealTwoCards(Shoe deck){
        cardlist.add(deck.getRandomCard());
        cardlist.add(deck.getRandomCard());
    
    
    
    }
    
    
    
    public void updateHandValueAce(){
        ArrayList<Integer> templist1 = new ArrayList<>();
        ArrayList<Integer> templist2 = new ArrayList<>();
        
        if (handvalueslist.isEmpty()) {
            handvalueslist.add(1);
            handvalueslist.add(11);
        }
        else{
            for (int i = 0; i < handvalueslist.size(); i++) {
                templist1.add(handvalueslist.get(i)+1);
            }
            for (int i = 0; i < handvalueslist.size(); i++) {
                templist2.add(handvalueslist.get(i)+11);
            }
            handvalueslist.clear();
            handvalueslist.addAll(templist1);
            handvalueslist.addAll(templist2);
        }
    
    }
    
    
    public void updateHandValueList(Card card){
        
        
        
        if (card.getCardface()==Card.Face.ACE) {
            updateHandValueAce();
        }
        
        else if (handvalueslist.isEmpty()) {
            handvalueslist.add(card.getValue1());
            
        }
        
        else{
              
            int value = card.getValue1();
            //System.out.println("The size of the handvalueslist before loop: "+handvalueslist.size());
            //int loops = handvalueslist.size();
            for (int i = 0; i < handvalueslist.size(); i++) {
                //System.out.println("The size of the handvalueslist: "+handvalueslist.size());
                int newvalue = value+handvalueslist.get(i);
                handvalueslist.set(i, newvalue);
                //System.out.println("The size of the handvalueslist after adding new value: "+handvalueslist.size());
            }
        }
        
        //System.out.println("Hand value list after update: "+handvalueslist);
        sortHandValueList();
        removeOver21HandValueList();
        //fillInHandCompareList();
        isOver21();
        isTwentyone();
    }
    
    public void sortHandValueList(){
        Collections.sort(handvalueslist);
        //System.out.println("Hand value list after sort: "+handvalueslist);
    
    }
    
    public void removeOver21HandValueList(){
        //System.out.println("Hand value list before remove 21: "+handvalueslist);
        for (int i = 0; i < handvalueslist.size();) {
            if (handvalueslist.get(i)>21) {
                handvalueslist.remove(i);
            }
            else i++;
        }
        //System.out.println("Hand value list after remove 21: "+handvalueslist);
    
    }
    
    public void updateHandValueList(){
        sortHandList();
        handvalueslist= new ArrayList();
              
        //this is not right, it needs to double the number of hand values each ace
        
        
        
        for (int i = cardlist.size()-1; i >= 0; i--) {
            Card currentcard = cardlist.get(i);
            if (cardlist.get(i).getCardface()==Card.Face.ACE) {
                handvalueslist.add(1);
                handvalueslist.add(11);
                
            }
            else if(!handvalueslist.isEmpty()){
            for (int j = 0; j < handvalueslist.size(); j++) {
                int oldvalue=handvalueslist.get(j);
                handvalueslist.set(j, oldvalue+currentcard.getValue1());
                
            }} 
            else  handvalueslist.add(currentcard.getValue1());
           
            
        }
        
        
    
    
    }
    
    public boolean isTwentyone(){
        
        
        for (Integer integer : handvalueslist) {
            if (integer==21) {
                return true;
            
            }
        
    }
        return false;
    
    }
    
    public boolean isAtLeast17(){
        for (Integer integer : handvalueslist) {
            if (integer>=17 || handvalueslist.isEmpty()) {
                return true;
            
            }
             
        }
        return false;
    }
    
    
    public boolean isOver21(){
        
        if (handvalueslist.isEmpty()) {
            over21=true;
            return true;
        }
        return false;
    
    
    }
    
    public boolean checkNoMoreHits(){
        if (isTwentyone()||isOver21()) {
            return true;
        }
        else return false;
    
    }
    
     
    
    
    
    public final void sortHandList(){
        Collections.sort(cardlist);
    
    
    
    }
    
    
    

    
    public int compareHands(Hand otherhand){
        
        for (int i = 0; i < handcomparelist.size(); i++) {
            if (handcomparelist.get(i)> otherhand.handcomparelist.get(i)) {
                return 1;
            }
            
            if (handcomparelist.get(i)< otherhand.handcomparelist.get(i)) {
                return -1;
            }
            
        }
        return 0;
    
    
    
    }
    
    public void displayHand(){
    
        for (Card card : cardlist) {
            System.out.println("Card in hand: "+card);
        }
    
    
    }
    
    public void displayHandValues(){
    
        System.out.println("Hand values: "+handvalueslist );
        
        
        
        
    }
    
    public void displayHandCompareList(){
    
        System.out.println("Hand compare values: "+ handcomparelist);
    
    }
    
    
    
    public boolean enterBet(int newbet){
        if (newbet<=checkPlayerBalance()) {
            bet=newbet;
            player.setBank(player.getBank()-newbet);
            return true;
        }
        return false;
    }
    
    public double checkPlayerBalance(){
        
        return player.getBank();
    
    
    
    }
    
    public boolean checkDoubleDown(){
        if (cardlist.size()==2) {
            for (Integer integer : handvalueslist) {
                if (integer==9 || integer==10 || integer==11 ) {
                    if (player.getBank()>=bet) {
                        return true;
                    }


                }

        }
        }
            
        return false;
    
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getBet() {
        return bet;
    }

    public void setNomoreturns(boolean nomoreturns) {
        this.nomoreturns = nomoreturns;
    }

    public boolean isNomoreturns() {
        return nomoreturns;
    }

    public ArrayList<Card> getCardlist() {
        ArrayList<Card> returnlist = new ArrayList<>();
        for (Card card : cardlist) {
            returnlist.add(card);
        }
        return returnlist;
    }
    
    public void addToBank(double winamount){
        player.setBank(player.getBank()+winamount);
        bet=0;
    }
    
    public int getHighestHandValue(){
        if (!handvalueslist.isEmpty()) {
            return handvalueslist.get(handvalueslist.size()-1);
        }
        else return -1;
    
    
    
    }

    public int getHboxnumber() {
        return hboxnumber;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public HBox getHbox() {
        return hbox;
    }
    
    public void doubleDown(){
        double oldbet = bet;
        double newbet=bet*2;
        bet=newbet;
        double oldbank= player.getBank();
        double newbank = oldbank-oldbet;
        player.setBank(newbank);
        nomoreturns=true;
    
    
    }
}
