public class Main {

    public static void main(String[] args) {

        String[] arguments = {
                "-g", "classpath:step_definitions",
                "-t", "@pokedex",
                "classpath:feature_files",
                "-m"
        };

        io.cucumber.core.cli.Main.main(arguments);
    }
}
