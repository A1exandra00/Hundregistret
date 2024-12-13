// Alexandra Bjelica albj3795

import java.util.Comparator;

public class DogTailComparator implements Comparator <Dog> {

    public int compare(Dog first, Dog second) {
        return Double.compare(first.getTailLength(), second.getTailLength());
    }
}