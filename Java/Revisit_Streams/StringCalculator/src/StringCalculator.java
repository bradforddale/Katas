import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {

    private int countAddCall;

    public StringCalculator() {
        countAddCall = 0;
    }

    public int add(final String numbersText) {
        System.out.println(numbersText);
        countAddCall++;
        final int sum = numbersText.isBlank() || numbersText.isEmpty()
                        ? 0
                        : calculateSum(extractNumbers(numbersText));
        System.out.println(sum);
        return sum;
    }

    private static int calculateSum(final List<String> numbers) {
        final Stream<Integer> numbersStream =  numbers.stream()
                .map(Integer::parseInt);

        final List<Integer> negativeNumbers = numbersStream
                .filter(i -> i < 0)
                .collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new NegativesFoundException(negativeNumbers);
        } else {
            return numbers.stream()
                    .map(Integer::parseInt)
                    .filter(i -> i <= 1000)
                    .reduce(0, Integer::sum);
        }
    }

    private List<String> extractNumbers(final String originalText) {
        // This flag is something I'm not sure about as it feels like a code smell but I do prefer the efficiency of calculating it once and not again throughout the code
        // Will do some googling on the topic
        final boolean usingCustomDelimeters = originalText.startsWith("//");
        final List<String> delimiters = extractDelimiters(originalText, usingCustomDelimeters);
        String numbersText = adjustNumbersTextToWithoutDelimeters(usingCustomDelimeters, originalText);
        List<String> numbers = List.of(numbersText);
        for (final String delimiter: delimiters) {
            numbers = extractNumbersForDelimeter(numbers, delimiter);
        }
        return numbers;
    }

    private static String adjustNumbersTextToWithoutDelimeters(final boolean usingCustomDelimeters,
                                                               final String originalText) {
        if (usingCustomDelimeters) {
            return originalText.split("\n")[1];
        }
        return originalText;
    }

    private static List<String> extractDelimiters(final String numbersText,
                                                  final boolean usingCustomDelimeters) {
        if (usingCustomDelimeters) {
            if (numbersText.contains("//[") && numbersText.contains("]")) {
                final String delimeterText = numbersText.split("\n")[0];
                return Arrays.asList(delimeterText.substring(3, delimeterText.length() - 1).split(Pattern.quote("][")));
            } else {
                return List.of(numbersText.substring(2).split("\n")[0]);
            }
        } else {
            return Arrays.asList(",", "\n");
        }
    }

    private List<String> extractNumbersForDelimeter(final List<String> numbersCurrently,
                                                    final String delimeter) {
        return numbersCurrently.stream()
                .flatMap(numberCurrently -> Arrays.stream(numberCurrently.split(Pattern.quote(delimeter))))
                .collect(Collectors.toList());
    }

    public int getCalledCount() {
        return countAddCall;
    }
}
