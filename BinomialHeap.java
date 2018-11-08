/**
 * BinomialHeap
 *
 * An implementation of lazy binomial heap over non-negative integers.
 */
public class BinomialHeap
{
	private int size=0; //Size of the Heap
	private BinomialHeapTree min; //min node in heap
	private BinomialHeapTree trees=null; //list of trees of the heap
	private BinomialHeapTree lastTree=null; //last tree in trees

   /**
    * public boolean empty()
    *
    * precondition: none
    *
    * The method returns true if and only if the heap
    * is empty.
    *
    */
    public boolean empty()
    {
    	if(size==0)
    		return true;
    	return false; 
    }

   /**
    * public void insert(int value)
    *
    * Insert value into the heap
    *
    */
    public void insert(int value)
    {
    	
    	BinomialHeapTree tree=new BinomialHeapTree();
    	tree.value=value;
    	tree.nextSibling=null;
    	if(lastTree==null){ //insert to trees list
    		trees=tree;
    	}else
    	{
    		lastTree.nextSibling=tree;
    	}
    	lastTree=tree;
    	
    	size++;
    	if(min==null||min.value>tree.value)//replace min if need
    		min=tree;
    }

   /**
    * public void deleteMin()
    *
    * Delete the minimum value.
    * Return the number of linking actions that occured in the process.
    *
    */
    public int deleteMin()
    {
    	
    	if(size<=1){ //heap is now empty
    		trees=null;
    		lastTree=null;
    		min=null;
    		size=0;
    		return 0;
    	}
    	lastTree.nextSibling=min.child;
    	BinomialHeapTree tempTrees=min.nextSibling;
    	BinomialHeapTree[] treeArray=new BinomialHeapTree[(int)Math.floor(Math.log10(size)/(Math.log10(2)))+1];
    	//inserting all trees to basket-array by rank, for melding
    	insertToArray(trees,treeArray);
    	insertToArray(tempTrees,treeArray);
    	size--;
     	return fixHeap(treeArray);
    }
    /**
     * private void insertToArray(BinomialHeapTree node,BinomialHeapTree[] treeArray)
     *
     * Inserting tree's list to array by rank
     * treeArray-array 
     *node-the first tree in the list
     */
    private void insertToArray(BinomialHeapTree node,BinomialHeapTree[] treeArray){
    	BinomialHeapTree nextNode;
    	while(node!=min&&node!=null){
    		nextNode=node.nextSibling;
    		node.nextSibling=treeArray[node.rank];
    		treeArray[node.rank]=node;
    		node=nextNode;
    	}
    }
    /**
     * private int fixHeap(BinomialHeapTree[] treeArray)
     *
     * Meld the heap after delete
     * Return the number of linking actions that occured in the process.
     *
     */
    private int fixHeap(BinomialHeapTree[] treeArray){
    	min=null;
    	int linkNum=0;
    	for(int i=0;i<treeArray.length-1;i++){//for every rank of tree
    		BinomialHeapTree node=treeArray[i];
    		BinomialHeapTree node2;
    		BinomialHeapTree nextNode;
    		while(node!=null&&node.nextSibling!=null){//if have more than one tree in same size
    			nextNode=node.nextSibling.nextSibling;//linking them
    			if(node.value<node.nextSibling.value)//the minimum between them is the parent
    				node2=node.nextSibling;
    			else{
    				node2=node;
    				node=node.nextSibling;
    			}
    			node2.nextSibling=node.child;
    			node.child=node2;
    			node.rank=i+1;
    			linkNum++;//number of linking increase
    			node.nextSibling=treeArray[i+1];
    			treeArray[i+1]=node;
    			node=nextNode;
    			treeArray[i]=node;
    		}
    	}
    	lastTree=null;
    	
    	for(int i=0;i<treeArray.length;i++){//return trees back to trees list
    		if(treeArray[i]!=null){
    			if(lastTree==null){
    				trees=treeArray[i];
    			}
    			else
    				lastTree.nextSibling=treeArray[i];
    			lastTree=treeArray[i];
    			if(min==null||lastTree.value<min.value)//find the new min
    				min=lastTree;
    		}
    	}
    	if(lastTree!=null)
    		lastTree.nextSibling=null;
    	return linkNum;
    }
   /**
    * public int findMin()
    *
    * Return the minimum value
    *
    */
    public int findMin()
    {
    	if(size==0)
    		return -1;
    	return min.value;
    }

    /**
     * public void meld (BinomialHeap heap2)
     *
     * Meld the heap with heap2
     *
     */
     public void meld (BinomialHeap heap2)
    {
    	 heap2.getLastTree().nextSibling=trees;
    	 trees=heap2.getFirstTree();//connect heap2.trees with this.trees
    	 if(size==0)
    		lastTree= heap2.getLastTree();
    	 
    	 if(this.size==0||heap2.findMin()<this.findMin())//find new min
    		 min=heap2.getMin();
    	 size+=heap2.size();
    }

   /**
    * public int size()
    *
    * Return the number of elements in the heap
    *
    */
    public int size()
    {
    	return size;
    }


    /**
     * public static int sortArray(int[] array)
     *
     * Sort an array by using insert and deleteMin actions on a new heap.
     * Return the number of linking actions that occured in the process.
     *
     */
     public static int sortArray(int[] array)
     {
    	BinomialHeap heap=new BinomialHeap();
    	for(int i:array)//inserting array to heap
    		heap.insert(i);
    	
    	int linking=0;
    	
    	for(int i=0;i<array.length;i++){//delete min and insert to array
    		array[i]=heap.findMin();
    		linking+=heap.deleteMin();
    	}
        return linking;
    }

     /**
      * public int[] treesRanks()
      *
      * Return an array containing the ranks of the trees that represent the heap
      * in ascending order.
      *
      */
      public int[] treesRanks()
      {
    	if(size==0)
    		return new int[0];
    	int[] counter=new int[(int)Math.floor(Math.log10(size)/(Math.log10(2)))+1];//count-array
    	int treeNum=0;
        BinomialHeapTree tree=trees;
        for(;tree!=null;tree=tree.nextSibling){//count how much trees in every rank
        	counter[tree.rank]++;
        	treeNum++;
        }
        int[] arr = new int[treeNum];
        int index=0;
        for(int i=0;i<counter.length;i++)//make the array
        	for(int j=0;j<counter[i];j++){
        		arr[index]=i;
        		index++;
        	}
        return arr;
    }
      /**
       *  public BinomialHeapTree getFirstTree()
       *
       * return first tree in trees
       * 
       *
       */
     public BinomialHeapTree getFirstTree(){
    	 return trees;
     }
     /**
      * public BinomialHeapTree getLastTree()
      *
      * return lastTree
      * 
      *
      */
     public BinomialHeapTree getLastTree(){
    	 return lastTree;
     }
     /**
      * public BinomialHeapTree getMin()
      *
      * return tree with min value
      * 
      *
      */
     public BinomialHeapTree getMin(){
    	 return min;
     }

   /**
    * public class BinomialHeapTree
    *
    * If you wish to implement classes other than BinomialHeap
    * (for example BinomialHeapTree), do it in this file, not in
    * another file
    *
    */
    private class BinomialHeapTree{
    	public int rank=0;//rank of the node
    	public int value;//value of the node
    	public BinomialHeapTree child=null;//list of the children of the node
    	public BinomialHeapTree nextSibling=null;//next tree in the list
    }

}