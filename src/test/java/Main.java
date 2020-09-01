public class Main {

    public static void main(String[] args) {

        String[] arguments = {
                "-g", "classpath:step_definitions",
                "-t", "@SmokeTest",
                "classpath:feature_files",
                "-m"
        };

        io.cucumber.core.cli.Main.main(arguments);
    }
}
