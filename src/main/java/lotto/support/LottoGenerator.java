package lotto.support;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;

public class LottoGenerator {

    private final int lottoCount;

    public LottoGenerator(int purchaseAmount) {
        this.lottoCount = purchaseAmount / LottoRules.LOTTO_PRICE;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<Lotto> generateLottos() {
        ArrayList<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(generateUniqueNumbers()));
        }

        return lottos;
    }

    public List<Integer> generateUniqueNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LottoRules.MIN_NUMBER, LottoRules.MAX_NUMBER, LottoRules.LOTTO_NUMBER_COUNT);
    }
}
