package com.mycompany.app;

/**
 * change the code here.
 */
class Shell {
    Shell cd(final String path) {
        return this;
    }

    public String path() {
        return "/";
    }
}

public class Directory {
    public static void main(String[] args) {

        System.out.println("Hello the first line in main");
        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }
}