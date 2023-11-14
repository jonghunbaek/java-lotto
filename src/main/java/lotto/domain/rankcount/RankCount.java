package lotto.domain.rankcount;

import lotto.domain.lotto.LotteryRank;

public class RankCount {

    private LotteryRank rank;
    private long winningCount;

    public RankCount(LotteryRank rank, long winningCount) {
        this.rank = rank;
        this.winningCount = winningCount;
    }

    public long winningCount() {
        return this.winningCount;
    }

    public boolean isSameRank(LotteryRank rank) {
        return this.rank.equals(rank);
    }
}
