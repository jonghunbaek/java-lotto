package lotto.domain.lotto.wrapper;

import lotto.domain.rankcount.RankCountGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static lotto.domain.lotto.LotteryRank.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottosTest {

    @DisplayName("당첨번호+보너스 번호를 인자로 받아 자신의 번호와 비교 후 일치 하는 개수 별로 그룹핑하여 각 그룹의 개수를 반환한다.")
    @Test
    void countByMatchingNumGroupWithBonusNumber() {
        // given
        Lottos lottos = new Lottos(
            List.of(
                new LottoNumbers(Set.of(1, 5, 12, 21, 32, 43)),
                new LottoNumbers(Set.of(1, 5, 12, 21, 32, 43)),
                new LottoNumbers(Set.of(1, 5, 12, 21, 42, 45)),
                new LottoNumbers(Set.of(1, 5, 12, 21, 32, 42)),
                new LottoNumbers(Set.of(1, 5, 12, 21, 32, 45)),
                new LottoNumbers(Set.of(1, 5, 12, 21, 31, 45)),
                new LottoNumbers(Set.of(1, 5, 12, 22, 31, 45))
            ));

        WinningNumber winningNumber = new WinningNumber(Set.of(1, 5, 12, 21, 32, 43), 45);

        // when
        RankCountGroup rankCountGroup = lottos.groupByRank(winningNumber);

        // then
        assertAll(
            () -> assertEquals(2,rankCountGroup.findWinningCountBy(FIRST)),
            () -> assertEquals(1, rankCountGroup.findWinningCountBy(SECOND)),
            () -> assertEquals(1, rankCountGroup.findWinningCountBy(THIRD)),
            () -> assertEquals(2, rankCountGroup.findWinningCountBy(FOURTH)),
            () -> assertEquals(1, rankCountGroup.findWinningCountBy(FIFTH))
        );
    }
}
