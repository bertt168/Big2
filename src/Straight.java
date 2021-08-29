import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Straight extends CardPattern {

    @Override
    public String getName() {
        return "Straight";
    }

    @Override
    public boolean isLegal(List<Card> currentPlay) {
        if (currentPlay.size() != 5)
            return false;
        else {
            Collections.sort(currentPlay, (a1, a2) -> a1.compareTo(a2));
            for (int i = 0; i < currentPlay.size()-1; i++) {
                Rank r1 = currentPlay.get(i).rank().getNext();
                Rank r2 = currentPlay.get(i+1).rank();
                if (!r1.equals(r2)){
                    throw new RuntimeException();
                }
            }
        }
        return true;
    }

    @Override
    public void isSamePattern(CardPattern topPlay) {
        if (!(topPlay instanceof Straight) && topPlay != null) {
            throw new RuntimeException();
        }
    }

    @Override
    public void compareToCardPattern(CardPattern topPlay) {
        int rankCompare = cards.get(4).rank().compareTo(topPlay.cards.get(4).rank());
        if (rankCompare > 0)
            return;
        else if (rankCompare == 0) {
            int suitCompare = cards.get(4).suit().compareTo(topPlay.cards.get(4).suit());
            if (suitCompare < 0)
                throw new RuntimeException();
        } else {
            throw new RuntimeException();
        }
    }
}
