<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>First Page</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
</head>
<body class="container-fluid">
<h3 class="pb-2">Page 1</h3>
<form method="post">
<div class="form-group">
    <label for="name">Please Select a User</label>
    <select class="form-control" name="name" id="name">
    	<c:forEach items="${userentries}" var="entry">	
			<option> ${entry.name}</option>
		</c:forEach>
    </select>
</div>
<button type="submit" class="btn btn-primary">Submit</button>
</form>
</body></html>