package org.stoxbot;

import org.stoxbot.commands.SubcommandStatus;

public class SubcommandTool {
    //Class to basically tell the main command class, "Hey, you can do this subcommand now!" after commands telling this class, "Hey, we can do subcommands now"
    //Probably objectively a bad way to do this, but I did not feel like rewriting the entire code base for this
    //I will make it better, someday, hopefully.

    //Singleton class that is supposed to be accessible from anywhere else
    private static SubcommandTool instance = null;
    public static SubcommandTool getInstance() {
        if (instance == null){
            instance = new SubcommandTool();
        }
        return instance;
    }

    private Object SubcommandParentObject;

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

    public void setParentObject(Object parentObject) {
        SubcommandParentObject = parentObject;
    }

    public Object getParentObject() {
        return SubcommandParentObject;
    }

    public void clearStatus(){
        status = SubcommandStatus.NONE;
        SubcommandParentObject = null;
    }

}
