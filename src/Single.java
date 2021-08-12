public class Single extends CardPattern {

	private Card card;

	Single(Rank rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	public boolean hasPlum3(Card[] releasedCards) {
		return false;
	}

	@Override
	public boolean isLegal(Card[] releasedCards) {
		return false;
	}

	@Override
	public boolean isSamePattern(CardPattern releasedCards, CardPattern topPlay) {
		return false;
	}

	@Override
	public boolean compareToCardPattern(CardPattern released, CardPattern topPlay) {
		return false;
	}
}
