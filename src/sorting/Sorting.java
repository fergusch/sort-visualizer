package sorting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Collection of sorting algorithms for visualization.
 */
public class Sorting {
	
	/**
	 * Formats an array of integers into a string to be
	 * read by the VisualizationScreen class, like so:<br />
	 * {@code h1;h2;x1 x2 x3 x4 ...}
	 * @param h1 - first element to be highlighted (red)
	 * @param h2 - second element to be highlighted (yellow)
	 * @param arr - the array to be formatted
	 * @see VisualizationScreen
	 * @return a formatted string
	 */
	public static String array2Str(int h1, int h2, int[] arr) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(h1).append(";").append(h2).append(";").append(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			try {
				sb.append(" ").append(arr[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
				sb.append(" 0");
			}
			
		}
		sb.append("\n");
		return sb.toString();
		
	}
	
	public static void bubbleSort(int[] arr, BufferedWriter w) {
		
		boolean swap = true;
		
		while (swap) {
			
			swap = false;
			
			for (int i = 0; i < arr.length - 1;i++) {
				
				if (arr[i] > arr[i+1]) {
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					swap = true;
					
					// write the formatted array to file
					try {
						w.write(array2Str(i+1, i, arr));
						w.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
	}
	
	public static void insertionSort(int[] arr, BufferedWriter w) {
		
		int i = 1;
		while (i < arr.length) {
			int j = i;
			while (j > 0 && arr[j-1] > arr[j]) {
				int tmp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = tmp;
				j--;
				// write the formatted array to file
				try {
					w.write(array2Str(j, i, arr));
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			i++;
		}
		
	}
	
	public static void selectionSort(int[] arr, BufferedWriter w) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i+1; j < arr.length; j++) {
				if (arr[j] < arr[index]) {
					index = j;
				}
				
				// write the formatted array to file
				try {
					w.write(array2Str(i, j, arr));
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
		}
		
	}
	
	public static int partition(int[] arr, int p, int r, BufferedWriter w) {
		
		int x = arr[r];
		int i = p-1;
		for (int j = p; j < r; j++) {
			if (arr[j] <= x) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
			
			// write the formatted array to file
			try {
				w.write(array2Str(i, j, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		int temp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = temp;
		
		return i+1;
		
	}
	
	public static void _quickSort(int[] arr, int p, int r, BufferedWriter w) {
		
		if (p < r) {
			int q = partition(arr, p, r, w);
			_quickSort(arr, p, q-1, w);
			_quickSort(arr, q+1, r, w);
			
		}
		
	}
	
	public static void quickSort(int[] arr, BufferedWriter w) {
		_quickSort(arr, 0, arr.length - 1, w);
	}
	
	
	public static void merge(int[] arr, int left, int mid, int right, BufferedWriter w) {
		
		int n1 = mid - left;
		int n2 = right - mid;
		
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		
		for (int i = 0; i < n1; i++) {
			L[i] = arr[left + i];
		}
		
		for (int i = 0; i < n2; i++) {
			R[i] = arr[mid + i];
		}
		
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		
		for (int k = left; k < right; k++) {
			
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
				
				// write the formatted array to file
				try {
					w.write(array2Str(k, -1, arr));
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				arr[k] = R[j];
				j++;
				
				// write the formatted array to file
				try {
					w.write(array2Str(k, -1, arr));
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public static void _mergeSort(int[] arr, int left, int right, BufferedWriter w) {
		
		if (left + 1 < right) {
			
			int mid = (left + right)/2;
			_mergeSort(arr, left, mid, w);
			_mergeSort(arr, mid, right, w);
			merge(arr, left, mid, right, w);
			
		}
		
	}
	
	public static void mergesort(int[] arr, BufferedWriter w) {
		_mergeSort(arr, 0, arr.length, w);
	}
	
	public static void insertionSort2(int[] arr, int n, int g, BufferedWriter w) {
		
		for (int i = g; i < n; i++) {
			int v = arr[i];
			int j = i - g;
			while (j >= 0 && arr[j] > v) {
				arr[j+g] = arr[j];
				j -= g;
				
				// write the formatted array to file
				try {
					w.write(array2Str(j+g, i, arr));
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			arr[j+g] = v;
			
		}
		
	}
	
	public static void shellSort(int[] arr, int n, BufferedWriter w) {
		
		int m = 0;
		
		// calculate the list of gaps beforehand, in ascending order
		ArrayList<Integer> G = new ArrayList<Integer>();
		for (int i = 1; i <= n; i = (i * 3) + 1) {
			G.add(m, i);
			m++;
			
		}
		
		// reverse the list to use for sorting
		Collections.reverse(G);
		
		// perform the shell sort
		for (int i = 0; i < m; i++) {
			insertionSort2(arr, n, G.get(i), w);
		}
	}
	
	public static void countingSort(int[] arr, BufferedWriter w) {
		
		int k = 10000;
		
		int[] c = new int[k];
		int[] result = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			c[arr[i]]++;
		}
		
		for (int i = 1; i < k; i++) {
			c[i] = c[i] + c[i-1];
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			result[c[arr[i]] - 1] = arr[i];
			c[arr[i]]--;
			
			// write the formatted array to file
			try {
				w.write(array2Str(-1, i, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = result[i];
			
			// write the formatted array to file
			try {
				w.write(array2Str(i, -1, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void countingSort2(int[] arr, int exp, BufferedWriter w) {
		
		int[] output = new int[arr.length];
		int[] count = new int[10];
		for (int i = 0; i < 10; i++) {
			count[i] = 0;
		}
		
		for (int i = 0; i < arr.length; i++) {
			count[(arr[i]/exp) % 10]++;
			
			// write the formatted array to file
			try {
				w.write(array2Str(-1, i, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		for (int i = 1; i < 10; i++) {
			count[i] += count[i-1];
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			output[count[(arr[i])/exp % 10] - 1] = arr[i];
			count[(arr[i]/exp) % 10]--;
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = output[i];
			
			// write the formatted array to file
			try {
				w.write(array2Str(i, -1, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void radixLSD(int[] arr, BufferedWriter w) {
		
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		
		for (int exp = 1; max/exp > 0; exp *= 10) {
			countingSort2(arr, exp, w);
			
			// write the formatted array to file
			try {
				w.write(array2Str(exp, max, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void _slowSort(int[] arr, int i, int j, BufferedWriter w) {
		
		if (i >= j) return;
		
		int m = (i+j)/2;
		
		_slowSort(arr, i, m, w);
		_slowSort(arr, m+1, j, w);
		
		if (arr[j] < arr[m]) {
			int temp = arr[j];
			arr[j] = arr[m];
			arr[m] = temp;
			
			// write the formatted array to file
			try {
				w.write(array2Str(i, j, arr));
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		_slowSort(arr, i, j-1, w);
		
	}
	
	public static void slowSort(int[] arr, BufferedWriter w) {
		_slowSort(arr, 0, arr.length - 1, w);
	}
	
}
