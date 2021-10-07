
package BlackjackFX;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author User
 */
public class Shoe implements Serializable{
    
    private int randomint;
    //private Card.Suit[] suitarray;
    //private Card.Face[] facearray;
    private int randomcounter=0;
    private Random random ;
    private int cardcount;
    private Card.Suit[] suitarray; 
    private Card.Face[] facearray; 
    private ArrayList<Card> decklist ;
    
    

     
   public Shoe(){
       decklist = new ArrayList<>();
       random = new Random();
       suitarray = new Card.Suit[4];
        facearray = new Card.Face[13];  
       makeSuitFaceArrays();
       //Card.makeSuitArray();
       //suitarray = Card.getSuitarray();
       //facearray = Card.getFacearray();
                    
       
       createDeck();
       
      
       
   }
    
       public void makeSuitFaceArrays(){
    suitarray[0] = Card.Suit.CLUBS;
       suitarray[1] = Card.Suit.HEARTS;
       suitarray[2] = Card.Suit.DIAMONDS;
       suitarray[3] = Card.Suit.SPADES;
       
       facearray[0] = Card.Face.TWO;
       facearray[1] = Card.Face.THREE;
       facearray[2] = Card.Face.FOUR;
       facearray[3] = Card.Face.FIVE;
        facearray[4] = Card.Face.SIX;
        facearray[5] = Card.Face.SEVEN;
        facearray[6] = Card.Face.EIGHT;        
        facearray[7] = Card.Face.NINE;     
       facearray[8] = Card.Face.TEN;    
       facearray[9] = Card.Face.JACK;
        facearray[10] = Card.Face.QUEEN ;
         facearray[11] = Card.Face.KING;
         facearray[12] = Card.Face.ACE;         
    }
   
   
   
   //returns random card from deck and deletes it from deck, 
   public Card getRandomCard(){
       
       
       Card randomcard = decklist.get(0);
       decklist.remove(0);
       
       
       
       //System.out.println("Cards remaining in deck "+decklist.size());
       cardCount(randomcard);
       return randomcard;
       
       
   }
   
   private void cardCount(Card card){
       Card.Face cardface = card.getCardface();
       
       
       
       switch (cardface){
           case TWO:
           case THREE:
           case FOUR:
           case FIVE:
           case SIX:
               cardcount++;
               break;
           case SEVEN:
           case EIGHT:
           case NINE:
               break;
           case TEN:
           case JACK:
           case QUEEN:
           case KING:
               cardcount--;
               break;
                   
       
       }
            
   
   }
   
   
   
   //creates a deck and shuffles it
   private void createDeck(){
       //System.out.println("new deck created");
   
       //create deck
       for (int k = 0; k < 6; k++) {
           for (int i = 0; i < 4; i++) {
                for (int s = 0; s < 13; s++) {
                 decklist.add(new Card(suitarray[i],facearray[s] ));


            }}
       }
            
       
       
       Collections.shuffle(decklist);
       cardcount=0;
       customDeck();
       
       
       
       
   
   
   }
   
   
   
   
   private void customDeck(){
       Card ace = new Card(Card.Suit.CLUBS,Card.Face.ACE);
        Card king = new Card(Card.Suit.CLUBS,Card.Face.KING);
        Card queen = new Card(Card.Suit.CLUBS,Card.Face.QUEEN);
        Card jack = new Card(Card.Suit.CLUBS,Card.Face.JACK);
        Card ten = new Card(Card.Suit.CLUBS,Card.Face.TEN);
        Card nine = new Card(Card.Suit.CLUBS,Card.Face.NINE);
        Card eight = new Card(Card.Suit.CLUBS,Card.Face.EIGHT);
        Card seven = new Card(Card.Suit.CLUBS,Card.Face.SEVEN);
        Card six = new Card(Card.Suit.CLUBS,Card.Face.SIX);
        Card five = new Card(Card.Suit.CLUBS,Card.Face.FIVE);
        Card four = new Card(Card.Suit.CLUBS,Card.Face.FOUR);
        Card three = new Card(Card.Suit.CLUBS,Card.Face.THREE);
        Card two = new Card(Card.Suit.CLUBS,Card.Face.TWO);
        //Players get 1 card, then dealer, then repeated
        //then players, then dealer
       // decklist.set(0,nine );
        //decklist.set(1, two);
        //decklist.set(2, two);
        //decklist.set(3,two );
        //decklist.set(4,ace );
       //decklist.set(5,ace );
      // decklist.set(6,ace );
       //decklist.set(7,ace );
      // decklist.set(8,ace );
       //decklist.set(9,ace );
       //decklist.set(10,ace );
      // decklist.set(11,ace );
        //decklist.set(0, );
        //decklist.set(0, );
        //decklist.set(0, );
       // decklist.set(0, );
        //decklist.set(0, );
        //decklist.set(0, );
       // decklist.set(0, );
        //decklist.set(0, );
       // decklist.set(0, );
//        for (int i = 0; i < 270; i++) {
//            decklist.remove(0);
//           
//       }
                
   
   
   } 
   
   public void checkReloadDeck(){
       if (decklist.size()<30) {
           createDeck();
       }
   
   
   
   
   }

    public int getCardcount() {
        return cardcount;
    }
   
   
}
