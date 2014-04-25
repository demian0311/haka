<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Haka | <g:layoutTitle/></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />
	</head>
	<body>
        <div class="site">
            <div class="header">
                <h1><g:link controller="project" action="index">Haka</g:link> | <g:layoutTitle/></h1>
            </div>

		    <g:layoutBody/>

            <div class="footer">
                <div class="printonly">http://neidetcher.com/index.html</div>
                <g:if test="${start}">
                    ${System.currentTimeMillis() - start}ms
                </g:if>
            </div>
            <r:layoutResources />
        </div>
	</body>
</html>
