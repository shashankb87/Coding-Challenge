import java.util.*;

public class ArraysStrings {
    public static void main(String[] args){
        //System.out.println(isUniqueChars("abcd"));
        //System.out.println(urlify(new char[]{'M','r',' ','J','o','h','n',' ','S','m','i','t','h',' ',' ',' ',' ',},13));
        //System.out.println(isPalindrome("abaab"));
        //System.out.println(oneAway("pale","ple"));
        //System.out.println(compress("aabcccccaaa"));
        //int[][] matrix = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        //rotateMatrix(matrix);
        System.out.println(isRotation("waterbottle","rbottlewate"));
    }
    
    /*https://leetcode.com/problems/product-of-array-except-self/
    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.
    */
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for(int i = 1;i<nums.length;++i){
            ans[i] = ans[i-1]*nums[i-1];
        }
        int p = 1;
        for(int i = nums.length-2;i>=0;--i){
            p *= nums[i+1];
            ans[i] *= p;
        }
        return ans;
    }

    //Return is s1 is a rotation of s2. ex. waterbottle, rbottlewate
    public static boolean isRotation(String s1, String s2){
        return (s1+s1).indexOf(s2) != -1;
    }
    //Rotate a square n X n matrix by 90 degrees
    public static void rotateMatrix(int[][] matrix){
        int len = matrix.length;
        for(int i = 0;i<len/2;++i){
            for(int j = i;j<len - i - 1;++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len-j-1][i];
                matrix[len-j-1][i] = matrix[len-i-1][len-j-1];
                matrix[len-i-1][len-j-1] = matrix[j][len - i -1];
                matrix[j][len - i -1] = temp;
            }
        }
    }

    //check if a string has all unique characters, using O(1) memory (bit array)
    public static boolean isUniqueChars(String s){
        int check = 0;
        for(char c : s.toCharArray()){
            if((check & (1 << (c-'a'))) > 0)
                return false;
            check |= (1 << (c-'a'));
        }
        return true;
    }

    //Convert a string into a url by replacing space with %20. Input char array has space in the end to accomodate the extra characters
    public static char[] urlify(char[] in, int len){
        int i = len-1,j = in.length-1;
        while(i >= 0){
            if(in[i] == ' '){
                in[j--] = '0';
                in[j--] = '2';
                in[j--] = '%';
            }else{
                in[j--] = in[i];
            }
            i--;
        }
        return in;
    }

    //Check if string is a palindrome / can be converted to a palindrome
    public static boolean isPalindrome(String s){
        Map<Character,Integer> m = new HashMap<>();
        int oddCount = 0;
        for(char c : s.toCharArray()){
            int count = m.getOrDefault(c, 0)+1;
            m.put(c,count);
        }
        for(int i : m.values()){
            if(i%2 > 0)oddCount++;
        }
        return s.length()%2 == oddCount;
    }


    //check if s1 can be converted to s2 by performing 1 of 3 operations - add a new character, delete a character, replace a character
    public static boolean oneAway(String s1, String s2){
        if(s1 == null || s2 == null)return false;
        if(Math.abs(s1.length() - s2.length()) > 1) return false;
        String l = s1.length() > s2.length() ? s1 : s2;
        String s = s1.length() > s2.length() ? s2 : s1;
        int i = 0,j = 0;
        while(i<l.length() && j<s.length()){
            if(l.charAt(i) != s.charAt(j)){
                if(i != j)
                    return false;
                if(l.length() == s.length()){
                    j++;
                }
                i++;
                continue;
            }
            i++;
            j++;
        }
        return true;
    }

    //Compress - encode a string. ex. aabcccccaaa -> a2b1c5a3. If the encoded string has a greater length than the original, return the original
    public static String compress(String s){
        if(s == null || s.length() == 0)return s;
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for(int i = 0;i<s.length();++i){
            count++;
            if((i+1 == s.length()) || (s.charAt(i) != s.charAt(i+1))){
                builder.append(s.charAt(i));
                builder.append(count);
                count = 0;
            }
        }
        return builder.length() >= s.length() ? s : builder.toString();
    }
}
