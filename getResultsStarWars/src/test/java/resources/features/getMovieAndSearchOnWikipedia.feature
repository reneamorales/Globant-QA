# languaje: en

Feature: Random movie request to Star Wars API and verification on Wikipedia


  Scenario: Random movie request and Wikipedia search and verification on the History page.

    Given I am a user requesting a random movie from the Star Wars API
    When I get the API response
    Then I look up the title of the film on Wikipedia
    And I navigate to the corresponding article page
    When I click on the view history button
    Then I check that the title of the view story page includes the name of the film

