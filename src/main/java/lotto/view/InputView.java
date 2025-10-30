package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * TODO
 * 1. Validator, Parser 분리하기
 * 2. 예외 메시지 상수로 분리하기
 * 3. split 구분자 상수로 분리하기
 */
public class InputView {


    public InputView() {
    }

    public int inputPurchaseAmount() {
        String purchaseAmountInput = Console.readLine();
        int purchaseAmount = parseInteger(purchaseAmountInput);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public List<Integer> inputWinningNumbers() {
        String numbersInput = Console.readLine();
        String[] splits = numbersInput.split(",");

        List<Integer> winningNumbers = Arrays.stream(splits)
                .map(String::trim)
                .map(this::parseInteger)
                .toList();

        validateLottoNumberCount(winningNumbers);
        validateUniqueLottoNumbers(winningNumbers);

        winningNumbers.forEach(this::validateLottoNumberRange);



        return winningNumbers;
    }

    public int parseInteger(String numberInput) {
        int purchaseAmount;

        try {
            purchaseAmount = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력핫신 값이 숫자가 아닙니다.");
        }

        return purchaseAmount;
    }

    public void validatePurchaseAmount(int purchaseAmount) {
        validateNaturalNumber(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
    }

    public void validateLottoNumberCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    public void validateLottoNumberRange(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException("입력하신 숫자는 1에서 45 사이의 값이어야 합니다.");
        }
    }

    private void validateUniqueLottoNumbers(List<Integer> winningNumbers) {
        if (new HashSet<Integer>(winningNumbers).size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 서로 달라야 합니다.");
        }
    }

    private void validateNaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("입력하신 값이 자연수가 아닙니다.");
        }
    }

    private void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("입력하신 금액이 1000원 단위가 아닙니다.");
        }
    }
}
