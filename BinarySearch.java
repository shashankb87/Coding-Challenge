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
}
