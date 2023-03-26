// Ana Paula Hong 22731

import java.util.Comparator;

public class compareStrings<K> implements Comparator<K>{

	
    /** 
     * @param o1
     * @param o2
     * @return int
     */
    @Override
	public int compare(K o1, K o2) {
		int comparison = o1.toString().compareTo(o2.toString());
		if (comparison < 0) {
			return -1;
		} else{
			if(comparison == 0) {
				return 0;
			} else {
				return 1;	
			}
		}
	}
}