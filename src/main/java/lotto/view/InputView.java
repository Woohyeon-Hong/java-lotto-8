package lotto.view;

import static lotto.support.LottoRules.*;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lotto.model.PurchaseAmount;


/**
 * TODO
 * 1. Validator, Parser 분리하기
 */
public class InputView {

    public static final String NOT_A_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 값이 숫자가 아닙니다.";

    private static final String LOTTO_NUMBER_DELIMITER = ",";

    public InputView() {
    }

    public PurchaseAmount inputPurchaseAmount() {
        String purchaseAmountInput = Console.readLine();
        int value = parseInteger(purchaseAmountInput);
        return new PurchaseAmount(value);
    }

    public List<Integer> inputLottoNumbers() {
        String numbersInput = Console.readLine();
        String[] rawNumbers = numbersInput.split(LOTTO_NUMBER_DELIMITER);
        return parseRawNumbers(rawNumbers);
    }

    public int inputBonusNumber() {
        String numberInput = Console.readLine();
        return parseInteger(numberInput);
    }

    private List<Integer> parseRawNumbers(String[] splits) {
        return Arrays.stream(splits)
                .map(String::trim)
                .map(this::parseInteger)
                .toList();
    }


    public int parseInteger(String numberInput) {
        int purchaseAmount;

        try {
            purchaseAmount = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException( NOT_A_NUMBER_ERROR_MESSAGE);
        }

        return purchaseAmount;
    }
}
