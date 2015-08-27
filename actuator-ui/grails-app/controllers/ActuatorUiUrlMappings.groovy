class ActuatorUiUrlMappings {
    static mappings = {
        "/actuator/dashboard"(controller: "actuatorDashboard", namespace: 'actuator')
        "/actuator/traceability"(controller: "actuatorDashboard", action: "traceability", namespace: 'actuator')
        "/actuator/beans"(controller: "actuatorDashboard", action: "springBeans", namespace: 'actuator')
        "/actuator/mappings"(controller: "actuatorDashboard", action: "allMappings", namespace: 'actuator')
        "/actuator/notAllowed"(view: "/actuatorDashboard/notAllowed")
    }
}
