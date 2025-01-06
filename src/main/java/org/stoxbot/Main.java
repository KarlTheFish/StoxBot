package org.stoxbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.stoxbot.commands.MainCommand;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create(Environment.getDiscordToken());

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
                    //Readyevent
                    Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->
                            Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as: %s%n", self.getUsername());
                            }))
                            .then();

                    Mono<Void> handleCommand = gateway.on(MessageCreateEvent.class, event -> {
                        Message message = event.getMessage();

                        if (message.getContent().startsWith("!stox ")) {
                            MainCommand evokedCommand = new MainCommand("Karl", message);
                            return evokedCommand.EvokeCommand(message);
                        } else {
                            return Mono.empty();
                        }
                    }).then();

                    return printOnLogin.and(handleCommand);
                }
        );
        login.block();
    }
}