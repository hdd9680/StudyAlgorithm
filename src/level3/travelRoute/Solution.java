package level3.travelRoute;

import java.util.*;

/**
 * 작성일 : 2023/09/13
 * 문제 설명 : 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
 * 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *  - 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
 *  - 주어진 공항 수는 3개 이상 10,000개 이하입니다.
 *  - tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
 *  - 주어진 항공권은 모두 사용해야 합니다.
 *  - 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
 *  - 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 *
 * 답지 확인해버림
 * 알고리즘의 핵심은 정렬된 그래프를 만드는것과 탐색 알고리즘의 선정으로 보인다.
 * 난 정렬된 그래프를 만다는것은 성공했지만 탐색알고리즘 선정 및 구현에 실패하였다.
 */
public class Solution {

    private Map<String, PriorityQueue<String>> routeGraph = new HashMap<>();
    public String[] solution(String[][] tickets) {
        for(String[] ticket : tickets) {
            String start = ticket[0];
            PriorityQueue<String> route = routeGraph.get(start);

            if(route == null) {
                route = new PriorityQueue<String>();
            }

            route.add(ticket[1]);
            routeGraph.put(start, route);
        }

        return dfs("ICN").toArray(new String[0]);
    }

    private List<String> dfs(String key) {
        List<String> routeSequenceList = new ArrayList<>();

        if(!routeGraph.containsKey(key) || routeGraph.get(key).isEmpty()) {
            routeSequenceList.add(key);
            return routeSequenceList;
        }

        routeSequenceList.add(key);

        List<String> right = dfs(routeGraph.get(key).poll());

        if(!routeGraph.get(key).isEmpty()) {
            List<String> left = dfs(routeGraph.get(key).poll());
            routeSequenceList.addAll(left);
        }

        routeSequenceList.addAll(right);

        return routeSequenceList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] result = solution.solution(new String[][]{new String[]{"ICN", "SFO"}, new String[]{"ICN", "ATL"}, new String[]{"SFO", "ATL"}, new String[] {"ATL", "ICN"}, new String[] {"ATL", "SFO"}});

        Arrays.stream(result).forEach(System.out::println);
    }
}
