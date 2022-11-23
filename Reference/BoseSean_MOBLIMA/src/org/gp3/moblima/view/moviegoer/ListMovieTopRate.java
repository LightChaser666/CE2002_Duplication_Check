package org.gp3.moblima.view.moviegoer;

import org.gp3.moblima.controller.Manager;
import org.gp3.moblima.model.Constant;
import org.gp3.moblima.model.Movie;
import org.gp3.moblima.view.BaseMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.gp3.moblima.model.Constant.Tables;
import static org.gp3.moblima.view.IOUtil.*;

/**
 * Menu to list the top five movies by ratings
 */
public class ListMovieTopRate extends BaseMenu {
    public ListMovieTopRate(BaseMenu previousMenu) {
        super(previousMenu);
    }

    /**
     * Display the top five movies by rating to the user
     * @return return the corresponding menu that the user has selected
     */
    @Override
    public BaseMenu execute() {
        Manager manager = Manager.getInstance();

        printTitle("Top 5 by Rating");
        ArrayList<Movie> movies =  manager.getAll(Tables.MOVIE);

        ArrayList<String> choices = new ArrayList<>();

        try {

            sortRating(movies);

            int top = 1;

            for (Movie movie : movies) {
                if(movie.getShowingStatus() != Constant.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Constant.ShowingStatus.COMING_SOON){
                    choices.add(movie.getOverAllRating() + " Overall Rating for " + movie.getTitle());
                    if (top++ == 5) {
                        break;
                    }
                }

            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }


        choices.add("Shop Top 5 by sales");
        choices.add("Back to main menu");

        printMenuItems(choices, 0);

        int c = readChoice(0, choices.size());

        BaseMenu nextMenu = this;

        if (c <= choices.size() - 3) {
            nextMenu = new MovieInfo(this, movies.get(c));
        } else if (c == choices.size() - 2) {
            nextMenu = new ListMovieTopSale(this.getPreviousMenu());
        } else if (c == choices.size() - 1) {
            nextMenu = this.getPreviousMenu();
        }

        return nextMenu;

    }

    private void sortRating(ArrayList<Movie> movies) {
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getOverAllRating(), m1.getOverAllRating());
            }
		});
    }

}
