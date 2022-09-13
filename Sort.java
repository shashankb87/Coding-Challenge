import java.util.*;

public class Sort {

    public static void main(String[] args){
        int[] array = new int[]{9,3,6,8,1,0,15};
        quickSort(array);
        for(int i : array)
            System.out.println(i);
    }

    //Recursive mergesort. O(nlog(n))
    public static int[] mergeSort(int[] array){
        if(array.length <= 1)
            return array;
        int[] f = new int[array.length/2];
        int[] s = new int[array.length/2];
        for(int i = 0;i<array.length;++i){
            f[i] = array[i];
            s[i] = array[array.length/2 + i];
        }

        f = mergeSort(f);
        s = mergeSort(s);

        int j = 0, k = 0;
        for(int i = 0;i<array.length;++i){
            if(f[j]<= s[k]){
                array[i] = f[j++];
            }else{
                array[i] = s[k++];
            }
        }

        return array;
    }

    private static Random _rand = new Random();

    //Recursive quick sort. O(nlog(n)) average time complexity. O(n) worst case.
    public static void quickSort(int[] array){
        qs(array,0,array.length);
    }
    
    private static void qs(int[] array, int start, int end){
        if(start >= end)
            return;
        int pivot = _rand.nextInt(start, end);
        swap(array,start,pivot);
        int j = start+1;
        for(int i = start+1;i<end;++i){
            if(array[i] <= array[start])
                swap(array,i,j++);
        }
        swap(array,j-1,start);
        qs(array,start,j-1);
        qs(array,j,end);
    }

    private static void swap(int[] array, int i1, int i2){
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
