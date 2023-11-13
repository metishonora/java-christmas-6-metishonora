package christmas.model.badge;

import java.util.Arrays;

public enum Badge {
    // 주의: 높은 금액의 배지가 위쪽에 와야 합니다.
    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),
    NONE(0, "없음");

    private final Integer minimumBenefitAmount;
    private final String name;

    Badge(Integer minimumBenefitAmount, String name) {
        this.minimumBenefitAmount = minimumBenefitAmount;
        this.name = name;
    }

    public static Badge getBadgeFrom(Integer totalBenefit) {
        return Arrays.stream(Badge.values())
                .filter(i -> i.minimumBenefitAmount <= totalBenefit)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
