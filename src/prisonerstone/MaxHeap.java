package prisonerstone;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MaxHeap {

    static int[] heap;
    int size;

    public MaxHeap(int[] heap) {
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }


    public void maxHeapify(int index) {
        int largest = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < size && heap[index] < heap[leftIndex]) {
            largest = leftIndex;
        }
        if (rightIndex < size && heap[largest] < heap[rightIndex]) {
            largest = rightIndex;
        }

        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    public void buildMaxHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public void insert(int elem) {
        // increase heap size
        heap = Arrays.copyOf(heap, size + 1);
        int i = size;
        int parentIndex = (int) Math.floor((i - 1) / 2);
        // move up through the heap till you find the right position
        while (i > 0 && elem > heap[parentIndex]) {
            heap[i] = heap[parentIndex];
            i = parentIndex;
            parentIndex = (int) Math.floor((i - 1) / 2);
        }
        heap[i] = elem;
        size++;
    }

    public int findMax() {
        if (size == 0) {
            return -1;
        } else {
            return heap[0];
        }
    }

    public int extractMax() {
        if (size == 0) return -1;

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);
        return min;
    }

    public int getSize() {
        return size;
    }

    public int[] getHeap() {
        return heap;
    }

    public void printHeap() {
        if (heap == null)
            System.out.print("null");
        int iMax = size - 1, i;
        if (iMax == -1)
            System.out.print("[]");

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (i = 0; i < iMax; i++) {
            b.append(heap[i]);
            b.append(", ");
        }
        System.out.println(b.append(heap[i]).append(']').toString());
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    // test cases
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file;
        file = new File("D:\\PrisonerStone.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //Reading Number of Array Elements
        int numberOfStones = Integer.parseInt(bufferedReader.readLine());
        int[] stonesArray = new int[numberOfStones];

        // Reading Array Elements
        for (int i=0;i<numberOfStones;i++) {

            stonesArray[i] = Integer.parseInt(bufferedReader.readLine());
            System.out.println(stonesArray[i]);
        }

        MaxHeap maxHeap = new MaxHeap(stonesArray);

        //Creating Max Heap
        maxHeap.buildMaxHeap();

        int result = -1;

        while(maxHeap.size!=0) {

            // If one stone left.
            if(maxHeap.size == 1) {
                result = heap[0];
                break;
            }

            int maxOne = maxHeap.extractMax();

            int maxTwo = maxHeap.extractMax();

            result = maxOne - maxTwo;

            //If weight of resultant stone is 0.
            if(result!=0){
                maxHeap.insert(result);
            }

        }

            System.out.println(+result);

    }
}