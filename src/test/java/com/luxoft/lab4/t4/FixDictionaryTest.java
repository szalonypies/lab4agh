package com.luxoft.lab4.t4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by aniamamam on 2014-06-05.
 */
public class FixDictionaryTest {

    private FixDictionary fixDictionary;

    @Test
    public void testGetStore() throws Exception {
        assertNull(fixDictionary.getValueForKey("PossDupFlag"));
        assertEquals(35, fixDictionary.getValueForKey("MsgType"));
        assertEquals(49, fixDictionary.getValueForKey("SenderCompID"));
        assertEquals(56, fixDictionary.getValueForKey("TargetCompID"));

    }

    @Before
    public void prepareDictionary() {
        fixDictionary = new FixDictionary();
        fixDictionary.values.put("MsgType", 35);
        fixDictionary.values.put("SenderCompID", 49);
        fixDictionary.values.put("TargetCompID", 56);
    }
}
