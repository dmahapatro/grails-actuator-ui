package com.grails.plugin

import grails.converters.JSON

class ActuatorDashboardController {
    ActuatorDashboardService actuatorDashboardService

    def index() {
        def health  = JSON.parse(g.include(view: '/health') as String)
        def info    = JSON.parse(g.include(view: '/info') as String)
        def metrics = JSON.parse(g.include(view: '/metrics') as String)
        def env     = JSON.parse(g.include(view: '/env') as String)

        metrics = actuatorDashboardService.metricsUtility(metrics)

        [ health: health, info: info, metrics: metrics, env: env ]
    }

    def traceability() {
        def trace   = JSON.parse(g.include(view: '/trace') as String)
        Map traceMap = actuatorDashboardService.traceUtility(trace)

        render view: "trace", model: [traceMap: traceMap]
    }

    def springBeans() {
        def allBeans = JSON.parse(g.include(view: '/beans') as String)
        Map beansMap = actuatorDashboardService.beansUtility(allBeans)

        render view: "beans", model: [beansMap: beansMap]
    }

    def allMappings() {
        def allMappings = JSON.parse(g.include(view: '/mappings') as String)
        Map mappingsMap = actuatorDashboardService.mappingsUtility(allMappings)

        render view: "mappings", model: [mappingsMap: mappingsMap]
    }
}
