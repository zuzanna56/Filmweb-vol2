package mini.projekt.zpoif.Filmweb.api.Quotes;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Quote {
	private Boolean spoiler;
	private List<Line> lines;
	public Boolean getSpoiler() {
		return spoiler;
	}
	public List<Line> getLines() {
		return lines;
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
