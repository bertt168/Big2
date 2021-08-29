import java.util.*;

public class Player {
    private static final String PASS = "-1";
    public String name;
    public HandCard handCard = new HandCard();
    public boolean hasSpade3 = false;
    public int playerId;
    private CardPattern currentPattern;
    Scanner scanner = new Scanner(System.in);

    private boolean doCheck(String[] arrayOfCardsId, boolean isNewRound) {
        try {
            List<String> idList = Arrays.asList(arrayOfCardsId);
            List<Card> currentPlay = new ArrayList<>();
            List<Integer> tempCardIds = new ArrayList<>();
            isId(idList, tempCardIds);
            isUniqueAndInRange(tempCardIds);
            setCurrentPlay(tempCardIds, currentPlay);
            isLegalCardPattern(currentPlay);
            if (Big2.topPlay == null && isNewRound) {
                isCardPatternHasSpade3();
            }else if (!isNewRound) {
                currentPattern.isSamePattern(Big2.topPlay);
                currentPattern.compareToCardPattern(Big2.topPlay);
            }
            handCard.updateHandCard(currentPlay);
        } catch (Exception e) {
            System.out.println("Invalid play, please try again.");
            return false;
        }
        return true;
    }

    private void isCardPatternHasSpade3() {
        for (Card c : currentPattern.cards) {
            if (c.suit().equals(Suit.C) && c.rank().equals(Rank.THREE)){
                return;
            }
        }
        throw new RuntimeException();
    }

    private void setCurrentPlay(List<Integer> tempCardIds, List<Card> currentPlay) {
        for (int i : tempCardIds) {
            currentPlay.add(handCard.cards.get(i));
        }
    }

    private void isLegalCardPattern(List<Card> currentPlay) throws Exception {
        CardPattern s;
        boolean islegal;
        s = new Single();
        islegal = s.isLegal(currentPlay);
        if (islegal) {
            currentPattern = s;
            currentPattern.cards = currentPlay;
            return;
        }
        s = new Pair();
        islegal = s.isLegal(currentPlay);
        if (islegal) {
            currentPattern = s;
            currentPattern.cards = currentPlay;
            return;
        }
        s = new Straight();
        islegal = s.isLegal(currentPlay);
        if (islegal) {
            currentPattern = s;
            currentPattern.cards = currentPlay;
            return;
        }

        s = new FullHouse();
        islegal = s.isLegal(currentPlay);
        if (islegal) {
            currentPattern = s;
            currentPattern.cards = currentPlay;
            return;
        }
        throw new Exception();
    }

    private void isUniqueAndInRange(List<Integer> tempCardIds) throws Exception {
        Set<Integer> set = new HashSet<>();
        int maxId = handCard.cards.size() - 1;
        for (int i : tempCardIds) {
            if (!set.add(i))
                throw new Exception();
            if (i > maxId)
                throw new Exception();
        }
    }

    private void isId(List<String> idList, List<Integer> tempCardIds) {
        for (String s : idList) {
            int id = Integer.parseInt(s);
            tempCardIds.add(id);
        }
    }

    public Action makeAction(boolean isNewRound) {
        Play play = new Play();
        Pass pass = new Pass();
        boolean making = true;
        boolean isLegal;
        String[] idArray;
        System.out.println("print the card's id(s) to play or print -1 to pass.");
        while (making) {
            String action = scanner.nextLine();
            if (action.equals(PASS) && isNewRound)
                System.out.println("You can’t pass in the new round.");
            else if (action.equals(PASS))
                return pass;
            else {
                idArray = action.split("\\s+");
                isLegal = doCheck(idArray, isNewRound);
                if (isLegal) {
                    making = false;
                }
            }
        }
        play.setCardPattern(currentPattern);
        return play;
    }
}
