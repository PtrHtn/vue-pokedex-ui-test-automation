package step_definitions;

import page_objects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VuePokedexPage {

    User user = new User();

    @Given("user is on the homepage Vue Pokedex site")
    public void userIsOnTheHomepageVuePokedexSite() {
        user.goesToHomePage();
    }

    @Then("user should see pokeball on the page")
    public void userShouldSeePokeballOnThePage() {
        user.seeThat().pokeballIsDisplayed();
    }

    @When("user goes through all members of the Pokemons list")
    public void userGoesThroughAllMembersOfThePokemonsList() {
        user.goesThroughAllMembersOfPokemonsListAndAssertThatListNameAndDetailsNameAreEqual();
    }

    @Then("user should see that name of the Pokemon in the list is equal to name of the Pokemon in the details section")
    public void userShouldSeeThatNameOfThePokemonInTheListIsEqualToNameOfThePokemonInTheDetailsSection() {
    }


}