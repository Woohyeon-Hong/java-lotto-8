package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    private LottoStatistics lottoStatistics;

    @BeforeEach
    void beforeEach() {
        WinningNumbers winningNumbers =
                new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),       // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),       // 2등 (보너스 포함)
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),      // 3등 (1)
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),      // 3등 (2)
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),     // 4등 (1)
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),     // 4등 (2)
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),     // 4등 (3)
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),    // 5등 (1)
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),    // 5등 (2)
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),    // 5등 (3)
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),    // 5등 (4)
                new Lotto(List.of(1, 2, 10, 11, 12, 13))   // 낙첨
        );

        lottoStatistics = new LottoStatistics(winningNumbers, lottos, 12);
    }

    @Test
    void summarize_등수별_통계를_계산한다() {
        //when
        Map<Rank, Long> rankCounts = lottoStatistics.summarize();

        //then
        assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.THIRD)).isEqualTo(2);
        assertThat(rankCounts.get(Rank.FOURTH)).isEqualTo(3);
        assertThat(rankCounts.get(Rank.FIFTH)).isEqualTo(4);
        assertThat(rankCounts.get(Rank.NONE)).isEqualTo(1);
    }

    @Test
    void sortByWinningCountDescThenRankAsc_당첨된_개수가_많은_순서대로_정렬한다() {
        //given
        Map<Rank, Long> rankCounts = lottoStatistics.summarize();

        //when
        List<Entry<Rank, Long>> sorted = lottoStatistics.sortByWinningCountDescThenRankDesc(rankCounts);

        //then

        // 당첨된 개수가 많은 순서대로 정렬한다.
        assertThat(sorted.get(0).getKey()).isSameAs(Rank.FIFTH);
        assertThat(sorted.get(1).getKey()).isSameAs(Rank.FOURTH);
        assertThat(sorted.get(2).getKey()).isSameAs(Rank.THIRD);

        //당첨된 개수가 동일하면, 등수에 따라 내림차순으로 정렬한다.
        assertThat(sorted.get(3).getKey()).isSameAs(Rank.NONE);
        assertThat(sorted.get(4).getKey()).isSameAs(Rank.SECOND);
        assertThat(sorted.get(5).getKey()).isSameAs(Rank.FIRST);

    }
}