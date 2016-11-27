package com.cmpe281.project2.restapis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmpe281.project2.db.AwsRdsDBImpl;
import com.cmpe281.project2.db.RecEngineDBLayerInterface;
import com.cmpe281.project2.entities.Movie;
import com.cmpe281.project2.recoengine.RecoEngineInterface;
import com.cmpe281.project2.recoengine.SimpleRecoEngineImpl;

/**
 * Servlet implementation class MovieRecApiServlet
 */
public class MovieRecApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RecEngineDBLayerInterface db = AwsRdsDBImpl.getInstance();
	RecoEngineInterface recoEngine = new SimpleRecoEngineImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieRecApiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String movieTitle = request.getParameter("name");
		List<Movie> similarTitles = db.getMoviesByTitle(movieTitle);
		if (similarTitles != null && similarTitles.size() != 0) {

			if (similarTitles.size() == 1) {
				List<Movie> recoList = recoEngine.getRecommendations(similarTitles.get(0));
				if (recoList != null) {
					request.setAttribute("message", "The following movies are recommended for viewers of "+ similarTitles.get(0).getTitle());
					request.setAttribute("movieList", recoList);
					request.getRequestDispatcher("recommendations.jsp").forward(request, response);
					
				
				} else {
					request.setAttribute("haveMovies", "false");
					request.setAttribute("message", "Sorry!! No recommendations available for this criteria :( ");
					request.getRequestDispatcher("recommendations.jsp").forward(request, response);
				}
			} else {
				
				request.setAttribute("message", "Please select one of the following title names and search again.");
				request.setAttribute("movieList", similarTitles);
				request.getRequestDispatcher("recommendations.jsp").forward(request, response);
				
			}
		} else {
			request.setAttribute("haveMovies", "false");
			request.setAttribute("message", "Sorry!! No movie with this name is present ");
			request.getRequestDispatcher("recommendations.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
