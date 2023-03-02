package mini.projekt.zpoif.Filmweb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mini.projekt.zpoif.Filmweb.api.RestClient;
import mini.projekt.zpoif.Filmweb.api.Movies.Movie;

public class DetailsPanel extends JPanel {
	
	
	private Component leftJustify( JLabel panel )  {
	    Box  b = Box.createHorizontalBox();
	    b.add( panel );
	    b.add( Box.createHorizontalGlue() );
	    return b;
	}
	private Component leftJustifypanel( JPanel panel )  {
	    Box  b = Box.createHorizontalBox();
	    b.add( panel );
	    b.add( Box.createHorizontalGlue() );
	    return b;
	}
	public DetailsPanel(Movie movie, JFrame frame, List<Movie> lista) throws MalformedURLException {
		List<String> labels = lista.stream().map(s->s.getMovieLabel()).collect(Collectors.toList());
		JPanel suggestionPanel = new JPanel();
		JLabel suggestionlabel = new JLabel("You may also like:");
		suggestionlabel.setFont(new Font("Calibri", Font.BOLD, 15));
		suggestionlabel.setForeground(Color.white);
		suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
		suggestionPanel.add(suggestionlabel);
		for (int i = 0; i<labels.size();i++) {
			suggestionPanel.add(new JLabel(labels.get(i)));
		}
		suggestionPanel.setBackground(Color.DARK_GRAY);
		
		
		frame.revalidate();
		RestClient restClient = new RestClient();
		restClient.setId(movie);
		restClient.setPlotOutline();
		String plot = restClient.getDetailsResult().getPlotSummary().getText();
		
		String Starrings = movie.getMovieStarrings();
		restClient.setQuote();
		String quote = restClient.getQuote();
		JLabel outlineTitle = new JLabel("Outline:");
		
		JLabel yearLabel = new JLabel(movie.getYearOfRelease().toString());
		yearLabel.setForeground(Color.white);
		yearLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		outlineTitle.setForeground(Color.WHITE);
		JTextArea quotearea= new JTextArea(quote);
		quotearea.setLineWrap(true);
		quotearea.setWrapStyleWord(true);
		quotearea.setBackground(Color.DARK_GRAY);
		quotearea.setPreferredSize(new Dimension(200,200));
		quotearea.setForeground(Color.WHITE);
		
		JLabel titlelabel = new JLabel(movie.getMovieLabel());
		titlelabel.setForeground(Color.WHITE);
		JLabel starringsTitle = new JLabel("Starrings:");
		JLabel starringsLabel = new JLabel(Starrings);
		JLabel quoteTitle = new JLabel("Quote:");
		quoteTitle.setForeground(Color.WHITE);
		JLabel quoteLabel = new JLabel(quote);
		quoteLabel.setForeground(Color.WHITE);;
		JTextArea area= new JTextArea(plot);
		area.setBackground(Color.DARK_GRAY);
		area.setForeground(Color.WHITE);
		JLabel blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(10,10));
		blankLabel.setBackground(Color.DARK_GRAY);
		area.setSize(200, 200);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		String url = movie.getMoviePoster().getImageUrl();

		ImageIcon imageIcon;

		imageIcon = new ImageIcon(new URL(movie.getMoviePoster().getImageUrl()));
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(350, 518, java.awt.Image.SCALE_SMOOTH); // scale it smoothly
		ImageIcon newImageIcon = new ImageIcon(newimg);

		JPanel panelek = new JPanel();
		panelek.setLayout(new BoxLayout(panelek, BoxLayout.X_AXIS));
		JLabel moviecover = new JLabel(newImageIcon);
		moviecover.setBackground(Color.DARK_GRAY);
		panelek.setBackground(Color.DARK_GRAY);
		panelek.add(moviecover, BorderLayout.CENTER);
		panelek.add(Box.createHorizontalStrut(10));
		
		titlelabel.setFont(new Font("Calibri", Font.BOLD, 20));
		starringsTitle.setFont(new Font("Calibri", Font.BOLD, 15));
		starringsTitle.setForeground(Color.WHITE);
		starringsLabel.setForeground(Color.white);
		quoteTitle.setFont(new Font("Calibri", Font.BOLD, 15));
		outlineTitle.setFont(new Font("Calibri", Font.BOLD, 15));
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
		
		descriptionPanel.add(leftJustify(titlelabel));
		descriptionPanel.add(leftJustify(yearLabel));
		descriptionPanel.add(blankLabel);
		descriptionPanel.setBackground(Color.DARK_GRAY);
		descriptionPanel.add(leftJustify(starringsTitle));
		
		descriptionPanel.add(leftJustify(starringsLabel));
		descriptionPanel.add(blankLabel);
		descriptionPanel.add(Box.createHorizontalStrut(3));
		descriptionPanel.add(leftJustify(outlineTitle));
		
		descriptionPanel.add(area);
		descriptionPanel.add(blankLabel);
		descriptionPanel.add(leftJustify(quoteTitle));
		
		descriptionPanel.add(quotearea);
		descriptionPanel.setPreferredSize(new Dimension(900,400));
		descriptionPanel.setForeground(Color.WHITE);
		descriptionPanel.add(leftJustifypanel(suggestionPanel));
		panelek.add(descriptionPanel);
		
		
		this.add(panelek);
		frame.revalidate();
		
		
	}
}
