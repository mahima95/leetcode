class Solution {
    public boolean isValid(String s) {
    /*Things to consider: 
    - [{}] nested brackets
    - Single and odd number of characters like "["
    - ([)] Approacch 3 wont work now because just counting is not important but placement too
    
     */   
         char c[] = s.toCharArray();
        Stack <Character>stack = new Stack<>();

        if(c.length%2 != 0)
            return false;

         for (int i = 0; i < c.length; i++) {

            switch (c[i]) {

                case '(':
                        stack.push('(');
                    break;

                case '[':
                        stack.push('[');
                    break;

                case '{':
                        stack.push('{');
                    break;
  case ')':

                    if(stack.empty() || stack.pop()!='(')
                        return false;
                    break;

                case ']':
                   if(stack.empty() || stack.pop()!='[')
                        return false;
                    break;

                case '}':
                    if(stack.empty() || stack.pop()!='{')
                        return false;
                    break;

                default:
                    break;

            }

        }
           if(stack.empty()==false)
             return false;
        return true;
    }
}
