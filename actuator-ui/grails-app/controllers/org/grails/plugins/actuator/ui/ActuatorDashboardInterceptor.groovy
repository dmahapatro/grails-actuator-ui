package org.grails.plugins.actuator.ui

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.HTTP_VERSION_NOT_SUPPORTED
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE

@CompileStatic
class ActuatorDashboardInterceptor {
    ActuatorEndpointsConfig actuatorUiConfig

    ActuatorDashboardInterceptor() {
        match(controller: "actuatorDashboard", namespace: "actuator")
    }

    boolean before() {
        if ( actuatorUiConfig.isHttpDisabled() ) {
            return httpNotAllowed()
        } else if ( !actuatorUiConfig.monitoringEnabled ) {
            return monitoringNotAllowed()
        } else if ( !actuatorUiConfig.dashboardItemsEnabled ) {
            return endpointsNotEnabled()
        } else if ( actuatorUiConfig.port || actuatorUiConfig.address ) {
            return currentlyUnsupported()
        }

        true
    }

    private boolean httpNotAllowed() {
        orchestrateModelAndRedirect(
            HTTP_VERSION_NOT_SUPPORTED,
            "Application configuration does not allow HTTP call for actuator endpoints. Check config property 'management.port' should not be -1."
        )
        false
    }

    private boolean monitoringNotAllowed() {
        orchestrateModelAndRedirect(
            NOT_ACCEPTABLE,
            "Application configuration has some of the endpoints disabled. Check config property 'endpoints.enabled' should be true."
        )
        false
    }

    private boolean endpointsNotEnabled() {
        orchestrateModelAndRedirect(
            NOT_ACCEPTABLE,
            "Application configuration has some of the endpoints disabled. Check config property 'endpoints.*.enabled' should be true."
        )
        false
    }

    private boolean currentlyUnsupported() {
        orchestrateModelAndRedirect(
            NOT_IMPLEMENTED,
            "Coming Soon... Support for custom address and port for actuator. Check config property 'management.port' and 'management.address' should not be specified."
        )
        false
    }

    private void orchestrateModelAndRedirect(HttpStatus httpStatus, String message) {
        redirect uri: "/actuator/notAllowed", params: [ error: httpStatus.value(), errorDescription: httpStatus.reasonPhrase, errorDetail: message ]
    }
}
