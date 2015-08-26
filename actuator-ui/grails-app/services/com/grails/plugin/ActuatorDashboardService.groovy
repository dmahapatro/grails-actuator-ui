package com.grails.plugin

import org.joda.time.Period

class ActuatorDashboardService {

    def metricsUtility(def metrics) {
        if(!metrics) return [:]

        // Uptime
        if( metrics["instance.uptime"] && metrics["instance.uptime"] in Number ) {
            Period uptimePeriod = new Period(metrics["instance.uptime"] as Long)
            if (uptimePeriod) {
                metrics.uptimeHours = uptimePeriod.hours
                metrics.uptimeMinutes = uptimePeriod.minutes
                metrics.uptimeSeconds = uptimePeriod.seconds
            }
        }

        // Memory
        if(metrics.mem && metrics["mem.free"]) {
            metrics.freeMemPercent = ( metrics["mem.free"] / metrics.mem ) * 100 as int
        }

        // Heap
        if ( metrics.heap && metrics["heap.used"] ) {
            metrics.heapUsedPercent = ( metrics["heap.used"] / metrics.heap ) * 100 as int
        }

        // Counter and Gauge
        def counters = metrics.findAll { String k, v -> k.startsWith('counter') && !k.contains('.assets.') }
        def gauges   = metrics.findAll { String k, v -> k.startsWith('gauge') && !k.contains('.assets.') }

        metrics.countersByStatus = counters.collect { String k, v ->
            List brokenKey = k.tokenize(/./)

            if(!brokenKey || brokenKey.size() < 4 ) {
                return [:]
            }

            String name = brokenKey[3..-1].join(/./)

            [
                name: name != 'star-star' ? name : "/**",
                status: brokenKey[2],
                value: v as int,
                gauge: gauges.findResult { key, value -> key == "gauge.response.$name" ? value as int : null }
            ]
        }

        metrics
    }

    def traceUtility(def trace) {
        if(!trace) return [:]

        Map traceMap = [trace: trace]
        traceMap.paginatedTrace = trace.collate(10).indexed()

        traceMap
    }

    def beansUtility(def beans) {
        if(!beans) return [:]
        [beans: beans]
    }

    Map mappingsUtility(def mappings) {
        if(!mappings) return [:]

        mappings
    }
}
