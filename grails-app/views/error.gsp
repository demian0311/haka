<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css"></g:if>
	</head>
	<body>
		<g:if env="production">
            <ul class="errors">
                <li>An error has occurred</li>
            </ul>
		</g:if>
		<g:else>
            <g:renderException exception="${exception}" />
		</g:else>
	</body>
</html>
