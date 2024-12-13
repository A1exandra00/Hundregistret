//Alexandra Bjelica albj3795

import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

public class InputReader {

    private static ArrayList<InputStream> usedStreams = new ArrayList<>();
    private Scanner input;

    public InputReader() {
        this(System.in);
    }

    public InputReader(InputStream inputStream) {
        if (usedStreams.contains(inputStream)) {
            throw new IllegalStateException("Har redan inläsningstjänst!");
        } else {
            usedStreams.add(inputStream);
        }
        this.input = new Scanner(inputStream);
    }

    public int readInt(String prompt) {
        System.out.println(prompt + "?> ");
        int result = input.nextInt();
        input.nextLine();
        return result;
    }

    public double readDouble(String prompt){
        System.out.println(prompt + "?> ");
        double result = input.nextDouble();
        input.nextLine();
        return result;
    }

    public String readLine (String prompt){
        System.out.println(prompt + "?> ");
        return input.nextLine();
    }
}


