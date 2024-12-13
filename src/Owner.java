// Alexandra Bjelica albj3795

import java.util.ArrayList;

public class Owner implements Comparable<Owner> {
    private String name;
    private ArrayList<Dog> dogs= new ArrayList<>();

    public Owner(String name) {
        this.name = normalizeString(name);
    }

    private String normalizeString(String str) {
        if (str == null || str.isEmpty()) return "";
        str = str.trim();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public boolean addDog(Dog dog) {
        if (dog == null || dogs.contains(dog)) {
            return false;
        }
        if (dog.getOwner() != null && !this.equals(dog.getOwner())) {
            return false;
        }
        dogs.add(dog);
        if (!this.equals(dog.getOwner())) {
            dog.setOwner(this);
        }
        return true;
    }
    public boolean removeDog(Dog dog) {
        if (dog == null || !dogs.contains(dog))
            return false;

        dogs.remove(dog);
        if (this.equals(dog.getOwner()))
            dog.setOwner(null);
        return true;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogs);
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs);
        return sortedDogs;
    }
    public String getName() {
        return name;  }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Owner{name='").append(name).append("', dogs=[");
        for (Dog dog : dogs) {
            sb.append(dog.getName()).append(", ");
        }
        if (!dogs.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }

    public int compareTo(Owner otherOwner) {
        return this.name.compareToIgnoreCase(otherOwner.getName());
    }
}