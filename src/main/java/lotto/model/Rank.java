package lotto.model;

public enum Rank {
    FIRST(1,"6개 일치", 2000000000, "2,000,000,000원"),
    SECOND(2,"5개 일치, 보너스 볼 일치", 30000000, "30,000,000원"),
    THIRD( 3,"5개 일치", 1500000, "1,500,000원"),
    FOURTH(4, "4개 일치", 50000, "50,000원"),
    FIFTH( 5,"3개 일치", 5000, "5,000원"),
    NONE( 6, "others", 0, "0원");

    private final int order;
    private final String matchingCountMessage;
    private final int prize;
    private final String prizeMessage;

    Rank(int order, String matchingCount, int prize, String prizeMessage) {
        this.order = order;
        this.matchingCountMessage = matchingCount;
        this.prize = prize;
        this.prizeMessage = prizeMessage;
    }

    public int getOrder() {
        return order;
    }

    public String getMatchingCountMessage() {
        return matchingCountMessage;
    }

    public int getPrize() {
        return prize;
    }

    public String getPrizeMessage() {
        return prizeMessage;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && bonusMatch) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }
}
