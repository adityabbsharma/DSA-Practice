import java.util.Arrays;
import java.util.stream.Collectors;

public class StringRever {

    public static void main(String[] args) {
        String original = "Java Concept Of The Day";
        String[] arr = original.split(" ");

        Arrays.asList(arr).stream().map(s->new StringBuilder(s).reverse().toString())
            .collect(Collectors.toList());

    }
}
