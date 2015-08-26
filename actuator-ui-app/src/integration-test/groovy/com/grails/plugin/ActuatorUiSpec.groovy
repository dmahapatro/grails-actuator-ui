package com.grails.plugin

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

@Integration
@Rollback
class ActuatorUiSpec extends GebSpec {

    void "test dashboard"() {
        when: "The home page is visited"
        go '/actuator/dashboard'

        then: "The title is correct"
        $('title').text() == "actuator-ui-app | Admin"

        and: "section header has app name"
        $('section.content-header').$('h1').text().startsWith "actuator-ui-app"

        and: "first row has application health, metrics and metadata"
        $('section.content').find("div.row", 0).find("p")*.text() == ["Status", "System Uptime", "Threads", "Active HTTP Sessions"]

        and: "second row shows system information"
        $('section.content').find("div.row", 1).find(".info-box-text")*.text()[1, 3] == ['Java', 'PID']

        and: "third row shows Memory and heap statistics"
        $('section.content').find("div.row", 2).find(".box-title").text() == "Memory (KB)"
    }

    void "test dashboard shows the http call statistics after an http call is made"() {
        when: "go to traceability page"
        go "/actuator/traceability"

        and: "follow with going to dashboard page"
        go "/actuator/dashboard"

        then: "http call statistic section is shown as the third row above memory chart"
        $('section.content').find("div.row", 2).find(".box-title").text() == "HTTP Call Statistics"
        $("table", id: "counterGauge").children("tbody").children().size() > 0
    }

    void "test traceability shows the http call trace"() {
        when: "click on traceability menu"
        go "/actuator/traceability"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text() == "HTTP Call Trace"
        $('table', id: 'traceTable').find("tbody").children().size() > 0
    }

    void "test beans shows the available beans in application context"() {
        when: "click on traceability menu item"
        go "/actuator/beans"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text().startsWith "Beans loaded to Context:"
        $('table', id: 'beansTable').find("code")*.text().intersect(['actuatorDashboardService', 'application'])
    }

    void "test mapping shows all of the url mappings in the application"() {
        when: "click on mappings menu item"
        go "/actuator/mappings"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text() == "Request Mappings"
    }
}
