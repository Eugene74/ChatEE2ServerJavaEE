
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
    <title>WelcomeChat</title>
    <link rel="stylesheet" href="questionstyle.css">
 </head>

<body>
<div class="welcome"><h2>WelcomeChatScanner</h2>
<br><h3>You will see all messages in chat</h3></div>
<table>
  <tr><td> Dir - <h1><c:out value="${fromLogin}"/></h1></td> </tr>
  <tr><td> all messages in chat below</td> </tr>
</table>

<form action="/weblistmessages" method="GET">
    <c:set var="f" value="${fromLogin}"/>
    <input  hidden type="radio" checked name="fromLogin" value="${f}" />
    <p> <input type="submit" value="reNew"/></p>
</form>

    <div class="common">
	 <p><b>Messages Common Chat:</b></p>
     <textarea rows="15" cols="60" name="text" disabled>
	 	 <c:if test="${not empty list}">
<c:forEach items="${list}" var="s">
[date-<c:out value="${s.date}"/> from-<c:out value="${s.from}"/> to-<c:out value="${s.to}"/>] text-<c:out value="${s.text}"/>
</c:forEach>
         </c:if>
	</textarea>
     </div>
     <div class="private">
	 <p><b>Messages Private Chat:</b></p>
    <textarea rows="15" cols="60" name="text" disabled>
             <c:if test="${not empty list1}">
 <c:forEach items="${list1}" var="s">
[date-<c:out value="${s.date}"/> from-<c:out value="${s.from}"/> to-<c:out value="${s.to}"/>] text-<c:out value="${s.text}"/>
 </c:forEach>
             </c:if>
	</textarea>
     </div>

</body>
</html>
