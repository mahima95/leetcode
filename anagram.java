import java.util.*;

class Solution {
    public boolean isAnagram(String s, String t) {

        try{
            ArrayList<Character> s1 = new ArrayList();
            ArrayList<Character> t2 = new ArrayList();
            int j = -1;

            for (char c: s.toCharArray())
            {
                s1.add(c);
            }

            for (char c:t.toCharArray())
            {
                t2.add(c);
            }

            if(s1.size()!=t2.size())
                return false;

            for(int i = 0; i< s1.size(); i++ )
            {
                while(t2.size()!=0)
                {
                    j++;
                    if(s1.get(i)==t2.get(j) && j<t2.size())
                    {
                        t2.remove(j);
                        j=-1;
                        break;
                    }
                    else
                    {
                        if(j==t2.size()-1)
                        return false;
                    }

                }
            }




        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }



    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args)  {

            String s = "anagram";

            String t = "nagaram";

            boolean ret = new Solution().isAnagram(s, t);

            String out = booleanToString(ret);

            System.out.print(out);

    }
}
