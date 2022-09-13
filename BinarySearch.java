import java.util.*;

public class BinarySearch{
  //Find the target in a rotated sorted array
    public int search(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while(r>=l){
            int mid = l + (r-l)/2;
            if(target == nums[mid])
                return mid;
            else if(nums[l] <= nums[mid]){
                if(target < nums[mid] && target >= nums[l])
                    r = mid-1;
                else
                    l = mid+1;
            }else{
                if(target > nums[mid] && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return -1;
    }
  /* https://leetcode.com/problems/search-a-2d-matrix/
     Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
  */
    public boolean searchMatrix(int[][] matrix, int target) {
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
}
