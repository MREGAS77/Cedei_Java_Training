<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Curso Spring 2011</title>
</head>

<body>
    <div class="topbar">
        <div class="fill">
            <div class="container">
                <a class="brand" href="<c:url value='/'/>">Curso Spring 2011</a>
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
                    <form action="#">
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
                                    <input type="text" size="30" name="name" id="name" class="xlarge" disabled value="${task.name}" />
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
            <a href="<c:url value="/app/tasks.html"/>">Volver</a>
        </div>

        <footer>
            <p>&copy; Company 2011</p>
        </footer>

    </div>
    <!-- /container -->
</body>

</html>