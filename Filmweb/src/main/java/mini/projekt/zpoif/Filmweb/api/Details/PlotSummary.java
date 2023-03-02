package mini.projekt.zpoif.Filmweb.api.Details;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PlotSummary {
	private String text;
	
	public String getText() {
		return text;
	}
	
	public PlotSummary () {
		
	}
	
	public PlotSummary(String text) {
		this.text= text;
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
