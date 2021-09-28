import java.util.*;

public class Player {
    private static final String PASS = "-1";
    public String name;
    public HandCard handCard = new HandCard();
    public boolean hasSpade3 = false;
    public int playerId;
    private CardPattern currentPattern;
    Scanner scanner = new Scanner(System.in);

    public Player(int id, String name) {
        this.playerId = id;
        this.name = name;
    }

    private boolean doCheck(String[] arrayOfCardsId, boolean isNewRound) {
        try {
            List<String> idList = Arrays.asList(arrayOfCardsId);
            List<Card> currentPlay = new ArrayList<>();
            List<Integer> tempCardIds = new ArrayList<>();
            checkIdListAllOfInteger(idList, tempCardIds);
            checkIdListIsUniqueAndInRange(tempCardIds);
            setCurrentPlay(tempCardIds, currentPlay);
            validateCurrentPlay(currentPlay);

            if (Big2.topPlay == null && isNewRound) {
                isCardPatternHasSpade3();
            }
            if (!isNewRound) {
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
        boolean hasSpade3 = false;
        for (Card c : currentPattern.cards) {
            if (c.suit().equals(Suit.C) && c.rank().equals(Rank.THREE)){
                hasSpade3 = true;
            }
        }
        if (hasSpade3 == false)
            throw new RuntimeException();
    }

    private void setCurrentPlay(List<Integer> tempCardIds, List<Card> currentPlay) {
        for (int i : tempCardIds) {
            currentPlay.add(handCard.cards.get(i));
        }
    }

    private void validateCurrentPlay(List<Card> currentPlay) {
        CardPattern s;
        boolean islegal;
        s = new FullHouse();
        islegal = s.isLegal(currentPlay);
        if (islegal) {
            currentPattern = s;
            currentPattern.cards = currentPlay;
            return;
        }
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
        throw new RuntimeException();
    }

    private void checkIdListIsUniqueAndInRange(List<Integer> tempCardIds) {
        Set<Integer> set = new HashSet<>();
        int maxId = handCard.cards.size() - 1;
        for (int i : tempCardIds) {
            if (!set.add(i) || (i > maxId))
                throw new RuntimeException();
        }
    }

    private void checkIdListAllOfInteger(List<String> idList, List<Integer> tempCardIds) {
        for (String s : idList) {
            int id = Integer.parseInt(s);
            tempCardIds.add(id);
        }
    }

    public Action makeAction(boolean isNewRound) {
        Pass pass = new Pass();
        boolean making = true;
        System.out.println("print the card's id(s) to play or print -1 to pass.");
        while (making) {
            String action = scanner.nextLine();
            switch (action) {
                case PASS:
                    if(canPass(isNewRound)) {
                        return pass;
                    }
                    break;
                default:
                    making = checkPlayerInput(action, isNewRound);
            }
        }

        Play play = new Play(currentPattern);
        return play;
    }

    private boolean canPass(boolean isNewRound) {
        if (isNewRound) {
            System.out.println("You can not pass in the new round.");
        }
        return !isNewRound;
    }

    private boolean checkPlayerInput(String action, boolean isNewRound) {
        String[] idArray = action.split("\\s+");
        boolean isLegal = doCheck(idArray, isNewRound);
        boolean reMakeAction = !isLegal;
        return reMakeAction;
    }
}
