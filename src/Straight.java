import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Straight extends CardPattern {

    @Override
    public boolean isLegal(List<Card> currentPlay) {
        if (currentPlay.size() != 5)
            return false;
        else {
            Collections.sort(currentPlay, (a1, a2) -> a1.compareTo(a2));
            for (int i = 0; i < currentPlay.size() - 1; i++) {
                Rank r1 = currentPlay.get(i).rank().getNext();
                Rank r2 = currentPlay.get(i + 1).rank();
                if (!r1.equals(r2)) {
                    throw new RuntimeException();
                }
            }
        }
        return true;
    }

    @Override
    public void isSamePattern(CardPattern topPlay) {
        if (getClass() != topPlay.getClass()) {
            throw new RuntimeException();
        }
    }

    @Override
    public void compareToCardPattern(CardPattern topPlay) {
        Rank topPlayLastCardRank = topPlay.cards.get(4).rank();
        Rank currentPlayLastCardRank = cards.get(4).rank();
        Suit topPlayLastCardSuit = topPlay.cards.get(4).suit();
        Suit currentPlayLastCardSuit = cards.get(4).suit();

        int rankCompare = currentPlayLastCardRank.compareTo(topPlayLastCardRank);
        int suitCompare = currentPlayLastCardSuit.compareTo(topPlayLastCardSuit);

        if (rankCompare < 0 || (rankCompare == 0 && suitCompare < 0)) {
            throw new RuntimeException();
        }
    }
}
