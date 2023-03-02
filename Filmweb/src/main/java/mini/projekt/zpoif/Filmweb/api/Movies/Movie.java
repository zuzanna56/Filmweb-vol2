package mini.projekt.zpoif.Filmweb.api.Movies;


import java.io.Serializable;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class Movie implements Serializable  {
	
	private MoviePoster i = new MoviePoster("https://bitsofco.de/content/images/2018/12/broken-1.png");
	
	
	private String id = "Not Available";
	public String getId() {
		return id;
	}
	

	private String l = "Not Available"; 
	private Integer rank = 0000; 
	private String s = "Not Avaliable"; 
	private Integer y = 0000; 
	
	public MoviePoster getMoviePoster() {
		return i;
	}
	public String getMovieLabel() {
		return l;
	}
	public Integer getIMDbMovieRank() {
		return rank;
	}
	public String getMovieStarrings() {
		return s;
	}
	public Integer getYearOfRelease() {
		return y;
	}	
	
	@Override
	public String toString() {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
