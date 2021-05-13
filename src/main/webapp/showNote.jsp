<!DOCTYPE html>

<%@page import="java.sql.Date" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

</head>
<body>

	<div class="container">
		<h2>Note</h2>
		<table class="table table-striped">
			<tbody>
				<tr>
					<th>Name</th>
					<td>${note.noteName}</td>
				</tr>
				<tr>
					<th>StartDate</th>
					<td>${note.startDate}</td>
				</tr>
				<tr>
					<th>EndDate</th>
					<td>${ note.endDate}</td>
				</tr>
				<tr>
					<th>RemainderDate</th>
					<td>${note.remainderDate}</td>
				</tr>
				<tr>
					<th>Status</th>
					<td>${ note.statusName}</td>
				</tr>
				<tr>
					<th>Tag</th>
					<td>${note.tagName}</td>
				</tr>
				<tr>
					<th>Description</th>
					<td height="100px">${note.noteDescription}</td>
				</tr>
				
			</tbody>
		</table>
			<a id="noteViews" href="editNoteBook?itemId=${note.notebook_id}">Back
												
												</a>
	</div>
    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>
</body>
</html>
