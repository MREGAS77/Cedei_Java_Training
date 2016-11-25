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
                <h1>Login</h1>
            </div>
            <div class="row">
                <div class="span14">
                    <c:if test="${!empty  param.login_error}">
                        <div class="alert-message error">
                            <p>Usuario o password Incorrectos</p>    
                        </div>
                    </c:if>


                    <form action="<c:url value='/login'/>" method="post">
                        <fieldset>
                            <div class="clearfix">
                                <label for="username">Username:</label>
                                <div class="input">
                                    <input type="text" size="30" name="username" id="username" class="xlarge"  />
                                </div>
                            </div>
                            <div class="clearfix">
                                <label for="password">Password:</label>
                                <div class="input">
                                    <input type="text" size="30" name="password" id="password" class="xlarge"  />
                                </div>
                            </div>
                            <div class="actions">
                                <input class="btn primary" name="submit" type="submit" value="Login"/>
                            </div>
                        </fieldset>
                        
                    </form>
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