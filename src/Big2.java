import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Big2 {

    public static final int PLAYER_SIZE = 4;
    public static boolean noWinner = true;

    private Deck deck = new Deck();

    private final List<Player> players = new ArrayList<Player>();

    public static CardPattern topPlay;

    public static Player topPlayer;

    Scanner scanner = new Scanner(System.in);

    public Big2() {
    }

    public static void setTopPlay(CardPattern cp) {
        topPlay = cp;
    }

    public static void setTopPlayer(Player currentPlayer) {
        topPlayer = currentPlayer;
    }

    public void startGame() {
        deck.shuffle();
        createPlayerList();
        dealing();
        while(noWinner){
            startRound(players);
        }
        endGame();
    }

    private void startRound(List<Player> players) {
        Round round = new Round();
        round.start(players);
    }

    private void dealing() {
        for (int i = deck.FULL_DECK_SIZE; i > 0; i--) {
            Player player = players.get(i % PLAYER_SIZE);
            player.handCard.cards.add(deck.cards.get(i-1));
            if (deck.cards.get(i-1).suit().equals(Suit.C) && deck.cards.get(i-1).rank().equals(Rank.THREE)) {
                player.hasSpade3 = true;
            }
        }
    }

    private void createPlayerList() {
        for (int i = 0; i < PLAYER_SIZE; i++) {
            Player player = new Player();
			System.out.println("What is the Player'" + (i+1) + "s name?");
			player.name = scanner.nextLine();
			player.playerId = i;
            players.add(player);
        }
    }

    public void endGame() {
        System.out.println();
        System.out.println("Game over, the winner is <" + topPlayer.name + ">.");
    }

}
