static int[] borderSums(int[][] matrix) {
    int [] border = new int[(matrix.length)/2];
    int start = 0;
    int end = matrix.length-1;  
    int sum = 0;
    int k = 0;
    
    while(start<end)
    {
    for(int i=start; i<=end;i++)
        {
      
         for (int j=start;j<=end;j++)
        {
            
             
                if (i == start) 
                {
                    sum+=matrix[i][j]; 
                }
                else if (i == end) 
                {
                    sum+=matrix[i][j]; 
                    
                }
                else if (j == start) 
                {
                   sum+=matrix[i][j]; 
                     
                   
                    
                }
                else if (j == end) 
                {
                    sum+=matrix[i][j]; 
                  
                }
        
        }
       
    }
        border[k++] = sum;
        start = start +1 ;
        end = end -1;    
    }
    
  
    
    return border;
    
}
 
 
   
      


