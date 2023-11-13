package christmas.dto;

import java.util.List;

// TODO: unmodifiable List로 전달하기
public record EventDto(
        String giveAwayEventResult,
        List<String> eventList,
        List<Integer> benefitAmount,
        int count,
        Integer totalBenefit,
        Integer finalPrice,
        String badgeName
) {
}
