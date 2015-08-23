package com.grails.plugin

import org.joda.time.Period

class ActuatorDashboardService {

    Map metricsUtility(def metrics) {
        if(!metrics) return [:]

        // Uptime
        if(metrics.uptime) {
            Period uptimePeriod = new Period(metrics.uptime as Long)
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
        if ( metrics.heap && metrics["heap.init"] && metrics["heap.committed"] && metrics["heap.used"] ) {
            metrics.heapUsedPercent = ( metrics["heap.used"] / metrics.heap ) / 100 as int
        }

        return metrics
    }

    Map traceUtility(def trace) {
        if(!trace) return [:]

        Map traceMap = [trace: trace]

        def callsGroupedByContentType = trace.groupBy { it.info.headers.response."Content-Type" }

        traceMap.callsByContentType = callsGroupedByContentType

        traceMap
    }
}
