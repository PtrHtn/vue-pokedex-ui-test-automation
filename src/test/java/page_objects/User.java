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
    private final By pokemonsListTypeLocator = By.cssSelector("td[aria-colindex='3']");
    private final By pokemonsDetailsNameLocator = By.xpath("//h3");
    private final By pokemonsDetailsTypeLocator = By.xpath("//h3/../div");
    private final By searchBar = By.id("search-bar");

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
        listOfPokemons.forEach(pokemon -> {

            fromListOfPokemonsSelect(pokemon);

            assertNameEqualityOf(listNameOf(pokemon), detailsNameOf(pokemon),
                    listPositionOf(pokemon));
        });
    }

    public void goesThroughAllMembersOfPokemonsListAndAssertThatListTypeAndDetailsTypeAreEqual() {
        List<WebElement> listOfPokemons = getAllPokemons();
        listOfPokemons.forEach(pokemon -> {

            fromListOfPokemonsSelect(pokemon);

            assertTypeEqualityOf(listTypeOf(pokemon), detailsTypeOf(pokemon),
                    listPositionOf(pokemon));
        });
    }

    public void assertNameEqualityOf(
            String pokemonListName,
            String pokemonDetailsName,
            String pokemonListPosition) {

        Assertions.assertEquals(pokemonListName, pokemonDetailsName,
            "\n\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\t\t\t!                     Pokemon with the list ID: " + pokemonListPosition
            + "                       !\n" +
            "\t\t\t! Name of pokemon in the list and name in pokemon's details are different !\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\n" +
            "\t\t\t\t               Pokemon's list name   : " + pokemonListName + "\n" +
            "\t\t\t\t               Pokemon's details name: " + pokemonDetailsName + "\n\n"
        );
    }

    public void assertTypeEqualityOf(
            String pokemonListType,
            String pokemonDetailsType,
            String pokemonListPosition) {

        Assertions.assertEquals(pokemonListType, pokemonDetailsType,
            "\n\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\t\t\t!                     Pokemon with the list ID: " + pokemonListPosition
                                                                   + "                       !\n" +
            "\t\t\t! Type of pokemon in the list and type in pokemon's details are different !\n" +
            "\t\t\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
            "\n" +
            "\t\t\t\t               Pokemon's list type   : " + pokemonListType + "\n" +
            "\t\t\t\t               Pokemon's details type: " + pokemonDetailsType + "\n\n"
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

    private String listTypeOf(WebElement pokemon) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsListTypeLocator));
        String[] pokemonListTypeArray = pokemon.findElement(pokemonsListTypeLocator).getText().split("/");
        return switch (pokemonListTypeArray.length) {
            case 1 -> pokemonListTypeArray[0].toUpperCase();
            case 2 -> pokemonListTypeArray[0].toUpperCase() + " " + pokemonListTypeArray[1].toUpperCase();
            default -> throw new IllegalStateException("Unexpected value: " + pokemonListTypeArray.length);
        };
    }

    private String detailsTypeOf(WebElement pokemon) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pokemonsDetailsTypeLocator));
        List<WebElement> listOfPokemonDetailsType = driver.findElements(pokemonsDetailsTypeLocator);
        return switch (listOfPokemonDetailsType.size()) {
            case 3 -> listOfPokemonDetailsType.get(0).getText();
            case 4 -> listOfPokemonDetailsType.get(0).getText() + " " + listOfPokemonDetailsType.get(1).getText();
            default -> throw new IllegalStateException("Unexpected value: " + listOfPokemonDetailsType.size());
        };
    }

    public void enterIntoSearchBar(String pokemonsName) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        driver.findElement(searchBar).sendKeys(pokemonsName);
    }

    public void canSeeInList(String pokemonsName) {
        List<WebElement> listOfPokemons = getAllPokemons();
        listOfPokemons.forEach(pokemon -> {

            Assertions.assertTrue(listNameOf(pokemon).contains(pokemonsName));

            System.out.println(listNameOf(pokemon));
            System.out.println(pokemonsName);
        });
    }
}
