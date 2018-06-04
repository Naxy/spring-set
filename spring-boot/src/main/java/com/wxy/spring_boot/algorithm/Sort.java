package com.wxy.spring_boot.algorithm;

public class Sort {

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 1, 8, 7, 2, 5, 9, 0 };
		// insertSort(arr);
		//quickSort(arr, 0, arr.length - 1);
		heapSort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				int tmp = arr[i];
				int j = i - 1;
				for (; j >= 0 && tmp < arr[j]; j--)
					arr[j + 1] = arr[j];
				arr[j + 1] = tmp;
			}

		}
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low >= high)
			return;
		int l = low, h = high;
		int pivot = arr[l];
		while (l < h) {
			while (l < h && arr[h] >= pivot)
				h--;
			arr[l] = arr[h];
			while (l < h && arr[l] <= pivot)
				l++;
			arr[h] = arr[l];
		}
		arr[l] = pivot;
		quickSort(arr, low, l - 1);
		quickSort(arr, l + 1, high);
	}

	public static void heapSort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			// 从第一个非叶子结点从下至上，从右至左调整结构
			adjustHeap(arr, i, arr.length);
		}
		for (int j = arr.length - 1; j >= 0; j--) {
			// 将堆顶元素与末尾元素进行交换
			int temp = arr[0];
			arr[0] = arr[j];
			arr[j] = temp;
			// 重新对堆进行调整
			adjustHeap(arr, 0, j);
		}

	}

	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		// 从i结点的左子结点开始，也就是2i+1处开始
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// 如果左子结点小于右子结点，k指向右子结点
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}
			if (arr[k] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[i] = arr[k];
				i = k;//继续向下筛选
			} else
				break;
		}
		arr[i] = temp;
	}
}
