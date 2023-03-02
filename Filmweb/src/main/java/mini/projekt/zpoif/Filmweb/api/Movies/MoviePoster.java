package mini.projekt.zpoif.Filmweb.api.Movies;


import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MoviePoster implements Serializable {
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public MoviePoster () {
		
	}
	
	public MoviePoster(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		try {ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(this);
	} catch(Exception ex) {
		ex.printStackTrace();
	}
	return null;
}
	
}
