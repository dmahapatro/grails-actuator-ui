package org.grails.plugins.actuator.ui

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ActuatorDashboardService)
class ActuatorDashboardServiceSpec extends Specification {

    void "test metrics utility adds uptime information in HH, mm and ss as additional properties based on pre-defined properties"() {
        when:
        def result = service.metricsUtility(metrics)

        then:
        result.uptimeHours == hour
        result.uptimeMinutes == minutes
        result.uptimeSeconds == seconds

        where:
        metrics                       || hour || minutes || seconds
        [:]                           || null || null    || null
        ["instance.uptime": null]     || null || null    || null
        ["instance.uptime": 0]        || null || null    || null
        ["instance.uptime": 'blah']   || null || null    || null
        ["instance.uptime": 10000]    || 0    || 0       || 10
        ["instance.uptime": 36342552] || 10   || 5       || 42
    }

    void "test metrics utility to add free memory percentage as additional properties"() {
        when:
        def result = service.metricsUtility([mem: memory, 'mem.free': free])

        then:
        result.freeMemPercent == freeMemoryPercent

        where:
        memory | free || freeMemoryPercent
        null   | null || null
        0      | 0    || null
        200    | 175  || 87
    }

    void "test metrics utility to add heap percentage as additional properties"() {
        when:
        def result = service.metricsUtility([heap: heap, 'heap.used': used])

        then:
        result.heapUsedPercent == heapPercent

        where:
        heap | used || heapPercent
        null | null || null
        0    | 0    || null
        200  | 175  || 87
    }

    void "test counter and gauge value is parsed and added to the metrics"() {
        when:
        String counterKey = ['counter', 'status', status, resource].join(/./)
        String gaugeKey = ['gauge', 'response', resource].join(/./)
        def result = service.metricsUtility([
            (counterKey): counterValue,
            (gaugeKey): gaugeValue
        ])

        then:
        result.countersByStatus == countersByStatus

        where:
        resource    | status | counterValue | gaugeValue || countersByStatus
        "foo.bar"   | 200    | 4            | 100        || [[name: 'foo.bar', status: '200', value: 4, gauge: 100]]
        ""          | 200    | 4            | 100        || [[:]]
        "star-star" | 201    | 6            | 234        || [[name: '/**', status: '201', value: 6, gauge: 234]]
    }
}
