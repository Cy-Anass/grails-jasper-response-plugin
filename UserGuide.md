# Introduction #

This plugin aims to render as PDF or HTML reports built on JasperReport (or IReport) in a very simple way.

## Why this plugin? ##

  * Legacy reports are more likely to expect simple parameters and use big/complex SQL queries.
  * Most developers prefer build reports using IReport IDE and SQL Queries. Even more when we are talking about subreports.
  * Refactor reports built on "parameters -> report" approach to "model -> report" is a hard task. Again, even more when we are talking about subreports.

# ---------------------------------------- #
# Installation #

As always: <br>
<code>install-plugin jasper-response</code>

or<br>
<br>
<code>compile ":jasper-response:1.0.1"</code>
at <b>BuildConfig.groovy</b>

Or:<br>
<ol><li><a href='http://code.google.com/p/grails-jasper-response-plugin/downloads/list'>Download</a> the .zip<br>
</li><li><code>install-plugin /path_to_your_jar/grails-jasper-response-x.zip</code></li></ol>

<b>Important:</b> This plugin does not bring JasperReport library along with it. As of JasperReport <b>3.6.0</b> is compatible.<br>
<br>
<h1>----------------------------------------</h1>
<h1>Usage Example</h1>

After install the plugin and a compatible JasperReport library, let's do the following...<br>
<br>
<h2>1. Build a JaperReport using IReport</h2>
It's a simple report that waits for one parameter (<b><i>"name"</i></b>).<br>
<img src='https://lh3.googleusercontent.com/-Rv5UDWK9-ms/T9PmLrNtcfI/AAAAAAAAAQY/xMkwlJTWV1E/s640/step01.jpg' />

<br>
<h2>2. Build the "BeeController" and the "report" action</h2>
<img src='https://lh3.googleusercontent.com/-Dg_8MLyD2Ow/UKU6S0rOXOI/AAAAAAAAASQ/mKhHMotjtgY/s521/controller.png' /><br>
<b>Important:</b> The above example take <b>params</b> to render the report, but any other Map object can be used.<br>
<br>
<br>
<br>
<h2>3. Build the "index.gsp" on "views/bee" folder</h2>
We could use GSP or HTML tags. The important is reach <b>report()</b> method at <b>BeeController</b>.<br>
<img src='https://lh4.googleusercontent.com/-Bg76VUZ9Cko/UKU6VDYmPTI/AAAAAAAAASQ/0oBXO5PnMX8/s425/form.png' /><br>
See that we have a <b>name</b> parameter sent to controller. Why <b>name</b>? Because <b>name</b> is the expected parameter in JasperReport file.<br>
<br>
<br>
<h2>4. Place the .jasper file into right place</h2>
Place the <b>".jasper"</b> file into <b>/view/bee/</b> folder. If the called method is <b>report()</b>, so file name must be <b>report.jasper</b>. <br>
<i>Yes, following Grails conventions!</i>.<br>If you prefer/need a custom name, no panic: Take a look at <b>Custom Configuration</b>.<br>
<br>
<br>
<h2>5. Run the application, and test</h2>
Access <i>http://host:port/context/bee/</i> <br>
<img src='https://lh3.googleusercontent.com/-tcpKENWJvZA/T9PmMSzu_iI/AAAAAAAAAQM/ZeQg8oUOC6I/s562/step04.jpg' />

<br>

Fill and submit the form <br>
<img src='https://lh5.googleusercontent.com/-Ckk9CxA3z5E/T9PmMxvmlXI/AAAAAAAAAP4/PuW27DUwc5k/s640/step05.png' />
<br>
<b>DONE! That's our PDF!</b> The file name is <b>report.pdf</b>. If you need a custom name, no panic: Take a look at <b>Custom Configuration</b>.<br>
<br>
<h1>----------------------------------------</h1>
<h1>Custom Configurations</h1>
The final step is always <i>render <Map object> as Jasper</i>. <b>The used Map must contain the required parameters in Jasper file</b>, but if you add some <i>specific parameters</i>, it's possible to customize the response. These parameters are:<br>
<table><thead><th> <b>Paramater</b> </th><th> <b>Default Value</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> jasperRenderType </td><td> <i>pdf</i>           </td><td> Choose between <b>pdf</b> and <b>html</b> </td></tr>
<tr><td> jasperForceDownload </td><td> <b>false</b>         </td><td> Force (<b>true</b>) or not (<b>false</b>) the report download </td></tr>
<tr><td> jasperDownloadName </td><td> <i>Same of invoked method at Controller</i> </td><td> File name of generated report. <i>Don't use extension!</i> </td></tr>
<tr><td> jasperSourceName </td><td> <i>Same of invoked method at Controller</i> </td><td> ".jasper" file name, if you don't want or can't use Grails conventions. <i>Don't use extension!</i></td></tr></tbody></table>

<br>
<h2>About ".jasper" file location</h2>

<b>If the ".jasper" file is into a folder at "view" folder</b><br>
The "jasperSourceName" parameter need starts with <b>"../"</b>. Example:<br>
<pre><code>params.jasperSourceName='../anotherController/anotherReport' <br>
</code></pre>
so the jasper-response-plugin would search <i>"view/anotherController/anotherReport.jasper"</i>

<br>
<b>If the ".jasper" file is into a folder at "web-app" folder</b><br>
The "jasperSourceName" parameter need starts with <b>"/"</b>. Example:<br>
<pre><code>params.jasperSourceName='/myCustomReportsFolder/myGeneralReport' <br>
</code></pre>
so the jasper-response-plugin would search <i>"/web-app/myCustomReportsFolder/myGeneralReport.jasper"</i>