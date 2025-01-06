package org.stoxbot.commands;

public class HelpCommand {

    public String Command() {
        String botMSG = "StoxBot commands:\n";
        botMSG += "**!stox help** - displays this help message\n";
        botMSG += "**!stox pricenow SYMBOL** - displays the current price of the stock\n";
        botMSG += "**!stox highPrice SYMBOL** - displays the highest price of the stock today\n";
        botMSG += "**!stox lowPrice SYMBOL** - displays the lowest price of the stock today\n";
        botMSG += "**!stox getSymbol NAME** - get the symbol for the company\n";
        botMSG += "**!stox getName SYMBOL** - get the name of the symbol\n";
        return botMSG;
    }
}
