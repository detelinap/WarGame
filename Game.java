import java.util.Scanner;

public class Game {
    private int numberOfPlayers;
    private Player[] players;

    public Game() {
      Scanner scan = new Scanner(System.in);
        System.out.println("Please enter number of players");
        this.numberOfPlayers = scan.nextInt();
        this.players = new Player[numberOfPlayers] ;
        for (int i = 0; i < numberOfPlayers; i++){
            this.players[i] = new Player();
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Player getPlayer(int index){
        return players[index];
    }

    public int getIndexOfPlayer(Player player){
        for (int i = 0; i < players.length; i++){
            if(player == players[i]){
                return i;
            }
        }
        return 0;
    }


    public void addCards(Card card, int i){
        players[i].getDeck().push(card);
    }
}
