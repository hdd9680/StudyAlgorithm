package level3.integerTriangle;

import java.util.Arrays;

/**
 * 작성일 : 2023/09/11
 * 문제 설명 : 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
 * 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
 * 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
 *  - 삼각형의 높이는 1 이상 500 이하입니다.
 *  - 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */

public class Solution {
    public int solution(int[][] triangle) {

        for(int i = 1 ; i < triangle.length ; i++) {

            int xLength = triangle[i].length;
            int maxLength = xLength - 1;

            for(int j = 0 ; j < xLength ; j++) {

                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][0];
                    continue;
                }

                if(j == maxLength) {
                    triangle[i][j] += triangle[i - 1][triangle[i - 1].length - 1];
                    continue;
                }

                int a = triangle[i - 1][j - 1];
                int b = triangle[i - 1][j];

                triangle[i][j] += Math.max(a, b);
            }
        }

        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.solution(new int[][]{
                                                    new int[]{7},
                                                    new int[]{3, 8},
                                                    new int[]{8, 1, 0},
                                                    new int[]{2, 7, 4, 4},
                                                    new int[]{4, 5, 2, 6, 5}});
        System.out.println(result);

    }
}
