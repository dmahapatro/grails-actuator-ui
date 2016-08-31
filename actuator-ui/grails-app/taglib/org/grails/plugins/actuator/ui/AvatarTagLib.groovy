package org.grails.plugins.actuator.ui

import grails.plugins.GrailsPluginManager

class AvatarTagLib {

    static namespace = "actuator"

    GrailsPluginManager pluginManager

    /**
     * Outputs the Gravatar this is associated with the given email address
     *
     * @attr src    	Default image for asset-pipeline to be used when spring security or gravatar aren't enabled
     * @attr alt        alt-attribute for the resulting img-element
     * @attr cssClass   REQUIRED     class-attribute for the resulting img-element
     */
    def avatar = { attrs, body ->
        String alt = attrs.remove('alt') ?: "User Image"
        String cssClass = attrs.remove('cssClass')
        String src = attrs.remove('default')

        if(!pluginManager.hasGrailsPlugin("spring-security-core")) {
            out << asset.image(src: src, class: cssClass, alt: alt)
            return
        }

        def springSecurityService = grailsApplication.mainContext.getBean('springSecurityService')

        if (grailsApplication.config.actuator.gravatar.enabled
                && springSecurityService.isLoggedIn()) {
            out << gravatar.image(email: springSecurityService.principal.username, cssClass: cssClass, alt: alt)
        } else {
            out << asset.image(src: src, class: cssClass, alt: alt)
        }
    }

}
