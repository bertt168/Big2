public abstract class CardPattern extends Card {

	CardPattern(Rank rank, Suit suit) {
		super(rank, suit);
	}

	public abstract boolean hasPlum3(Card[] releasedCards);

	public abstract boolean isLegal(Card[] releasedCards);

	public abstract boolean isSamePattern(CardPattern releasedCards, CardPattern topPlay);

	public abstract boolean compareToCardPattern(CardPattern released, CardPattern topPlay);

}
