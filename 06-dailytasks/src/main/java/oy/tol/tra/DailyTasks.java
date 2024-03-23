package oy.tol.tra;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   /** 
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {
         dailyTaskQueue = new QueueImplementation<>(10); // Initialize with an initial capacity of 10
         readTasks();
         timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if (!dailyTaskQueue.isEmpty()) {
                  try {
                     System.out.println(dailyTaskQueue.dequeue());
                  } catch (QueueIsEmptyException e) {
                     System.out.println("Queue is empty.");
                     timer.cancel();
                  }
               } else {
                  timer.cancel();
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      } catch (QueueAllocationException | NullPointerException e) {
         System.out.println("Error initializing the queue: " + e.getMessage());
      }
   }

   private void readTasks() throws IOException {
      String tasks;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");
      for (String task : allTasks) {
         // TODO: Enqueue the task to your Queue implementation:
         try{
            dailyTaskQueue.enqueue(task);
         }catch (QueueAllocationException | NullPointerException e){
            System.out.println("Failed to enqueue task: " + e.getMessage());
         }
      }
      // TODO: print out to the console the number of tasks in the queue:
      System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size());
   }
}
