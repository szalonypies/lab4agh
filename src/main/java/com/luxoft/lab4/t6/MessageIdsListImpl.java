package com.luxoft.lab4.t6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by aniamamam on 2014-04-23.
 */
public class MessageIdsListImpl implements MessagesIds {
    private Logger logger = LoggerFactory.getLogger(MessageIdsListImpl.class);

    private List<Integer> messageIds = new LinkedList<>();

    public void addId(int id) {
        messageIds.add(id);
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < messageIds.size();
            }

            @Override
            public Integer next() {
                Integer messageId = messageIds.get(pos++);
                if (logger.isDebugEnabled()) {
                    logger.debug("Next messageId=" + messageId);
                }
                return messageId;
            }
        };
    }
}
