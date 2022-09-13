import java.util.*;

public class StacksQueues {

    //Sort a stack, using another stack.
    public static void sort(Stack<Integer> st){
        Stack<Integer> temp = new Stack<>();
        while(!st.isEmpty()){
            int v = st.pop();
            while(!temp.isEmpty() && temp.peek() > v)
                st.push(temp.pop());
            temp.push(v);
        }
        while(!temp.isEmpty())
            st.push(temp.pop());
    }

    //Implement a queue using two stacks
    static class MyQueue{
        Stack<Integer> s1, s2;

        public void push(int i){
            s2.push(i);
        }

        public int poll(){
            if(s1.isEmpty()){
                while(!s2.isEmpty())
                    s1.push(s2.pop());
            }
            if(s1.isEmpty())return Integer.MIN_VALUE;
            return s1.pop();
        }

        public int peek(){
            if(s1.isEmpty()){
                while(!s2.isEmpty())
                    s1.push(s2.pop());
            }
            if(s1.isEmpty())return Integer.MIN_VALUE;
            return s1.peek();
        }
    }

    //Array of stacks with fixed capacity
    static class StackPlates{
        List<Stack<Integer>> stack;
        int capacity;

        public StackPlates(int capacity){
            stack = new ArrayList<>();
            this.capacity = capacity;
        }

        public void push(int i){
            if(stack.isEmpty() || stack.get(stack.size()-1).size() == capacity){
                stack.add(new Stack<>());
            }
            stack.get(stack.size()-1).push(i);
        }

        public int pop(){
            if(stack.isEmpty())return Integer.MIN_VALUE;
            Stack<Integer> st = stack.get(stack.size()-1);
            int i = st.pop();
            if(st.isEmpty())
                stack.remove(stack.size()-1);
            return i;
        }

        public int popAt(int indx){
            if(stack.size() <= indx)return Integer.MIN_VALUE;
            Stack<Integer> st = stack.get(indx);
            int i = st.pop();
            for(int x = indx+1;x<stack.size();++x){
                stack.get(indx).push(stack.get(indx+1).pop());
            }
            if(stack.get(stack.size()-1).isEmpty())
                stack.remove(stack.size()-1);
            return i;
        }
    }

    //Implement a stack that keeps track of the minimum element along with all the existing functionality of the stack
    static class StackMin{
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int i){
            if(minStack.isEmpty() || i <= minStack.peek())
                minStack.push(i);
            stack.push(i);
        }

        public int pop(){
            if(stack.isEmpty())
                return Integer.MIN_VALUE;
            if(stack.peek() == minStack.peek())
                minStack.pop();
            return stack.pop();
        }

        public int min(){
            return minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peek();
        }
    }
}
