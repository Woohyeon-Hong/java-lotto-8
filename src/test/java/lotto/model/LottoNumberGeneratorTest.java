package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    
    @Test
    void generateUniqueNumbers_서로_다른_6개의_수를_반환한다() {
        //given && when
        List<Integer> numbers = lottoNumberGenerator.generateUniqueNumbers();
        
        //then
        assertThat(numbers.size()).isEqualTo(6);                        //6 개의 수를 반환하는지 검증
        assertThat(new HashSet<Integer>(numbers).size()).isEqualTo(6);  //6 개의 수가 서로 다른지 검증
        assertThat(numbers)                                                      //1 ~ 45 사이인지 검증
                .allMatch(number -> number >= 1 && number <= 45);
    }
}