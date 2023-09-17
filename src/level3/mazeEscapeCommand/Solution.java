package level3.mazeEscapeCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * 작성일 : 2023/09/17
 * 문제 설명 : n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
 * 단, 미로를 탈출하는 조건이 세 가지 있습니다.
 * - 격자의 바깥으로는 나갈 수 없습니다.
 * - (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
 * - 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
 *
 * 이동 경로는 다음과 같이 문자열로 바꿀 수 있습니다.
 * l: 왼쪽으로 한 칸 이동
 * r: 오른쪽으로 한 칸 이동
 * u: 위쪽으로 한 칸 이동
 * d: 아래쪽으로 한 칸 이동
 * https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */

public class Solution {

    private final Map<Character, int[]> rotateMap = new HashMap<>() {{
        put('d', new int[] {1, 0});
        put('l', new int[] {0, -1});
        put('r', new int[] {0, 1});
        put('u', new int[] {-1, 0});
    }};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        int xDistance = r - x;
        int yDistance = c - y;
        int distance = Math.abs(xDistance) + Math.abs(yDistance);

        if(distance > k || (k - distance) % 2 == 1) {
            return "impossible";
        }

        while(k > distance) {

            char rotate;

            if(x < n) {
                rotate = 'd';
            } else if(y > 1) {
                rotate = 'l';
            } else if(y < m) {
                rotate = 'r';
            } else {
                rotate = 'u';
            }

            answer.append(rotate);
            int[] move = rotateMap.get(rotate);
            x += move[0];
            y += move[1];
            xDistance = r - x;
            yDistance = c - y;
            distance = Math.abs(xDistance) + Math.abs(yDistance);
            k--;
        }

        while(k > 0) {

            char rotate;

            if(xDistance > 0) {
                rotate = 'd';
            }else if(yDistance < 0) {
                rotate = 'l';
            } else if(yDistance > 0) {
                rotate = 'r';
            } else {
                rotate = 'u';
            }

            answer.append(rotate);
            int[] move = rotateMap.get(rotate);
            x += move[0];
            y += move[1];
            xDistance = r - x;
            yDistance = c - y;
            distance = Math.abs(xDistance) + Math.abs(yDistance);
            k--;
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(2, 2, 1, 1, 2, 2, 2));
    }
}
