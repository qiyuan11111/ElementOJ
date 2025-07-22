import java.util.*;

class Solution {
    static int maxn = 100005;

    int k;
    Integer[] num = new Integer[maxn];
    public long solve(){
        Arrays.sort(num, Comparator.comparingInt(Math::abs));
        System.out.println(num);
        return 0l;
    }
    public long kSum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++){
            num[i+1] = nums[i];
        }
        this.k = k;

        return solve();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = new int[]{2,4,-2};
        System.out.println(solution.kSum(num, 5));
    }
}