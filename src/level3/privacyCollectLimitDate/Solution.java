package level3.privacyCollectLimitDate;

import java.util.*;

/**
 * 작성일 : 2023/09/15
 * 문제 설명 : 고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다. 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
 * 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다. 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
 * 예를 들어, A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
 * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
 *  - 모든 달은 28일까지 있다고 가정합니다.
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
public class Solution {

    private final int MONTH_OF_YEAR = 12;
    private final int DAY_OF_MONTH = 28;

    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayTotalDays = stringDateToDays(today);

        Map<String, Integer> termsMap = new HashMap<>();

        for(String each : terms) {
            String[] splitTerms = each.split(" ");
            String policyName = splitTerms[0];
            int term = Integer.valueOf(splitTerms[1]);
            termsMap.put(policyName, todayTotalDays - term * DAY_OF_MONTH);
        }

        List<Integer> answer = new ArrayList(privacies.length);
        for(int i = 0 ; i < privacies.length ; i++) {
            String[] splitPrivacy = privacies[i].split(" ");
            String privacyDate = splitPrivacy[0];
            String policyName = splitPrivacy[1];

            if(stringDateToDays(privacyDate) <= termsMap.get(policyName)) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int stringDateToDays(String date) {
        String[] splitDate = date.split("\\.");
        int year = Integer.valueOf(splitDate[0]);
        int month = Integer.valueOf(splitDate[1]);
        int day = Integer.valueOf(splitDate[2]);

        return year * MONTH_OF_YEAR * DAY_OF_MONTH + month * DAY_OF_MONTH + day;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Arrays.stream(sol.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})).forEach(System.out::println);
    }
}
