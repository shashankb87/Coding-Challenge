public class Greedy {
    /* https://leetcode.com/problems/maximum-subarray/
    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.
*/
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int curr = nums[0];
        for(int i = 1;i<nums.length;++i){
            curr = Math.max(nums[i],nums[i]+curr);
            max = Math.max(curr,max);
        }
        return max;
    }
    
    /* https://leetcode.com/problems/jump-game/submissions/
        You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.
    */
    public boolean canJump(int[] nums) {
        int curr = 0;
        int max = curr + nums[curr];
        while(curr <= max){
            if(max >= nums.length-1)return true;
            max = Math.max(max,curr + nums[curr]);
            curr++;
        }
        return false;
    }
}
