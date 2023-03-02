package mini.projekt.zpoif.Filmweb.api;

import java.io.IOException;

import java.util.ArrayList;

import kong.unirest.UnirestException;
import mini.projekt.zpoif.Filmweb.api.Details.DetailsEndPoint;
import mini.projekt.zpoif.Filmweb.api.Details.DetailsGetter;
import mini.projekt.zpoif.Filmweb.api.Movies.Movie;
import mini.projekt.zpoif.Filmweb.api.Movies.MovieEndPoint;
import mini.projekt.zpoif.Filmweb.api.Movies.MovieResultGetter;
import mini.projekt.zpoif.Filmweb.api.Quotes.QuoteEndPoint;
import mini.projekt.zpoif.Filmweb.api.Quotes.QuoteGetter;

public class RestClient {
	private MovieEndPoint movieResult;
	private String id;
	private String quote;
	private DetailsEndPoint detailsResult;
	private QuoteEndPoint quoteEndPoint;
	private String plotOutline;
	private ArrayList<Movie> topMovies;
	
	
	
	public void setMovies(String q) {
		 MovieResultGetter movieResultGetter= new MovieResultGetter();
		 movieResultGetter.makeRequest(q);
		 this.movieResult= movieResultGetter.getmovieResults(); 
	}
	
	
	public void setId(Movie movie) { 
		this.id= movie.getId();	
		
	}

	
	public void setPlotOutline() {
		DetailsGetter detailsGetter= new DetailsGetter();
		detailsGetter.makeRequest(this.id);
		
		this.detailsResult = detailsGetter.getDetailsResult();
		if (detailsResult !=null) {
			if (detailsResult.getPlotSummary()!=null) {
		this.plotOutline= detailsResult.getPlotSummary().getText();}}
		else {
			this.plotOutline="";
		}
		
	}
	
	public String getPlotOutline() {
		return plotOutline;
	}

	public void setQuote() {
		QuoteGetter quoteGetter= new QuoteGetter();
		quoteGetter.makeRequest(id);
		this.quoteEndPoint = quoteGetter.getQuoteEndpoint();
		if (quoteEndPoint!=null) {
			if (quoteEndPoint.getQuotes()!=null) {
		this.quote = quoteEndPoint.getQuotes().get(0).getLines().get(0).getText();
		}}
		else {
			this.quote="";
		}
		
	}


	public ArrayList<Movie> getTopMovies() {
		return topMovies;
	}

	

	public MovieEndPoint getMovieResult() {
		return movieResult;
	}

	public String getId() {
		return id;
	}

	public DetailsEndPoint getDetailsResult() {
		return detailsResult;
	}

	public String getQuote() {
		return quote;
	}

	public QuoteEndPoint getQuoteEndPoint() {
		return quoteEndPoint;
	}
	
	

}
