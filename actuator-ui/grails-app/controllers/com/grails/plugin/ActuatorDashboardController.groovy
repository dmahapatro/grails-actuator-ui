package com.grails.plugin

import grails.converters.JSON

class ActuatorDashboardController {

    def index() {
        def health  = JSON.parse(g.include(view: '/health') as String)
        def info    = JSON.parse(g.include(view: '/info') as String)
        def metrics = JSON.parse(g.include(view: '/metrics') as String)

        render view: "index", model: [health: health, info: info, metrics: metrics]
    }
}
