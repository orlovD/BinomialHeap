

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.List;
	import java.util.NoSuchElementException;
	import java.util.Random;
	 
	/**
	 * BinomialHeapTester
	 * @author Izik
	 */
	public class Test2 {	 
	    public static void main(String[] args) {
	        for (Test test : tests) {
	            if (!test.test()) {
	                System.out.println();
	                System.out.println("==== !!FAILED!! ====");
	 
	                return;
	            }
	 
	            System.out.println();
	        }
	 
	        System.out.println();
	        System.out.println("==== BIG SUCCESS ====");
	    }
	    private static Random random = new Random();
	 
	    private static boolean test0() {
	        System.out.println("==== Insert + Del-min + treeRanks - 5 elements ====");
	        final int[] INSERTS_ARRAY = {2, 3, 1, 5, 4};
	        BinomialHeap heap = new BinomialHeap();
	        List<Integer> test = new ArrayList<Integer>();
	 
	        System.out.println("Inserting " + Arrays.toString(INSERTS_ARRAY));
	        for (int key : INSERTS_ARRAY) {
	            System.out.println("Inserting " + key);
	 
	            heap.insert(key);
	            test.add(key);
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - key:" + key + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	        System.out.println("Deleting min. keys");
	        for (int i : INSERTS_ARRAY) {
	            int min = heap.findMin();
	 
	            int minTest = -1;
	            try {
	                minTest = Collections.min(test);
	            } catch (NoSuchElementException e) {
	            };
	 
	            System.out.println("Deleting min. " + min);
	 
	            if (min != minTest) {
	                System.out.println("Min NOT EQUAL - heap:" + min + ",  tester:" + minTest);
	                return false;
	            }
	 
	            heap.deleteMin();
	            test.remove(new Integer(minTest));
	 
	            if (heap.size() != test.size()) {
	                System.out.println("Size NOT EQUAL - heap:" + heap.size() + ",  tester:" + test.size());
	                return false;
	            }
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - min:" + min + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	 
	        System.out.println("Del-min from empty heap");
	        for (int i = 0; i < 5; i++) {
	            System.out.println("Iter " + (i + 1));
	            if (heap.findMin() != -1) {
	                System.out.println("Min. not -1");
	                return false;
	            }
	 
	            heap.deleteMin();
	 
	            if (heap.size() != 0) {
	                System.out.println("Size NOT EQUAL 0 - heap:" + heap.size());
	                return false;
	            }
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	        System.out.println("treeRanks - SUM( 2^rank ) matched heapSize all the way");
	 
	        System.out.println("==== PASSED ====");
	        System.out.println();
	 
	        return true;
	    }
	 
	    /**
	     * Sequence of inserts followed by del-min
	     */
	    private static boolean test1() {
	        System.out.println("==== Insert + Del-min + treeRanks ====");
	        final int INSERTS = 10000;
	        BinomialHeap heap = new BinomialHeap();
	        List<Integer> test = new ArrayList<Integer>(INSERTS);
	 
	        System.out.println("Inserting " + INSERTS + " keys");
	        for (int i = 0; i < INSERTS; i++) {
	            int key = random.nextInt(INSERTS * 10);
	 
//	        System.out.println("Inserting " + key);
	 
	            heap.insert(key);
	            test.add(key);
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - iter:" + i + ", key:" + key + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	 
	        System.out.println("Deleting " + INSERTS + " min. keys");
	        for (int i = 0; i < INSERTS + 10; i++) {
	            int min = heap.findMin();
	 
	            int minTest = -1;
	            try {
	                minTest = Collections.min(test);
	            } catch (NoSuchElementException e) {
	            };
	 
//		    System.out.println("Deleting " + min);
	 
	            if (min != minTest) {
	                System.out.println("Min NOT EQUAL - iter:" + i + ", heap:" + min + ",  tester:" + minTest);
	                return false;
	            }
	 
	            heap.deleteMin();
	            test.remove(new Integer(minTest));
	 
	            if (heap.size() != test.size()) {
	                System.out.println("Size NOT EQUAL - iter:" + i + ", heap:" + heap.size() + ",  tester:" + test.size());
	                return false;
	            }
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - iter:" + i + ", min:" + min + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	        System.out.println("treeRanks - SUM( 2^rank ) matched heapSize all the way");
	 
	        System.out.println("==== PASSED ====");
	        System.out.println();
	 
	        return true;
	    }
	 
	    private static boolean test2() {
	        System.out.println("==== sortArray ====");
	        final int INSERTS = 10000;
	 
	        int[] array = new int[INSERTS];
	 
	        System.out.println("Inserting " + INSERTS + " keys to int[]");
	        for (int i = 0; i < INSERTS; i++) {
	            int key = random.nextInt(INSERTS * 10);
	 
	            array[i] = key;
	        }
	 
	        System.out.println("Duplicating array");
	        int[] arrayCopy = Arrays.copyOf(array, array.length);
	 
	        System.out.println("sortArray(int[])");
	        BinomialHeap.sortArray(array);
	 
	        System.out.println("Arrays.sort(int[]) on duplicate");
	        Arrays.sort(arrayCopy);
	 
	        System.out.println("Checking equals...");
	        if (!Arrays.equals(array, arrayCopy)) {
	            System.out.println("Sorting NOT EQUALS");
	            return false;
	        }
	 
	        System.out.println("==== PASSED ====");
	        System.out.println();
	 
	        return true;
	    }
	 
	    private static boolean test3() {
	        System.out.println("==== Meld - random heaps ====");
	        final int HEAPS_COUNT = 10;
	        final int MAX_HEAPS_SIZE = 5000;
	        BinomialHeap heap = new BinomialHeap();
	        List<Integer> test = new ArrayList<Integer>();
	 
	        System.out.println("Creating " + HEAPS_COUNT + " random heaps");
	        for (int i = 0; i < HEAPS_COUNT; i++) {
	            int heapSize = random.nextInt(MAX_HEAPS_SIZE);
	 
	            System.out.println("Creating heap " + (i + 1) + " of size " + heapSize);
	            BinomialHeap meldedHeap = new BinomialHeap();
	            for (int j = 0; j < heapSize; j++) {
	                int key = random.nextInt(MAX_HEAPS_SIZE * HEAPS_COUNT);
	 
	                meldedHeap.insert(key);
	                test.add(key);
	            }
	 
	            System.out.println("   Del-min from heap to be meld");
	            int min = meldedHeap.findMin();
	            meldedHeap.deleteMin();
	 
	            if (min != -1) {
	                test.remove(new Integer(min));
	            }
	 
	            System.out.println("   Melding");
	 
	            heap.meld(meldedHeap);
	 
	            if (heap.size() != test.size()) {
	                System.out.println("Size NOT EQUAL - iter:" + i + ", size:" + heap.size() + ", testSize:" + test.size());
	            }
	        }
	 
	        System.out.println("Deleting " + heap.size() + " min. keys from final melded");
	        for (int i = 0; !heap.empty(); i++) {
	            int min = heap.findMin();
	 
	            int minTest = -1;
	            try {
	                minTest = Collections.min(test);
	            } catch (NoSuchElementException e) {
	            };
	 
	            if (min != minTest) {
	                System.out.println("Min NOT EQUAL - iter:" + i + ", heap:" + min + ",  tester:" + minTest);
	                return false;
	            }
	 
	            heap.deleteMin();
	            test.remove(new Integer(minTest));
	 
	            if (heap.size() != test.size()) {
	                System.out.println("Size NOT EQUAL - iter:" + i + ", heap:" + heap.size() + ",  tester:" + test.size());
	                return false;
	            }
	 
	            int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	            if (treeRanksSize != heap.size()) {
	                System.out.println("Size NOT EQUAL treeRanksSize - iter:" + i + ", min:" + min + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                return false;
	            }
	        }
	 
	        System.out.println("treeRanks - SUM( 2^rank ) matched heapSize all the way");
	 
	        System.out.println("==== PASSED ====");
	        System.out.println();
	 
	        return true;
	    }
	 
	    private static boolean test4() {
	        System.out.println("==== Random Insert\\Delete-min ====");
	        final int OPERATIONS = 10000;
	        BinomialHeap heap = new BinomialHeap();
	        List<Integer> test = new ArrayList<Integer>(OPERATIONS);
	 
	        System.out.println("Gaussian distributed insert\\delete operations");
	        int insertsCount = 0;
	        int delMinCount = 0;
	        for (int i = 0; i < OPERATIONS; i++) {
	            if (random.nextBoolean()) {
	                insertsCount++;
	 
	                int key = random.nextInt(OPERATIONS * 10);
	 
	                heap.insert(key);
	                test.add(key);
	 
	                int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	                if (treeRanksSize != heap.size()) {
	                    System.out.println("Size NOT EQUAL treeRanksSize - iter:" + i + ", key:" + key + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize+"  "+heap.treesRanks()[1]);
	                    return false;
	                }
	            } else {
	                delMinCount++;
	                
	                int min = heap.findMin();
	 
	                int minTest = -1;
	                try {
	                    minTest = Collections.min(test);
	                } catch (NoSuchElementException e) {
	                };
	 
	                if (min != minTest) {
	                    System.out.println("Min NOT EQUAL - iter:" + i + ", heap:" + min + ",  tester:" + minTest);
	                    return false;
	                }
	 
	                heap.deleteMin();
	                test.remove(new Integer(minTest));
	 
	                if (heap.size() != test.size()) {
	                    System.out.println("Size NOT EQUAL - iter:" + i + ", heap:" + heap.size() + ",  tester:" + test.size());
	                    return false;
	                }
	 
	                int treeRanksSize = treeRanksToTreeSize(heap.treesRanks());
	                if (treeRanksSize != heap.size()) {
	                    System.out.println("Size NOT EQUAL treeRanksSize - iter:" + i + ", min:" + min + ",  size:" + heap.size() + ", treeRanksSize:" + treeRanksSize);
	                    return false;
	                }
	            }
	        }
	        
	        System.out.println("Inserted: " + insertsCount + ", Deleted: " + delMinCount);
	 
	        System.out.println("treeRanks - SUM( 2^rank ) matched heapSize all the way");
	 
	        System.out.println("==== PASSED ====");
	        System.out.println();
	 
	        return true;
	    }
	 
	    private static int treeRanksToTreeSize(int[] treeRanks) {
	        int size = 0;
	        int lastRank = -1;
	        for (int rank : treeRanks) {
	            size += Math.pow(2, rank);
	        
	            if (rank < lastRank) {
	                throw new RuntimeException("treeRanks is not ordered! treeRanks: " + Arrays.toString(treeRanks));
	            }
	            
	            lastRank = rank;
	        }
	 
	        return size;
	    }
	
	    abstract private static class Test{
	 
	        abstract boolean test();
	    }
	    private static Test[] tests = {
	       new Test() {
	            @Override
	            boolean test() {
	                return test0();
	            }
	        },
	        new Test() {
	            @Override
	            boolean test() {
	                return test1();
	            }
	        },
	        new Test() {
	            @Override
	            boolean test() {
	                return test2();
	            }
	        },
	        new Test() {
	            @Override
	            boolean test() {
	                return test3();
	            }
	        },
	        new Test() {
	            @Override
	            boolean test() {
	                return test4();
	            }
	        }
	    };
	}
	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		int m;
		for(int k=1;k<=10;k++){
			m=k*10000;
			int[] array=new int[m];
			for(int i=0;i<array.length;i++)
				array[i]=(int)(Math.random()*100000);
			int first=BinomialHeap.sortArray(array);
			
			int first2=BinomialHeap.sortArray(array);
			
			int[] array2=new int[m];
			for(int i=0;i<array.length;i++)
				array2[i]=array[m-i-1];
			int first3=BinomialHeap.sortArray(array2);
			
			System.out.println(k+"\t"+first+"\t"+first2+"\t"+first3);
			
		}
		
		

	}
	}*/


