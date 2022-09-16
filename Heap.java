import java.util.*;

//Min heap / priority queue implementation.
public class Heap {
    private List<Integer> store;

    public Heap(){
        store = new ArrayList<>();
    }

    public static void main(String[] args){
        int[] x = new int[]{5,1,3,4,9,0};
        Heap h = new Heap(x);
        while(!h.isEmpty())
            System.out.println(h.poll());
    }

    //heapify operation. Construct a heap from an unsorted array O(N)
    public Heap(int[] arr){
        store = new ArrayList<>();
        for(int i : arr)
            store.add(i);
        for(int i = store.size()-1;i>=0;--i){
            bubbleDown(i);
        }
    }

    //Add an element to the heap. O(N)
    public void add(int i){
        store.add(i);
        bubbleUp(store.get(store.size()-1));
    }
    //Remove and return the minimum element (head of the list). O(N)
    public int poll(){
        if(store.isEmpty())
            throw new RuntimeException("No elements found");
        Collections.swap(store,0,store.size()-1);
        int min = store.remove(store.size()-1);
        bubbleDown(0);
        return min;
    }

    public boolean isEmpty(){
        return store.isEmpty();
    }
    //If an element is smaller than its parent, bubble it up
    private void bubbleUp(int indx){
        int parent = (indx-1)/2;
        if(parent >= 0  && store.get(parent) > store.get(indx)){
            Collections.swap(store, indx, parent);
            bubbleUp(parent);
        }
    }
    //If an element is larger than its parent, bubble it down
    private void bubbleDown(int indx){
        int child1 = 2*indx + 1;
        int child2 = 2*indx + 2;
        int min = -1;
        if(child1 >= store.size() && child2 >= store.size())
            return;
        if(child1 >= store.size())
            min = child2;
        else if(child2 >= store.size())
            min = child1;
        else
            min = store.get(child1) <= store.get(child2) ? child1 : child2;
        if(store.get(indx) <= store.get(min))
            return;
        Collections.swap(store, min, indx);
        bubbleDown(min);
    }
}
