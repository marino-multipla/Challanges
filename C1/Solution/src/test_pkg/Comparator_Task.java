package test_pkg;

import java.util.Comparator;

/*
 * DATE:04/02/21;
 * AUTHOR: Maccagni Giacomo
 * DESCRIPTION: This comparator is used to compare
 * Tasks by dependentTaskId in crescent order;
 */
public class Comparator_Task implements Comparator<Task>{

	@Override
	public int compare(Task o1, Task o2) {
		return Integer.compare(o1.getDependentTaskId(), o2.getDependentTaskId());
	}

}//end of class Comparator_Task