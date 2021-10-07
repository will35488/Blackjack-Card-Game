
package BlackjackFX;

import java.io.Serializable;



/**
 *
 * @author User
 */

public class Card implements Comparable<Card>,Serializable{

    
    
    //Enumerations for fase and suit
    public enum Suit {HEARTS, CLUBS, DIAMONDS, SPADES};
    public enum Face {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};
    
    //Variables for suit, face, and ordinal of face
    private Suit cardSuit;    
    private Face cardface;
    private int value1;
    private int value2;
    private String facestring;
    private String suitstring;
    
    private int cardordinal;
    

   
    
    
    //Card constructor takes a Suit and Face
    public Card(Suit cardSuit, Face cardface){
        this.cardSuit = cardSuit;
        this.cardface = cardface;
        

        
        switch(cardface){
            case TWO:
                value1=2;
                break;
            case THREE:
                value1=3;
                break;
            case FOUR:
                value1=4;
                break;
            case FIVE:
                value1=5;
                break;
            case SIX:
                value1=6;
                break;
            case SEVEN:
                value1=7;
                break;
            case EIGHT:
                value1=8;
                break;
            case NINE:
                value1=9;
                break;
            case TEN:
                value1=10;
                break;
            case JACK:case QUEEN:case KING:
                value1=10;
                break;
            case ACE:
                value1=1;
                value2=11;
                break;
                
        
        
        
        
        }
        
        cardordinal = cardface.ordinal();


     

}   
    //copy constructor
    public Card(Card newcard){
        this.cardSuit = newcard.cardSuit;
        this.cardface = newcard.cardface;
        
        cardordinal = newcard.cardordinal;


     

}





    
    


    public int getCardordinal() {
        return cardordinal;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public Face getCardface() {
        return cardface;
    }
    
    
    
    
    
    public String toString(){
        String str = cardface +""+ cardSuit;
        
        return str;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
    
    @Override
    public int compareTo(Card o) {
        if (this.cardface.ordinal()>o.cardface.ordinal()) {
            return 1;
        }
        if (this.cardface.ordinal()<o.cardface.ordinal()){
            return -1;
        
        }
        return 0;        
        
        }
    public boolean equals(Card othercard){
        if (othercard.getCardface().ordinal()==cardface.ordinal()){
            return true;
    
    
    }
        else return false;
    }
    
    
}

