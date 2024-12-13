public class test {
    public static void main(String[] args){
        Dog dog1 = new Dog("åke", "test", 10,10);
        Dog dog2 = new Dog("per", "test", 10,10);

        Owner o = new Owner("karl");

        dog1.setOwner(o);
        dog2.setOwner(o);

        System.out.println(dog1.getOwner());
        System.out.println(o.getDogs());

    }
}