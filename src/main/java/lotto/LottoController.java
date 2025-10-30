package lotto;

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
    }
}
