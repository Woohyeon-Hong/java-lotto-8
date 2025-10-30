package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {


    public InputView() {
    }

    public int inputPurchaseAmount() {
        String purchaseAmountInput = Console.readLine();
        int purchaseAmount = parsePurchaseAmountInput(purchaseAmountInput);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public int parsePurchaseAmountInput(String purchaseAmountInput) {
        int purchaseAmount;

        try {
            purchaseAmount = Integer.parseInt(purchaseAmountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력핫신 금액이 숫자가 아닙니다.");
        }

        return purchaseAmount;
    }

    public void validatePurchaseAmount(int purchaseAmount) {
        validateNaturalNumber(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
    }

    private void validateNaturalNumber(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("입력하신 금액이 자연수가 아닙니다.");
        }
    }

    private void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("입력하신 금액이 1000원 단위가 아닙니다.");
        }
    }
}
