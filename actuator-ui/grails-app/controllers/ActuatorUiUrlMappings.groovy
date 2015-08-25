class ActuatorUiUrlMappings {
    static mappings = {
        "/actuator/dashboard"(controller: "actuatorDashboard")
        "/actuator/traceability"(controller: "actuatorDashboard", action: "traceability")
        "/actuator/beans"(controller: "actuatorDashboard", action: "springBeans")
        "/actuator/mappings"(controller: "actuatorDashboard", action: "allMappings")
    }
}
