package lotto.controller;

import lotto.domain.LottoGame;
import lotto.domain.lotto.wrapper.LottoNumber;
import lotto.domain.lotto.wrapper.LottoNumbers;
import lotto.domain.lotto.wrapper.WinningNumber;
import lotto.domain.rankcount.RankCountGroup;
import lotto.util.LottoMachine;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoController {

    private final InputView inputView;
    private final ResultView resultView;

    public LottoController() {
        inputView = new InputView();
        resultView = new ResultView();
    }

    public void startGame() {
        List<LottoNumbers> lottos = purchase();
        WinningNumber winningNumber = winning();

        LottoGame lottoGame = new LottoGame(lottos, winningNumber);
        RankCountGroup rankCountGroup = lottoGame.groupByRank();

        result(rankCountGroup, lottoGame.calculateProfitRate(rankCountGroup));
    }

    private List<LottoNumbers> purchase() {
        int numOfLotto = inputView.inputPurchaseMoney();
        int countOfManual = inputView.inputCountOfManual(numOfLotto);
        int countOfAuto = numOfLotto - countOfManual;

        List<LottoNumbers> lottos = IntStream.range(0, countOfManual)
            .mapToObj(i -> new LottoNumbers(inputView.inputManualLotto()))
            .collect(Collectors.toList());

        lottos.addAll(drawLottos(countOfAuto));

        resultView.printPurchaseResult(numOfLotto, lottos);
        return lottos;
    }

    private List<LottoNumbers> drawLottos(int numOfLotto) {
        return IntStream.range(0, numOfLotto)
            .mapToObj(i -> LottoMachine.drawLottoNumbers())
            .collect(Collectors.toUnmodifiableList());
    }

    private WinningNumber winning() {
        Set<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber(winningNumbers);

        return new WinningNumber(winningNumbers, bonusNumber);
    }

    private void result(RankCountGroup rankCountGroup, double profitRate) {
        resultView.printLottoGameResult(rankCountGroup, profitRate);
    }
}
