package com.jokes;

import java.util.ArrayList;
import java.util.Random;

public class JokesProvider {
    ArrayList<String> jokes = new <String> ArrayList();

    public JokesProvider(){
        jokes.add("Why do hens lay eggs? Because if they were throwing them, they’d break!\n");
        jokes.add("What is the strongest creature in the world? The snail. It carries its whole house on its back.\n");
        jokes.add("How does a celebrity stay cool? \n" +
                "-\n" +
                "A: By keeping close to his fans.\n" +
                "\n");
        jokes.add("Where do pencils spend their vacations? \n" +
                "-\n" +
                "In Pencilvania.\n|");
        jokes.add("What did 0 say to 8? \n" +
                "-\n" +
                "Hey, nice belt!");
    }
    public String getJoke(){
       // Random ran = new Random(jokes.size());
        Random rand = new Random();

        int  n = rand.nextInt(jokes.size()-1);
        return jokes.get(n);

    }
    public static void main(){
        JokesProvider provier = new JokesProvider();
        provier.getJoke();
    }
}
