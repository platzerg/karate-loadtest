Feature: certificate health test script

  Background:
    * url apiUrl

  @debug
  Scenario: health check
    * configure retry = { count: 3, interval: 5000 }
    Given path 'actuator/health'
    And retry until response.status == 'UP'
    When method get
    Then status 200
    * def components = response.components
    And match response.status == 'UP'
