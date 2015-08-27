package com.grails.plugin

import geb.spock.GebReportingSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.grails.plugins.actuator.ui.ActuatorEndpointsConfig
import org.springframework.beans.factory.annotation.Autowired
import pages.DashboardPage
import pages.LoginPage
import spock.lang.Stepwise

@Integration
@Rollback
@Stepwise
class ActuatorUiSpec extends GebReportingSpec {
    @Autowired
    ActuatorEndpointsConfig actuatorUiConfig

    def cleanup() {
        actuatorUiConfig.managementProperties = [:]
        actuatorUiConfig.endpointsProperties = [:]
    }

    void "test dashboard"() {
        when:
        go "actuator/dashboard"

        then:
        at LoginPage

        when:
        loginModule.login()

        then:
        at DashboardPage

        and: "The title is correct"
        $('title').text() == "actuator-ui-app | Admin"

        and: "section header has app name"
        $('section.content-header').$('h1').text().startsWith "actuator-ui-app"

        and: "first row has application health, metrics and metadata"
        $('section.content').find("div.row", 0).find("p")*.text() == ["Status", "System Uptime", "Threads", "Active HTTP Sessions"]

        and: "second row shows system information"
        $('section.content').find("div.row", 1).find(".info-box-text")*.text()[1, 3] == ['Java', 'PID']

        and: "third row shows HTTP Call statistics"
        $('section.content').find("div.row", 2).find(".box-title").text() == "HTTP Call Statistics"
    }

    void "test dashboard shows the http call statistics after an http call is made"() {
        when: "go to traceability page"
        go "actuator/traceability"

        and: "follow with going to dashboard page"
        to DashboardPage

        then: "http call statistic section is shown as the third row above memory chart"
        $('section.content').find("div.row", 2).find(".box-title").text() == "HTTP Call Statistics"
        $("table", id: "counterGauge").children("tbody").children().size() > 0
    }

    void "test traceability shows the http call trace"() {
        when: "click on traceability menu"
        go "actuator/traceability"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text() == "HTTP Call Trace"
        $('table', id: 'traceTable').find("tbody").children().size() > 0
    }

    void "test beans shows the available beans in application context"() {
        when: "click on traceability menu item"
        go "actuator/beans"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text().startsWith "Beans loaded to Context:"
        $('table', id: 'beansTable').find("code")*.text().intersect(['actuatorDashboardService', 'application'])
    }

    void "test mapping shows all of the url mappings in the application"() {
        when: "click on mappings menu item"
        go "actuator/mappings"

        then: "http call trace is shown in a data table"
        $('section', class: 'content').find(".box-title").text() == "Request Mappings"
    }

    void "test user is forwarded to error page with status 406 if endpoints are not enabled"() {
        given: 'endpoints are not enabled'
        actuatorUiConfig.endpointsProperties = [enabled: false]

        when:
        to DashboardPage

        then: 'user forwarded to error page'
        $('section', class: 'content').find("h2.headline").text() == "406"
        $('section', class: 'content').find("h3").text() == "Not Acceptable"
        $('section', class: 'content').find(".error-content").find("div").text() ==
            "Application configuration has some of the endpoints disabled. Check config property 'endpoints.enabled' should be true."
    }

    void "test user is forwarded to error page with status 406 if particular endpoint(s) is/are disabled"() {
        given: 'endpoints are not enabled'
        actuatorUiConfig.endpointsProperties = [health: [enabled: false]]

        when:
        to DashboardPage

        then: 'user forwarded to error page'
        $('section', class: 'content').find("h2.headline").text() == "406"
        $('section', class: 'content').find("h3").text() == "Not Acceptable"
        $('section', class: 'content').find(".error-content").find("div").text() ==
            "Application configuration has some of the endpoints disabled. Check config property 'endpoints.*.enabled' should be true."
    }

    void "test user is forwarded to error page with status 505 if http is not enabled for actuator"() {
        given: 'management port is set to -1 to disable http endpoints'
        actuatorUiConfig.managementProperties = [port: -1]

        when:
        to DashboardPage

        then: 'user forwarded to error page'
        $('section', class: 'content').find("h2.headline").text() == "505"
        $('section', class: 'content').find("h3").text() == "HTTP Version not supported"
        $('section', class: 'content').find(".error-content").find("div").text() ==
            "Application configuration does not allow HTTP call for actuator endpoints. Check config property 'management.port' should not be -1."
    }

    void "test user is forwarded to error page with status 501 if port or address is customized"() {
        given: 'management port is set to -1 to disable http endpoints'
        actuatorUiConfig.managementProperties = configuration

        when:
        to DashboardPage

        then: 'user forwarded to error page'
        $('section', class: 'content').find("h2.headline").text() == "501"
        $('section', class: 'content').find("h3").text() == "Not Implemented"
        $('section', class: 'content').find(".error-content").find("div").text() ==
            "Coming Soon... Support for custom address and port for actuator. Check config property 'management.port' and 'management.address' should not be specified."

        where:
        configuration << [
            [port: 8081],
            [address: '127.0.0.1'],
            [port: 8081, address: '127.0.0.1']
        ]
    }
}
