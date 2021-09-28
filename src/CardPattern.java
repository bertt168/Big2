import java.util.ArrayList;
import java.util.List;

public abstract class CardPattern {

	public List<Card> cards = new ArrayList<>();

	public String getName() {
		return getClass().getSimpleName();
	};

	public abstract boolean isLegal(List<Card> currentPlay);

	public abstract void isSamePattern(CardPattern topPlay);

	public abstract void compareToCardPattern(CardPattern topPlay);

}
