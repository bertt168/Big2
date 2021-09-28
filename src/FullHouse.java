import java.util.*;
import java.util.stream.Collectors;

public class FullHouse extends CardPattern {

    private final int TWO_KINDS_OF_RANK = 2;
    private final long THREE_OF_A_KIND = 3;
    private List<Rank> rankList = new ArrayList<Rank>();
    private Map<Object, Long> countedDup;

    @Override
    public boolean isLegal(List<Card> currentPlay) {
        if (currentPlay.size() != 5)
            return false;
        else {
            setRankList(currentPlay);
            countedDup = rankList.stream()
                    .collect(Collectors.groupingBy(r -> r, Collectors.counting()));
            if (countedDup.size() == TWO_KINDS_OF_RANK && isThreeOfAKind()) {
                return true;
            }
        }
        return true;
    }

	private boolean isThreeOfAKind() {
		for (Object r : countedDup.keySet()) {
			if (countedDup.get(r).equals(THREE_OF_A_KIND))
				return true;
		}
		return false;
	}

	private void setRankList(List<Card> currentPlay) {
        for (Card c : currentPlay) {
            rankList.add(c.rank());
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
        Rank topPlayMiddleCardRank = topPlay.cards.get(2).rank();
        Rank currentPlayMiddleCardRank = cards.get(2).rank();

        int rankCompare = currentPlayMiddleCardRank.compareTo(topPlayMiddleCardRank);
        if (rankCompare <= 0)
            throw new RuntimeException();
    }
}
