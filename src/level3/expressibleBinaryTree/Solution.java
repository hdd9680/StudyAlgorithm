package level3.expressibleBinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 작성일 : 2023/09/14
 * 문제 설명 : 당신은 이진트리를 수로 표현하는 것을 좋아합니다, 이진트리를 수로 표현하는 방법은 다음과 같습니다.
 *  - 이진수를 저장할 빈 문자열을 생성합니다.
 *  - 주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
 *  - 만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
 *  - 살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면, 문자열 뒤에 1을 추가합니다.
 *  - 문자열에 저장된 이진수를 십진수로 변환합니다.
 * 이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며,
 * 자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.
 * 다음은 이진트리를 수로 표현하는 예시입니다.
 * 주어진 이진트리는 다음과 같습니다.
 * https://school.programmers.co.kr/learn/courses/30/lessons/150367
 *
 * 답지 확인해버림
 * 일단 이번 문제에서 문제의 요점은 포화 이진트리의 구조를 이해하는게 가장 중요해 보임
 * 포화이진트리의 구조상 모든 리프의 레빌이 같다는 점과 이러한 이유로 포화 노드트리의 노드 개수는 레벨의 따라 결정된다는 점을 이용하여
 * 가장 적은 노드를 추가하여 포화 이진트리가 가능한 만큼 사전에 좌측으로 새로 노드를 만드는게 중요해보인다
 * 그 이후에는 루트 노드를 검색하여 루트 노드가 새로 생긴 노드라면 하위노드도 모두 새로 생긴 노드여야하며
 * 새로 새긴 노드가 아니라면 위에 과정을 다시 반복하는것으로 포화이진트리가 가능한지 확인할 수 있다.
 */

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0 ; i < answer.length ; i++) {
            if (isBinaryTree(numbers[i])) {
                answer[i] = 1;
            }
        }

        return answer;
    }

    private boolean isBinaryTree(long number) {
        String binary = Long.toBinaryString(number);
        String fullBinary = getFullBinary(binary);
        int len = fullBinary.length();

        int root = len / 2;
        String leftSubTree = fullBinary.substring(0, root);
        String rightSubTree = fullBinary.substring(root + 1);

        if (fullBinary.charAt(root) == '0') {
            return false;
        }

        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }

    private String getFullBinary(String binary) {

        int length = binary.length();
        int nodeCount = 1;
        int level = 1;
        while (length > nodeCount) {
            level *= 2;
            nodeCount += level;
        }

        int offset = nodeCount - length;
        return "0".repeat(offset) + binary;
    }

    private boolean isBinaryTree(String binary) {
        int len = binary.length();
        if (binary.length() == 0) return true;

        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '0') {
            return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
        }

        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }

    private boolean isZeroTree(String binary) {
        int len = binary.length();
        if (binary.length() == 0) return true;

        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '1') {
            return false;
        }

        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        Arrays.stream(sol.solution(new long[]{7L, 42L, 5L})).forEach(System.out::print);
        Arrays.stream(sol.solution(new long[] {63L, 111L, 95L})).forEach(System.out::print);
    }
}
