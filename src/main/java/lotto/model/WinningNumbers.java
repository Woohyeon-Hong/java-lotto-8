package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.numbers = List.copyOf(numbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank evaluate(Lotto lotto) {
        int matchCount = lotto.countMatchingNumbers(this.numbers);
        boolean bonusMatch = lotto.hasBonusNumber(this.bonusNumber);
        return Rank.of(matchCount, bonusMatch);
    }
}
