
package BlackjackFX;

import java.io.Serializable;





public class Player implements Serializable{
    
    private String name;
    private double bank;
    
    public Player(String name){
    
        bank = 100;
        this.name = name;
    
    }

    public String getName() {
        return name;
    }

    public double getBank() {
        return bank;
    }

    public void setBank(double bank) {
        this.bank = bank;
    }
    
    public String toString(){
        return name;
    
    }
    
    
}
