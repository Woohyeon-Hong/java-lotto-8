package lotto.view;

import static lotto.support.LottoRules.*;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * TODO
 * 1. Validator, Parser 분리하기
 */
public class InputView {

    private static final String LOTTO_NUMBER_DELIMITER = ",";


    //예외 메시지
    public static final String NOT_A_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 값이 숫자가 아닙니다.";
    public static final String NOT_NATURAL_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 값이 자연수가 아닙니다.";
    public static final String NOT_MULTIPLE_OF_THOUSAND_ERROR_MESSAGE =
            "[ERROR] 입력하신 금액이 1000원 단위가 아닙니다.";



    public InputView() {
    }

    public int inputPurchaseAmount() {
        String purchaseAmountInput = Console.readLine();
        int purchaseAmount = parseInteger(purchaseAmountInput);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
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

    public void validatePurchaseAmount(int purchaseAmount) {
        validateNaturalNumber(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
    }

    private List<Integer> parseRawNumbers(String[] splits) {
        return Arrays.stream(splits)
                .map(String::trim)
                .map(this::parseInteger)
                .toList();
    }

    private void validateNaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(NOT_MULTIPLE_OF_THOUSAND_ERROR_MESSAGE);
        }
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
