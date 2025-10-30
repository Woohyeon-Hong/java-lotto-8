package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class InputViewTest {

    InputView inputView = new InputView();

    @Test
    void parseInteger_숫자가_입력되면_정상적으로_파싱된다() {
        //given
        String naturalNumberInput = "1000";

        //when
        int purchaseAmount = inputView.parseInteger(naturalNumberInput);

        //then
        assertThat(purchaseAmount).isEqualTo(1000);
    }

    @Test
    void parseInteger_숫자가_아닌_값이_입렫되면_예외가_발생한다() {
        //given
        String[] wrongs = {
                "abcde",    // 문자
                "",         // empty
                " ",        // blank
                " 1"        // 공백 포함
        };

        //when && then
        Arrays.stream(wrongs)
                .forEach(wrong ->
                        assertThatThrownBy(() ->
                                inputView.parseInteger(wrong))
                                    .isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    void validatePurchaseAmount_1000의_배수가_입력되면_예외가_발생하지_않는다() {
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
    void validatePurchaseAmount_1000의_배수가_아니거나_자연수가_아닌_값이_입력되면_예외가_발생한다() {
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

    @Test
    void validateLottoNumberCount_당첨번호가_6개가_아니면_예외가_발생한다() {
        //given
        List<Integer> numbers = List.of(1,2,3,4,5,6,7);

        //when && then
        assertThatThrownBy(() ->
                inputView.validateLottoNumberCount(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateUniqueLottoNumbers_입렫된_6개의_당첨번호는_서로_달라야한다() {
        //given
        List<Integer> numbers = List.of(1,1,2,3,4,5,6);

        //when && then
        assertThatThrownBy(() ->
                inputView.validateLottoNumberCount(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateLottoNumberRange_1에서_45_사이_밖의_범위의_숫자가_입력되면_예외가_발생한다() {
        //given
        int outOfRangeNumber = 46;

        //when && then
        assertThatThrownBy(() ->
                inputView.validateLottoNumberRange(outOfRangeNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}