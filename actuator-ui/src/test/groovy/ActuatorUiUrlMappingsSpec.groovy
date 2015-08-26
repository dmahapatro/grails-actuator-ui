import com.grails.plugin.ActuatorDashboardController
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ActuatorUiUrlMappings)
@Mock(ActuatorDashboardController)
class ActuatorUiUrlMappingsSpec extends Specification {

    void "test url mappings"() {
        expect:
        assertForwardUrlMapping("/actuator/dashboard", controller: 'actuatorDashboard', namespace: 'actuator')
        assertForwardUrlMapping("/actuator/traceability", controller: 'actuatorDashboard', action: "traceability", namespace: 'actuator')
        assertForwardUrlMapping("/actuator/beans", controller: 'actuatorDashboard', action: "springBeans", namespace: 'actuator')
        assertForwardUrlMapping("/actuator/mappings", controller: 'actuatorDashboard', action: "allMappings", namespace: 'actuator')
    }
}
