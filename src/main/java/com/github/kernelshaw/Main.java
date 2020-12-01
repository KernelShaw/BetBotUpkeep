package com.github.kernelshaw;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.io.File;

public class Main implements java.io.Serializable {

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder().setToken("NDcwMTk1NDQwNTQxODkyNjI4.DjSvbA.slKl6rn8u-Ux1yZ3VKVjMTsZB_A").login().join();
        System.out.println("Logged in!");

        System.out.println("You can invite me by using the following url: " + api.createBotInvite());

        Gamblers.loadGamblers();
        Epic.loadE();
        Rule34 botChan = new Rule34();

        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().contains("!chicken") && Gamblers.getBetBool()) {
                Gamblers.pussyOut();
                message.getChannel().sendMessage("Bet rendered void by " + message.getUserAuthor().get().getName() + ".");
                message.getChannel().sendMessage(new File(Gamblers.pathetic()));
            }
            else if (message.getContent().contains("!bet") && message.getMentionedUsers().size() == 0) {
                event.getChannel().sendMessage("You dumb fucking piece of shit, who are you betting against?");
            }
            else if (message.getContent().contains("good girl") && message.getMentionedUsers().get(0).isBot()) {
                if (message.getMentionedUsers().get(0).isYourself()){
                    event.getChannel().sendMessage("Hehe, thank yoooooou~");
                }
                else{
                    event.getChannel().sendMessage("Heyyyyy, what about me? =3=");
                }
            }
            else if (message.getContent().contains("10/10")){
                message.getChannel().sendMessage("I personally would rate it 9/11 desu~");
            }
            else if (message.getContent().contains("ezpz") || message.getContent().contains("ez pz")
                    || message.getContent().contains("easy peasy")){
                message.getChannel().sendMessage("lemon squeezy");
            }
            else if (message.getContent().contains("!gamestatus")) {
                message.getChannel().sendMessage((Gamblers.getBet()));
            }
            else if (message.getContent().contains("+E")) {
                Epic.addCount();
                message.getChannel().sendMessage("One (1) \"Epic\" added.");
            }
            else if (message.getContent().contains("-E")) {
                Epic.decCount();
                message.getChannel().sendMessage("One (1) \"Epic\" removed.");
            }
            else if (message.getContent().contains("?E")) {
                message.getChannel().sendMessage("Jeric has said some variation of \"Epic\" " + Epic.getCount() + " times.");
            }
            else if (message.getContent().contains("!stats") && message.getMentionedUsers().size() == 1) {
                if (Gamblers.findGambler(message.getUserAuthor().get().getId())){
                    message.getChannel().sendMessage((Gamblers.getGambler(message.getMentionedUsers().get(0).getId()).toString()));
                }
                else {
                    message.getChannel().sendMessage("This user is not part of the playerbase. " +
                            "Please add them if they so wish.");
                }
            }
            else if (message.getContent().contains("!bet") && Gamblers.findGambler(message.getUserAuthor().get().getId())) {
                if (message.getMentionedUsers().size() == 1 && !Gamblers.getBetBool()) {
                    event.getChannel().sendMessage(Gamblers.startBet(new Bet(message.getUserAuthor().get(), message.getMentionedUsers().get(0))));
                }
                else if (message.getMentionedUsers().size() == 1 && Gamblers.getBetBool()) {
                    event.getChannel().sendMessage("There is a bet going on! Please be patient.");
                }
            }
            else if (message.getContent().contains("!win")) {
                Gamblers.verdict(message.getUserAuthor().get());
                event.getChannel().sendMessage(message.getUserAuthor().get().getName() + " wins!");
            }
            else if (message.getContent().contains("!bet") && !Gamblers.findGambler(message.getMentionedUsers().get(0).getId())) {
                Gamblers.addGambler(new Gambler(message.getMentionedUsers().get(0).getName(), message.getMentionedUsers().get(0)));
                if (message.getMentionedUsers().size() == 1 && !Gamblers.getBetBool()) {
                    event.getChannel().sendMessage(Gamblers.startBet(new Bet(message.getUserAuthor().get(), message.getMentionedUsers().get(0))));
                }
                else if (message.getMentionedUsers().size() == 1 && Gamblers.getBetBool()) {
                    event.getChannel().sendMessage("There is a bet going on! Please be patient.");
                }
            }
            else if (message.getContent().contains("!roster")) {
                event.getChannel().sendMessage("Current list of players: " + Gamblers.gamblerListString());
            }
            else if (message.getContent().contains("!player")) {
                User temp = message.getMentionedUsers().get(0);
                if (Gamblers.findGambler(temp.getId())) {
                    event.getChannel().sendMessage("Already in the roster, you braindead, scumfuck, " +
                            "degenerate pile of trash.");
                } else {
                    Gamblers.addGambler(new Gambler(temp.getName(), temp));
                    event.getChannel().sendMessage("Added!");
                    event.getChannel().sendMessage("Current list of players: " + Gamblers.gamblerListString());
                }
            }
        });
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            String content = message.getContent();
            if (content.contains("james")) {
                message.getChannel().sendMessage("This is a Christian server and making fun of <@!279820067229925378> " +
                        "with this bot is encouraged.");
            }
            if (content.contains("trap")) {
                message.getChannel().sendMessage("Did you mean: *sam* ?");
            }

        });
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().contains("jeric")) {
                message.getChannel().sendMessage("*Jeric. Please capitalize the name of our Supreme Leader, " +
                        "Marshal of the Republic, speed-boi, Jeric Tianco.");
            }
        });
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().length() > 3){
                int resin = dadBot(message.getContent());
                if (resin >= 1){
                    message.getChannel().sendMessage("Hi " +
                            message.getContent().substring(2 + resin) + ", I'm Dad!");
                }
            }

        });
        api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            if (message.getContent().contains("-r34")){
                if (message.getServerTextChannel().get().isNsfw()) {
                    String chopped = message.getContent().substring(5);
                    message.getChannel().sendMessage(botChan.returnURL(chopped));
                }
                else{
                    message.getChannel().sendMessage("Honey, this kind of stuff belongs in the NSFW channel...");
                }
            }
        });
    }

    public static boolean samChecker(String message) {
        if (message.length() == 3){
            return true;
        }

        int count = instances(message);
        int pos = 0;

        for (int i = 0; i < count; i++){
            pos = message.indexOf("sam", pos);
            if (pos + 3 >= message.length()){
                return true;
            }
            else if (message.charAt(pos + 3) == ' '){
                return true;
            }
            pos++;
        }

        return false;
    }

    public static int instances(String toCheck){
        int cur = 0;
        int total = 0;
        while (toCheck.indexOf("sam", cur) != -1){
            total++;
            cur = toCheck.indexOf("sam", cur) + 1;
        }
        return total;
    }

    public static int dadBot(String check){
        if (check.substring(0, 2).equalsIgnoreCase("im")){
            return 1;
        }
        else if (check.substring(0, 3).equalsIgnoreCase("i'm")){
            return 2;
        }
        else {
            return 0;
        }
    }
}


