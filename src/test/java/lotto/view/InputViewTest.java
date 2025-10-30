package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class InputViewTest {

    InputView inputView = new InputView();

    @Test
    void parsePurchaseAmountInput_숫자_입력() {
        //given
        String naturalNumberInput = "1000";

        //when
        int purchaseAmount = inputView.parsePurchaseAmountInput(naturalNumberInput);

        //then
        assertThat(purchaseAmount).isEqualTo(1000);
    }

    @Test
    void parsePurchaseAmountInput_문자열_입력() {
        //given
        String notNumberInput = "abcde";

        //when && then
        assertThatThrownBy(() -> inputView.parsePurchaseAmountInput(notNumberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validatePurchaseAmount_정상_수행() {
        //given
        int[] correctPurchaseAmounts = {
                1000,
                2000,
                10000,
                11000
        };

        //when && then
        Arrays.stream(correctPurchaseAmounts)
                .forEach(correctPurchaseAmount ->
                        inputView.validatePurchaseAmount(correctPurchaseAmount));
    }

    @Test
    void validatePurchaseAmount_비정상_수행() {
        //given
        int[] wrongPurchaseAmounts = {
                1001,   //1000의 배수 x
                0,      //0
                -1000   //음수
        };

        //when && then
        Arrays.stream(wrongPurchaseAmounts)
                .forEach(correctPurchaseAmount ->
                            assertThatThrownBy(() ->
                                    inputView.validatePurchaseAmount(correctPurchaseAmount)));
    }
}