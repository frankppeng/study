package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.util.SystemUtil;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 示例 1:

 输入: [1,2,3,1]
 输出: 4
 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
      偷窃到的最高金额 = 1 + 3 = 4 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/house-robber
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 *
 * Created by wangzhx on 2020/5/29.
 */
public enum Q198 {
    instance;

    /**
     * memo[i] 表示考虑抢劫 nums[i...n] 所能获得的最大收益
     */
    private int[] memo;

    /**
     * 方式一：记忆化搜索
     * ① 状态：考虑抢劫 nums[index...num.length） 这个范围内的所有房子
     * ② 状态转移：tryRob(n) = Max{rob(0) + tryRob(2), rob(1) + tryRob(3)... rob(n-3) + tryRob(n-1), rob(n-2), rob(n-1)}
     */
    public int rob1(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        // 记忆化搜索可以避免重叠子问题的重复运算
        if (memo[index] != -1) {
            return memo[index];
        }
        // 下面是对状态转移方程的描述
        int res = 0;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }

    /**
     * 方式二：动态规划
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // memo[i] 表示考虑抢劫 nums[i...n-1] 所能获得最大收益（不是说一定从 i 开始抢劫）
        int[] memo = new int[n];
        // 先考虑最简单的情况
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // memo[i] 的取值在考虑抢劫 i 号房子和不考虑抢劫之间取最大值
            memo[i] = Math.max(nums[i] + (i + 2 >= n ? 0 : memo[i + 2]), nums[i + 1] + (i + 3 >= n ? 0 : memo[i + 3]));
        }
        return memo[0];
    }

    /**
     * 动态规划简化版
     */
    public int rob4(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n == 0 ? 0 : nums[0];
        int[] memo = new int[n];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++)
            memo[i] = Math.max(memo[i - 1], nums[i] + memo[i - 2]);
        return memo[n - 1];
    }

    public static void main(String[] args) {
        // assert 4
        SystemUtil.print(Q198.instance.rob(new int[]{1,2,3,1}));
        // assert 12
        SystemUtil.print(Q198.instance.rob(new int[]{2,7,9,3,1}));
        // assert 4
        SystemUtil.print(Q198.instance.rob(new int[]{2,1,1,2}));

        // dp[n] = MAX( dp[n-1], dp[n-2] + num )
        // 第n间房子，其最大数额是dp(n-1)或是dp[n-2]+num,num是第n间房子的金额
    }

    public int rob(int[] nums) {
        if(nums==null||nums.length==0)return 0;
        if(nums.length==1)return nums[0];

        int[] dp=new int[nums.length];
        dp[0]=nums[0];
      //dp[1]=MAX(dp[0],dp[n-2]+num)
        dp[1]=MAX(dp[0],0+nums[1]);
        int n=2;
        for(;n<nums.length;n++){
            int num=nums[n];
          //dp[n]=MAX(dp[n-1],dp[n-2]+num)
            dp[n]=MAX(dp[n-1],dp[n-2]+num);
        }

        return dp[n-1];
    }

    private int MAX(int a,int b){
        return a>b?a:b;
    }
}
