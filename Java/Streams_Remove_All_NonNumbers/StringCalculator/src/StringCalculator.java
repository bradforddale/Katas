import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalculator {

    public int Add(String input) throws NegativesFoundException {
        if (input.isEmpty()) {
            return 0;
        } else {
            if (input.indexOf("-") > -1) {
                throw new NegativesFoundException(formatInput(input).trim());
            } else {
                return sumOfFormattedInput(formatInput(input).trim());
            }
        }
    }

    private int sumOfFormattedInput(String input) {
        return Arrays.stream(input.split(" "))
                .filter(c -> !c.equals(""))
                .mapToInt(i -> (Integer.parseInt(i)))
                .filter(i -> i < 1000)
                .sum();
    }

    private String formatInput(String input) {
        String formattedInput = input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> replaceUnwantedCharacterWithSpace(c))
                .collect(StringBuilder::new,(sb, i) -> sb.append((char)i),StringBuilder::append)
                .toString();
        return formattedInput;
    }

    public char replaceUnwantedCharacterWithSpace(char c) {
        return !isCharachterUnwanted(c) ? ' ' : c;
    }

    public boolean isCharachterUnwanted(char inputCharacter) {
        return isCharANumber(inputCharacter) || isCharASpecialCharacter(inputCharacter);
    }

    private boolean isCharANumber(String c) {
        return isCharANumber(c.charAt(0));
    }

    private boolean isCharANumber(char c) {
        boolean isNumber ;
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                isNumber = true;
                break;
            default:
                isNumber = false;
                break;
        }
        return isNumber;
    }

    public boolean isCharASpecialCharacter(String c) {
        return isCharASpecialCharacter(c.charAt(0));
    }

    private boolean isCharASpecialCharacter(char c) {
        boolean isSpecialCharacter ;
        switch (c) {
            case '-':
            case ' ':
                isSpecialCharacter = true;
                break;
            default:
                isSpecialCharacter = false;
                break;
        }
        return isSpecialCharacter;
    }

}
