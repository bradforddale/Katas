import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NegativesFoundException extends RuntimeException {

    private List<Integer> negativeNumbers = new ArrayList<>();

    public NegativesFoundException(final List<Integer> negativeNumbers) {
        this.negativeNumbers = negativeNumbers;
    }

    @Override
    public String getMessage() {
        return negativeNumbers.size() > 1
                ? "negatives not allowed: "+ negativeNumbers.stream().map(Object::toString).collect(Collectors.joining(", "))
                : "negatives not allowed";
    }
}
