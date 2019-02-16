import javafx.util.Pair;

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int sum  = 0;
       List<Pair<Integer,Integer>> jobs = new ArrayList<>();
        for(int i = 0 ;i<profit.length;i++)
            jobs.add(new Pair<Integer,Integer>(difficulty[i], profit[i]));
        Collections.sort(jobs,Comparator.comparing(Pair::getKey));
        Arrays.sort(worker);
        int previous = 0;
        int j = 0;
        
        for(int i : worker)
        {   
            while( j<profit.length && i>=jobs.get(j).getKey())
            {
                 
               previous = Math.max(jobs.get(j++).getValue(), previous);
               
            }
            
            sum+=previous;
             
                }
                 return sum;
               
            }
            }
        
    
