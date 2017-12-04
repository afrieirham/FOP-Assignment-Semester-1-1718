package blackjack.pkg2;

public class Cards {
    
    private Suit suit;
    private Value value;
    
    public Cards(Suit suit, Value value){
        this.value = value;
        this.suit = suit;
    }
    
    public String toString(){
        return this.suit.toString() + "-" + this.value.toString();
    }
    
    public Value getValue(){
        return this.value;
    }
}