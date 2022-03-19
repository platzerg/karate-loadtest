package sdi.certificate;

import com.intuit.karate.junit5.Karate;

class CertificateRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("certificate").relativeTo(getClass());
    }    

}
