package com.grails.plugin

import grails.converters.JSON

class ActuatorDashboardController {
    static namespace = "actuator"

    ActuatorDashboardService actuatorDashboardService
    def actuatorUiConfig

    def index() {
        def health  = parsedEndpointResponse 'health'
        def info    = parsedEndpointResponse 'info'
        def metrics = parsedEndpointResponse 'metrics'
        def env     = parsedEndpointResponse 'env'

        metrics = actuatorDashboardService.metricsUtility(metrics)

        [ health: health, info: info, metrics: metrics, env: env ]
    }

    def traceability() {
        def trace   = parsedEndpointResponse 'trace'
        def traceMap = actuatorDashboardService.traceUtility(trace)

        render view: "trace", model: [traceMap: traceMap]
    }

    def springBeans() {
        def allBeans = parsedEndpointResponse 'beans'
        def beansMap = actuatorDashboardService.beansUtility(allBeans)

        render view: "beans", model: [beansMap: beansMap]
    }

    def allMappings() {
        def allMappings = parsedEndpointResponse 'mappings'
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
