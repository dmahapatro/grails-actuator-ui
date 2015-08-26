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
            beansUtility(_) >> { def param -> param }
        }
        controller.metaClass.hitEndpoint = { String id ->
            '{"a": "b"}'
        }
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

    void "test model returned from springBeans has bean information and view rendered in beans gsp"() {
        when:
        controller.springBeans()

        then:
        model.size()
        model.keySet() == ['beansMap'] as Set
        view == '/actuatorDashboard/beans'
    }

    void "test model returned from allMappings has mapping information and view rendered in mappings gsp"() {
        when:
        controller.allMappings()

        then:
        model.size()
        model.keySet() == ['mappingsMap'] as Set
        view == '/actuatorDashboard/mappings'
    }
}
