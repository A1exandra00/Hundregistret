import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog first, Dog second) {

        double tailLengthOne = first.getTailLength();
        double tailLengthTwo = second.getTailLength();
        int tailLengthComparison = Double.compare(tailLengthOne, tailLengthTwo);

        if (tailLengthComparison != 0) {
            return tailLengthComparison;
        }

        String nameOne = first.getName();
        String nameTwo = second.getName();

        return nameOne.compareTo(nameTwo);
    }
}
