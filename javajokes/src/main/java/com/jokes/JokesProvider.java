package com.jokes;

import java.util.ArrayList;
import java.util.Random;

public class JokesProvider {
    ArrayList<String> jokes = new <String> ArrayList();

    public JokesProvider(){
        jokes.add("Joke1");
        jokes.add("Joke2");
        jokes.add("Joke3");
        jokes.add("Joke4");
        jokes.add("Joke5");
    }
    public String getJoke(){
        Random ran = new Random(jokes.size());
        Random rand = new Random();

        int  n = rand.nextInt(jokes.size()) + 1;
        return jokes.get(n);

    }
    public static void main(){
        JokesProvider provier = new JokesProvider();
        provier.getJoke();
    }
}
