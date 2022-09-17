import java.util.*;

public class LRUCache {
  /*
    Implement the Least Recently Used Cache class:
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. 
    If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.
  */
    
    public static void main(String[] args){
        LRUCache c = new LRUCache(2);
        c.put(1,1);
        c.put(2, 2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));
        c.put(4,4);
        System.out.println(c.get(1));
        System.out.println(c.get(3));
        System.out.println(c.get(4));
    }

    Map<Integer,Node> cache;
    Node head;
    Node tail;
    int size;
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        head = null;
        tail = null;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            Node n = cache.get(key);
            remove(n);
            add(n);
            return n.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            remove(cache.get(key));
        }
        add(new Node(key,value));
        if(size > capacity){
            remove(tail);
        }
    }

    private void add(Node n){
        n.next = head;
        n.prev = null;
        if(head != null)
            head.prev = n;
        head = n;
        if(tail == null)
            tail = n;
        size++;
        cache.put(n.key,n);
    }

    private void remove(Node n){
        if(n.prev != null)
            n.prev.next = n.next;
        if(n.next != null)
            n.next.prev = n.prev;
        if(n == head)
            head = head.next;
        if(n == tail)
            tail = tail.prev;
        cache.remove(n.key);
        size--;
    }
    
    class Node{
        public int value;
        public int key;
        public Node next;
        public Node prev;
        
        public Node(int key, int value){this.key = key;this.value = value;}
    }
}
