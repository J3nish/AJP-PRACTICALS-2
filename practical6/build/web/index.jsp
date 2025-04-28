<!DOCTYPE html>
<html>
  <head>
    <title>Request Redirection</title>
  </head>
  <body>
    <h2>Enter the keyword to search:</h2>
    <form action="RedirectServlet" method="POST">
      <label for="location">Search: </label>
      <input type="text" id="location" name="location" placeholder="Enter search term" required />
      <input type="submit" value="Go" />
    </form>
  </body>
</html>
