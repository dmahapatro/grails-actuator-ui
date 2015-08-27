package org.grails.plugins.actuator.ui

enum ActuatorEndpoints {
    AUTOCONFIG, CONFIGPROPS, DUMP, ENV, HEALTH, INFO, METRICS, MAPPINGS, SHUTDOWN, TRACE, BEANS

    String getValue() {
        this.toString().toLowerCase()
    }
}
