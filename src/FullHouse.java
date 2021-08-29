import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FullHouse extends CardPattern {

	public List<Card> cards = new ArrayList<>();

	@Override
	public String getName() {
		return "FullHouse";
	}

	@Override
	public boolean isLegal(List<Card> currentPlay) {
		if (currentPlay.size() != 5)
			return false;
		else {
			Collections.sort(currentPlay, (a1, a2) -> a1.compareTo(a2));
			Card middleCard = currentPlay.get(2);
			if (currentPlay.get(0).equals(middleCard)) {
				if (currentPlay.get(1).equals(middleCard) && currentPlay.get(3).rank().equals(currentPlay.get(4).rank())) {
					return true;
				} else {
					throw new RuntimeException();
				}
			} else {
				if (currentPlay.get(3).equals(middleCard) && currentPlay.get(0).rank().equals(currentPlay.get(1).rank())) {
					return true;
				} else {
					throw new RuntimeException();
				}
			}
		}
	}

	@Override
	public void isSamePattern(CardPattern topPlay) {
		if (!(topPlay instanceof FullHouse) && topPlay != null) {
			throw new RuntimeException();
		}
	}

	@Override
	public void compareToCardPattern(CardPattern topPlay) {
		int rankCompare = cards.get(2).rank().compareTo(topPlay.cards.get(2).rank());
		if (rankCompare > 0)
			return;
		else {
			throw new RuntimeException();
		}
	}
}
