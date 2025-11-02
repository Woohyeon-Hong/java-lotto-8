package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoStatistics;
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

        try {
            int purchaseAmount = requestPurchaseAmount();
            LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);
            outputView.printLottoCount(lottoGenerator.getLottoCount());

            List<Lotto> lottos = lottoGenerator.generateLottos();
            outputView.printLottos(lottos);

            WinningNumbers winningNumbers = requestWinningNumber();

            LottoStatistics statistics = computeStatistics(winningNumbers, lottos, purchaseAmount);
            printLottoStatistics(statistics);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }

    }

    private int requestPurchaseAmount() {
        outputView.printPurchaseAmountInputPrompt();
        return inputView.inputPurchaseAmount();
    }

    private WinningNumbers requestWinningNumber() {
        outputView.printLottoNumbersInputPrompt();
        List<Integer> lottoNumbers = inputView.inputLottoNumbers();

        outputView.printBonusNumberInputPrompt();
        int bonusNumber = inputView.inputBonusNumber(lottoNumbers);

        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);
        return winningNumbers;
    }

    private static LottoStatistics computeStatistics(WinningNumbers winningNumbers, List<Lotto> lottos,
                                                     int purchaseAmount) {
        LottoStatistics lottoStatistics = new LottoStatistics(winningNumbers, lottos, purchaseAmount);
        lottoStatistics.compute();
        return lottoStatistics;
    }

    private void printLottoStatistics(LottoStatistics lottoStatistics) {
        outputView.printLottoStatistics(lottoStatistics);
        outputView.printRateOfReturn(lottoStatistics);
    }
}
