package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoNumberGenerator;
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

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        ArrayList<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = lottoNumberGenerator.generateUniqueNumbers();
            Lotto lotto = new Lotto(numbers);
            outputView.printLottoNumbers(lotto);
            lottos.add(lotto);
        }
    }
}
