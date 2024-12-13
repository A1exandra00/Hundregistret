// Alexandra Bjelica albj3795

import java.util.*;
public class DogCollection {
    private ArrayList<Dog> dogList = new ArrayList<>();

    public boolean addDog(Dog dog) {
        if (dog == null) {
            return false;
        }
        for (Dog d : dogList) {
            if (d.getName().equalsIgnoreCase(dog.getName())) {
                return false;
            }
        }
        dogList.add(dog);
        return true;
    }

    public boolean removeDog(Dog dog) {
        if (dog == null || dog.getOwner() != null) {
            return false;
        }
        boolean removed = dogList.remove(dog);
        if (removed) {
            if (dog.getOwner() != null) {
            dog.getOwner().removeDog(dog);
            }
        }
        return removed;
    }

    public boolean removeDog(String dogName) {
        if (dogName == null || dogName.isBlank()) {
        return false;
    }
        Dog dog = getDog(dogName);
        if (dog == null) {
            return false;
        }
        return removeDog(dog);
    }

    public boolean containsDog(Dog dog) {
        return dog != null && dogList.contains(dog);
    }

    public boolean containsDog(String dogName) {
        return getDog(dogName) != null;
    }

    public Dog getDog(String dogName) {
        if (dogName == null) {
            return null;
        }
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                return dog;
            }
        }
        return null;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> copy = new ArrayList<>(dogList);
        DogSorter.sortDogs(new DogNameComparator(), copy);
        return copy;
    }

    public ArrayList<Dog> getDogsByTailLength(double minTailLength) {
        ArrayList<Dog> copy = new ArrayList<>();
        for (Dog dog : dogList) {
            if (dog.getTailLength() >= minTailLength) {
                copy.add(dog);
            }
        }
        DogSorter.sortDogs(new DogTailNameComparator(), copy);
        return copy;
    }
}
