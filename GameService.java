import java.util.LinkedList;
import java.util.Scanner;

public class GameService {

    private Game game;

    public GameService() {
        game = new Game();
    }

    public int gamePlay() {
        Card savedCard = null;
        Card secondSavedCard;
        Card firstCard = null; // will hold initial value for i = 0; and strongest card after
        int winningPlayer = -1;
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            if (i == 0 && game.getPlayer(i).getDeck().size() > 0) {
                firstCard = game.getPlayer(i).popCard();
                winningPlayer = 0;
                savedCard = firstCard;
            } else if (game.getPlayer(i).getDeck().size() > 0) {
                Card c = game.getPlayer(i).popCard();
                secondSavedCard = c;
                System.out.println(firstCard.toString() + " against " + c.toString());
                int compared = firstCard.compareTo(c);
                if (compared == -1) { // c is stronger than first hand
                    winningPlayer = i - 1;
                    System.out.println("Winning Player is " + game.getPlayer(winningPlayer).getName() + " with card " + firstCard.toString());
                    game.addCards(savedCard, i - 1);
                    game.addCards(secondSavedCard, i - 1);
                }
                if (compared == 1) { // first hand is stronger than c
                    winningPlayer = i;
                    firstCard = c;
                    System.out.println("Winning Player is " + game.getPlayer(winningPlayer).getName() + " with card " + firstCard.toString());
                    game.addCards(savedCard, i);
                    game.addCards(secondSavedCard, i);
                }
                if (compared == 0) {
                    System.out.println("This is war!");
                    Player player = war(game.getPlayer(i), game.getPlayer(winningPlayer));
                    winningPlayer = game.getIndexOfPlayer(player);
                    game.addCards(savedCard, winningPlayer);
                    game.addCards(secondSavedCard, winningPlayer);
                    winningPlayer = -1;
                }
            }

        }
        if (winningPlayer != -1) {
            System.out.println("Player " + game.getPlayer(winningPlayer).getName() + " has won this round!");
        }
        threeCardsOperation();
        int won = hasWon();
        return won;
    }

    public void threeCardsOperation() {                                         //Combines the other three cards functionalities
        if (checkIfThreeCards() != null) {
            Player player = checkIfThreeCards();
            threeCardsLeft(player);
        }

    }

    public Player checkIfThreeCards() {                                         //Checks if any player in the game has three cards left
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            if (game.getPlayer(i).getDeck().size() == 0) {
                return game.getPlayer(i);
            }
        }
        return null;
    }

    public void threeCardsLeft(Player player) {                                // Lets the player rearange his three cards
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to rearrange your cards? Write 1 for yes and 0 for no");
        int choice = scan.nextInt();
        if (player.getDeck().size() > 0) {
            while (choice == 1) {
                player.printDeck();
                System.out.println("Please write the index of the card you would want to put first");
                int index = scan.nextInt();
                player.changeCardOrder(index);
                System.out.println("Would you like to rearrange your cards again? Write 1 for yes and 0 for no");
                choice = 0;
                choice = scan.nextInt();
            }
        } else {
            hasWon();
        }
    }


    public int hasWon() {                                                    //Checks if player has won the game
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            if (game.getPlayer(i).getDeck().size() == 52) {
                System.out.println("Congradulations Player " + game.getPlayer(i).getName() + "you have won this game!");
                return 1;
            }
        }
        return 0;
    }

    private Player war(Player first, Player second) {
        System.out.println("First Player :" + first.getName() + " against second Player: " + second.getName());
        LinkedList<Card> poppedCards = null;
        for (int i = 0; i > 3; i++) {
            threeCardsOperation();
            Card firstCard = first.popCard();
            System.out.println(firstCard.toString());
            poppedCards.push(firstCard);
            Card secondCard = second.popCard();
            System.out.println(secondCard.toString());
            poppedCards.push(secondCard);

            if (i == 2) {
                int c = firstCard.compareTo(secondCard);
                if (c == 0) {
                    System.out.println("Another war");
                    war(first, second);
                } else if (c == 1) {
                    first.addToWon(poppedCards);
                    System.out.println(first.toString() + " has won this war");
                    return first;
                } else if (c == -1) {
                    second.addToWon(poppedCards);
                    System.out.println(second.toString() + " has won this war");
                    return second;
                }
            }

        }
        return null;
    }

    public void startGame() {
        CardService start = new CardService();
        start.createPlayDecks(game);
        int i = 0;
        do {
            i = gamePlay();
        } while (i == 0);
    }

}
