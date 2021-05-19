import java.util.*;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static int difficultyLevel;
    private static boolean isGameWon = false;
    private static List<Integer> listOfNumbers;
    private static int nOfTries = 0;

    public static void main(String[] args) {
        printWelcomeAndRules();
        startGame();
    }

    private static void startGame() {
        System.out.print("set number of digits(from 1 to 10): ");

        try {
            difficultyLevel = takeNumberOfDigits(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            startGame();
            return;
        }

        generateNumber(difficultyLevel);

        System.out.println("Game is started!");

        while (!isGameWon) {
            checkAndGiveTips(scanner.next());
        }

        System.out.println("Congratulations! You won in " + nOfTries + " try(ies)");
        System.out.println("Wanna try again?(y/n)");
        if (scanner.next().toLowerCase().equals("y")) {
            startGame();
        } else if (scanner.next().toLowerCase().equals("n")) {
            System.exit(0);
        } else System.out.println("wrong, try again");
    }

    private static void printWelcomeAndRules() {
        String greeting = "Hello. I want to play a game. I think up an X-digit number(X is up to you but maximum 10) " +
                "where every digit is unique, and you try to guess it. Don't worry, I'll be giving you some tips! " +
                "I will tell you, how many digits you guessed and they are RIGHT place and how many digits you " +
                "guessed and they are on WRONG place. For example: i thought up 4752 and you told 3724, so my tips " +
                "will be \"R:1  W:2 \". The less tries it takes - the better you are. Simple, isn't it? Let's go!";
        System.out.println(StringFormatter.splitInLines(greeting, 100));
    }

    private static int takeNumberOfDigits(String input) throws IllegalArgumentException {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("you must type a number!");
        }

        int inputInt = Integer.parseInt(input);

        //TODO разобраться, нахрена я это сделал, а то писал пьяный
//        try {
//            inputInt = Integer.parseInt(input);
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException("only from 1 to 10!");
//        }

        if (inputInt < 1 || inputInt > 10) {
            throw new IllegalArgumentException("only from 1 to 10!");
        }

        return inputInt;
    }

    private static void generateNumber(int length) {
        Set<Integer> setOfNumbers = new HashSet<>(length);

        while (setOfNumbers.size() < length) {
            setOfNumbers.add((int) (Math.random() * 10));
        }

        listOfNumbers = new ArrayList<>(setOfNumbers);
    }

    private static void checkAndGiveTips(String guess) {
        if (!guess.matches("\\d+")) {
            System.out.println("only numbers!");
            checkAndGiveTips(scanner.next());
            return;
        }
        if (!hasOnlyUniqueNumbers(guess)) {
            System.out.println("numbers must be unique!");
            checkAndGiveTips(scanner.next());
            return;
        }
        if (guess.length() != difficultyLevel) {
            System.out.println("must be as many digits as your difficulty level is!");
            checkAndGiveTips(scanner.next());
            return;
        }

        int[] intArr = new int[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            intArr[i] = guess.charAt(i) - '0';  //converting char to int
        }

        int nOfWrongPlaces = 0;
        int nOfRightPlaces = 0;
        for (int number: intArr) {
            if (listOfNumbers.contains(number)) {
                nOfWrongPlaces++;
            }
        }

        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] == listOfNumbers.get(i)) {
                nOfRightPlaces++;
            }
        }

        nOfWrongPlaces -= nOfRightPlaces;

        System.out.println("R:" + nOfRightPlaces + "  " + "W:" + nOfWrongPlaces + "  |  number of try: " + ++nOfTries);
        if (nOfRightPlaces == difficultyLevel) isGameWon = true;
    }

    private static boolean hasOnlyUniqueNumbers(String guess) {
        char[] charArray = guess.toCharArray();
        Set<Character> set = new HashSet<>(difficultyLevel);

        for (char c : charArray) {
            set.add(c);
        }

        return guess.length() == set.size();
    }


}
