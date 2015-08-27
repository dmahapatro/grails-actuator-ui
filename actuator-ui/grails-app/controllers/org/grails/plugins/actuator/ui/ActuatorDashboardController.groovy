package org.grails.plugins.actuator.ui

import grails.converters.JSON
import static org.grails.plugins.actuator.ui.ActuatorEndpoints.*

class ActuatorDashboardController {
    static namespace = "actuator"

    ActuatorDashboardService actuatorDashboardService
    ActuatorEndpointsConfig actuatorUiConfig

    def index() {
        def health  = parsedEndpointResponse(actuatorUiConfig.getUrl(HEALTH))
        def info    = parsedEndpointResponse(actuatorUiConfig.getUrl(INFO))
        def metrics = parsedEndpointResponse(actuatorUiConfig.getUrl(METRICS))
        def env     = parsedEndpointResponse(actuatorUiConfig.getUrl(ENV))

        metrics = actuatorDashboardService.metricsUtility(metrics)

        [ health: health, info: info, metrics: metrics, env: env ]
    }

    def traceability() {
        def trace   = parsedEndpointResponse(actuatorUiConfig.getUrl(TRACE))
        def traceMap = actuatorDashboardService.traceUtility(trace)

        render view: "trace", model: [traceMap: traceMap]
    }

    def springBeans() {
        def allBeans = parsedEndpointResponse(actuatorUiConfig.getUrl(BEANS))
        def beansMap = actuatorDashboardService.beansUtility(allBeans)

        render view: "beans", model: [beansMap: beansMap]
    }

    def allMappings() {
        def allMappings = parsedEndpointResponse(actuatorUiConfig.getUrl(MAPPINGS))
        Map mappingsMap = actuatorDashboardService.mappingsUtility(allMappings)

        render view: "mappings", model: [mappingsMap: mappingsMap]
    }

    private def parsedEndpointResponse(String endpointId) {
        JSON.parse(hitEndpoint(endpointId))
    }

    String hitEndpoint(String endpointId) {
        g.include(view: "/$endpointId") as String
    }
}
