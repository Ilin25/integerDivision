import ru.ilin.integerdivision.Division;
import ru.ilin.integerdivision.DivisionData;
import ru.ilin.integerdivision.Formatter;

public class Main {

    public static void main(String[] args) {

        Division division = new Division();
        Formatter formatter = new Formatter();
        DivisionData data = division.divide(78945,4);
        String result = formatter.format(data);
        System.out.println(result);
    }
}
