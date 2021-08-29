import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	public int FULL_DECK_SIZE = 52;
	public List<Card> cards  = new ArrayList<Card>();

	public void shuffle() {
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values())
				cards.add(new Card(rank, suit));
		Collections.shuffle(cards);

		// show deck
		System.out.print("<Shuffled Deck> := ");
		for (Card card : cards) {
			System.out.print("<" +  card.suit() + ">" + "[<" + card.rank().getValue() + ">]  ");
		}
		System.out.println();
	}

}
