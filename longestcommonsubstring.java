public static int getLongestCommonSubstring(String a, String b){
	int m = a.length();
	int n = b.length();
 
	int max = 0;
 
	int[][] dp = new int[m][n];
 
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(a.charAt(i) == b.charAt(j)){ //check if same
				if(i==0 || j==0){
					dp[i][j]=1;
				}else{
					dp[i][j] = dp[i-1][j-1]+1; //Checking the number of one previous block
				}
 
				if(max < dp[i][j])
					max = dp[i][j];
			}
 
		}
	}
 
	return max;
}


//https://www.programcreek.com/2015/04/longest-common-substring-java/
