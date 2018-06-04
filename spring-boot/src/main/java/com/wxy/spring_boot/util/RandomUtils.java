package com.wxy.spring_boot.util;

import java.util.Random;

public class RandomUtils {
	public static void main(String[] args) {
		int[] r = random(10);
		print(r,r.length);
		//System.out.println("sort ...");
		//quickSort(r,0, r.length-1);
		//print(r,r.length);
	}

	public static int random(int min, int max) {
		return new Random().nextInt(max) % (max - min + 1) + min;
	}

	public static int[] random(int size) {
		int[] r = new int[size];
		for (int i = 0; i < r.length; i++)
			r[i] = i;
		for (int i = 0; i < r.length; i++) {
			int t = random(i, size - 1);
			int temp = r[i];
			r[i] = r[t];
			r[t] = temp;
		}
		return r;
	}

	public static void quickSort(int[] a, int low, int high) {
		if(low >= high) return;
		int l = low,h = high;
		int pivot = a[l];
		while (l < h) {
			while (l < h && a[h] >= pivot)
				h--;
			a[l] = a[h];
			while (l < h && a[l] <= pivot)
				l++;
			a[h] = a[l];
		}
		a[l] = pivot;
		quickSort(a, low, l-1);
		quickSort(a, l+1, high);
	}
	public static void print(int[] o,int n) {
		for (int i = 0; i < Math.min(o.length,n); i++)
			System.out.println(o[i]);
	}
}
