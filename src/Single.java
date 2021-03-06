import java.util.ArrayList;
import java.util.List;

public class Single extends CardPattern {

	@Override
	public boolean isLegal(List<Card> currentPlay) {
		return currentPlay.size() == 1;
	}

	@Override
	public void isSamePattern(CardPattern topPlay) {
		if (getClass() != topPlay.getClass()) {
			throw new RuntimeException();
		}
	}

	@Override
	public void compareToCardPattern(CardPattern topPlay) {
		if (topPlay == null)
			return;

		int rankCompare = cards.get(0).rank().compareTo(topPlay.cards.get(0).rank());
		if (rankCompare > 0)
			return;
		else if (rankCompare == 0) {
			int suitCompare = cards.get(0).suit().compareTo(topPlay.cards.get(0).suit());
			if (suitCompare < 0)
				throw new RuntimeException();
		} else {
			throw new RuntimeException();
		}
	}
}
