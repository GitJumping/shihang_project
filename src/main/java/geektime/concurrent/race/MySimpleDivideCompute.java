package geektime.concurrent.race;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MySimpleDivideCompute implements ComputeRunnable {

	SimpleShareData ssd;
	int size;
	int offset;
	public MySimpleDivideCompute(SimpleShareData ssd, int size, int offset) {
		this.ssd = ssd;
		this.size = size;
		this.offset = offset;
	}
	
	@Override
	public void run() {
		go();
	}

	@Override
	public void go() {
		//15s
		//extracted();

		//0.1s
		//extracted1();

		//0.0x~0.1
		extracted2();
	}

	private void extracted2() {
		System.out.println("开始计算随机数: " + size + " " + offset);
		int cycleSize = SimpleShareData.BUFSIZE * size / SimpleShareData.COUNT;
		int oSize = offset * size;
		List<Integer> subList = ssd.getScore().subList(oSize, oSize + cycleSize);
		for (int i = 0; i < cycleSize; i++) {
			ssd.addExchange(subList.get(i));
		}
		ssd.getCompSig().countDown();
	}

	private void extracted1() {
		long ms = System.currentTimeMillis();
		System.out.println("开始计算随机数: " + size + " " + offset);
		int[] alist = new int[size];
		List<Integer> list = ssd.getScore();
		for (int i = 0; i < size; i++) {
			alist[i] = list.get(offset * size + i);
		}
//		Arrays.sort(alist);
		alist = getMaximumNumbers(alist,10);
		for (int i = 0; i < 10; i++) {
			ssd.addExchange(alist[alist.length - i - 1]);
		}
		ssd.getCompSig().countDown();
	}

	private void extracted() {
		System.out.println("开始计算随机数: " + size + " " + offset);
		int[] alist = new int[size];
		for (int i = 0; i < size; i++) {
			alist[i] = ssd.getScore().indexOf(offset * size + i);
		}
		Arrays.sort(alist);

		for (int i = 0; i < SimpleShareData.BUFSIZE * size / SimpleShareData.COUNT; i++) {
			//System.out.println("随机数: " + alist[alist.length - i - 1]);
			ssd.addExchange(alist[alist.length - i - 1]);
		}
		ssd.getCompSig().countDown();
		;
		//System.out.println("计算随机数完毕: " + size + " " + SimpleShareData.BUFSIZE * size / SimpleShareData.COUNT);
	}

	public int[] getMaximumNumbers(int[] arr, int k) {
		int[] vec = new int[k];
		if (k == 0) { // 排除 0 的情况
			return vec;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < k; ++i) {
			queue.offer(arr[i]);
		}
		for (int i = k; i < arr.length; ++i) {
			if (queue.peek() < arr[i]) {
				queue.poll();
				queue.offer(arr[i]);
			}
		}
		for (int i = 0; i < k; ++i) {
			vec[i] = queue.poll();
		}
		return vec;
	}
}
