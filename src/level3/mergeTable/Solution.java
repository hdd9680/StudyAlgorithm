package level3.mergeTable;

import java.util.*;

/**
 * 작성일 : 2023/09/17
 * 문제 설명 : 문제 설명이 너무 긴 관계로 하기 사이트 참조
 * https://school.programmers.co.kr/learn/courses/30/lessons/150366
 *
 * 답지 확인해버림
 * 이번 문제는 뭔가 속도를 해결하는 무언가의 방법을 찾는문제가 아니였다. 진짜 개발하다 이건 너무 무식한 방법 같다는 생각에 잠겨
 * 갈아엎은게 7번 정도 되는거같다.(아마 무식하게 해결하려했으면 소스는 좀 지저분하고 로직이 복잡해도 해결은 했을거같다)
 * 문제 해결에서 가장 중요한 점은 UNION_FIND 알고리즘의 사용과 표를 1차원 배열에 저장하는게 로직을 깔끔하게 바꾸는 핵심같다.
 */
public class Solution {

    public int[] parent = new int[2501];
    public String[] value = new String[2501];

    public int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    public int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }

    public String[] solution(String[] commands) {
        //초기화
        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
            value[i] = "";
        }

        List<String> answer = new ArrayList<>();
        for (int ind = 0; ind < commands.length; ind++) {
            String line = commands[ind];
            StringTokenizer st = new StringTokenizer(line);
            String command = st.nextToken();

            if ("UPDATE".equals(command)) {
                if (st.countTokens() == 2) {
                    String before = st.nextToken();
                    String after = st.nextToken();
                    for (int i = 1; i <= 2500; i++) {
                        if (before.equals(value[i]))
                            value[i] = after;
                    }
                }
                else {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    String after = st.nextToken();
                    int num = convertNum(x, y);
                    value[find(num)] = after;
                }
            } else if ("MERGE".equals(command)) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int n1 = convertNum(x1, y1);
                int n2 = convertNum(x2, y2);
                int root1 = find(n1);
                int root2 = find(n2);

                if (root1 == root2) {
                    continue;
                }

                String rootString = value[root1].isBlank() ? value[root2] : value[root1];
                value[root1] = "";
                value[root2] = "";
                union(root1, root2);
                value[root1] = rootString;
            } else if ("UNMERGE".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                String rootString = value[root];
                value[root] = "";
                value[num] = rootString;
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (find(i) == root)
                        deleteList.add(i);
                }
                for (Integer t : deleteList)
                    parent[t] = t;
            } else if ("PRINT".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                if (value[root].isBlank())
                    answer.add("EMPTY");
                else
                    answer.add(value[root]);
            }
        }

        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        Arrays.stream(sol.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean"
//                , "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant"
//                , "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik"
//                , "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"})).forEach(System.out::println);

        Arrays.stream(sol.solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2"
                , "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"})).forEach(System.out::println);
    }

}
