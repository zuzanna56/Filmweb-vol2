package mini.projekt.zpoif.Filmweb.api.Details;

import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DetailsGetter {
	private DetailsEndPoint detailsResult;
	
	
	public DetailsEndPoint getDetailsResult() {
		return detailsResult;
	}


	public void makeRequest(String searchQuerry) {
        String getRequestURL = "https://online-movie-database.p.rapidapi.com/title/get-overview-details?tconst=" + searchQuerry;
        this.detailsResult = Unirest.get(getRequestURL)
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .header("X-RapidAPI-Key", "3c72edb685msh921ef2fc6185f75p155f23jsn5bdf1acaebaa")
                .asObject(DetailsEndPoint.class)
                .getBody();

        
    }
}
	
	
	
	


