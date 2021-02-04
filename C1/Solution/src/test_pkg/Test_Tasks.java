package test_pkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * DATE: 04/02/21;
 * AUTHOR: Maccagni Giacomo
 * DESCRIPTION: This class is used to test
 * the assignment;
 */
public class Test_Tasks {

	public static void main(String[] args) {

		ArrayList<Task> list = new ArrayList<Task>();
		list.add(new Task(4, 7, 3));
		list.add(new Task(1, 3, -1));
		list.add(new Task(2, 9, -1));
		list.add(new Task(3, 4, 1));
		list.add(new Task(5, 5, 1));
		
		/*
		 * This instruction is used to verify the correct result
		 * also in case of random task insert.
		 */
		Collections.shuffle(list);		

		//TEST v1
		//System.out.println("Total time: "+getTimeToCompleteAllTasks1(list));
		
		//TEST v2
		//System.out.println("Total time: "+getTimeToCompleteAllTasks2(list));		

		//TEST v3
		System.out.println("Total time: "+getTimeToCompleteAllTasks3(list));
	
		System.out.println("Expected result: 14");
		
	}//end method main(...)

	/**
	 * This version is not working correctly because it uses
	 * the index of the list as the identifier of the Task;
	 * So if the id_task != id_list this method can cause a loop;
	 */
	@Deprecated
	public static int getTimeToCompleteAllTasks1(ArrayList<Task> list) {

		int total_time = 0;
		int partial_time = 0;
		Task dependent_task = null;


		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i).getId());

			if(list.get(i).getDependentTaskId() == -1) {
				if(total_time < list.get(i).getDuration()) {
					total_time = list.get(i).getDuration();
				}
			}//end case dependent_task_id == -1
			else {

				partial_time = 0;
				partial_time = list.get(i).getDuration();
				dependent_task = list.get(list.get(i).getDependentTaskId()-1);
				while(dependent_task != null) {

					partial_time = partial_time + dependent_task.getDuration();
					if(dependent_task.getDependentTaskId() == -1) {
						break;
					}
					dependent_task = list.get(dependent_task.getDependentTaskId()-1);

				}//end while cycle over dependent_tasks

				if(total_time < partial_time) {
					total_time = partial_time;
				}

			}//end case dependent_task_id != -1

		}//end for cycle over list

		return total_time;
	
	}//end of method getTimeToCompleteAllTasks1(...)
	
	/**
	 * This version is working correctly because the list
	 * is ordered by dependentTaskId;
	 * It is not optimized because an inner while
	 * cycle is executed multiple times;
	 */
	public static int getTimeToCompleteAllTasks2(ArrayList<Task> list) {

		int total_time = 0;
		int partial_time = 0;
		Task dependent_task = null;
		
		/*
		 * This instruction is used to reproduce
		 * the getNextTask();
		 * Instead of using that method, all tasks will be
		 * sorted by dependentTaskId.
		 * The obtained property will be:
		 * for given a Task, all tasks that it depends on have already
		 * been returned.
		 */
		Collections.sort(list, new Comparator_Task());

		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i).getId());

			if(list.get(i).getDependentTaskId() == -1) {
				if(total_time < list.get(i).getDuration()) {
					total_time = list.get(i).getDuration();
				}
			}//end case dependent_task_id == -1
			else {

				partial_time = 0;
				partial_time = list.get(i).getDuration();
				dependent_task = list.get(list.get(i).getDependentTaskId()-1);
				while(dependent_task != null) {

					partial_time = partial_time + dependent_task.getDuration();
					if(dependent_task.getDependentTaskId() == -1) {
						break;
					}
					dependent_task = list.get(dependent_task.getDependentTaskId()-1);

				}//end while cycle over dependent_tasks

				if(total_time < partial_time) {
					total_time = partial_time;
				}

			}//end case dependent_task_id != -1

		}//end for cycle over list

		return total_time;
	
	}//end of method getTimeToCompleteAllTasks2(...)

	/**
	 * This version is working correctly.
	 */
	public static int getTimeToCompleteAllTasks3(ArrayList<Task> list) {

		int total_time = 0;
		int partial_time = 0;
		Map<Integer, Task> map = new HashMap<>();
		
		/*
		 * This instruction is used to reproduce
		 * the getNextTask();
		 * Instead of using that method, all tasks will be
		 * sorted by dependentTaskId.
		 * The obtained property will be:
		 * for given a Task, all tasks that it depends on have already
		 * been returned.
		 */
		Collections.sort(list, new Comparator_Task());

		Task current_task = null;
		Iterator<Task> iterator = list.iterator();
		while(iterator.hasNext() == true) {
			
			current_task = iterator.next();
			partial_time = current_task.getDuration();
			map.put(current_task.getId(), current_task);
			
			if(current_task.getDependentTaskId() == -1) {
				if(total_time < partial_time) {
					total_time = partial_time;
				}
			}//end case dependent_task_id == -1
			else {

				partial_time = partial_time + map.get(current_task.getDependentTaskId()).getCumulative_time();
																				
				if(total_time < partial_time) {
					total_time = partial_time;
				}

			}//end case dependent_task_id != -1
			
			map.get(current_task.getId()).setCumulative_time(partial_time);

		}//end while cycle over list

		return total_time;
	
	}//end of method getTimeToCompleteAllTasks3(...)

}//end of class Test_Tasks