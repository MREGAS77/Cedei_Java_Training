<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
       <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/1.4.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="<c:url value="/theme/css/main.css"/>" type="text/css" />
<title>Curso Spring</title>
</head>

<body>
    <div class="topbar">
        <div class="fill">
            <div class="container">
                <a class="brand" href="<c:url value='/'/>">Curso Spring</a>
            </div>
        </div>
    </div>

    <div class="container">

        <div class="content">
            <div class="page-header">
                <h1>Access Denied</h1>
            </div>
            <div class="row">
                <div class="span14">
                    <p>Sorry, Access Denied</p>
                    <p><a href="<c:url value='/'/>">Return to Home Page</a> or
                    <p><a href="<c:url value='/logout'/>">Logout</a></p>
                </div>
            </div>
        </div>

        <footer>
            <p>&copy; Company</p>
        </footer>

    </div>
    <!-- /container -->
</body>

</html>