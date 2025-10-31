package lotto.view;

import lotto.model.Lotto;

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

    public void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.formatNumbers());
    }

    public void printWinningNumbersInputPrompt() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInputPrompt() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
