package sdi.performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

import sdi.performance.createTokens.CreateTokens

class PerfTest extends Simulation {

  CreateTokens.createAccessTokens()

  val protocol = karateProtocol(
    "actuator/health" -> Nil
  )

  protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")

  val csvFeeder = csv("articles.csv").circular
  val tokenFeeder = Iterator.continually(Map("token" -> CreateTokens.getNextToken))

  val createCertificate = scenario("Create and delete article")
      .feed(csvFeeder)
      .feed(tokenFeeder)
      .exec(karateFeature("classpath:sdi/performance/certificate.feature"))

  setUp(
    createCertificate.inject(
          atOnceUsers(1),
      // rampUsers(19) during (5 seconds)
      // heavisideUsers(1000) during (20 seconds)
          nothingFor(4 seconds),
          constantUsersPerSec(1) during (3 seconds),
          constantUsersPerSec(2) during (10 seconds),
          rampUsersPerSec(2) to 10 during (20 seconds),
          nothingFor(5 seconds),
          constantUsersPerSec(1) during (5 seconds)
        ).protocols(protocol)
  )

}
