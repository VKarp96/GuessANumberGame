public class Test {
    public static void main(String[] args) {
        String greeting = "Hello. I want to play a game. I think up an X-digit number(X is up to you but maximum 10) " +
                "where every digit is unique, and you try to guess it. Don't worry, I'll be giving you some tips! " +
                "I will tell you, how many digits you guessed and they are RIGHT place and how many digits you " +
                "guessed and they are on WRONG place. For example: i thought up 4752 and you told 3724, so my tips " +
                "will be \"R:1  W:2 \". The less tries it takes - the better you are. Simple, isn't it? Let's go!";
        System.out.println(StringFormatter.splitInLines(greeting, 100));
    }
}
