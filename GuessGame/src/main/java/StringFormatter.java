class StringFormatter {
    private StringFormatter() {
    }

    /**
     * Splits {@code string} in lines having minimum length that have {@code lengthOfLine} characters
     * or more, but not splitting words - only replacing spaces by {@code "\n"}
     *
     * @param string the string to split
     * @param lengthOfLine min length of line
     * @author Vlad
     * @return char sequence to return
     */

    static CharSequence splitInLines(String string, int lengthOfLine) {
        StringBuilder builder = new StringBuilder(string);
        int charsInCurrentLine = 0;
        for (int i = 0; i < builder.length(); i++) {
            if (charsInCurrentLine++ >= lengthOfLine && builder.charAt(i) == ' ') {
                builder.delete(i, i + 1);
                builder.insert(i, "\n");
                charsInCurrentLine = 0;
            }
        }

        return builder;
    }




}
