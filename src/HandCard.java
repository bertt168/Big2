import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCard {
    public List<Card> cards = new ArrayList<Card>();

    public void sortedCards() {
        Collections.sort(cards, (a1, a2) -> a1.compareTo(a2));
    }

    public void addHandCard(Card card) {
        cards.add(card);
    }

    public void updateHandCard(List<Card> currentPlay) {
        cards.removeAll(currentPlay);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
