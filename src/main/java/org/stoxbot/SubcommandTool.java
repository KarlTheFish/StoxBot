package org.stoxbot;

import org.stoxbot.commands.SubcommandStatus;

public class SubcommandTool {
    //Class to basically tell the main command class, "Hey, you can do this subcommand now!" after commands telling this class, "Hey, we can do subcommands now"
    //Probably objectively a bad way to do this, but I did not feel like rewriting the entire code base for this
    //Will make it better, someday, hopefully.
    private SubcommandStatus status;

    public SubcommandTool(){
        status = SubcommandStatus.NONE;
    }

    public void setStatus(SubcommandStatus status) {
        this.status = status;
    }

    public SubcommandStatus getStatus() {
        return status;
    }
}
