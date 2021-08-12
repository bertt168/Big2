import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Big2 {

    public static final int PLAYER_SIZE = 4;

    private Deck deck = new Deck();

    private final List<Player> players = new ArrayList<Player>();

    private Round round = new Round();

    public static CardPattern topPlay;

    public static Player topPlayer;

    public boolean noWinner = true;

    Scanner scanner = new Scanner(System.in);

    public Big2() {
    }

    public void startGame() {
        deck.shuffle();
        createPlayerList();
        dealing();
        startRound(players);
//        while(noWinner){
//            startRound(players);
//        }
    }

    private void startRound(List<Player> players) {
        round.start(players);

    }

    private void dealing() {
        for (int i = deck.FULL_DECK_SIZE; i > 0; i--) {
            Player player = players.get(i % PLAYER_SIZE);
            player.handCard.cards.add(deck.cards.get(i-1));
            if (deck.cards.get(i-1).suit().equals(Suit.C) && deck.cards.get(i-1).rank().equals(Rank.THREE)) {
                player.hasSpade3inHandCard = true;
            }
        }

//        for (int i = 0; i < players.size(); i++) {
//            Player player = players.get(i);
//            for (Card card : player.handCard.cards) {
//                System.out.print("<" +  card.suit() + ">" + "[<" + card.rank().getValue() + ">]  ");
//            }
//            System.out.println();
//        }

    }

    private void createPlayerList() {
        for (int i = 0; i < PLAYER_SIZE; i++) {
            Player player = new Player();
			System.out.println("What is the Player'" + (i+1) + "s name?");
			player.name = scanner.next();
			player.id = i;
            players.add(player);
        }
    }

    public void endGame() {

    }

}
