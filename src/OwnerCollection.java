// Alexandra Bjelica albj3795

import java.util.*;
public class OwnerCollection {

    private Owner[] owners;
    private int size;

    public OwnerCollection() {
        this.owners = new Owner[10];
        this.size = 0;
    }
    public boolean addOwner(Owner newOwner) {
        if (newOwner == null) {
            return false;
        }
        if (!containsOwner(newOwner.getName())) {
            ensureCapacity();
            owners[size++] = newOwner;
            return true;
        }
        return false;
    }

    public boolean removeOwner(String nameOfOwner) {
        Owner ownerToRemove = getOwner(nameOfOwner);
        return removeOwner(ownerToRemove);
    }

    public boolean removeOwner(Owner ownerToRemove) {
        if (ownerToRemove != null && ownerToRemove.getDogs().isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (owners[i].equals(ownerToRemove)) {
                    owners[i] = owners[--size];
                    owners[size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsOwner(String nameOfOwner) {
        if (nameOfOwner == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equals(nameOfOwner)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOwner(Owner owner) {
        if (owner == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (owners[i].equals(owner)) {
                return true;
            }
        }
        return false;
    }

    public Owner getOwner(String nameOfOwner) {
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equalsIgnoreCase(nameOfOwner)) {
                return owners[i];
            }
        }
        return null;
    }

    public ArrayList<Owner> getOwners() {
        Owner[] sortedOwners = Arrays.copyOf(owners, size);
        Arrays.sort(sortedOwners, Comparator.comparing(Owner::getName));
        return new ArrayList<>(Arrays.asList(sortedOwners));
    }

    private void ensureCapacity() {
        if (size >= owners.length) {
            int newCapacity = Math.max(owners.length * 2, 10);
            owners = Arrays.copyOf(owners, newCapacity);
        }
    }
}