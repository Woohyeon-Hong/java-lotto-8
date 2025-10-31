package lotto;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoStatistics;
import lotto.model.Rank;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {

        outputView.printPurchaseAmountInputPrompt();
        int purchaseAmount = inputView.inputPurchaseAmount();

        int lottoCount = purchaseAmount / 1000;
        outputView.printLottoCount(lottoCount);

        LottoGenerator lottoGenerator = new LottoGenerator();

        List<Lotto> lottos = lottoGenerator.generateLottos(lottoCount);
        outputView.printLottos(lottos);

        outputView.printWinningNumbersInputPrompt();
        List<Integer> lottoNumbers = inputView.inputLottoNumbers();

        outputView.printBonusNumberInputPrompt();
        int bonusNumber = inputView.inputBonusNumber(lottoNumbers);

        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        LottoStatistics lottoStatistics = new LottoStatistics(winningNumbers, lottos, purchaseAmount);
        Map<Rank, Long> rankCounts = lottoStatistics.summarize();
        List<Entry<Rank, Long>> sortedRankedCounts = lottoStatistics.sortByWinningCountDescThenRankDesc(rankCounts);
        outputView.printLottoStatistics(sortedRankedCounts);

        double rateOfReturn = lottoStatistics.calculateRateOfReturn(rankCounts);
        outputView.printRateOfReturn(rateOfReturn);
    }
}
