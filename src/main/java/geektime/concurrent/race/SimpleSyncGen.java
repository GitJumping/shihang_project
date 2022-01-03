package geektime.concurrent.race;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleSyncGen implements GenRunnable {

	SimpleShareData ssd;
	int size;
	int offset;
	public SimpleSyncGen(SimpleShareData ssd, int size, int offset) {
		this.ssd = ssd;
		this.size = size;
		this.offset = offset;
	}
	
	@Override
	public void run() {
		gen();
	}

//	@Override
//	public void gen() {
//		//System.out.println("开始产生随机数: " + size);
//		Random rand = new Random(System.currentTimeMillis());
//		int r;
//		int i;
//    	for (i = 0; i < size; i++) {
//    		r = rand.nextInt(SimpleShareData.COUNT);
//    		synchronized (ssd.getScore()) {
//    			ssd.getScore().add(new Integer(r));
//    		}
//    	}
//    	//System.out.println("产生随机数个数: " + i);
//    	ssd.getGenSig().countDown();
//	}

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
	}

}
