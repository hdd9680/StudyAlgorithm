package level3.entryScreening;

import java.util.Arrays;

/**
 * 작성일 : 2023/09/12
 * 문제 설명 : n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.
 * 처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다.
 * 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.
 * 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.
 * 입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때,
 * 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * 답지 확인해버림
 * 알고리즘의 핵심은 시간을 기준으로 이분탐색을 진행하여 해결한다는 과정이다.
 * 이러한 방법으로 문제를 해결할 경우 입국자의 수가 적을경우에는 다소 느릴지 몰라도,
 * 입국자의 수가 기하급수 적으로 늘어나거나 극단적으로 최소 소요시간이 최악인 경우에는 비교적 빠른 소요시간을 가진다.
 */

public class Solution {
    public long solution(int n, int[] times) {
        int length = times.length;
        Arrays.sort(times);

        long maxTime = (long) times[length - 1] * n;
        long minTime = 1;
        long answer = maxTime;

        while (minTime <= maxTime) {
            long averageTime = (minTime + maxTime) / 2;
            long tempN = 0;

            for (int i = 0; i < length; i++) {
                tempN += averageTime / times[i];
                if (tempN >= n) break;
            }

            if (tempN < n) {
                minTime = averageTime + 1;
            } else {
                answer = Math.min(answer, averageTime);
                maxTime = averageTime - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[] {7, 10}));
    }

}
