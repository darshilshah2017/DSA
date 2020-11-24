package sorting;

import java.util.Scanner;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeSort {
	
	private static Logger log = LoggerFactory.getLogger(MergeSort.class);
	
	private void merge(int a[], int l, int m, int r) {
		log.info("l:{}, m:{}, r:{}",l,m,r);
		
		int n1 = m-l+1;
		int n2 = r-m;
		
		//create 2 temp array.
		int[] m1 = new int[n1];
		int[] m2 = new int[n2];
		
		//Copy the a[]'s data into newly created temp arrays
		for(int i=0;i<n1;i++) 
			m1[i] = a[l+i];
		for(int j=0;j<n2;j++) 
			m2[j] = a[m+1+j];
		
		int i=0;
		int j=0;
		int k=l;
		
		while(i<n1 && j<n2) {
			if(m1[i]<m2[j])
				a[k++] = m1[i++];
			else
				a[k++] = m2[j++];
		}
		
		//copy all remaining elements from temp array m1[] into a[]
		while(i<n1)
			a[k++]=m1[i++];
		
		//copy all remaining elements from temp array m2[] into a[]
		while(j<n2)
			a[k++]=m2[j++];
		
	}
	
	
	private void mergesort(int[] a, int l, int r) {
		
		if(l<r) {
			//Find middle element
			int m = (l+r)/2;
			
			//Recursively call mergeSort() for left & right side
			mergesort(a, l, m);
			mergesort(a, m+1, r);
			
			//Merge the left & right side
			merge(a, l, m, r);
		}
	}

	public static void main(String[] args) {

		try(Scanner sc = new Scanner(System.in)){
			log.info("Enter number of elements:");
			int n = sc.nextInt();
			int[] a = new int[n];
			
			log.info("Enter all elements inside list");
			int i=0;
			while(i<n) {
				a[i++] = sc.nextInt();
			}
			
			MergeSort m = new MergeSort();
			m.mergesort(a, 0, a.length-1);
			
			log.info("Sorted array:\n");
			i=0;
			while(i<a.length)
				log.info("{}",a[i++]);
			
		}
		catch(Exception e) {log.error("Error msg: {}, stackTrace: {}", e.getLocalizedMessage(), ExceptionUtils.getStackTrace(e));}
	}

}
