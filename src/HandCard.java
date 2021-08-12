import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HandCard {
	public List<Card> cards = new ArrayList<Card>();

	public void sortedCards() {
		Collections.sort(cards, (a1, a2) -> a1.compareTo(a2));
	}

}
