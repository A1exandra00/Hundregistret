// Alexandra Bjelica albj3795

public class Dog {
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private String name;
    private String breed;
    private int weight;
    private int age;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = normalizeString(name);
        this.breed = normalizeString(breed);
        this.age = Math.max(age, 0);
        this.weight = Math.max(weight, 0);
        this.owner = null;
    }

    public boolean setOwner(Owner owner) {
        if (this.owner == owner) {
            return false;
        }
        if (this.owner != null && owner != null && !this.owner.equals(owner)) {
            return false;
        }
        if (this.owner != null) {
            this.owner.removeDog(this);
        }
        this.owner = owner;
        if (owner != null && !owner.getDogs().contains(this)) {
            owner.addDog(this);
        }
        return true;
    }

    public Owner getOwner() {
        return owner;
    }

    private String normalizeString(String str) {
        if (str == null || str.isEmpty()) return "";
        str = str.trim();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public int increaseAge(int years) {
        if (years > 0) {
            if (years + age < 0) {
                age = Integer.MAX_VALUE;
            } else {
                age += years;
            }
            getTailLength();
        }
        return age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund")) {
            return DACHSHUND_TAIL_LENGTH;
        }
        return age * weight / 10.0;
    }

    @Override
    public String toString() {
        return String.format("Dog{name='%s', breed='%s', age=%d, weight=%d, tailLength=%.2f, owner=%s}", name, breed, age, weight, getTailLength(), owner != null ? owner.getName() : "");
    }
}
