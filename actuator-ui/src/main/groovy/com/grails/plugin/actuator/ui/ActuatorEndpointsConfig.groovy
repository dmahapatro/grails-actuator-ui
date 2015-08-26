package com.grails.plugin.actuator.ui

class ActuatorEndpointsConfig {
    def endpointsProperties, managementProperties

    boolean isMonitoringEnabled() {
        endpointsProperties.enabled
    }

    String getContextPath() {
        managementProperties."context-path" ?: ''
    }

    String getPort() {
        managementProperties.port ?: ''
    }

    String getAddress() {
        managementProperties.address ?: ''
    }

    boolean isHttpDisabled() {
        port == '-1'
    }

    def methodMissing(String name, args) {
        if (name.startsWith("is") && name.endsWith('Enabled') && !args.size()) {
            String endpointId = name.toLowerCase() - 'is' - 'enabled'
            return endpointsProperties."$endpointId".enabled as boolean
        } else if (name.startsWith("find") && name.endsWith('Id') && !args.size()) {
            String endpointId = name.toLowerCase() - 'find' - 'id'
            return endpointsProperties."$endpointId".id ?: ''
        } else if (name.startsWith("is") && name.endsWith('Sensitive') && !args.size()) {
            String endpointId = name.toLowerCase() - 'is' - 'sensitive'
            return endpointsProperties."$endpointId".sensitive as boolean
        } else {
            throw new MissingMethodException(name, this.class, args)
        }
    }


}
