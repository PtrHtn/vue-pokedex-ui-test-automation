@pokedex
Feature: Pokemon details
  Will verify equality of Pokemon details in the list and details section

  Background:
    Given user is on the homepage Vue Pokedex site
    Then user should see pokeball on the page

  @LongTest
  Scenario: Verify if Pokemon's name in the list is equal to Pokemon's name in the details section
    When user goes through all members of the Pokemons list
    Then user should see that name of the Pokemon in the list is equal to name of the Pokemon in the details section

  @LongTest
  Scenario: Verify if Pokemon's type in the list is equal to Pokemon's type in the details section
    When user goes through all members of the Pokemons list
    Then user should see that type of the Pokemon in the list is equal to type of the Pokemon in the details section

