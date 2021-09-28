import java.util.List;

public class Round {

    public boolean isNewRound = true;

    private Player currentPlayer;

    private Player nextPlayer;

    public Round() {
    }

    public <T> void start(List<Player> players) {
        System.out.println("New round begins.");
        while(true) {
            setCurrentPlayer(players);
            int nextPlayerId = currentPlayer.playerId == (Big2.PLAYER_SIZE - 1) ? 0 : (currentPlayer.playerId + 1);
            setNextPlayer(players, nextPlayerId);

            currentPlayer.handCard.sortedCards();
            printCurrentPlayerHandCard(currentPlayer);

            Action action = currentPlayer.makeAction(isNewRound);
            doAction(action);
            boolean isWinner = isWinner(currentPlayer);
            if (isWinner) {
                Big2.noWinner = false;
                break;
            }
            if (isEndRound()) {
                break;
            }
            isNewRound = false;
        }
    }

    private boolean isEndRound() {
        return Big2.topPlayer == nextPlayer;
    }

    private void setCurrentPlayer(List<Player> players) {
        if (isNewRound && Big2.topPlay == null)
            currentPlayer = checkWhoHasSpade3(players);
        else if (isNewRound)
            currentPlayer = Big2.topPlayer;
        else
            currentPlayer = nextPlayer;
    }

    private boolean isWinner(Player player) {
        if (player.handCard.isEmpty())
            return true;
        else
            return false;
    }

    public void doAction(Action action) {
        if (action instanceof Pass) {
            System.out.printf("Player %s passes.", currentPlayer.name);
            System.out.println();
        } else {
            Big2.setTopPlay(((Play) action).cardPattern);
            Big2.setTopPlayer(currentPlayer);
            System.out.print("Player <" + currentPlayer.name + "> plays a <" + ((Play) action).cardPattern.getName() + ">");
            for (Card c : ((Play) action).cardPattern.cards) {
                System.out.print(" <" + c.suit() + ">[" + c.rank().getValue() + "]");
            }
            System.out.println();
        }
    }

    private void setNextPlayer(List<Player> players, int nextPlayerId) {
        this.nextPlayer = players.get(nextPlayerId);
    }

    private void printCurrentPlayerHandCard(Player player) {
        System.out.println("Next turn: " + player.name);
        int i = 0;
        for (Card c : player.handCard.cards) {
            if (c.rank().equals(Rank.TEN))
                System.out.printf("%-6d", i);
            else
                System.out.printf("%-5d", i);
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
            if (p.hasSpade3)
                return p;
        }
        throw new RuntimeException("no way.");
    }
}
