Feature: certificate business test script

    Background:
        * url apiUrl

        * configure afterScenario = function(){ karate.call('classpath:helpers/Dummy.feature') }
        * configure afterFeature =
        """
            function(){
                karate.log('After Feature Text');
            }
        """

    ##@debug
    Scenario: certificate post certificate package
        * def sleep = function(pause){ java.lang.Thread.sleep(pause) }

        Given path '/certificates/v1/async/ecu'
        And request
        """
            {
                "ecuSecurityProjectId": "ecuSecurityProjectId",
                "ecuUuid": "ca6e6fb6-c250-571f-bac5-358d0a6f94b9",
                "shortVin": "WMAN567",
                "snr7000": "snr7000",
                "validity": "SHORT",
                "vdfVersion": "111.55.98",
                "csrData": "csrData"
            }
        """
        When method post
        * eval sleep(10000)
        Then status 404

    ##@debug
    Scenario: certificate get certificate package
        * def packageId = '10989f8d-1329-459d-b4df-beb36b87ca49'
        * print packageId

        Given path '/certificates/v1/async/certificate-package/' + packageId
        When method get
        Then status 404
        * def components = packageId
