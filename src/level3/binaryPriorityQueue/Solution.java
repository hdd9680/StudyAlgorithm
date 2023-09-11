package level3.binaryPriorityQueue;

import java.util.TreeMap;

/**
 * 작성일 : 2023/09/11
 * 문제 설명 : 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
 *  - I 숫자 : 큐에 주어진 숫자를 삽입합니다.
 *  - D 1 : 큐에서 최댓값을 삭제합니다.
 *  - D -1 : 큐에서 최솟값을 삭제합니다.
 * 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면
 * [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */

public class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();

        for (String each : operations) {

            String[] splitOperation = each.split(" ");
            char operation = splitOperation[0].toCharArray()[0];
            int integer = Integer.parseInt(splitOperation[1]);

            if(operation == 'D') {

                if(tree.isEmpty())
                    continue;

                if(integer == -1) {
                    Integer value = tree.get(tree.firstKey());
                    if(value == 0)
                        tree.remove(tree.firstKey());
                    else
                        tree.put(tree.firstKey(), value - 1);
                    continue;
                }

                Integer value = tree.get(tree.lastKey());
                if(value == 0)
                    tree.remove(tree.lastKey());
                else
                    tree.put(tree.firstKey(), value - 1);
                continue;
            }

            Integer value = tree.get(integer);
            tree.put(integer, value == null ? 0 : value + 1);
        }

        if(tree.isEmpty()) {
            return new int[] {0, 0};
        }

        return new int[]{tree.lastKey(), tree.firstKey()};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] answer = sol.solution(new String[] {"I 16", "I 16", "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "I 123", "D 1"});
        System.out.println(answer[0] + ", " + answer[1]);

    }
}
