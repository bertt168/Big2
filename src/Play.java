import java.util.ArrayList;
import java.util.List;

public class Play extends Action {

	public CardPattern cardPattern;
	public List<Card> cards = new ArrayList<>();

	public void setCardPattern(CardPattern cp) {
		cardPattern = cp;
	}
}
