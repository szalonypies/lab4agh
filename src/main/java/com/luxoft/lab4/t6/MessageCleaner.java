package com.luxoft.lab4.t6;


/**
 * Created by aniamamam on 2014-04-23.
 */
public class MessageCleaner {
    private MessagesIds messageIds;
    private MessageDbStore messageDbStore;

    public void cleanup() {
        for (int messageId : messageIds) {
            performCleanup(messageId);
        }
    }

    private void performCleanup(int messageId) {
        messageDbStore.cleanupMessage(messageId);
    }

    public void setMessageIds(MessagesIds messageIds) {
        this.messageIds = messageIds;
    }

    public void setMessageDbStore(MessageDbStore messageStore) {
        this.messageDbStore = messageStore;
    }

}
