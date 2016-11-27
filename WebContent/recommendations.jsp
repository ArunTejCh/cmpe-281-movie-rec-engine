<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="com.cmpe281.project2.entities.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div style="padding-left: 50px;">
		<h2>
			<span> Movie Recommendations </span>
		</h2>
	</div>
	<br>
	<%

Object message = request.getAttribute("message");
Object moviesNotPresent = request.getAttribute("haveMovies");	
List<Movie> mList = (List<Movie>)request.getAttribute("movieList");	

%>
	<div class="inner contact">
		<!-- Form Area -->
		<div class="contact-form">
			<!-- Form -->
			<form id="contact-us" method="post" action="recommendations">
				<!-- Left Inputs -->
				<div class="col-xs-6 wow animated slideInLeft" data-wow-delay=".5s">
					<!-- Name -->
					<input type="text" name="name" id="name" required="required"
						class="form" placeholder="Movie Title" />
					<!-- Email -->
					<!-- <label for="min_rating">Minimum Rating</label>
                            <input type="range" name="min_rating" id="min_rating" class="form" min="1" max="4" value="2" step="1" oninput="outputUpdate(value)" placeholder="Minimum Rating" />
                            <output for="min_rating" id="rating">2</output>
                            <script type ="text/javascript">
                            function outputUpdate(vol) {
                            	document.querySelector('#rating').value = vol;
                            }
                            </script>
                            Subject
                            <input type="range" name="subject" id="subject" class="form" placeholder="Minimum No of Reviews" />-->
				</div>
				<!-- End Left Inputs -->
				<!-- Right Inputs -->
				<div class="col-xs-6 wow animated slideInRight" data-wow-delay=".5s">
					<!-- Message -->
					<div id="results" class="form textarea"
						style="min-width: 100%; min-height: 320px; overflow: scroll;">
						<% if(message == null){ %>
						<p>Results....</p>
						<% } else{%>
						<p>${message}</p>
						<% if(moviesNotPresent == null){%>
						<table cellspacing="10" >
								<tr>
									<th>Movie title    </th>
									<th>Rating         </th>
									<th>No. of Reviews </th>
								</tr>
							<c:forEach items="${movieList}" var="movie">
								<tr>
									<td><c:out value="${movie.getTitle()}" /></td>
									<td><c:out value="${movie.getAvgRating()}" /></td>
									<td><c:out value="${movie.getNoOfReviews()}" /></td>
								</tr>
							</c:forEach>
						</table>
						<% } %>
						<% }%>
					</div>
					<!-- <textarea name="message" id="message" class="form textarea"  placeholder="Results"></textarea> -->
				</div>
				<!-- End Right Inputs -->
				<!-- Bottom Submit -->
				<div class="relative fullwidth col-xs-12">
					<!-- Send Button -->
					<button type="submit" id="submit" name="submit"
						class="form-btn semibold">Get Recommendations</button>
				</div>
				<!-- End Bottom Submit -->
				<!-- Clear -->
				<div class="clear"></div>
			</form>

			<!-- Your Mail Message -->
			<div class="mail-message-area">
				<!-- Message -->
				<div class="alert gray-bg mail-message not-visible-message">
					<strong>Thank You !</strong> Your email has been delivered.
				</div>
			</div>

		</div>
		<!-- End Contact Form Area -->
	</div>
	<!-- End Inner -->
</body>
</html>