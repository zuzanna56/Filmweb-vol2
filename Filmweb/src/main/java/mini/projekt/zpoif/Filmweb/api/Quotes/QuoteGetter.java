package mini.projekt.zpoif.Filmweb.api.Quotes;

import kong.unirest.Unirest;

import mini.projekt.zpoif.Filmweb.api.Details.DetailsEndPoint;

public class QuoteGetter {
	private QuoteEndPoint quoteEndpoint;
	
	public void makeRequest(String searchQuerry) {
        String getRequestURL = "https://online-movie-database.p.rapidapi.com/title/get-quotes?tconst=" + searchQuerry;
        this.quoteEndpoint = Unirest.get(getRequestURL)
                .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                .header("X-RapidAPI-Key", "3c72edb685msh921ef2fc6185f75p155f23jsn5bdf1acaebaa")
                .asObject(QuoteEndPoint.class)
                .getBody();

    }

	public QuoteEndPoint getQuoteEndpoint() {
		return quoteEndpoint;
	}

}
