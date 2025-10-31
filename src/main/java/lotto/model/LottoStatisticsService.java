package lotto.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatisticsService {

    public Map<Rank, Long> summarize(WinningNumbers winningNumbers, List<Lotto> lottos) {
        return lottos.stream()
                .map(winningNumbers::evaluate)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }
}
