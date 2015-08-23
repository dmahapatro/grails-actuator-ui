package com.grails.plugin

import grails.converters.JSON

class ActuatorDashboardController {

    ActuatorDashboardService actuatorDashboardService

    def index() {
        def health  = JSON.parse(g.include(view: '/health') as String)
        def info    = JSON.parse(g.include(view: '/info') as String)
        def metrics = JSON.parse(g.include(view: '/metrics') as String)
        def trace   = JSON.parse(g.include(view: '/trace') as String)

        metrics = actuatorDashboardService.metricsUtility(metrics)
        Map traceMap = actuatorDashboardService.traceUtility(trace)

        render view: "index", model: [health: health, info: info, metrics: metrics, traceMap: traceMap]
    }

    def traceability() {
        def trace   = JSON.parse(g.include(view: '/trace') as String)
        Map traceMap = actuatorDashboardService.traceUtility(trace)

        render view: "trace", model: [traceMap: traceMap]
    }
}
