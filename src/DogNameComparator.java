// Alexandra Bjelica albj3795

import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {
    public int compare(Dog first, Dog second) {

        String nameOne = first.getName();
        String nameTwo = second.getName();

        return nameOne.compareTo(nameTwo);
    }

}