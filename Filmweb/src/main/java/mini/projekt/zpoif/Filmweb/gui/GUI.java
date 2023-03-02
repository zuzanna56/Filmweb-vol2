package mini.projekt.zpoif.Filmweb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import mini.projekt.zpoif.Filmweb.api.RestClient;
import mini.projekt.zpoif.Filmweb.api.Movies.Movie;
import mini.projekt.zpoif.Filmweb.api.Movies.MovieEndPoint;

public class GUI {

	private static String querry;
	public static List<Movie> lista;
	private static int yearStart;
	private static int yearEnd;
	public static JFrame frame;
	public JPanel resultPanel;
	public JButton randomButton;
	public JLabel labelfrom;
	public JLabel labelto;
	public JLabel yearfrom;
	public JComboBox yearsboxend;
	public JComboBox yearsboxstart;
	public JButton sortRankButton;
	public JButton sortYearButton;
	public JButton filterbutton;

	// FUNKCJA GENERUJACA BOXY Z OKLADKAMI FILMOW
	void showMovies(JPanel resultPanel, List<Movie> lista) {
		resultPanel.removeAll();
		for (int i = 0; i < lista.size(); i++) {
			try {

				addImage(resultPanel, lista.get(i).getMoviePoster().getImageUrl(), lista.get(i).getMovieLabel(),
						lista.get(i).getId(), lista.get(i));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

		}
		resultPanel.revalidate();

	}
	

	// FUNKCJA GENERUJACA BOXY Z OKLADKA I TYTULEM FILMU
	// LISTENER DO KLIKNIECIA NA OKLADKE
	static void addImage(JPanel cp, String url, String Title, String id, Movie movie) throws MalformedURLException {
		ImageIcon imageIcon = new ImageIcon(new URL(url));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(200, 296, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImageIcon = new ImageIcon(newimg);
		JPanel panelek = new JPanel();
		panelek.setLayout(new BoxLayout(panelek, BoxLayout.Y_AXIS));
		JLabel moviecover = new JLabel(newImageIcon);
		moviecover.setBackground(Color.DARK_GRAY);
		panelek.add(moviecover, BorderLayout.CENTER);
		panelek.add(Box.createHorizontalStrut(10));
		JLabel titlelabel = new JLabel(Title);
		JTextArea textArea = new JTextArea(Title);
		textArea.setWrapStyleWord(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.BLACK);
		textArea.setPreferredSize(new Dimension(200, 20));
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Calibri", Font.BOLD, 15));
		titlelabel.add(textArea);
		titlelabel.setForeground(Color.WHITE);
		titlelabel.setPreferredSize(new Dimension(200, 20));
		titlelabel.setBackground(Color.black);

		panelek.add(titlelabel, BorderLayout.CENTER);
		panelek.add(Box.createHorizontalStrut(10));
		panelek.setBackground(Color.DARK_GRAY);
		cp.add(panelek);
		cp.revalidate();
		panelek.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				cp.removeAll();
				cp.revalidate();
				try {
					frame.revalidate();
					DetailsPanel detailsPanel = new DetailsPanel(movie, frame, lista);

					cp.removeAll();
					cp.add(detailsPanel);
					cp.revalidate();
					SwingUtilities.updateComponentTreeUI(frame);
					frame.invalidate();
					frame.validate();
					frame.repaint();

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public String getRandomQuote(Movie movie) {
		RestClient restClient = new RestClient();
		restClient.setId(movie);
		restClient.setQuote();
		return restClient.getQuote();
	}

	public GUI() {
		frame = new JFrame("Filmweb");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 1400);

		JPanel searchPanel = new JPanel();
		FlowLayout flowlayout = new FlowLayout(20, 20, 20);
		JPanel resultPanel = new JPanel(flowlayout);

		JLabel label = new JLabel("Enter Text");
		JTextField inputSearch = new JTextField(10);
		inputSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				querry = inputSearch.getText();

			}
		});

		JButton send = new JButton("Search");
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RestClient restClient = new RestClient();
				querry = inputSearch.getText();
				resultPanel.removeAll();
				System.out.println(querry);
				restClient.setMovies(querry);
				MovieEndPoint movieResult = restClient.getMovieResult();
				lista = movieResult.getMovieMatches();

				showMovies(resultPanel, lista);
				resultPanel.revalidate();

				labelfrom.setVisible(true);
				labelto.setVisible(true);
				yearsboxstart.setVisible(true);
				yearsboxend.setVisible(true);
				sortRankButton.setVisible(true);
				sortYearButton.setVisible(true);
				filterbutton.setVisible(true);

				randomButton.setVisible(true);
				randomButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Random random = new Random();
						int a = random.nextInt(lista.size());
						String quote = getRandomQuote(lista.get(a));
						while (quote == null) {
							quote = getRandomQuote(lista.get(random.nextInt(lista.size())));
						}
						if (quote.equals("")) {
							quote = "A kto umarł ten nie żyje";
						}
						JTextArea quoteLabel = new JTextArea(quote);
						quoteLabel.setLineWrap(true);
						quoteLabel.setWrapStyleWord(true);
						quoteLabel.setPreferredSize(new Dimension(1200, 1200));
						quoteLabel.setForeground(Color.WHITE);
						quoteLabel.setFont(new Font("Calibri", Font.BOLD, 40));
						quoteLabel.setBackground(Color.DARK_GRAY);
						resultPanel.removeAll();
						resultPanel.add(quoteLabel, BorderLayout.CENTER);
						resultPanel.revalidate();

						SwingUtilities.updateComponentTreeUI(frame);
						frame.invalidate();
						frame.validate();
						frame.repaint();
					}
				});

			}
		});

		this.yearsboxstart = new JComboBox();

		for (int i = 0; i < 50; i++) {
			yearsboxstart.addItem(1973 + i);
		}

		yearsboxstart.addActionListener(yearsboxstart);
		yearStart = (int) yearsboxstart.getSelectedItem();

		this.yearsboxend = new JComboBox();

		for (int i = 0; i < 50; i++) {
			yearsboxend.addItem(1973 + i);
		}

		yearsboxend.addActionListener(yearsboxend);
		yearEnd = (int) yearsboxend.getSelectedItem();

		this.filterbutton = new JButton("Filter");
		filterbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				yearStart = (int) yearsboxstart.getItemAt(yearsboxstart.getSelectedIndex());
				yearEnd = (int) yearsboxend.getItemAt(yearsboxend.getSelectedIndex());
				lista = lista.stream().filter(s -> s.getYearOfRelease() > yearStart && s.getYearOfRelease() < yearEnd)
						.collect(Collectors.toList());
				resultPanel.removeAll();
				showMovies(resultPanel, lista);
				resultPanel.revalidate();
				SwingUtilities.updateComponentTreeUI(frame);
				frame.invalidate();
				frame.validate();
				frame.repaint();

			}

		});

		this.sortYearButton = new JButton("Sort by Year");
		sortYearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lista = lista.stream().sorted(Comparator.comparingInt(Movie::getYearOfRelease).reversed())
						.collect(Collectors.toList());
				;
				resultPanel.removeAll();
				showMovies(resultPanel, lista);
				resultPanel.revalidate();
				SwingUtilities.updateComponentTreeUI(frame);
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
		});

		this.sortRankButton = new JButton("Sort by Alphabetical Order");
		sortRankButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lista = lista.stream().sorted(Comparator.comparing(Movie::getMovieLabel)).collect(Collectors.toList());
				
				resultPanel.removeAll();
				showMovies(resultPanel, lista);
				resultPanel.revalidate();
				SwingUtilities.updateComponentTreeUI(frame);
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
		});

		searchPanel.add(label);
		searchPanel.add(inputSearch);
		searchPanel.add(send);
		this.labelfrom = new JLabel("FROM:");
		this.labelto = new JLabel("TO:");
		labelfrom.setForeground(Color.white);
		labelto.setForeground(Color.white);
		searchPanel.add(labelfrom);
		labelfrom.setVisible(false);
		labelto.setVisible(false);
		searchPanel.add(yearsboxstart);
		yearsboxstart.setVisible(false);
		searchPanel.add(labelto);
		searchPanel.add(yearsboxend);
		yearsboxend.setVisible(false);
		searchPanel.add(filterbutton);
		filterbutton.setVisible(false);
		searchPanel.add(sortRankButton);
		sortRankButton.setVisible(false);
		searchPanel.add(sortYearButton);
		sortYearButton.setVisible(false);
		this.randomButton = new JButton("Random Quote");
		searchPanel.add(this.randomButton);
		this.randomButton.setVisible(false);
		searchPanel.setBackground(Color.BLACK);
		resultPanel.setBackground(Color.DARK_GRAY);

		resultPanel.repaint();

		frame.getContentPane().add(BorderLayout.NORTH, searchPanel);
		frame.getContentPane().add(BorderLayout.CENTER, resultPanel);
		frame.setVisible(true);

	}
}
