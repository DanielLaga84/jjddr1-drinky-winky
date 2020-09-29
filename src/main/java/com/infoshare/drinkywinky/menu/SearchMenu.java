package com.infoshare.drinkywinky.menu;

import com.infoshare.drinkywinky.searchby.ListOfDrinks;
import com.infoshare.drinkywinky.searchby.ShowByCategory;
import com.infoshare.drinkywinky.searchby.ShowByIngredients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SearchMenu {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void secondMenu() {
        int mainExitCode = 0;

        while ( mainExitCode != 4 ) {

            STDOUT.info("┌──────────────────────────────────────────┐\n");
            STDOUT.info("│  \u001b[101m      CHOOSE OPTION FROM MENU       \u001b[0m   │\n");
            STDOUT.info("│                                          │\n");
            STDOUT.info("│   1. Search for a drink by name          │\n");
            STDOUT.info("│   2. Search for a drink by ingredient    │\n");
            STDOUT.info("│   3. Search for a drink by category      │\n");
            STDOUT.info("│                                          │\n");
            STDOUT.info("│  \u001b[36m 4. TO MAIN MENU                   \u001b[0m  │\n");
            STDOUT.info("└──────────────────────────────────────────┘\n");
            STDOUT.info("\n");
            STDOUT.info("CHOOSE NUMBER: ");

            switch (ChoiceMenu.choiceMenu()) {
                case 1:
                    STDOUT.info(" CHOSEN : 1. Search for a drink by name  \n");
                    new ListOfDrinks().alphabeticalScrollingMenu();
                    break;
                case 2:
                    STDOUT.info(" CHOSEN : 2. Search for a drink by ingredient  \n");
                    new ShowByIngredients().alphabeticalScrollingMenu();
                    break;
                case 3:
                    STDOUT.info(" CHOSEN : 3. Search for a drink by category  \n");
                    new ShowByCategory().alphabeticalScrollingMenu();
                    break;
                case 4:
                    STDOUT.info(" Welcome to main menu \n");
                    Menu.mainMenu();

                    mainExitCode = 4;
                    break;
                default:
                    STDOUT.info("\n");
                    STDOUT.info("┌──────────────────────────────────────────┐\n");
                    STDOUT.info("│                                          │\n");
                    STDOUT.info("│       \u001b[31mYOU HAVE ENTERED WRONG NUMBER! \u001b[0m    │\n");
                    STDOUT.info("│                                          │\n");
                    STDOUT.info("│               TRY AGAIN!                 │\n");
                    STDOUT.info("│                                          │\n");
                    STDOUT.info("└──────────────────────────────────────────┘\n");
                    STDOUT.info("\n");
                    break;
            }
        }
    }
}