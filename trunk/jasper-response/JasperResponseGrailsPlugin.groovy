class JasperResponseGrailsPlugin {

	def version = "0.0.9"
    
    def grailsVersion = "2.0 > *"
    
    def dependsOn = [:]
    
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
	
    def title = "Jasper Response Plugin"
    def author = "José Yoshiriro"
    def authorEmail = "jyoshiriro@gmail.com"
    def description = '''\
Easy way to render JasperReports as PDF or HTML in Grails Projects.
'''

    def documentation = "http://code.google.com/p/grails-jasper-response-plugin/w/list"

    def license = "APACHE"

    def developers = [ [ name: "Jose Yoshiriro", email: "jyoshiriro@gmail.com" ]]

    def issueManagement = [ system: "Google Code", url: "http://code.google.com/p/grails-jasper-response-plugin/issues/list" ]

    def scm = [ url: "http://code.google.com/p/grails-jasper-response-plugin/source/browse/" ]

    def doWithApplicationContext = { applicationContext ->
		String rootPath = applicationContext.getServletContext().getRealPath("/")
		rootPath = rootPath.substring(0,rootPath.indexOf("web-app"))
		grails.plugin.jyoshiriro.jasperResponse.renderers.Jasper.setWEBAPPROOTPATH(rootPath)
    }


}
