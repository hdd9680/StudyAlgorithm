package level2.continuousSubSequenceSum;

import java.util.Arrays;

/**
 * 작성일 : 2023/09/07
 * 문제 설명 : 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 * - 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
 * - 부분 수열의 합은 k입니다.
 * - 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
 * - 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,
 * 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요. 이때 수열의 인덱스는 0부터 시작합니다.
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 *
 * 로직 자체는 성립하지만 속도가 너무 느려서 문제에서 원하는 속도를 맞추진 못하였다.
 */

public class Solution {

    public int[] solution(int[] sequence, int k) {

        int sequenceCount = 1;
        int sequenceLength = sequence.length;

        while(true) {

            startIndexLoop :
            for(int startIndex = 0 ; startIndex < sequenceLength ; startIndex++) {
                int sumResult = 0;

                if(sequence[startIndex] > k) {
                    break;
                }

                for(int i = 0 ; i < sequenceCount ; i++) {
                    if(i + startIndex >= sequenceLength) {
                        break startIndexLoop;
                    }

                    sumResult += sequence[startIndex + i];

                    if(sumResult > k) {
                        break startIndexLoop;
                    }
                }

                if(sumResult == k) {
                    return new int[]{startIndex, startIndex + sequenceCount - 1};
                }
            }

            sequenceCount++;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] result = solution.solution(new int[]{1, 2, 3, 4, 5}, 7);
        System.out.println(result[0] + ", " + result[1]);

        result = solution.solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5);
        System.out.println(result[0] + ", " + result[1]);

        result = solution.solution(new int[]{2, 2, 2, 2, 2}, 6);
        System.out.println(result[0] + ", " + result[1]);

    }

}
