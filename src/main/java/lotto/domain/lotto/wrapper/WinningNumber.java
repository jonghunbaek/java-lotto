package lotto.domain.lotto.wrapper;

import java.util.Set;

public class WinningNumber {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(Set<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public WinningNumber(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public boolean isBonus(LottoNumbers lotto) {
        return lotto.contains(bonusNumber);
    }

    public boolean isSecond(LottoNumbers lotto) {
        return !lotto.contains(bonusNumber);

    }

    public boolean contains(LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }
}
