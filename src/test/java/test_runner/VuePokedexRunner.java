package test_runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"classpath:feature_files"},
        glue = {"classpath:step_definitions", "helpers"},
        monochrome = false,
        dryRun = false,
        tags = ("@SmokeTest")
)

public class VuePokedexRunner {
}

