package com.infoshare.drinkywinky.searchby;

import com.infoshare.drinkywinky.menu.Menu;
import com.infoshare.drinkywinky.repositories.Repository;
import com.infoshare.drinkywinky.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.infoshare.drinkywinky.menu.Menu.SCANNER;

public class ShowByCategory {
        private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
        public static final int NUMBER_OF_CATEGORIES_BY_PAGE = 7;
        public static final String MENU_BUILDER = "│                                          │\n";
        public static final int MENU_WIDTH_1 = 37;
        public static final int MENU_WIDTH_2 = 33;
        private int pageNumber = 0;
        private String in;
        private int numberOfPages;
        private List<String> alphabeticalList;
        private int trigger;
        private Set<String> currentDefaultListOfDrinks;

        public void alphabeticalScrollingMenu() {
            currentDefaultListOfDrinks = Utils.getCategoryofAllDrink();
            countNumberOfMenuPages();
            toAlphabeticalList();

            do {
                STDOUT.info("\n┌──────────────────────────────────────────┐\n");
                STDOUT.info("│\u001b[33mCHOOSE NUMBER OF CATEGORY OR OTHER OPTION \u001b[0m│\n");
                STDOUT.info(MENU_BUILDER);
                fillingMenuByCategories();
                STDOUT.info(MENU_BUILDER);
                showPageChangeArrows();
                STDOUT.info(MENU_BUILDER);
                STDOUT.info(MENU_BUILDER);
                STDOUT.info("│    Press \u001b[33mX\u001b[0m to return to the MAIN MENU    │\n");
                STDOUT.info("└──────────────────────────────────────────┘\n\n");

                STDOUT.info("\u001b[33mYOUR CHOICE: \u001b[0m");

                //TODO NULL POINTER EXCEPTION BY INPUT WRONG LETTER
                chooseTheOption();

            } while (true);
        }

        private void showPageChangeArrows() {
            STDOUT.info("│                Page {}/{}                  │\n", pageNumber + 1, numberOfPages);
            if (numberOfPages > 2 && pageNumber != 0 && pageNumber != numberOfPages - 1) {
                STDOUT.info("│   \u001b[33mP\u001b[0m <- Previous page    Next page -> \u001b[33mN\u001b[0m   │\n");
            }
            if (pageNumber == numberOfPages - 1 && numberOfPages > 1) {
                STDOUT.info("│   \u001b[33mP\u001b[0m <- Previous page                     │\n");
            }
            if (pageNumber == 0 && numberOfPages > 1) {
                STDOUT.info("│                         Next page -> \u001b[33mN\u001b[0m   │\n");
            }
        }

        private void fillingMenuByCategories() {
            for (int i = (1 + pageNumber * NUMBER_OF_CATEGORIES_BY_PAGE); i <= (NUMBER_OF_CATEGORIES_BY_PAGE + (pageNumber * NUMBER_OF_CATEGORIES_BY_PAGE)); i++) {
                if (i <= currentDefaultListOfDrinks.size()) {
                    int numberOfSpaces = MENU_WIDTH_1 - Integer.toString(i).length() - alphabeticalList.get(i - 1).length();
                    String whitespace = String.format("%1$" + numberOfSpaces + "s", "");
                    STDOUT.info("│   \u001b[33m{}.\u001b[0m {}{}│\n", i, alphabeticalList.get(i - 1), whitespace);
                } else {
                    int numberOfSpaces2 = MENU_WIDTH_2 - Integer.toString(i).length();
                    String whitespace = String.format("%1$" + numberOfSpaces2 + "s", "");
                    STDOUT.info("│   \u001b[33m{}.\u001b[0m --- {}│\n", i, whitespace);
                }
            }
        }

        private void countNumberOfMenuPages() {
            numberOfPages = currentDefaultListOfDrinks.size() / NUMBER_OF_CATEGORIES_BY_PAGE;
            if (currentDefaultListOfDrinks.size() % NUMBER_OF_CATEGORIES_BY_PAGE != 0) {
                numberOfPages = currentDefaultListOfDrinks.size() / NUMBER_OF_CATEGORIES_BY_PAGE + 1;
            }
        }

        private void toAlphabeticalList() {
            alphabeticalList = currentDefaultListOfDrinks.stream().sorted().collect(Collectors.toList());
        }

        private void chooseTheOption() {
            in = SCANNER.next();
            trigger = 1;
            changePageOfMenu();
            quitToMainMenu();
            if (trigger == 1) {
                chooseSpecificCategory();
            }
        }

        private void changePageOfMenu() {
            if (in.equalsIgnoreCase("N")) {
                if (pageNumber == numberOfPages - 1) {
                    STDOUT.info("\n\u001b[31m It's LAST page, you cannot move forward!\u001b[0m\n");
                } else if (pageNumber < numberOfPages - 1) {
                    pageNumber++;
                }
                trigger = 0;
            }

            if (in.equalsIgnoreCase("P")) {
                if (pageNumber == 0) {
                    STDOUT.info("\n\u001b[31m It's FIRST page, you cannot move back!\u001b[0m\n");
                } else if (pageNumber > 0) {
                    pageNumber--;
                }
                trigger = 0;
            }
        }

        private void quitToMainMenu() {
            if (in.equalsIgnoreCase("X")) {
                trigger = 0;
                Menu.mainMenu();
            }
        }

        private void chooseSpecificCategory() {
            if ((Integer.parseInt(in) >= (1 + pageNumber * NUMBER_OF_CATEGORIES_BY_PAGE))
                    && (Integer.parseInt(in) <=
                    (alphabeticalList.size()))) {

                String s = String.valueOf(Repository.getInstance().getDrinkByCategories(alphabeticalList.get(Integer.parseInt(in) - 1)));
                STDOUT.info(s);

            }
        }

        public static void main(String[] args) {
            ShowByCategory list = new ShowByCategory();
            list.alphabeticalScrollingMenu();
        }
    }

