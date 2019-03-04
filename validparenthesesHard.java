class Solution {

        public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    
        }
}

//Check out the explanation on :https://leetcode.com/problems/longest-valid-parentheses/solution/
//Also here we need extra information-we cannot play with stacks , we need to know where brackets open and close and hence the position does the trick instead of dp
//e.g. in "()(()" does not work without i
