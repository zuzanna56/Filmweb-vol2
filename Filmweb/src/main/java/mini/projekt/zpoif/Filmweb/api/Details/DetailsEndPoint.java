package mini.projekt.zpoif.Filmweb.api.Details;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DetailsEndPoint {
	private String id;
	private PlotSummary plotSummary;
	
	
	public PlotSummary getPlotSummary() {
		return plotSummary;
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



	public String getId() {
		return id;
	}

}
