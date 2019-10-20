package com.luxoft.lab4.t5;

/**
 * Created by aniamamam on 2014-04-30.
 */
public class Application {

    private String name;

    public Application(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Application(" + name + ")";
    }
}
