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
<link rel="stylesheet" href="<c:url value="/theme/css/main.css"/>" type="text/css" />
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
                <h1>Detalles de la Tarea</h1>
            </div>
            <div class="row">
                <div class="span14">
                    <form method="post">
                        <input type="hidden" id="editing" name="editing" value="${editing}" />
                        <fieldset>
                            <div class="clearfix">
                                <label for="id">Id</label>
                                <div class="input">
                                    <input type="text" size="30" name="id" id="id" class="xlarge" disabled value="${task.id}" />
                                </div>
                            </div>
                            <div class="clearfix">
                                <label for="name">Nombre</label>
                                <div class="input">
                                    <input type="text" size="30" name="name" id="name" class="xlarge" <c:if test="${!editing}">disabled</c:if> value="${task.name}" />
                                </div>
                            </div>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="actions">
                                    <c:if test="${!editing}">
                                        <a href="task.html?id=${task.id}&editing=true" class="btn primary">Editar</a>
                                    </c:if>
                                    <c:if test="${editing}">
                                        <input type="submit" id="action" name="action" class="btn primary" value="Guardar" />
                                        <a href="task.html?id=${task.id}" class="btn">Cancelar</a>
                                    </c:if>
                                </div>
                            </security:authorize>
                        </fieldset>
                    </form>
                </div>
            </div>
            <a href="<c:url value="/app/tasks.html"/>">Volver</a>
        </div>

        <footer>
            <p>&copy; Company</p>
        </footer>

    </div>
    <!-- /container -->
</body>

</html>