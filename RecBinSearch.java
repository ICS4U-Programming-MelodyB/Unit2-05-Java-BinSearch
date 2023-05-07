import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program uses recursion to look
 * for a specific number in a list.
 *
 * @author  Melody Berhane
 * @version 1.0
 *
 * @since 2023-05-04.
 */

public final class RecBinSearch {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private RecBinSearch() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Pass path to file as parameter.
        final File file = new File("input.txt");

        // Create a list of strings.
        final List<String> listOfStrings =
            new ArrayList<String>();

        // Declare variable
        String inputString;

        // Usage of try catch to detect error.
        try {
            // Create FileWriter object to write to file.
            final FileWriter fileWrite = new FileWriter("output.txt");
            // Create Scanner object to read from file.
            final Scanner sc = new Scanner(file);
            // Create PrintWriter object to write to file.
            final PrintWriter write = new PrintWriter(fileWrite);

            while (sc.hasNextLine()) {
                // Read line as string.
                inputString = sc.nextLine();
                // Add to list.
                listOfStrings.add(inputString);
            }

            // Convert from list to array.
            final String[] arrayOfStr = listOfStrings.toArray(new String[0]);

            // Convert all elements in array to integers.
            for (int counter = 0; counter < arrayOfStr.length;
                    counter = counter + 2) {
                final String[] inputNum = arrayOfStr[counter].split(" ");
                final int[] arrayNum = new int[inputNum.length];
                for (int counter2 = 0; counter2 < inputNum.length; counter2++) {
                    arrayNum[counter2] = Integer.parseInt(inputNum[counter2]);

                }
                // Sort the array.
                java.util.Arrays.sort(arrayNum);

                // Read the number to be searched for
                final int numSearch =
                    Integer.parseInt(arrayOfStr[counter + 1].trim());

                // Call function to find the number.
                final int recSearch = recBinSearch(arrayNum, numSearch,
                    0, arrayNum.length - 1);

                // Display to console & write to file.
                write.println(recSearch);
            }

            // Closes scanner & writer.
            write.close();
            sc.close();

        } catch (IOException error) {
            // Displays error to user.
            System.out.println("An error occurred: "
                    + error.getMessage());
        }
    }

    /**
    * This function uses recursion to
    * find the search of number.
    *
    * @param listNum passed
    * @param searchNum passed.
    * @param left passed.
    * @param right passed.
    * @return recBinSearch.
    */
    public static int recBinSearch(int[] listNum, int searchNum,
            int left, int right) {
        if (left > right) {
            // Return -1 which mean the number
            // in search is not in the list.
            return -1;
        }

        // Declare variable and calculate mid.
        final int mid = (left + right) / 2;

        // If element found, return mid.
        if (listNum[mid] == searchNum) {
            return mid;
        } else if (searchNum < listNum[mid]) {
            // Calls function recursively, searching left side.
            return recBinSearch(listNum, searchNum, left, mid - 1);
        } else {
            // Calls function recursively, searching the right side.
            return recBinSearch(listNum, searchNum, mid + 1, right);
        }
    }
}
