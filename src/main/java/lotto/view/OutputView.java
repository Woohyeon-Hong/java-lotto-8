package lotto.view;

import java.util.List;
import java.util.Map.Entry;
import lotto.model.Lotto;
import lotto.model.LottoStatistics;
import lotto.model.Rank;

/**
 * 1. 출력 메시지 상수로 분리하기
 */
public class OutputView {

    public OutputView() {
    }

    public void printPurchaseAmountInputPrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount) {
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.formatNumbers());
        }
    }

    public void printLottoNumbersInputPrompt() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInputPrompt() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public void printLottoStatistics(LottoStatistics lottoStatistics) {
        for (Entry<Rank, Long> rankCount : lottoStatistics.getRankCounts()) {
            Rank rank = rankCount.getKey();
            Long count = rankCount.getValue();

            if (rank == Rank.NONE) continue;

            System.out.printf("%s (%,d원) - %d개\n", rank.getMatchingCountMessage(), rank.getPrize(), count);
        }
    }

    public void printRateOfReturn(LottoStatistics lottoStatistics) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", lottoStatistics.getRateOfReturn());
    }

    public void printerrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
