import java.util.Arrays;
import java.util.Random;



public class QueueDriver implements QueueDriverInterface{
	// Main start here
	public static void main(String[] args) {
		QueueDriver driver = new QueueDriver();
		TestTimes tests = new TestTimes();
		
		for(int i=0;i<10;i++) {
		System.out.println ("Enqueue starts here");
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.ArrayBasedQueue, QueueDriver.TestType.Enqueue, 10)));
			 tests.resetTestTimes();
			 
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.LinkedQueue, QueueDriver.TestType.Enqueue, 10)));
			 tests.resetTestTimes();
			
	   System.out.println ("dequeue start here");
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.ArrayBasedQueue, QueueDriver.TestType.Dequeue, 10)));
			 tests.resetTestTimes();
			 
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.LinkedQueue, QueueDriver.TestType.Dequeue, 10)));
			 tests.resetTestTimes();
			
		System.out.println ("Dequeue Random starts here");
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.ArrayBasedQueue, QueueDriver.TestType.DequeueRandom, 10)));
			 tests.resetTestTimes();
			 
			 System.out.println(Arrays.deepToString(driver.runTestCase(QueueDriver.QueueType.LinkedQueue, QueueDriver.TestType.DequeueRandom, 10)));
			 tests.resetTestTimes();
			 
		
		
		}

	}
	
	//Main ends here
	public static  int randonPourDqueue(int min, int max) {
		
		Random random = new Random();
		
		int valeur = random.nextInt(max + min)+min;
		
		return valeur;
		
	} 
	
	@Override
	public QueueInterface<String> createQueue(QueueDriverInterface.QueueType queueType,
			QueueDriverInterface.TestType testType) {
		switch (queueType) {
        case ArrayBasedQueue:
            {QueueInterface<String> list = new ArrayBasedQueue<String>();
                            
            switch (testType) {
                case Enqueue:
                	
                	list.isEmpty();
                	
            		
                	break;
                    	
                case Dequeue:  
                	for (int i = 1; i <= 10000; i++) {
            			list.enqueue("String"+i);
            		}
                	break;
                               
                case DequeueRandom:
                	for (int i = 1; i <= 10000; i++) {
            			list.enqueue("String"+i);
            		}                   
                	break;
               
            }
            return list;
            }
		
        case LinkedQueue:
        { QueueInterface<String> llist = new LinkedQueue<String>();
            switch (testType) {
            case Enqueue:
            	/*for (int i = 1; i <= 10000; i++) {
        			llist.enqueue("String"+i);
        		}*/
            	llist.isEmpty();
           	break;
               	
           case Dequeue:  
        	   for (int i = 1; i <= 10000; i++) {
       			llist.enqueue("String"+i);
       		}
               
           	break;
                          
           case DequeueRandom:
        	   for (int i = 1; i <= 10000; i++) {
       			llist.enqueue("String"+i);
       		}
               break;
          
            }
            return llist;
        }
        
        default:
            return null;
    }
}

	@Override
	public Object[] runTestCase(QueueDriverInterface.QueueType queueType, QueueDriverInterface.TestType queueTestType,
			int numberOfTimes) {
		 TestTimes testTimes = new TestTimes();
	        Object [] ArrayObject= new Object[(2*numberOfTimes)+1];
	        long et=0;
	        long st=0;
	        for (int i = 0; i < numberOfTimes*2; i+=2){
	        	QueueInterface<String> Olist= createQueue(queueType,queueTestType);
	            @SuppressWarnings("unused")
	            QueueInterface<String> CopyOlist= Olist.copy();
	            ArrayObject[i]= CopyOlist;
	        	
	        	if(queueType==QueueType.ArrayBasedQueue) {
	        	
	        	if(queueTestType== TestType.Enqueue) {
	        		//ArrayObject[i]= null;
	        		 st = System.nanoTime();
	        		 for(int n=1;n<=10000;n++) {
	        			 Olist.enqueue("String"+n);
	        		 }
	        		
	        		  et = System.nanoTime();
	        		}
	        		
	        	
	        	
	        	if(queueTestType== TestType.Dequeue) {
	        		//ArrayObject[i]= CopyOlist;
	        		for(int j=0; j<10000; j++) {
	        			st = System.nanoTime();
	        			Olist.dequeue();
	        			et = System.nanoTime();
	        		}
	        		
	        	}
	        	
	        	
	        	if(queueTestType== TestType.DequeueRandom) {
	        		//ArrayObject[i]= CopyOlist;
	        		 
	        			st = System.nanoTime();
	        			int numE=10000;
		        		while(Olist.isEmpty()==false) {
	        				int rd=(randonPourDqueue(0,numE));
	        				if(rd>=0 && rd<numE) {
	        					Olist.dequeue(rd);
	        					numE--;
	        				}
	        				
	        					
	        				
	        			}
	        			
	        			et = System.nanoTime();
	        		
	        		
	        	}
	        
	     
	        }
	        else if (queueType==QueueType.LinkedQueue) {
	        	//ArrayObject[i]= null;
	        	if(queueTestType== TestType.Enqueue) {
	        		 st = System.nanoTime();
	        		 for(int n=1;n<=10000;n++) {
	        			 Olist.enqueue("String"+n);
	        		 }
	        		  et = System.nanoTime();
	        		}
	        		
	        	
	        	
	        	if(queueTestType== TestType.Dequeue) {
	        		//ArrayObject[i]= CopyOlist;
	        		for(int j=0; j<10000; j++) {
	        			st = System.nanoTime();
	        			Olist.dequeue();
	        			et = System.nanoTime();
	        		}
	        		
	        	}
	        	if(queueTestType== TestType.DequeueRandom) {
	        		//ArrayObject[i]= CopyOlist;
	        		st = System.nanoTime();
	        		int numE=10000;
	        		while(Olist.isEmpty()==false) {
        				int rd=(randonPourDqueue(0,numE));
        				if(rd>=0 && rd<numE) {
        					Olist.dequeue(rd);
        					numE--;
        				}
        				
        					
        				
        			}
        			
        			et = System.nanoTime();
	        		
	        	}
	       
	        	
	        }
	        	
	        	
	            testTimes.addTestTime(et - st);
	            ArrayObject[i+1]= Olist;
	        }
	        
	        ArrayObject[numberOfTimes * 2] = testTimes;
		       return ArrayObject;
	    }
	

}
