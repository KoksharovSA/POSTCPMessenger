package ru.konsist;

import ru.konsist.views.IViewMenu;
import ru.konsist.views.ViewConsoleMenu;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        IViewMenu viewMenu = new ViewConsoleMenu();
        viewMenu.viewRun();
    }
}
