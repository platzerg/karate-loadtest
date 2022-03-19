package sdi.health;

import com.intuit.karate.junit5.Karate;

class HealthRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("health").relativeTo(getClass());
    }    

}
