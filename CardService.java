import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardService {

    private ArrayList<Card> fillDeck() {             //Creates original deck
        ArrayList<Card> deck = new ArrayList<>();
        int inx = 0;
        for (int i = 0; i < 4; i++) {
            String clr = null; //Color of the card
            if (i == 0) {
                clr = "Hearts";
            } else if (i == 1) {
                clr = "Diamonds";
            } else if (i == 2) {
                clr = "Clubs";
            } else if (i == 3) {
                clr = "Spades";
            }
            for (int str = 1; str <= 13; str++) {
                String names = null;
                switch (str) {
                    case 1:
                        names = "Two";
                        break;
                    case 2:
                        names = "Three";
                        break;
                    case 3:
                        names = "Four";
                        break;
                    case 4:
                        names = "Five";
                        break;
                    case 5:
                        names = "Six";
                        break;
                    case 6:
                        names = "Seven";
                        break;
                    case 7:
                        names = "Eight";
                        break;
                    case 8:
                        names = "Nine";
                        break;
                    case 9:
                        names = "Ten";
                        break;
                    case 10:
                        names = "Jack";
                        break;
                    case 11:
                        names = "Queen";
                        break;
                    case 12:
                        names = "King";
                        break;
                    case 13:
                        names = "Ace";
                }

                if (inx < 52) {
                    Card card = new Card(str, names, clr);
                    deck.add(card);
                    inx++;
                }
            }
        }

        return deck;
    }

    private ArrayList<Card> shuffle(List<Card> deck) {               //Shuffles the original deck
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        int deckLength = deck.size();
        while (deckLength > 0) {
            int randomCard = (int) (Math.random() * deckLength);
            Card c = deck.get(randomCard);
            deck.remove(randomCard);
            shuffledDeck.add(c);
            deckLength--;
        }
        return shuffledDeck;
    }

    private LinkedList<Card> divideDeck(List<Card> ogDeck, Game game) {//Creates every player's personal deck
        LinkedList<Card> playerDeck = new LinkedList<>();
        Collections.shuffle(playerDeck);
        int sizeOfDeck = 52/game.getNumberOfPlayers();
        int gamePlayers = game.getNumberOfPlayers();
        for (int count = 0; count <=sizeOfDeck-1 ; count++) {
            playerDeck.add(ogDeck.get(count));
            ogDeck.remove(count);

        }
        return playerDeck;
    }

    public void createPlayDecks(Game game) {
        ArrayList<Card> originalDeck = fillDeck();
        originalDeck = shuffle(originalDeck);
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            game.getPlayer(i).setDeck(divideDeck(originalDeck, game));
        }
    }
}
