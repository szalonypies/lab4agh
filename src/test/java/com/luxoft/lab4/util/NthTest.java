package com.luxoft.lab4.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class NthTest  {
    @Test
    public void nthTest() {
        assertEquals("1st", Nth.of(1));
        assertEquals("2nd", Nth.of(2));
        assertEquals("3rd", Nth.of(3));
        assertEquals("4th", Nth.of(4));
        assertEquals("5th", Nth.of(5));
        assertEquals("6th", Nth.of(6));
        assertEquals("7th", Nth.of(7));
        assertEquals("8th", Nth.of(8));
        assertEquals("9th", Nth.of(9));
        assertEquals("11th", Nth.of(11));
        assertEquals("12th", Nth.of(12));
        assertEquals("13th", Nth.of(13));
        assertEquals("14th", Nth.of(14));
        assertEquals("15th", Nth.of(15));
        assertEquals("16th", Nth.of(16));
        assertEquals("17th", Nth.of(17));
        assertEquals("18th", Nth.of(18));
        assertEquals("19th", Nth.of(19));
        assertEquals("21st", Nth.of(21));
        assertEquals("22nd", Nth.of(22));
        assertEquals("23rd", Nth.of(23));
        assertEquals("24th", Nth.of(24));
        assertEquals("25th", Nth.of(25));
        assertEquals("26th", Nth.of(26));
        assertEquals("27th", Nth.of(27));
        assertEquals("28th", Nth.of(28));
        assertEquals("29th", Nth.of(29));

        assertEquals("101st", Nth.of(101));
        assertEquals("102nd", Nth.of(102));
        assertEquals("103rd", Nth.of(103));
        assertEquals("104th", Nth.of(104));
        assertEquals("105th", Nth.of(105));
        assertEquals("106th", Nth.of(106));
        assertEquals("107th", Nth.of(107));
        assertEquals("108th", Nth.of(108));
        assertEquals("109th", Nth.of(109));
        assertEquals("111th", Nth.of(111));
        assertEquals("112th", Nth.of(112));
        assertEquals("113th", Nth.of(113));
        assertEquals("114th", Nth.of(114));
        assertEquals("115th", Nth.of(115));
        assertEquals("116th", Nth.of(116));
        assertEquals("117th", Nth.of(117));
        assertEquals("118th", Nth.of(118));
        assertEquals("119th", Nth.of(119));
        assertEquals("121st", Nth.of(121));
        assertEquals("122nd", Nth.of(122));
        assertEquals("123rd", Nth.of(123));
        assertEquals("124th", Nth.of(124));
        assertEquals("125th", Nth.of(125));
        assertEquals("126th", Nth.of(126));
        assertEquals("127th", Nth.of(127));
        assertEquals("128th", Nth.of(128));
        assertEquals("129th", Nth.of(129));

    }
}
