package level2.eachCircleInPoint;

/**
 * 작성일 : 2023/09/06
 * 문제 설명 : x축과 y축으로 이루어진 2차원 직교 좌표계에 중심이 원점인 서로 다른 크기의 원이 두 개 주어집니다.
 * 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때, 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수를 return하도록 solution 함수를 완성해주세요.
 * ※ 각 원 위의 점도 포함하여 셉니다.
 * https://school.programmers.co.kr/learn/courses/30/lessons/181187
 *
 * 로직 자체는 성립하지만 속도가 너무 느려서 문제에서 원하는 속도를 맞추진 못하였다.
 */

public class Solution {

    public long solution(int r1, int r2) {
        long oneQuarter = 0;

        long r1Pow = (long)r1 * r1;
        long r2Pow = (long)r2 * r2;

        for(int x = 1 ; x < r2 ; x++) {
            for(int y = 1 ; y < r2 ; y++) {

                int xPlusY = x + y;
                if(xPlusY < r1 && r2 < xPlusY) {
                    continue;
                }

                long xPow = (long) x * x;
                long yPow = (long) y * y;
                long xToYPow = xPow + yPow;

                if(r1Pow <= xToYPow && xToYPow <= r2Pow) {
                    oneQuarter++;
                }
            }
        }

        return (oneQuarter * 4) + ((r2 - r1 + 1) * 4);
    }

    public static void main(String[] args) {

        System.out.println(new Solution().solution(2, 3));

    }

}
