import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Big2 {

    public static final int PLAYER_SIZE = 4;
    public static boolean noWinner = true;

    private Deck deck = new Deck();

    private final List<Player> players = new ArrayList<Player>(PLAYER_SIZE);

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
            Card card = deck.dealCard();
            if (isDealCardEqualsSpade3(card)) {
                player.hasSpade3 = true;
            }
            player.handCard.addHandCard(card);
        }
    }

    private boolean isDealCardEqualsSpade3(Card card) {
        return card.suit().equals(Suit.C) && card.rank().equals(Rank.THREE);
    }

    private void createPlayerList() {
        for (int id = 0; id < PLAYER_SIZE; id++) {
			System.out.println("What is the Player'" + (id+1) + "s name?");
            String name = scanner.nextLine();
            Player player = new Player(id , name);
            players.add(player);
        }
    }

    public void endGame() {
        System.out.println();
        System.out.println("Game over, the winner is <" + topPlayer.name + ">.");
    }

}
