package lotto.view;

import lotto.model.Lotto;

public class OutputView {

    public OutputView() {
    }

    public void printPurchaseAmountInputPrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount) {
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");
    }

    public void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.formatNumbers());
    }
}
