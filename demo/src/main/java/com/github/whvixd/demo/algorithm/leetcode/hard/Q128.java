package com.github.whvixd.demo.algorithm.leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangzhx on 2020/6/6.
 */
public enum Q128 {
    instance;

    public int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0)return 0;

        int maxNum=0;
        int negativeMaxNum=0;
        for(int num:nums){
            if(num>=0){
                maxNum=num>maxNum?num:maxNum;
            }else {
                num=-num;
                negativeMaxNum=num>negativeMaxNum?num:negativeMaxNum;
            }
        }
        int[] list=new int[maxNum+1];
        int[] negativeList=new int[negativeMaxNum+1];
        for (int num : nums) {
            if(num>=0){
                list[num] = 1;
            }else {
                negativeList[-num]=-1;
            }
        }

        return getMax(list,negativeList);
    }

    private int getMax(int[] list,int[] negativeList){
        int count=1;

        int max=1;
        int negativeMax=1;
        boolean zero=true;

        boolean negativeZero=true;

        int countByZero=list.length>0&&list[0]!=0?1:0;

        int negativeCountByZero=0;


        for(int i=0;i<list.length-1;i++){
            if(list[i]!=0&&list[i+1]!=0){
                count++;
                if(zero){
                    countByZero=count;
                }
            }else {
                zero=false;
                count=1;
            }
            max=max<count?count:max;
        }

        count=1;
        for(int i=0;i<negativeList.length-1;i++){
            negativeCountByZero=1;
            if(negativeList[i]!=0&&negativeList[i+1]!=0){
                count++;
                if(negativeZero){
                    negativeCountByZero=count;
                }
            }else if(i>0){
                negativeZero=false;
                count=1;
            }
            negativeMax=negativeMax<count?count:negativeMax;
        }
        max=countByZero+negativeCountByZero>max?countByZero+negativeCountByZero:max;
        max=max>negativeMax?max:negativeMax;
        return max;
    }

    public int longestConsecutive1(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }


    public static void main(String[] args) {
        // assert 4
        System.out.println(Q128.instance.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        // assert 3
        System.out.println(Q128.instance.longestConsecutive(new int[]{-1,0,1}));
        // assert 3
        System.out.println(Q128.instance.longestConsecutive(new int[]{99,100,101}));

        // assert 5
        System.out.println(Q128.instance.longestConsecutive(new int[]{-1,0,1,4,5,6,7,8}));


        // assert 6
        System.out.println(Q128.instance.longestConsecutive(new int[]{-1,0,1,4,5,6,7,8,-3,-4,-5,-6,-7,-8}));
        // assert 10
        System.out.println(Q128.instance.longestConsecutive(new int[]{-1,0,1,4,5,6,7,8,-3,-4,-5,-6,-7,-8,-2}));

        // assert 3
        System.out.println(Q128.instance.longestConsecutive(new int[]{1,2,3,5,-1}));

        // assert 2
        System.out.println(Q128.instance.longestConsecutive(new int[]{0,-1}));
        System.out.println(Q128.instance.longestConsecutive1(new int[]{0,-1}));
    }
}
