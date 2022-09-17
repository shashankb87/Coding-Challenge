import java.util.*;

public class LinkedLists {

    public static void main(String[] args){
     /*    Node n1 = new Node(7);
        n1.next = new Node(1);
        n1.next.next = new Node(6);
        Node n2 = new Node(5);
        n2.next = new Node(9);
        n2.next.next = new Node(2);
        System.out.println(sumNumbers(n1, n2));*/
      /*   Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(0);
        System.out.println(isPalindromeRecurse(head,5).result); // 0 1 2 1 0*/
        Node list1 = new Node(3);
        list1.next = new Node(1);
        list1.next.next = new Node(5);
        list1.next.next.next = new Node(9);
        list1.next.next.next.next = new Node(7);
        list1.next.next.next.next.next = new Node(2);
        list1.next.next.next.next.next.next = new Node(1);
        Node list2 = new Node(4);
        list2.next = new Node(6);
        list2.next.next = list1.next.next.next.next;
        System.out.println(intersect(list1,list2));
    }

    //Find out if the linkedlist has a loop. If so, return the start of the loop
    public static Node startLoop(Node head){
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        if(fast != null && fast.next != null)
            return null;
            
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //If the two linked lists intersect, return the intersecting node
    public static Node intersect(Node list1, Node list2){
        Store s1 = getTailAndSize(list1);
        Store s2 = getTailAndSize(list2);
        if(s1.tail != s2.tail)
            return null;

        Node l = s1.size >= s2.size ? list1 : list2;
        Node s = s1.size >= s2.size ? list2 : list1;

        int diff = Math.abs(s1.size - s2.size);
        while(diff > 0){
            l = l.next;
            diff--;
        }
        while(l != s){
            l = l.next;
            s = s.next;
        }
        return l;
    }
    //Return the last element of the linkedlist along with the size
    private static Store getTailAndSize(Node head){
        Store s = new Store();
        while(head != null){
            s.size++;
            s.tail = head;
            head = head.next;
        }
        return s;
    }

    static class Store{
        public Node tail;
        public int size;

        public Store(){
            tail = null;
            size = 0;
        }
    }
    //Data structure to store the tail and size of the linked list
    static class Result{
        public Node node;
        public boolean result;

        public Result(Node node, boolean result){
            this.node = node;
            this.result = result;
        }
    }

    //Recursively calculate if a linked list is a palindrome
    public static Result isPalindromeRecurse(Node head, int length){
        if(head == null || length <= 0)
            return new Result(head,true);
        else if(length == 1)
            return new Result(head.next, true);
        
        Result res = isPalindromeRecurse(head.next, length - 2);

        if(!res.result || res.node == null){
            return res;
        }

        res.result = (head.value == res.node.value);
        res.node = res.node.next;

        return res;
    }

    //Iteratively calculate if a linked list is a palindrome
    public static boolean isPalindrome(Node head){
        Node slow = head, fast = head;
        Stack<Integer> s = new Stack<>();
        while(fast != null && fast.next != null){
            s.push(slow.value);
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast != null)
            slow = slow.next;
        while(slow != null){
            if(slow.value != s.pop())
                return false;
            slow = slow.next;
        }
        return true;
    }
    
    //Reverse Linked List - Iterative
    public static Node reverseList(ListNode head) {
        Node node = head,pre = null;
        while(node != null){
            Node next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
    
    //Reverse LinkedList - Recursive
    public ListNode reverseList(ListNode head) {
        if(head == null)return null;
        ListNode newHead = reverseList(head.next);
        if(newHead != null){
            head.next.next = head;
        }else{
            newHead = head;
        }
        head.next = null;
        return newHead;
    }

    //Sum two numbers represented by linked lists. 7215 is represented by 5->1->2->7
    public static int sumNumbers(Node n1, Node n2){
        Node ans = null,ansHead = null;
        int carry = 0;
        while(n1 != null || n2 != null || carry > 0){
            int i = 0, j = 0;
            if(n1!= null){
                i = n1.value;
                n1 = n1.next;
            }
            if(n2 != null){
                j = n2.value;
                n2 = n2.next;
            }
            int a = i + j + carry;
            if(ans == null){
                ans = new Node(a%10);
                ansHead = ans;
            }
            else{
                ans.next = new Node(a%10);
                ans = ans.next;
            }
            carry = a/10;
        }
        int a = 0;
        int mul = 1;
        while(ansHead != null){
            a += (ansHead.value*mul);
            ansHead = ansHead.next;
            mul*=10;
        }
        return a;
    }
    //Sum two numbers represented by linked lists. 7215 is represented by 7->2->1->5
    public static Node sumLists(Node n1, Node n2){
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>(), ans = new Stack<>();
        while(n1 != null){
            s1.add(n1.value);
            n1 = n1.next;
        }
        while(n2 != null){
            s2.add(n2.value);
            n2 = n2.next;
        }
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry > 0){
            int i1 = s1.isEmpty() ? 0 : s1.pop();
            int i2 = s2.isEmpty() ? 0 : s2.pop();
            int res = i1 + i2 + carry;
            ans.add(res%10);
            carry = res/10;
        }
        Node result = null;
        Node curr = null;
        while(!ans.isEmpty()){
            if(curr == null){
                result = new Node(ans.pop());
                curr = result;
            }else{
                curr.next = new Node(ans.pop());
                curr = curr.next;
            }
        }
        return result;
    }
    
    //get the length of the linkedlist
    public static int length(Node head){
        int l = 0;
        while(head != null){
            l++;
            head = head.next;
        }
        return l;
    }
    
    //Partition the linkedlist around a value x.
    public static Node partitionList(Node head,int x){
        Node beforeHead = null;
        Node before = null;
        Node afterHead = null;
        Node after = null;
        Node xNode = null;
        while(head != null){
            if(head.value < x){
                if(beforeHead == null){
                    beforeHead = head;
                    before = head;
                }else{
                    before.next = head;
                    before = before.next;
                }
            }else if(head.value > x){
                if(afterHead == null){
                    afterHead = head;
                    after = head;
                }else{
                    after.next = head;
                    after = after.next;
                }
            }else
                xNode = head;

            head = head.next;
        }
        Node newHead = beforeHead;
        if(newHead != null){
            before.next = xNode;
        }else{
            newHead = xNode;
        }
        if(newHead != null){
            xNode.next = afterHead;
        }else{
            newHead = afterHead;
        }
        return newHead;
    }


    //Return the kth element from the end
    public static int kthElem(Node head,int k){
        Node p1 = head, p2=head, prev = null;
        for(int i = 0;i<k;++i){
            if(p1 == null)return -1;
            p1=p1.next;
        }
        while(p1 != null){
            p1 = p1.next;
            prev = p2;
            p2 = p2.next;
        }
        prev.next = p2.next;
        return p2.value;
    }

    //Remove duplicates from a linked list.
    public static void removeDups(Node head){
        Set<Integer> s = new HashSet<>();
        Node p = null;
        while(head != null){
            if(s.contains(head.value)){
                p.next = head.next;
            }else{
                s.add(head.value);
                p = head;
            }
            head = head.next;
        }
    }
    //Merge K sorted linked lists and return the head
    public static Node mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length == 0)return null;
        PriorityQueue<Node> q = new PriorityQueue<>(lists.length,new Comparator<Node>(){
            public int compare(Node n1, Node n2){
                return n1.val - n2.val;
            }
        });
        for(Node node : lists)
            if(node != null)q.add(node);
        
        Node head = null;
        Node curr = null;
        while(!q.isEmpty()){
            Node node = q.poll();
            if(curr == null){
                curr = node;
                head = node;
            }else{
                curr.next = node;
                curr = curr.next;
            }
            
            if(curr.next != null){
                q.add(curr.next);
            }
        }
        return head;
    }
    
    /* https://leetcode.com/problems/reorder-list/
    You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.
    */
    public static void reorderList(Node head) {
        Node prev = null;
        Node curr = head;
        Node fast = head;
        //Find the middle of the list
        while(fast != null && fast.next != null){
            prev = curr;
            curr = curr.next;
            fast = fast.next.next;
        }
        if(fast != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        //Reverse the second half of the list
        Node p = null;
        while(curr != null){
            Node next = curr.next;
            curr.next = p;
            p = curr;
            curr = next; 
        } 
        //Merge the two halves
        curr = p;
        p = head;
        while(p != null && curr != null){
            Node next = p.next;
            p.next = curr;
            curr = curr.next;
            p.next.next = next;
            p = next;
        }
    }

    //Data structure to represent a linked list node.
    static class Node{
        int value;
        Node next;
        public Node(int value){
            this.value = value;
        }
        public String toString(){
            return Integer.toString(value);
        }
    }
}
