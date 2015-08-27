package org.grails.plugins.actuator.ui

import static org.grails.plugins.actuator.ui.ActuatorEndpoints.*

class ActuatorEndpointsConfig {
    def endpointsProperties, managementProperties

    boolean isMonitoringEnabled() {
        // If there is no configuration for endpoint or endpoints config does not have 'enabled' config or when enabled
        // is true then monitoring is enabled, otherwise disabled
        !endpointsProperties || !('enabled' in endpointsProperties.keySet()) || endpointsProperties.enabled.toBoolean()
    }

    String getContextPath() {
        managementProperties?."context-path" ?: ''
    }

    String getPort() {
        managementProperties?.port ?: ''
    }

    String getAddress() {
        managementProperties?.address ?: ''
    }

    boolean isHttpDisabled() {
        port == '-1'
    }

    String getUrl(ActuatorEndpoints endpointEnum) {
        if(!endpointEnum) { return '' }

        String endpointName = findEndpointId(endpointEnum)
        contextPath ? "/$contextPath/$endpointName" : "/$endpointName"
    }

    boolean isDashboardItemsEnabled() {
        monitoringEnabled && [
            HEALTH, METRICS, INFO, ENV, TRACE, BEANS, MAPPINGS
        ].every { isEnabled(it) }
    }

    private boolean isEnabled(ActuatorEndpoints endpointEnum) {
        String value = endpointsProperties?."$endpointEnum.value"?.enabled
        value != null ? value.toBoolean() : true
    }

    private String findEndpointId(ActuatorEndpoints endpointEnum) {
        endpointsProperties?."$endpointEnum.value"?.id ?: endpointEnum.value
    }

    private boolean isSensitive(ActuatorEndpoints endpointEnum) {
        String value = endpointsProperties?."$endpointEnum.value"?.sensitive
        value != null ? value.toBoolean() : true
    }
}
