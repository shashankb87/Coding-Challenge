import java.util.*;

public class BinarySearch{
  //Find the target in a rotated sorted array
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l<=r){
            int m = l + (r-l)/2;
            if(nums[m] == target)
                return m;
            if(nums[l] <= nums[m]){
                if(target < nums[m] && target >= nums[l]){
                    r = m - 1;
                }else{
                    l = m + 1;
                }
            }else{
                if(target > nums[m] && target <= nums[r]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
        }
        return -1;
    }
  /* https://leetcode.com/problems/search-a-2d-matrix/
     Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
  */
    public static boolean searchMatrix(int[][] matrix, int target) {
          int m = matrix.length;
          int n = matrix[0].length;
          int l = 0, r = (m * n) - 1;
          while(l<=r){
              int midIndx = l + (r-l)/2;
              int midElem = matrix[midIndx/n][midIndx%n];
              if(target == midElem){
                  return true;
              }
              if(target < midElem){
                  r = midIndx - 1;
              }else{
                  l = midIndx + 1;
              }
          }
          return false;
      }
  /* https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
  Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.

Given the sorted rotated array nums of unique elements, return the minimum element of this array.
  */
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l<=r){
            int m = l + (r-l)/2;
            if(nums[m] >= nums[l]){
                if(nums[r] >= nums[m]){
                    return nums[l];
                }else{
                    l = m + 1;
                }
            }else{
                r = m;
            }
        }
        return -1;
    }
}
