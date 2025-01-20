package org.stoxbot.commands;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import static org.stoxbot.Main.subcommandTool;

public class MainCommand {
    //General command class
    String userName;
    Message userMessage;
    String userMessageString;
    String command;
    String[] commandStringList;
    String botMSG;
    SubcommandStatus subCommandStatus;

    public MainCommand(String userName, Message userMessage) {
        this.userName = userName;
        this.userMessage = userMessage;
        this.userMessageString = userMessage.getContent().toLowerCase();
    }

    public Mono<Message> EvokeCommand(Message userMessage) {
        command = userMessageString.replaceAll("!stox ", "");
        commandStringList = command.split(" ");
        //See if we can perform a subcommand or not
        subCommandStatus = subcommandTool.getStatus();
        switch (commandStringList[0]){
            //All the case checks need to be lowercase bc I converted the entire command to lowercase earlier
            case "help":
                HelpCommand helpCmd = new HelpCommand();
                botMSG = helpCmd.Command();
                break;
            case "pricenow":
                PriceCommand priceCmd = new PriceCommand();
                botMSG = priceCmd.Command(commandStringList[1], "current");
                break;
            case "lowprice":
                PriceCommand lowPriceCmd = new PriceCommand();
                botMSG = lowPriceCmd.Command(commandStringList[1], "lowest");
                break;
            case "highprice":
                PriceCommand highPriceCmd = new PriceCommand();
                botMSG = highPriceCmd.Command(commandStringList[1], "highest");
                break;
            case "search":
                SymbolCommand symbolCmd = new SymbolCommand();
                botMSG = symbolCmd.Command(commandStringList[1]);
                break;
            case "status":
                botMSG = subCommandStatus.toString();
                break;
            default:
                botMSG = "what";
                break;
        }

        return userMessage.getChannel().flatMap(channel -> channel.createMessage(botMSG));
    }

    public void setSubCommandStatus(SubcommandStatus newSubCommandStatus) {
        subCommandStatus = newSubCommandStatus;
    }
}
