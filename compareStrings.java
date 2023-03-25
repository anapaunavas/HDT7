import java.util.Comparator;

public class compareStrings {
    public class StringComparator<K> implements Comparator<K>{

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
}
