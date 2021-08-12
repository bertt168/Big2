import java.util.Collections;
import java.util.List;

public class Round {

    private Player nowPlayer;

    private Player nextPlayer;

    boolean isFirstRound;

    private Action action;

	public Round() {
	}

	public <T> void start(List<Player> players) {
        isFirstRound = isTopPlayEmpty();
        if (isFirstRound) {
            System.out.println("New round begins.");
            for (int i = 0; i < Big2.PLAYER_SIZE; i++) {
                nowPlayer = (nowPlayer == null ? checkWhoHasSpade3(players) : nextPlayer);
                int nextPlayerId = nowPlayer.id == (Big2.PLAYER_SIZE-1) ? 0 : (nowPlayer.id + 1);
                setNextPlayer(players, nextPlayerId);
                System.out.println("Next turn: " + nextPlayer.name);
                nowPlayer.handCard.sortedCards();
                printTheHandCard(nowPlayer);
            }
        } else {

		}
    }

    private void setNextPlayer(List<Player> players, int nextPlayerId) {
	    this.nextPlayer = players.get(nextPlayerId);
    }

    private void printTheHandCard(Player player) {
        System.out.println(player.name);
        int i = 0;
        for (Card c : player.handCard.cards) {
            if (c.rank().equals(Rank.TEN))
                System.out.printf("%-6d",i);
            else
                System.out.printf("%-5d",i);
            i++;
        }
        System.out.println();
        for (Card c : player.handCard.cards) {
            System.out.print(c.suit() + "[" + c.rank().getValue() + "] ");
        }
        System.out.println();
    }

    private Player checkWhoHasSpade3(List<Player> players) {
        for (Player p : players) {
            if (p.hasSpade3inHandCard)
                return p;
        }
        throw new RuntimeException("no way.");
    }

    private boolean isTopPlayEmpty() {
        return Big2.topPlay == null;
    }

    public void end() {

    }

    public void toNextPlayer(Player player) {

    }

}
