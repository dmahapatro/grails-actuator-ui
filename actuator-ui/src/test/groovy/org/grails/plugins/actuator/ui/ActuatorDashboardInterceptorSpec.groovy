package org.grails.plugins.actuator.ui

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ActuatorDashboardInterceptor)
@Mock(ActuatorDashboardController)
class ActuatorDashboardInterceptorSpec extends Specification {

    void "Test actuatorDashboard interceptor matching"() {
        when:"A request matches the interceptor"
        withRequest(controller:"actuatorDashboard", namespace: 'actuator')

        then:"The interceptor does match"
        interceptor.doesMatch()
    }

    void "Test actuatorDashboard interceptor intercepts before action and redirects to error page"() {
        given:
        interceptor.actuatorUiConfig = Mock(ActuatorEndpointsConfig) {
            isMonitoringEnabled() >> { mappingEnabled }
            isDashboardItemsEnabled() >> { dashboardEndpointsEnabled }
            isHttpDisabled() >> { !httpEnabled }
        }

        when:"A request matches the interceptor"
        withRequest(controller:"actuatorDashboard", namespace: 'actuator')

        then:"The interceptor does match"
        interceptor.doesMatch()

        when:
        interceptor.before()

        then:
        response.redirectUrl.startsWith redirectUrlStartsWith

        where:
        mappingEnabled | dashboardEndpointsEnabled | httpEnabled || redirectUrlStartsWith
        false          | true                      | true        || '/actuator/notAllowed?error=406'
        true           | false                     | true        || '/actuator/notAllowed?error=406'
        true           | true                      | false       || '/actuator/notAllowed?error=505'
    }

    void "test actuatorDashboard interceptor intercepts before action and redirects to error page if management port and/or address is customized"() {
        given:
        interceptor.actuatorUiConfig = Mock(ActuatorEndpointsConfig) {
            isMonitoringEnabled() >> true
            isDashboardItemsEnabled() >> true
            isHttpDisabled() >> false
            getPort() >> { port }
            getAddress() >> { address }
        }

        when:"A request matches the interceptor"
        withRequest(controller:"actuatorDashboard", namespace: 'actuator')

        then:"The interceptor does match"
        interceptor.doesMatch()

        when:
        interceptor.before()

        then:
        if(response.redirectUrl) {
            assert response.redirectUrl.startsWith(redirectUrlStartsWith)
        }

        where:
        port | address     || redirectUrlStartsWith
        8081 | ''          || '/actuator/notAllowed?error=501'
        ''   | '127.0.0.1' || '/actuator/notAllowed?error=501'
        8081 | '127.0.0.1' || '/actuator/notAllowed?error=501'
        ''   | ''          || null
        null | null        || null
    }
}
