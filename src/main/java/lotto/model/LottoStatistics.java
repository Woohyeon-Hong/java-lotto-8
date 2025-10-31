package lotto.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LottoStatistics {

    private final WinningNumbers winningNumbers;
    private final List<Lotto> lottos;
    private final int purchaseAmount;

    public LottoStatistics(WinningNumbers winningNumbers, List<Lotto> lottos, int purchaseAmount) {
        this.winningNumbers = winningNumbers;
        this.lottos = lottos;
        this.purchaseAmount = purchaseAmount;
    }

    public Map<Rank, Long> summarize() {
        Map<Rank, Long> rankCounts = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank, rank -> 0L));

        Map<Rank, Long> counted = lottos.stream()
                .map(winningNumbers::evaluate)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        counted.forEach(rankCounts::put);

        return rankCounts;
    }

    public List<Entry<Rank, Long>> sortByWinningCountDescThenRankDesc(Map<Rank, Long> rankCounts) {
        Comparator<Map.Entry<Rank, Long>> comparator =
                Map.Entry.<Rank, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.<Rank, Long>comparingByKey(
                                Comparator.comparingInt(Rank::getOrder).reversed()
                        ));

        return rankCounts.entrySet().stream()
                .sorted(comparator)
                    .toList();
    }

    public double calculateRateOfReturn(Map<Rank, Long> counts) {
        long totalPrize = counts.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrize() * e.getValue())
                .sum();

        double rate = (totalPrize * 1.0 / purchaseAmount) * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
