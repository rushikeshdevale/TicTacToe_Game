package com.game;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static char[] element;
    static char userMark, computerMark;
    static Scanner scan = new Scanner(System.in);
    private static final Random random = new Random();
    static int userNumber, computerNumber;
    static int turn = 1, flag = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game !!!!!!");
        boardCreation();
        displayingBoard();
        choosingXorO();
        tossCoin();
        outerloop:
        while (flag == 0) {
            if ((turn + 1) % 2 == 0) {

                currentBoard();
                userCall();
                userMove();
                currentBoard();
                flag = checkWin();
                if (flag == 1) {
                    System.out.println("Excellent! You are the winner");
                    break outerloop;
                }
                // to check whether game is tie or not
                flag = checkTie();
                if (flag == 1) {
                    System.out.println("Nice Play! It's Tie");
                    ;
                    break outerloop;
                }
                turn++;
            }
        }
    }

    private static void boardCreation() {
        element = new char[10];
        for (int i = 1; i < 10; i++) {
            element[i] = ' ';
        }
    }

    private static void choosingXorO() {
        System.out.println("Choose 1 for 'X' or Choose 2 for 'O' as your mark");
        int option = scan.nextInt();
        switch (option) {
            case 1:
                userMark = 'X';
                computerMark = 'O';
                break;
            case 2:
                userMark = 'O';
                computerMark = 'X';
                break;
            default:
                System.out.println("Your input is invalid");
                choosingXorO();
        }
    }

    private static void currentBoard() {
        for (int i = 1; i < 10; i++) {
            if (element[i] != 'X' && element[i] != 'O') {
                element[i] = (char) (i + '0');
            }
        }
        displayingBoard();
    }

    private static void displayingBoard() {
        System.out.println("\n  " + element[1] + " | " + element[2] + " | " + element[3] + " ");
        System.out.println(" ----------- ");
        System.out.println("  " + element[4] + " | " + element[5] + " | " + element[6] + " ");
        System.out.println(" ----------- ");
        System.out.println("  " + element[7] + " | " + element[8] + " | " + element[9] + " \n");
    }

    private static void userCall() {
        System.out.println("\nEnter a number from board to make the mark:\n");
        userNumber = scan.nextInt();
        if (userNumber < 1 || userNumber > 9) {
            currentBoard();
            System.out.println("Your input is Invalid");
            userCall();
        }
    }

    private static void userMove() {
        if (element[userNumber] == 'X' || element[userNumber] == 'O') {
            currentBoard();
            System.out.println("Number which is selected is not free");
            userCall();
            userMove();
        } else {
            element[userNumber] = userMark;
            System.out.println(userMark + " user is marked " + userNumber);
        }
    }

    private static void tossCoin() {
        System.out.println("\nMaking a toss to check who plays first\nChoose 1 for Head or 2 for Tail");
        int option = scan.nextInt();
        if (option == 1 || option == 2) {
            int flip = random.nextInt(2) + 1;
            if (flip == 1) {
                System.out.println("\nBy tossing Coin it shows HEAD\n");
            } else {
                System.out.println("\nBy tossing Coin it shows TAIL\n");
            }
            if (flip == option) {
                System.out.println("User will start the game\n");
            } else {
                System.out.println("Computer will start the game\n");
                computerFirstTurn();
            }
        } else {
            System.out.println("\nInvalid input Again");
            tossCoin();
        }
    }

    public static void computerFirstTurn() {
        computerNumber = random.nextInt(9) + 1;
        element[computerNumber] = computerMark;
        System.out.println("Computer choses '" + computerNumber + "' now user turn");
    }

    public static int checkWin() {
        for (int i = 1; i < 9; i++) {
            int win[] = winArray(i);
            if (element[win[0]] == element[win[1]] && element[win[1]] == element[win[2]]) {
                flag = 1;
            }
        }
        return flag;
    }

    private static int[] winArray(int number) {
        if (number == 1) {
            int arrayWin[] = {1, 2, 3};
            return arrayWin;
        } else if (number == 2) {
            int arrayWin[] = {4, 5, 6};
            return arrayWin;
        } else if (number == 3) {
            int arrayWin[] = {7, 8, 9};
            return arrayWin;
        } else if (number == 4) {
            int arrayWin[] = {1, 4, 7};
            return arrayWin;
        } else if (number == 5) {
            int arrayWin[] = {2, 5, 8};
            return arrayWin;
        } else if (number == 6) {
            int arrayWin[] = {3, 6, 9};
            return arrayWin;
        } else if (number == 7) {
            int arrayWin[] = {1, 5, 9};
            return arrayWin;
        } else {
            int arrayWin[] = {3, 5, 7};
            return arrayWin;
        }
    }

    public static int checkTie() {
        for (int i = 1; i < 10; i++) {
            if (element[i] == 'X' || element[i] == 'O') {
                if (i == 9) {
                    flag = 1;
                }
            }
        }
        return flag;
    }
}
