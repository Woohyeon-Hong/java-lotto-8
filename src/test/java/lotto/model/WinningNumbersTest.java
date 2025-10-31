package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void evaluate_일치_개수와_보너스_번호_일_여부에_따라_당첨_등수를_결정한다() {
        //given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1,2,3,4,5,6)),        // 1등
                new Lotto(List.of(1,2,3,4,5,7)),        // 2등
                new Lotto(List.of(1,2,3,4,5,10)),       // 3등
                new Lotto(List.of(1,2,3,4,10,11)),      // 4등
                new Lotto(List.of(1,2,3,10,11,12)),     // 5등
                new Lotto(List.of(1,2,10,11,12,13)),    // 등수 외
                new Lotto(List.of(10,11,12,13,14,7))    // 보너스 번호만 당첨
        );

        //when
        Rank first = winningNumbers.evaluate(lottos.get(0));
        Rank second = winningNumbers.evaluate(lottos.get(1));
        Rank third = winningNumbers.evaluate(lottos.get(2));
        Rank fourth = winningNumbers.evaluate(lottos.get(3));
        Rank fifth = winningNumbers.evaluate(lottos.get(4));
        Rank none = winningNumbers.evaluate(lottos.get(5));
        Rank onlyBonusNumber = winningNumbers.evaluate(lottos.get(6));

        //then
        assertThat(first).isSameAs(Rank.FIRST);
        assertThat(second).isSameAs(Rank.SECOND);
        assertThat(third).isSameAs(Rank.THIRD);
        assertThat(fourth).isSameAs(Rank.FOURTH);
        assertThat(fifth).isSameAs(Rank.FIFTH);
        assertThat(none).isSameAs(Rank.NONE);
        assertThat(onlyBonusNumber).isSameAs(Rank.NONE);
    }
}