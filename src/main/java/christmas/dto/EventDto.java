package christmas.dto;

import java.util.List;

public record EventDto(
        List<String> giveAwayEventResult,
        List<String> eventList,
        List<Integer> benefitAmount,
        int count,
        Integer totalBenefit,
        Integer finalPrice,
        String badgeName
) {
}
