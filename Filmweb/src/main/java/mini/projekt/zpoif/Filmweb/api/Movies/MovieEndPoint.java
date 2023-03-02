package mini.projekt.zpoif.Filmweb.api.Movies;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MovieEndPoint {
private List<Movie> d =null;
	
	private String q;
	
	public String getSearchQuerry() {
		return q;
	}
	
	public List<Movie> getMovieMatches() {
		return d;
	}
	
	public Movie getMovieMatch() {
		return d.get(1);
	}
	
	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return mapper.writeValueAsString(this);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
