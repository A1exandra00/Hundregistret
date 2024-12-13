// Alexandra Bjelica albj3795

import java.util.ArrayList;
import java.util.List;

public class DogRegister {

    private InputReader input = new InputReader();
    private DogCollection dogCollection = new DogCollection();
    private OwnerCollection ownerCollection = new OwnerCollection();
    private boolean running = true;

    public static void main(String[] args) {
        new DogRegister().start();
    }

    private void start() {
        initialize();
        runCommandLoop();
    }

    private void initialize() {
        System.out.println("Welcome to the dog register!");
        System.out.println("The following commands are available:");
        System.out.println("* register new owner - Register new owner");
        System.out.println("* remove owner - Remove owner");
        System.out.println("* register new dog - Register new dog");
        System.out.println("* remove dog - Remove dog");
        System.out.println("* list dogs - List dogs");
        System.out.println("* list owners - List owners");
        System.out.println("* increase age - Increase age of a dog");
        System.out.println("* give dog to owner - Give dog to owner");
        System.out.println("* remove dog from owner - Remove dog from owner");
        System.out.println("* exit - Exit");
        runCommandLoop();
    }

    private void runCommandLoop() {
        while (running) {
            String command = input.readLine("Enter command").trim().toLowerCase();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        switch (command) {
            case "register new owner":
                registerNewOwner();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "register new dog":
                registerNewDog();
                break;
            case "remove dog":
                removeDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list owners":
                listOwners();
                break;
            case "increase age":
                increaseAge();
                break;
            case "give dog to owner":
                giveDogToOwner();
                break;
            case "remove dog from owner":
                removeDogFromOwner();
                break;
            case "exit":
                System.out.println("Exiting program...");
                shutDown();
                break;
            default:
                System.out.println("Error! Wrong command. Try again!");
                break;
        }
    }

    private void shutDown() {
        System.out.println("Program shut down.");
        running = false;
    }

    private void registerNewOwner() {
        String name = input.readLine("Enter owner name").trim();
        while (name.isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again");
            name = input.readLine("Enter owner name").trim();
        }
        Owner owner = new Owner(name);
        if (ownerCollection.addOwner(owner)) {
            System.out.println("The owner " + owner.getName() + " has been added to the register.");
            } else {
                System.out.println("Error: The owner " + owner.getName() + " already exists.");
            }
        }
    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
            return;
        }
        String ownerName = input.readLine("Enter owner name").trim();
        if (ownerName.isEmpty() || ownerName.isBlank()) {
            System.out.println("Error: Owner name cannot be empty or blank.");
            return;
        }
        Owner owner = ownerCollection.getOwner(ownerName);
        if (owner == null) {
            System.out.println("Error: The owner " + ownerName + " doesn't exist");
            return;
        }

    List<Dog> dogsToRemove = new ArrayList<>(owner.getDogs());
        for (Dog dog : dogsToRemove) {
            owner.removeDog(dog);
            dog.setOwner(null);
            dogCollection.removeDog(dog);
    }
        ownerCollection.removeOwner(ownerName);
        System.out.println("The owner " + ownerName + " has been removed from the register");
}


    private void registerNewDog() {
        String dogName = input.readLine("Enter dog name").trim();
        while (dogName.isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again");
            dogName = input.readLine("Enter dog name").trim();
        }
        if (dogCollection.containsDog(dogName)) {
            System.out.println("Error! Dog already exists");
            return;
        }

        String breed = input.readLine("Enter dog breed").trim();
        while (breed.isEmpty() || breed.isBlank()) {
            System.out.println("Error: Breed cannot be empty or blank.");
            breed = input.readLine("Enter dog breed");
        }

        int age = input.readInt("Enter dog's age:");
        int weight = input.readInt("Enter dog's weight:");

        Dog dog = new Dog(dogName, breed, age, weight);
        if (dogCollection.addDog(dog)) {
            System.out.println("The dog " + dogName + " has been added to the register.\n");
        } else {
            System.out.println("Error! A dog with that name is already registered.");
        }
    }

    private void removeDog() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        }

        String dogName = input.readLine("Enter dog name").trim();
        if (!dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
        } else {
            Dog dog = dogCollection.getDog(dogName);
            if (dog != null && dog.getOwner() != null) {
                dog.getOwner().removeDog(dog);
                dog.setOwner(null);
            }
            dogCollection.removeDog(dogName);
            System.out.println("The dog " + dogName + " has been removed from the register");
        }

    }

    private void listDogs() {
        ArrayList<Dog> dogs = dogCollection.getDogs();
        if (dogs.isEmpty()) {
            System.out.println("Error! No dogs registered.");
            return;
        }
        double minTailLength = input.readDouble("Enter the minimum tail length");
        dogs = dogCollection.getDogsByTailLength(minTailLength);

        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }

    private void listOwners() {
        ArrayList<Owner> owners = ownerCollection.getOwners();
        if (owners.isEmpty()) {
            System.out.println("Error: No owners in register");
            return;
        }
        for (Owner owner : owners) {
            String dogsOwned = "";
            for (Dog dog : owner.getDogs()) {
                dogsOwned += dog.getName() + " ";
            }
            System.out.println(owner.getName() + " " + dogsOwned);
        }
    }

    private void increaseAge() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
            return;
        }
        String dogName = input.readLine("Enter dog name").trim();

        Dog dogToIncreaseAge = null;
        for (Dog dog : dogCollection.getDogs()) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                dogToIncreaseAge = dog;
                break;
            }
        }
        if (dogToIncreaseAge == null) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
            return;
        }
        dogToIncreaseAge.increaseAge(1);
        System.out.println("The dog " + dogName + " is now one year older");
    }

    private void giveDogToOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error! No dogs registered");
            return;
        }

        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error! No owners registered");
            return;
        }
        Dog dog = dogCollection.getDog(input.readLine("Enter the name of the dog to give").trim());
        if (dog == null) {
            System.out.println("Error! No dog with that name found.");
            return;
        }
        if (dog.getOwner() != null) {
            System.out.println("Error! This dog is already owned by " + dog.getOwner().getName() + ".");
            return;
        }
        Owner owner = ownerCollection.getOwner(input.readLine("Enter the name of the owner").trim());
        if (owner == null) {
            System.out.println("Error! No owner with that name found.");
            return;
        }
        dog.setOwner(owner);
        owner.addDog(dog);
        System.out.println("Dog " + dog.getName() + " given to owner " + owner.getName() + " successfully.");
    }

    private void removeDogFromOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error! No dogs registered.");
            return;
        }
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error! No owners registered.");
            return;
        }
        String dogName = input.readLine("Enter the name of the dog to remove from its owner").trim();
        Dog dog = dogCollection.getDog(dogName);

        if (dog == null) {
            System.out.println("Error, No dog with that name found");
            return;
        }
        Owner owner = dog.getOwner();
        if (owner != null) {
            owner.removeDog(dog);
            dog.setOwner(null);
            System.out.println(dog.getName() + " has been removed from its owner.");
        } else {
            System.out.println("Error! The dog does not have an owner.");
        }
    }
}
