package lotto.view;

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
    public static final String DUPLICATE_BONUS_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 보너스 번호가 이미 당첨번호에 포함돼 있습니다.";
    public static final String NOT_A_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 값이 숫자가 아닙니다.";
    public static final String INVALID_LOTTO_COUNT_ERROR_MESSAGE =
            "[ERROR] 당첨 번호는 6개여야 합니다.";
    public static final String OUT_OF_RANGE_NUMBER_ERROR_MESSAGE =
            "[ERROR] 입력하신 숫자는 1에서 45 사이의 값이어야 합니다.";
    public static final String DUPLICATE_LOTTO_NUMBER_ERROR_MESSAGE =
            "[ERROR] 당첨 번호는 서로 달라야 합니다.";
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
        String[] splits = numbersInput.split(LOTTO_NUMBER_DELIMITER);

        List<Integer> lottoNumbers = Arrays.stream(splits)
                .map(String::trim)
                .map(this::parseInteger)
                .toList();

        validateLottoNumberCount(lottoNumbers);
        validateUniqueLottoNumbers(lottoNumbers);

        lottoNumbers.forEach(this::validateLottoNumberRange);

        return lottoNumbers;
    }

    public int inputBonusNumber(List<Integer> lottoNumbers) {
        String numberInput = Console.readLine();
        int number = parseInteger(numberInput);
        validateLottoNumberRange(number);
        validateBonusNumberNotInWinningNumbers(lottoNumbers, number);
        return number;
    }

    public void validateBonusNumberNotInWinningNumbers(List<Integer> winningNumbers, int number) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERROR_MESSAGE);
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

    public void validatePurchaseAmount(int purchaseAmount) {
        validateNaturalNumber(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
    }

    public void validateLottoNumberCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_COUNT_ERROR_MESSAGE);
        }
    }

    public void validateLottoNumberRange(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException(OUT_OF_RANGE_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateUniqueLottoNumbers(List<Integer> winningNumbers) {
        if (new HashSet<Integer>(winningNumbers).size() != 6) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateNaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException(NOT_MULTIPLE_OF_THOUSAND_ERROR_MESSAGE);
        }
    }
}
