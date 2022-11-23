package org.gp3.moblima.view.moviegoer;

import org.gp3.moblima.controller.Manager;
import org.gp3.moblima.model.Constant;
import org.gp3.moblima.model.Movie;
import org.gp3.moblima.view.BaseMenu;

import java.util.ArrayList;
import java.util.Collections;

import static org.gp3.moblima.view.IOUtil.*;

/**
 * Menu to list the top five movies by sales
 */
public class ListMovieTopSale extends BaseMenu {
    public ListMovieTopSale(BaseMenu previousMenu) {
        super(previousMenu);
    }

	/**
	 * Display the top five menu by sales
	 * @return return the corresponding menu that the user has selected
	 */
    @Override
    public BaseMenu execute() {
		Manager manager = Manager.getInstance();
        ArrayList<Movie> movies = manager.getAll(Constant.Tables.MOVIE);
		printTitle("Top 5 by Sales");
		ArrayList<String> choices = new ArrayList<>();

		try {

            sortTicketSales(movies);

			int top = 1;

			for (Movie movie : movies) {
				if(movie.getShowingStatus() != Constant.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Constant.ShowingStatus.COMING_SOON)
				{
					choices.add(movie.getTicketSales() + " Tickets for " + movie.getTitle());
					if (top++ == 5) {
						break;
					}
				}

			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		choices.add("Shop Top 5 by rating");
		choices.add("Back to main menu");
		printMenuItems(choices, 0);

		int c = readChoice(0, choices.size());

		BaseMenu nextMenu = null;

		System.out.println(choices.size());

		if (c <= choices.size() - 3) {
			nextMenu = new MovieInfo(this, movies.get(c));
		} else if (c == choices.size() - 2) {
			nextMenu = new ListMovieTopRate(this.getPreviousMenu());
		} else if (c == choices.size() - 1) {
			nextMenu = this.getPreviousMenu();
		}

		return nextMenu;

    }

    private void sortTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, (m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
    }
}
