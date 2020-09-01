import java.util.LinkedList;
import java.util.Scanner;

public class Player {
    private LinkedList<Card> deck;
    private String name;

    public Player() {
        System.out.println("Please give player name");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.next();
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public void addToWon(LinkedList<Card> cards){
        for(Card card : cards)
        deck.offerFirst(card);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card popCard() {
        return deck.pop();
    }

    public void printDeck(){
        for(int i = 0; i < deck.size(); i++){
            System.out.println(String.format("%d: %s", i, deck.get(i).toString()));
        }
    }

    public void changeCardOrder(int i){  // adds the card with index i in the beginning of the deck
        Card card = deck.get(i);
        deck.remove(deck.get(i));
        deck.push(card);
    }
}
