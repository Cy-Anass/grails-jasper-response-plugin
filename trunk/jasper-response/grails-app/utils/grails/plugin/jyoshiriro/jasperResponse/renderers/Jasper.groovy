package grails.plugin.jyoshiriro.jasperResponse.renderers


import java.sql.Connection

import javax.servlet.http.HttpServletResponse

import org.codehaus.groovy.grails.web.converters.AbstractConverter
import org.codehaus.groovy.grails.web.converters.Converter
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import org.codehaus.groovy.grails.web.sitemesh.GrailsContentBufferingResponse

/**
 * Jasper Render class. Use an Map object as parameter <i>(Example: <b>render params as JASPER</b>)</i>. <br/>
 * This Map may contain report parameters and plugin configuration parameters, as follows:<br/>
 * <p>Configuration Parameters:
 * <ul>
 * <li><b>jasperSourceName</b> - Name of Jasper file (by default, it follows Grails convention)</li>
 * <li><b>jasperDownloadName</b> - Name of downloaded file (by default, it follows Grails convention)</li>
 * <li><b>jasperRenderType</b> - Render Type. Default: <i>"pdf"</i>. Another option: <i>"htmlFile"</i></li>
 * </ul>
 * </p>
 * @author Jose Yoshiriro jyoshiriro@gmail.com
 *
 */
class Jasper extends AbstractConverter<JasperWriter> implements Converter<JasperWriter> {

	static String WEBAPPROOTPATH
	
	Writer out
	def target
	
	@Override
	public void render(Writer out) throws ConverterException {
		this.out= out
		
	}

	@Override
	public void render(HttpServletResponse response) throws ConverterException {
		
		GrailsContentBufferingResponse r = response as GrailsContentBufferingResponse
		Connection connection = r.webAppContext.request.getAttribute('jasperJdbcConnection')
		
		Map params = target as Map?target as Map:closureMap
		if (!params) throw new MissingPropertyException("No Map used into \"render\" closure. Example: \"render params as JasperPdf\"")
		
		String renderType = params.containsKey('jasperRenderType')?params.jasperRenderType:"Pdf"
		String action = params.containsKey('jasperSourceName')?params.jasperSourceName:params.action
		String controller = params.controller
		Boolean forceDownload = params.containsKey('jasperForceDownload')?params.jasperForceDownload as Boolean:false
		
		String downloadFileName = params.containsKey('jasperDownloadName')?params.jasperDownloadName:action
		downloadFileName += (renderType=="Pdf"?".pdf":".html").toLowerCase()
		
		String path = "${WEBAPPROOTPATH}/grails-app/views/${controller}/${action}.jasper"
		
		def jasperResponseStream = net.sf.jasperreports.engine.JasperRunManager."runReportTo${renderType.capitalize()}"(path,params,connection);
		
		byte[] responseStream = jasperResponseStream instanceof byte[]?jasperResponseStream:new File(jasperResponseStream).bytes 
		
		response.setHeader("Content-Disposition","${forceDownload?'attachment':'inline'};filename=${downloadFileName}");
		response.setContentType(renderType.equalsIgnoreCase("pdf")?"application/pdf":"text/html")
		response.setContentLength(responseStream.size())
		response.outputStream << responseStream
		
		render(response.writer)
		
	}

	@Override
	public JasperWriter getWriter() throws ConverterException {
		return new JasperWriter(writer:out);
	}

	@Override
	public void convertAnother(Object o) throws ConverterException {
		
	}

	@Override
	public void build(Closure c) throws ConverterException {
		
	}

	@Override
	public ObjectMarshaller<? extends Converter> lookupObjectMarshaller(
			Object target) {
		return null;
	}

	@Override
	public void setTarget(Object target) {
			this.target = target
	}

}
