package tictactoe;

import java.util.*;

public class Main {

    public static boolean checkImpossibleFields(String inputCells, String[][] matrix) {

        boolean isImpossible = false;

        ArrayList<String> elementsOfCell = new ArrayList<>(Arrays.asList(inputCells.split("")));

        if (Math.max(Collections.frequency(elementsOfCell, "X"), Collections.frequency(elementsOfCell, "O")) -
                Math.min(Collections.frequency(elementsOfCell, "X"), Collections.frequency(elementsOfCell, "O")) >= 2 || (
                checkIfXWins(matrix) && checkIfOWins(matrix))) {
            isImpossible = true;
        }
        return isImpossible;
    }


    public static boolean checkIfOWins(String[][] matrixOfTicTacToe) {

        boolean isOWin = false;

        int countOHorizontally = 0;
        int countOVertically = 0;

        if (("O".equals(matrixOfTicTacToe[0][1]) && "O".equals(matrixOfTicTacToe[1][2]) && "O".equals(matrixOfTicTacToe[2][3]))
                || ("O".equals(matrixOfTicTacToe[2][1]) && "O".equals(matrixOfTicTacToe[1][2]) && "O".equals(matrixOfTicTacToe[0][3]))) {

            isOWin = true;

        } else {

            for (int i = 0; i < 3; i++) {

                for (int j = 1; j < 4; j++) {

                    if ("O".equals(matrixOfTicTacToe[i][j])) {
                        countOHorizontally++;
                    }

                    if ("O".equals(matrixOfTicTacToe[j - 1][i + 1])) {
                        countOVertically++;
                    }

                    if (countOHorizontally == 3 || countOVertically == 3) {
                        isOWin = true;
                        break;
                    }

                }

                countOHorizontally = 0;
                countOVertically = 0;

            }

        }

        return isOWin;
    }


    public static boolean checkIfXWins(String[][] matrixOfTicTacToe) {

        boolean isXWin = false;

        int countXHorizontally = 0;
        int countXVertically = 0;

        if (("X".equals(matrixOfTicTacToe[0][1]) && "X".equals(matrixOfTicTacToe[1][2]) && "X".equals(matrixOfTicTacToe[2][3]))
                || ("X".equals(matrixOfTicTacToe[2][1]) && "X".equals(matrixOfTicTacToe[1][2]) && "X".equals(matrixOfTicTacToe[0][3]))) {

            isXWin = true;

        } else {

            for (int i = 0; i < 3; i++) {

                for (int j = 1; j < 4; j++) {

                    if ("X".equals(matrixOfTicTacToe[i][j])) {
                        countXHorizontally++;
                    }

                    if ("X".equals(matrixOfTicTacToe[j - 1][i + 1])) {
                        countXVertically++;
                    }

                    if (countXHorizontally == 3 || countXVertically == 3) {
                        isXWin = true;
                        break;
                    }

                }

                countXHorizontally = 0;
                countXVertically = 0;
            }

        }

        return isXWin;
    }


    public static String[][] stateOfFields(String inputLine) {

        ArrayList<String> enterCells = new ArrayList<>(Arrays.asList(inputLine.split("")));

        String[][] twoDimensionalArrayOfCells = new String[3][5];

        System.out.println("---------");

        int indexOfEnterCells = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {

                if (j == 0 || j == 4) {
                    twoDimensionalArrayOfCells[i][j] = "|";
                } else {
                    twoDimensionalArrayOfCells[i][j] = enterCells.get(indexOfEnterCells);
                    indexOfEnterCells++;
                }

                System.out.print(twoDimensionalArrayOfCells[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("---------");

        return twoDimensionalArrayOfCells;
    }

    public static boolean draw(String[][] matrixOfTicTacToe, String inputCell) {

        boolean isDraw = false;
        boolean isEmptySpace = false;

        for (String item : inputCell.split("")) {

            if ("_".equals(item)) {
                isEmptySpace = true;
                break;
            }
        }

        if (!checkIfOWins(matrixOfTicTacToe) && !checkIfXWins(matrixOfTicTacToe) && !isEmptySpace) {
            isDraw = true;
        }
        return isDraw;
    }

    public static boolean gameNotFinished(String[][] matrixOfTicTacToe, String inputCell) {

        ArrayList<String> elementsOfCell = new ArrayList<>(Arrays.asList(inputCell.split("")));

        int difference = Math.abs(Collections.frequency(elementsOfCell, "X") - Collections.frequency(elementsOfCell, "O"));

        return !checkIfOWins(matrixOfTicTacToe) && !checkIfXWins(matrixOfTicTacToe) && inputCell.contains("_") && difference < 2;

    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");

        String inputCells = scanner.nextLine();

        String[][] gridOfTicTacToe = stateOfFields(inputCells);

        while (true) {

            if (checkImpossibleFields(inputCells, gridOfTicTacToe)) {
                System.out.println("Impossible");
                break;
            }


            if (checkIfOWins(gridOfTicTacToe)) {
                System.out.println("O wins");
                break;
            }

            if (checkIfXWins(gridOfTicTacToe)) {
                System.out.println("X wins");
                break;
            }


            if (draw(gridOfTicTacToe, inputCells)) {
                System.out.println("Draw");
                break;
            }

            if (gameNotFinished(gridOfTicTacToe, inputCells)) {
                System.out.println("Game not finished");
                break;
            }

        }
    }

}