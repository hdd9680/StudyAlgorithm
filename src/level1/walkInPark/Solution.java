package level1.walkInPark;

import java.util.Collections;
import java.util.Map;

/**
 * 작성일 : 2023/09/08
 * 문제 설명 : 지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서 로봇 강아지가 산책을 하려합니다.
 * 산책은 로봇 강아지에 미리 입력된 명령에 따라 진행하며, 명령은 다음과 같은 형식으로 주어집니다.
 *  - ["방향 거리", "방향 거리" … ]
 * 예를 들어 "E 5"는 로봇 강아지가 현재 위치에서 동쪽으로 5칸 이동했다는 의미입니다. 로봇 강아지는 명령을 수행하기 전에 다음 두 가지를 먼저 확인합니다.
 *  - 주어진 방향으로 이동할 때 공원을 벗어나는지 확인합니다.
 *  - 주어진 방향으로 이동 중 장애물을 만나는지 확인합니다.
 * 위 두 가지중 어느 하나라도 해당된다면, 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행합니다.
 * 공원의 가로 길이가 W, 세로 길이가 H라고 할 때, 공원의 좌측 상단의 좌표는 (0, 0), 우측 하단의 좌표는 (H - 1, W - 1) 입니다.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/172928
 */

public class Solution {

    private final Map<Character, int[]> directionToValue = Collections.unmodifiableMap(Map.of('E', new int[] {1, 0}, 'W', new int[] {-1, 0}, 'S', new int[] {0, 1}, 'N', new int[] {0, -1}));

    private final char WALL = 'X';
    private final char LOAD = 'O';
    private final char START = 'S';

    private final int DIRECTION = 0;
    private final int MOVE_LENGTH = 2;

    public int[] solution(String[] park, String[] routes) {

        int nowX = 0;
        int nowY = 0;

        int minX = 0;
        int minY = 0;
        int maxX = park[0].length();
        int maxY = park.length;

        char[][] map = new char[park.length][];

        for(int y = 0 ; y < park.length ; y++) {
            map[y] = new char[park[y].length()];
            for (int x = 0; x < park[y].length(); x++) {
                char pointChar = park[y].charAt(x);
                if (pointChar == START) {
                    nowX = x;
                    nowY = y;
                }
                map[y][x] = pointChar;
            }
        }

        for(String route : routes) {
            char direction = route.charAt(DIRECTION);
            int moveLenght = Character.getNumericValue(route.charAt(MOVE_LENGTH));
            boolean canMove = true;
            int[] moveXy = directionToValue.get(direction);
            int moveX = moveXy[0] * moveLenght;
            int moveY = moveXy[1] * moveLenght;
            int movedX = nowX + moveX;
            int movedY = nowY + moveY;

            if(movedX < minX || movedX >= maxX || movedY < minY || movedY >= maxY) {
                continue;
            }

            for(int i = 1 ; i < moveLenght + 1 ; i++) {
                int tempX = nowX + i * moveXy[0];
                int tempY = nowY + i * moveXy[1];
                if(map[tempY][tempX] == WALL) {
                    canMove = false;
                    break;
                }
            }

            if(canMove) {
                nowX = movedX;
                nowY = movedY;
            }
        }

        return new int[] {nowY, nowX};
    }

}
