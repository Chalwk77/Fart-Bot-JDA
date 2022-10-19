package com.chalwk.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Authentication {

    /**
     * Gets the bot token from the environment variables:
     *
     * @return The bot token.
     */
    public static String getToken() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("auth.token"));
        return text.readLine();
    }
}
