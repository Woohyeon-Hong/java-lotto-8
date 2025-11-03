package lotto;

import static lotto.support.LottoRules.LOTTO_PRICE;

import java.util.List;
import lotto.model.Lotto;
import lotto.support.LottoGenerator;
import lotto.support.LottoStatistics;
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
            List<Lotto> lottos = purchaseLottos();
            WinningNumbers winningNumbers = drawWinningNumber();
            processLottoStatistics(winningNumbers, lottos);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }

    }

    private List<Lotto> purchaseLottos() {
        int purchaseAmount = requestPurchaseAmount();
        LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);
        outputView.printLottoCount(lottoGenerator.getLottoCount());

        List<Lotto> lottos = lottoGenerator.generateLottos();
        outputView.printLottos(lottos);

        return lottos;
    }

    private WinningNumbers drawWinningNumber() {
        outputView.printLottoNumbersInputPrompt();
        List<Integer> lottoNumbers = inputView.inputLottoNumbers();

        outputView.printBonusNumberInputPrompt();
        int bonusNumber = inputView.inputBonusNumber(lottoNumbers);

        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);
        return winningNumbers;
    }

    private int requestPurchaseAmount() {
        outputView.printPurchaseAmountInputPrompt();
        return inputView.inputPurchaseAmount();
    }

    private void processLottoStatistics(WinningNumbers winningNumbers, List<Lotto> lottos) {
        LottoStatistics statistics =
                computeStatistics(winningNumbers, lottos, lottos.size() * LOTTO_PRICE);

        printLottoStatistics(statistics);
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
