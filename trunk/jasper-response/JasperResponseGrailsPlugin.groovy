

import org.codehaus.groovy.grails.commons.spring.GrailsWebApplicationContext;

class JasperResponseGrailsPlugin {
    // the plugin version
    def version = "0.0.9"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Jasper Response Plugin" // Headline display name of the plugin
    def author = "José Yoshiriro"
    def authorEmail = "jyoshiriro@gmail.com"
    def description = '''\
Easy way to render JasperReports as PDF or HTML in Grails Projects.
'''

    // URL to the plugin's documentation
    def documentation = "http://code.google.com/p/grails-jasper-response-plugin/w/list"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://code.google.com/p/grails-jasper-response-plugin/source/browse/" ]
//  def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
		GrailsWebApplicationContext gac = applicationContext as GrailsWebApplicationContext  
		String rootPath = gac.getServletContext().getRealPath("/")
		rootPath = rootPath.substring(0,rootPath.indexOf("web-app"))
		grails.plugin.jyoshiriro.jasperResponse.renderers.Jasper.setWEBAPPROOTPATH(rootPath)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
