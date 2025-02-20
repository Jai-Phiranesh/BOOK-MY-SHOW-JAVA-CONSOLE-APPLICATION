package BookMyShow.Actions;

import java.time.LocalDate;
import java.util.ArrayList;

import BookMyShow.Movies;
import BookMyShow.User;

public interface UserAction extends Actions {

    public  void register();

    public  void displayAllMovies(User currentuser, LocalDate today);

    public  void show_movie_details(ArrayList<Movies> movieavailable, String moviechoice, User currenUser) ;
} 
