package mini.projekt.zpoif.Filmweb.api.Movies;

import java.util.Arrays;


import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;

import kong.unirest.Unirest;

public class MovieResultGetter {
	
	MovieEndPoint movieResults;
	
	public void makeRequest(String searchQuerry) {
		String getRequestURL = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + searchQuerry;
		this.movieResults = Unirest.get(getRequestURL)
				.header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
				.header("X-RapidAPI-Key", "3c72edb685msh921ef2fc6185f75p155f23jsn5bdf1acaebaa")
				.asObject(MovieEndPoint.class)
				.getBody();
		
	}
	public MovieEndPoint getmovieResults() {
		return movieResults;
	
	}
	
	
	
	
	

}
