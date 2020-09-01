import PageObject.User;

public class Main {

    public static void main(String[] args) throws Throwable {

        String[] arguments = {
                "-g", "classpath:StepDefinition",
                "-t", "@SmokeTest",
//                "-t", args[0],
                "classpath:Feature",
                "-m"
        };

        io.cucumber.core.cli.Main.main(arguments);
    }
}
