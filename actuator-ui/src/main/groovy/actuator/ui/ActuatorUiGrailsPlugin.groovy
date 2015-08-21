package actuator.ui

import grails.plugins.*

class ActuatorUiGrailsPlugin extends Plugin {
    def grailsVersion = "3.0.0 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Actuator UI" // Headline display name of the plugin
    def author = "Dhiraj Mahapatro"
    def authorEmail = "dhiraj.mahapatro@gmail.com"
    def description = '''\
Actuator UI is the UI representation of Spring Boot actuator endpoints for an app.
The response from HTTP endpoints available in a Grails 3 (Spring Boot) app is projected in a user friendly administration console.
'''
    def profiles = ['web']
    def documentation = "http://grails.org/plugin/actuator-ui"
    def license = "APACHE"
    //def developers = [ [ name: "", email: "" ]]

    // Location of the plugin's issue tracker.
    // def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    // def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
        } 
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
