
Feature: Create Token

    Scenario: Create Token
        Given url apiUrl
        Given path 'actuator/health'
        When method Get
        Then status 200
