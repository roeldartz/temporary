<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Second Page</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
</head>
<body class="container-fluid">
<h3 class="pb-2">Page 2</h3>
<form method="post">
<label for="name">${name}</label>
<table class ="table">

<tr>
	<th scope="col">Systolic</th>
	<th scope="col">Diastolic</th>
	<th scope="col">Time</th>

</tr>
<tr>
	<th scope="col"><input type="text" class="form-control" name ="systolic" id="systolic"/></th>
	<th scope="col"><input type="text" class="form-control" name ="diastolic" id="diastolic"/></th>
	<th scope="col"><button type="submit" class="btn btn-primary">Add Reading</button></th>
</tr>
<c:forEach items="${bloodpressureentries}" var="entry">
<tr>
		<c:choose>
			<c:when test="${entry.systolic < 120}">
				<td class="text-success">
				<c:out value="${entry.systolic}"/>
				</td>
			</c:when>
			<c:when test="${entry.systolic >= 120 && entry.systolic <= 180}">
				<td class="text-warning">
					<c:out value="${entry.systolic}"/>
				</td>
			</c:when>
			<c:when test="${entry.systolic > 180}">
				<td class="text-danger">
					<c:out value="${entry.systolic}"/>
				</td>
			</c:when>
			<c:otherwise>
				<c:out value="${entry.systolic}"/>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${entry.diastolic < 80}">
				<td class="text-success">
					<c:out value="${entry.diastolic}"/>
				</td>
			</c:when>
			<c:when test="${entry.diastolic >= 80 && entry.systolic <= 110}">
				<td class="text-warning">
					<c:out value="${entry.diastolic}"/>
				</td>
			</c:when>
			<c:when test="${entry.diastolic > 110}">
				<td class="text-danger">
					<c:out value="${entry.diastolic}" />
				</td>
			</c:when>
			<c:otherwise>
				<c:out value="${entry.diastolic}"/>
			</c:otherwise>
		</c:choose>

	<td><c:out value = "${entry.time}" /></td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>