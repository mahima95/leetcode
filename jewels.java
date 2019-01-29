import java.util.*;
class Solution {
    public int numJewelsInStones(String J, String S) {
        int ans = 0;
    for(int i=0;i<S.length();i++)       
    {
        int x = J.indexOf(S.charAt(i));       //Check if S's charcter is present in J
        
        if(x!=-1)
            ans++;
            
    }        
    return ans;
   
}
            
    }
