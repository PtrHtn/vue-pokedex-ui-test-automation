package page_objects;

import step_definitions.CucumberHooks;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class User {

    private final WebDriver driver = CucumberHooks.driver;
    private final WebDriverWait driverWait = CucumberHooks.driverWait;

    private final By pokeballLocator = By.cssSelector("img[class='pokemon-info-panel-pokeball']");
    private final By pokemonsListLocator = By.cssSelector("tr[role='row'][tabindex='0']");
    private final By pokemonsListPositionLocator = By.cssSelector("td[aria-colindex='1']");
    private final By pokemonsListNameLocator = By.cssSelector("td[aria-colindex='2']");
    private final By pokemonsDetailsNameLocator = By.xpath("//h3");


    public void goesToHomePage() {
        driver.get("https://shadforth.github.io/vue-pokedex/");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokeballLocator));
    }


    public void pokeballIsDisplayed() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokeballLocator));
        boolean pokeballIsDisplayed = driver.findElement(pokeballLocator).isDisplayed();
        Assertions.assertTrue(pokeballIsDisplayed,
                "\n\nPageObject.User can NOT see pokeball on the page!\n\n");
    }

    public User seeThat() {
        return this;
    }
    public User then() {
        return this;
    }

    public void goesThroughAllMembersOfPokemonsListAndAssertThatListNameAndDetailsNameAreEqual() {
        List<WebElement> listOfPokemons = getAllPokemons();
        assertThatListNameAndDetailsNameAreEqualForEachPokemonIn(listOfPokemons);
    }

    public void assertThatListNameAndDetailsNameAreEqualForEachPokemonIn(
            List<WebElement> listOfPokemons) {

        listOfPokemons.forEach(pokemon -> {

            fromListOfPokemonsSelect(pokemon);

            assertEqualityOf(listNameOf(pokemon), detailsNameOf(pokemon),
                    listPositionOf(pokemon));
        });

    }

    public void assertEqualityOf(
            String pokemonListName,
            String pokemonDetailsName,
            String pokemonListPosition) {

        Assertions.assertEquals(pokemonListName, pokemonDetailsName,
            "\n\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\t\t\t!      Pokemon on list position: " + pokemonListPosition + "       !\n" +
            "\t\t\t! list name and details name are different !\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\n" +
            "\t\t\t\tPokemon's list name   : " + pokemonListName + "\n" +
            "\t\t\t\tPokemon's details name: " + pokemonDetailsName + "\n\n"
        );
    }

    public List<WebElement> getAllPokemons() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsListLocator));
        List<WebElement> listOfPokemons = driver.findElements(pokemonsListLocator);
        System.out.println("\nNumber of Pokemons in the list: " + listOfPokemons.size() + "\n");
        return listOfPokemons;
    }

    public void fromListOfPokemonsSelect(WebElement pokemon) {
        driverWait.until(ExpectedConditions.elementToBeClickable(pokemon));
        pokemon.findElement(pokemonsListNameLocator).click();
    }

    public String listNameOf(WebElement pokemon) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsListNameLocator));
        return pokemon.findElement(pokemonsListNameLocator).getText();
    }

    public String detailsNameOf(WebElement pokemon) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsDetailsNameLocator));
        return pokemon.findElement(pokemonsDetailsNameLocator).getText();
    }

    public String listPositionOf(WebElement pokemon) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsListPositionLocator));
        return pokemon.findElement(pokemonsListPositionLocator).getText();
    }

}
