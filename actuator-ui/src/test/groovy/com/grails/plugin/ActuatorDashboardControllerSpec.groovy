package com.grails.plugin

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ActuatorDashboardController)
@Mock(ActuatorDashboardService)
class ActuatorDashboardControllerSpec extends Specification {

    def setup() {
        controller.actuatorDashboardService = Mock(ActuatorDashboardService) {
            metricsUtility(_) >> { def param -> param }
            traceUtility(_) >> { def param -> param }
        }
        controller.metaClass.hitEndpoint = { String id ->
            '{"a": "b"}'
        }
    }

    def cleanup() {
    }

    void "test model returned from index action has actuator endpoints information"() {
        when:
        def model = controller.index()

        then:
        model.size()
        model.keySet() == ['health', 'info', 'metrics', 'env'] as Set
        model.values().contains( [a: 'b'] )
    }

    void "test model returned from traceability has trace and view rendered in trace gsp"() {
        when:
        controller.traceability()

        then:
        model.size()
        model.keySet() == ['traceMap'] as Set
        view == '/actuatorDashboard/trace'
    }
}
