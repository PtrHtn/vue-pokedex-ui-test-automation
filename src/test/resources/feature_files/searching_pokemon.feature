@pokedex
Feature: Searching Pokemon
  Will verify if searching functionality is working as expected

  @searching_pokemon
  Rule: User can search pokemon

    @MultiTest
    Scenario Outline: User can search pokemon using search bar
      Given user is on the homepage Vue Pokedex site
      When user enter "<pokemonsName>" to the search bar
      Then only "<pokemonsName>" is displayed in the list
      Examples:
        |pokemonsName|
        |Arbok     |
        |Bellossom|
        |Caterpie|
