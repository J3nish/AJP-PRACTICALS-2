

<html>
<head>
    <title>Sort Numbers Custom Tag</title>
</head>

    <h2>Enter 10 Numbers (comma-separated)</h2>
    <form method="post">
        <input type="text" name="numbers" required />
        <select name="order">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
        <input type="submit" value="Sort" />
    </form>

    <% 
        String numbers = request.getParameter("numbers"); 
        String order = request.getParameter("order"); 
        if (numbers != null && !numbers.isEmpty()) { 
    %>
    
    <!-- Pass values correctly using JSTL -->
    <c:set var="inputNumbers" value="<%= numbers %>" />
    <c:set var="sortOrder" value="<%= order %>" />
    
    <h3>Sorted Numbers:</h3>
    <c:sortNumbers numbers="${inputNumbers}" order="${sortOrder}" />
    
    <% } %>


</html>
