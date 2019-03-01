class Solution {
    public boolean isAnagram(String s, String t) {
    char[] s1 = s.toCharArray();
		 char[] s2 = t.toCharArray();
		 Arrays.sort(s2);
		 Arrays.sort(s1);
		 
		 
		return String.valueOf(s2).contentEquals(String.valueOf(s1));    
    }
}
