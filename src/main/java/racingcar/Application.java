package racingcar;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    private static final int NO_CAR_ERROR = 0;
    private static final int NO_INT_ERROR = 1;
    private static final int LENGTH_RANGE_ERROR = 2;
    private static final int SAME_NAME_ERROR = 3;
    private static final int MIN_CAR_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        gameSetting(scanner);
        System.out.println("makeCars success");
        scanner.close();
    }

    private static void gameSetting(Scanner kbd) {
        displayCarNameInputMessage();
        String[] names = inputCarNames(kbd);
        boolean check = checkCarNames(names);
        while (!check) {
            displayCarNameInputMessage();
            names = inputCarNames(kbd);
            check = checkCarNames(names);
        }
        makeCars(names);
    }

    private static void makeCars(String[] names) {
        Car[] cars = new Car[names.length];
        for (int i = 0; i < names.length; i++) {
            cars[i] = new Car(names[i]);
        }
    }

    private static void displayCarNameInputMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    private static String[] inputCarNames(Scanner kbd) {
        String input = kbd.nextLine();
        StringTokenizer tokens = new StringTokenizer(input, ", ");
        int countTokens = tokens.countTokens();
        String[] names = new String[countTokens];
        for (int i = 0; i < countTokens; i++) {
            names[i] = tokens.nextToken();
        }
        return names;
    }

    private static boolean checkCarNames(String[] names) {
        boolean check = true;
        if (names.length < MIN_CAR_LENGTH) {
            check = false;
            displayErrorMessage(NO_CAR_ERROR);
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() > MAX_NAME_LENGTH) {
                check = false;
                displayErrorMessage(LENGTH_RANGE_ERROR);
            }
        }
        return check;
    }

    private static void displayErrorMessage(int errorCase) {
        if (errorCase == NO_CAR_ERROR)
            System.out.println("[ERROR] 경주할 자동차를 2대 이상 입력해주세요");
        if (errorCase == LENGTH_RANGE_ERROR)
            System.out.println("[ERROR] 이름은 5자 이하로 입력해주세요");
        if (errorCase == NO_INT_ERROR)
            System.out.println("[ERROR] 시도 횟수는 숫자여야 한다");
        if (errorCase == SAME_NAME_ERROR)
            System.out.println("[ERROR] 이름을 중복하여 사용할 수 없습니다");
    }
}