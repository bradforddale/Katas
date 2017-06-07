import java.util.Arrays;
import java.util.stream.Collectors;

public class NegativesFoundException extends Exception {

    String negativeNumbers = "";
    public NegativesFoundException(String formattedInput) {
        negativeNumbers = Arrays.stream(formattedInput.split(" "))
                .mapToInt(i -> (Integer.parseInt(i)))
                .filter(i -> i < 0)
                .mapToObj(i -> i+", ")
                .collect(StringBuilder::new,(sb, i) -> sb.append(i),StringBuilder::append)
                .toString();
        negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length()-2);
    }

    public String getMessage() {
        return "The following negatives were found: "+ negativeNumbers;
    }
}
