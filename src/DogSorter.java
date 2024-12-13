// Alexandra Bjelica albj3795

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        int count = 0;
        for (int i = 0; i < dogs.size() - 1; i++) {
            int smallestIndex = nextDog(comparator, dogs, i);
            if (smallestIndex != i) {
                swapDogs(dogs, smallestIndex, i);
                count++;
            }
        }
        return count;
    }
    private static void swapDogs(ArrayList<Dog> dogs, int indexEtt, int indexTva) {
        Dog first = dogs.get(indexEtt);
        Dog second = dogs.get(indexTva);

        dogs.set(indexEtt, second);
        dogs.set(indexTva, first);
    }
    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogList, int startIndex) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < dogList.size(); i++) {
            if (comparator.compare(dogList.get(i), dogList.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

}

