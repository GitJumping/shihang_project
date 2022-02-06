package geektime.concurrent.race;

import java.util.List;
import java.util.Random;

public class MySimpleSyncGen implements GenRunnable {

	SimpleShareData ssd;
	int size;
	int offset;
	public MySimpleSyncGen(SimpleShareData ssd, int size, int offset) {
		this.ssd = ssd;
		this.size = size;
		this.offset = offset;
	}
	
	@Override
	public void run() {
		gen();
	}

	@Override
	public void gen() {
		//System.out.println("开始产生随机数: " + size);
//		ThreadLocalRandom random = ThreadLocalRandom.current();
		Random rand = new Random(System.currentTimeMillis());
		int r;
		int i;
		List<Integer> score = ssd.getScore();
		synchronized (SimpleShareData.class) {
			for (i = 0; i < size; i++) {
				r = rand.nextInt(SimpleShareData.COUNT);
//				r = random.nextInt(SimpleShareData.COUNT);
				score.add(new Integer(r));
			}
		}
		//System.out.println("产生随机数个数: " + i);
		ssd.getGenSig().countDown();
		/*
		 */

		/*
		Random rand = new Random(System.currentTimeMillis());
		int r;
		int i;
		int[] genArr = new int[size];
		for (i = 0; i < size; i++) {
			r = rand.nextInt(SimpleShareData.COUNT);
			genArr[i] = r;
		}
		Arrays.sort(genArr);
		synchronized (ssd.getScore()) {
			List<Integer> genList = Arrays.stream(genArr).boxed().collect(Collectors.toList());
			Collections.reverse(genList);
			ssd.getScore().addAll(genList);
		}
		ssd.getGenSig().countDown();
		 */
	}

}
