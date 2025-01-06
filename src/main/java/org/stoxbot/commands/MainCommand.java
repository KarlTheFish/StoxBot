package org.stoxbot.commands;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public class MainCommand {
    //General command class
    String userName;
    Message userMessage;
    String userMessageString;
    String command;
    String[] commandStringList;
    String botMSG;

    public MainCommand(String userName, Message userMessage) {
        this.userName = userName;
        this.userMessage = userMessage;
        this.userMessageString = userMessage.getContent().toLowerCase();
    }

    public Mono<Message> EvokeCommand(Message userMessage){
        command = userMessageString.replaceAll("!stox ", "");
        commandStringList = command.split(" ");
        switch (commandStringList[0]){
            //All the case checks need to be lowercase bc I converted the entire command to lowercase earlier
            case "help":
                HelpRequestCommand helpCmd = new HelpRequestCommand();
                botMSG = helpCmd.Command();
                break;
            case "pricenow":
                PriceRequestCommand priceCmd = new PriceRequestCommand();
                botMSG = priceCmd.Command(commandStringList[1], "current");
                break;
            case "lowprice":
                PriceRequestCommand lowPriceCmd = new PriceRequestCommand();
                botMSG = lowPriceCmd.Command(commandStringList[1], "lowest");
                break;
            case "highprice":
                PriceRequestCommand highPriceCmd = new PriceRequestCommand();
                botMSG = highPriceCmd.Command(commandStringList[1], "highest");
                break;
            case "symbol":
            default:
                botMSG = "what";
                break;
        }
        return userMessage.getChannel().flatMap(channel -> channel.createMessage(botMSG));
    }
}
