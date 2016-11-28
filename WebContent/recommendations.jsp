<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="com.cmpe281.project2.entities.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recommendation Portal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">

 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 	
 	
   <script>
  $( function() {
    $( "#rating-slider").slider({
      range: "max",
      min: 1,
      max: 5,
      value: 1,
      step:0.5,
      slide: function( event, ui ) {
        $( "#min_rating" ).val( ui.value );
      }
    });
    $( "#min_rating" ).val( $("#rating-slider" ).slider( "value" ) );
  } );
  </script>	
  
  
   <script>
  $( function() {
    $( "#reviews-slider").slider({
      range: "max",
      min: 100,
      max: 10000,
      value: 500,
      step: 100,
      slide: function( event, ui ) {
        $( "#min_reviews" ).val( ui.value );
      }
    });
    $( "#min_reviews" ).val( $("#reviews-slider" ).slider( "value" ) );
  } );
  </script>	
  


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
			<form id="contact-us" method="post" action="recommendations"  >
				<!-- Left Inputs -->
				<div class="col-xs-6 wow animated slideInLeft" data-wow-delay=".5s">
					<!-- Name -->
					<input type="text" name="name" id="name" required="required"
						class="form" placeholder="Movie Title" />
					
					
					<p  style = "padding-left : 6px; padding-bottom : 3px">
					<label for="min_rating">Minimum average rating :</label>
					<input type="text" name ="min_rating" id="min_rating"  style="border:0; color:#f6931f; font-weight:bold;">	
					</p>
					
					<div id="rating-slider" style = "max-width:100px;margin-left:7px;margin-bottom:25px"  ></div>
					
					
					<p style = "margin-top :10px;padding-left:6px;padding-bottom:3px" >	
					<label for="min_reviews">Minimum no of reviews :</label>
					<input type="text" name = "min_reviews" id="min_reviews"  style="border:0; color:#f6931f; font-weight:bold;" >	
					</p>
					
					<div id="reviews-slider" style = "max-width:250px;margin-left:7px" ></div>
					
					
					
					
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

			

		</div>
		<!-- End Contact Form Area -->
	</div>
	<!-- End Inner -->
</body>
</html>