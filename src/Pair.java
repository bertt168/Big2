import java.util.List;

public class Pair extends CardPattern {

	@Override
	public String getName() {
		return "Pair";
	}

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
		if (!(topPlay instanceof Pair) && topPlay != null) {
			throw new RuntimeException();
		}
	}

	@Override
	public void compareToCardPattern(CardPattern topPlay) {
		int rankCompare = cards.get(1).rank().compareTo(topPlay.cards.get(1).rank());
		int suitCompare = cards.get(1).suit().compareTo(topPlay.cards.get(1).suit());
		if (rankCompare > 0) {
			return;
		}else if (rankCompare == 0 && suitCompare > 0) {
			return;
		}else {
			throw new RuntimeException();
		}
	}
}
