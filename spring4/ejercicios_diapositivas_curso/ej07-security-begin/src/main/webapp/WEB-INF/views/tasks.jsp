<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
       <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/1.4.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<c:url value="/theme/css/main.css"/>" type="text/css"/>
    <title>Curso Spring</title>
</head>

<body>
    <div class="topbar">
        <div class="fill">
            <div class="container">
                <a class="brand" href="<c:url value='/'/>">Curso Spring</a>
                <p class="pull-right">Logged in as <security:authentication property="principal.username"/> <a href="<c:url value='/logout'/>">Salir</a></p>
            </div>
        </div>
    </div>

    <div class="container">

        <div class="content">
            <div class="page-header">
                <h1>Listado de tareas disponibles</h1>
            </div>
            <div class="row">
                <div class="span14">
                    <table class="bordered-table zebra-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tasks}" var="task">
                            <tr>
                                <td><a href="<c:url value="/app/task.html?id=${task.id}"/>">${task.id}</a></td>
                                <td><a href="<c:url value="/app/task.html?id=${task.id}"/>">${task.name}</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>    
                    </table>              
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