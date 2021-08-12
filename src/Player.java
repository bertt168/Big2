public class Player {

	public String name;
	public HandCard handCard = new HandCard();
	private Deck deck;
	public boolean hasSpade3inHandCard = false;
	public int id;

	public void deal() {

	}

	public String getName() {
		return name;
	}

	public Action makeAction() {
		return null;
	}

	public void doAction(Action actioin) {

	}

	public boolean isWinner(HandCard handCard) {
		return false;
	}

}
