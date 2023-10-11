package level3.consultantNumber;

import java.util.*;

/**
 * 작성일 : 2023/10/11
 * 문제 설명 : 문제 설명이 매우 길기에 하기 주소 참조
 * https://school.programmers.co.kr/learn/courses/30/lessons/214288
 */
public class Solution {

    public int solution(int k, int n, int[][] counselorArray) {
        List<List<Time>> counselTypeList = new ArrayList<>(k) {};

        for(int i = 0 ; i < k ; i++) {
            counselTypeList.add(new ArrayList<>());
        }

        for(int[] counselor : counselorArray) {
            counselTypeList.get(counselor[2] - 1).add(new Time(counselor[0], counselor[1]));
        }

        int[][] counselorByTotalCounselTantTimeTable = new int[k][n];
        for(int i = 0 ; i < counselTypeList.size() ; i++) {
            List<Time> counselList = counselTypeList.get(i);

            for(int j = 0 ; j < n ; j++) {
                int waitTime = calculateWaitTime(counselList, j + 1);
                if(waitTime == 0) break;
                counselorByTotalCounselTantTimeTable[i][j] = waitTime;
            }
        }

        int[] counselorCountRow = new int[k];

        int remainCounselorNumber = n - k;
        while (remainCounselorNumber-- > 0) {
            int maxDecreasedTime = 0;
            int maxDecreasedType = 0;

            for (int type = 0 ; type < k; type++) {
                int currentCounselorsByType = counselorCountRow[type];

                int currentWaitTime = counselorByTotalCounselTantTimeTable[type][currentCounselorsByType];
                int nextWaitTime = counselorByTotalCounselTantTimeTable[type][currentCounselorsByType + 1];
                int reduceWaitingTime = Math.abs(currentWaitTime - nextWaitTime);

                if (reduceWaitingTime >= maxDecreasedTime) {
                    maxDecreasedTime = reduceWaitingTime;
                    maxDecreasedType = type;
                }
            }

            if (maxDecreasedTime == 0) break;

            counselorCountRow[maxDecreasedType]++;
        }

        int shortestWaitTime = 0;

        for (int type = 0; type < k; type++) {

            if (counselTypeList.get(type).size() == 0) continue;

            int counselors = counselorCountRow[type];
            shortestWaitTime += counselorByTotalCounselTantTimeTable[type][counselors];
        }

        return shortestWaitTime;
    }

    private int calculateWaitTime(List<Time> counselList, int consultantCount) {
        int waitTime = 0;
        PriorityQueue<Integer> consultingQueue = new PriorityQueue<>();

        for(Time consulting : counselList) {
            if(consultingQueue.size() < consultantCount) {
                consultingQueue.add(consulting.end);
                continue;
            }

            int shortestConsultingTime = consultingQueue.poll();

            if(consulting.start < shortestConsultingTime) {
                waitTime += (shortestConsultingTime - consulting.start);
                consultingQueue.add(shortestConsultingTime + consulting.duration);
                continue;
            }

            consultingQueue.add(consulting.end);
        }

        return waitTime;
    }

    private class Time {
        public final int start, duration, end;

        Time(int start, int duration) {
            this.start = start;
            this.duration = duration;
            this.end = duration + start;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(3, 5, new int[][] {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}}));
//        System.out.println(solution.solution(2, 3, new int[][] {{5, 55, 2}, {10, 90, 2}, {20, 40, 1}, {50, 45, 2}, {100, 50, 2}}));
    }

}
