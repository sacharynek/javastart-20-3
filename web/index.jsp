<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 12.07.2019
  Time: 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form method="post" action="/currency">
    <label> Ilość Waluty
        <input type="text" name="value"/>
    </label>
    <label>Waluta
        <select name="currency">
            <option>USD</option>
            <option>CHF</option>
            <option>EUR</option>
        </select></label>
    <label>Data<input type="date" name="date"/></label>
    <button type="submit">Przelicz</button>
</form>
</body>
</html>
