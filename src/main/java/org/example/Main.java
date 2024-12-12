package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Log log = LogFactory.getLog(Main.class);
        ArrayList<Joke> jokesList = new ArrayList<Joke>();

        for (int i = 0; i < 10; i++) {
            String json = new RestTemplate().getForObject(
                    "https://official-joke-api.appspot.com/jokes/random", String.class
            );

            JsonObject response = JsonParser.parseString(json).getAsJsonObject();
            String initial = response.get("setup").getAsString();
            String reply = response.get("punchline").getAsString();
            Joke joke = new Joke(initial, reply);
            jokesList.add(joke);
            log.info("JOKE NO. " + (i+1));
            log.info(joke);
        }

        System.out.println("The 5th joke is: " + jokesList.get(4));

    }
}
