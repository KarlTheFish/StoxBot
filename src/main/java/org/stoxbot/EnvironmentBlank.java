package org.stoxbot;

// Rename this file to "Environment" when running your own bots

public class EnvironmentBlank {
    // Environment variables
    private static String DiscordToken = "YOUR DISCORD TOKEN HERE";
    private static String FinnhubAPIkey = "YOUR FINNHUB.IO API KEY HERE";

    public static String getDiscordToken() {
        return DiscordToken;
    }

    public static String getFinnhubAPIKey() { return FinnhubAPIkey; }
}
