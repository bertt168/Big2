import java.util.List;

public class Pair extends CardPattern {

    @Override
    public boolean isLegal(List<Card> currentPlay) {
        if (currentPlay.size() != 2)
            return false;
        else {
            int rankCompare = currentPlay.get(0).rank().compareTo(currentPlay.get(1).rank());
            return (rankCompare == 0);
        }
    }

    @Override
    public void isSamePattern(CardPattern topPlay) {
        if (getClass() != topPlay.getClass()) {
            throw new RuntimeException();
        }
    }

    @Override
    public void compareToCardPattern(CardPattern topPlay) {
        Rank topPlayPairRank = topPlay.cards.get(1).rank();
        Suit topPlayBiggestSuit = topPlay.cards.get(1).suit();
        Rank currentPlayPairRank = cards.get(1).rank();
        Suit currentPlayBiggestSuit = cards.get(1).suit();

        int rankCompare = currentPlayPairRank.compareTo(topPlayPairRank);
        int suitCompare = currentPlayBiggestSuit.compareTo(topPlayBiggestSuit);

        if (rankCompare < 0 || (rankCompare == 0 && suitCompare > 0)) {
            throw new RuntimeException();
        }
    }
}
