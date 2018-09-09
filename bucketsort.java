package main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.awt.List;
import java.math.*;
 
public class bucketsort 
{	
	static final int BucketSize = 10; //The range is important to be known; if not come up with an algo here to come with perfect bucket size
   
	static int[] sort(int[] sequence) 
    {	
        // Bucket Sort
		
		 
        LinkedList<Integer>[] Bucket = new LinkedList[BucketSize];
        int[] sorted_sequence = new int[sequence.length];
        
        // Adding elements to Bucket
        for (int i = 0; i < sequence.length; i++)
        {
        	int data = (int) Math.floor(sequence[i]/BucketSize);  //To check all numbers within range of 100 within BucketSize
        	
        	if(Bucket[data]==null)
            Bucket[data]= new LinkedList<Integer>();
            Bucket[data].add(sequence[i]);
        }
        
        int k= 0;
        
        
        //Sorting and Type Conversion to Array
        
        for (int i = 0; i < BucketSize; i++)
        {
        	if(Bucket[i]!=null)
        	{
        	Collections.sort(Bucket[i]);
        	Object [] sortedObjects = Bucket[i].toArray();
        	for(Object j : sortedObjects)
        	{
        		sorted_sequence[k++]=(int)j;  //assert required here
        	}
        	}
        }
    
        return sorted_sequence;
    }
	
 
    static void printSequence(int[] sorted_sequence) 
    {
        for (int i = 0; i < sorted_sequence.length; i++)
            System.out.print(sorted_sequence[i] + " ");
    }
 
 
    public static void main(String args[]) 
    {
        System.out
                .println("Sorting of randomly generated numbers using BUCKET SORT");
        Random random = new Random();
        int N = 20;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(100));
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
       printSequence(sort(sequence));
    }
}