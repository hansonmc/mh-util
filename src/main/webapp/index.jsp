<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript">

</script>
<html>
    <body>
        <h2>Hello World!</h2>
        <h1><a id="aa" href="${ctx}/hello.do">点我</a> </h1>
    </body>
</html>
