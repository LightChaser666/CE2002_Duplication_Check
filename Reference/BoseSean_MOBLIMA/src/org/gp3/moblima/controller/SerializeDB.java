package org.gp3.moblima.controller;

import org.gp3.moblima.model.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Collection of all model objects to be stored
 */
class SerializeDB implements Serializable {

    ArrayList<Admin> admins;
    ArrayList<Booking> bookings;
    ArrayList<Cinema> cinemas;
    ArrayList<Movie> movies;
    ArrayList<Seat> seats;
    ArrayList<Ticket> tickets;
    ArrayList<User> users;
    ArrayList<Review> reviews;
	ArrayList<Holiday> holidays;
    ArrayList<TicketPrice> ticketprice; // new added feature

    SerializeDB() {
        admins = new ArrayList<>();
        bookings = new ArrayList<>();
        cinemas = new ArrayList<>();
        movies = new ArrayList<>();
		seats = new ArrayList<>();
        tickets = new ArrayList<>();
        users = new ArrayList<>();
        reviews = new ArrayList<>();
		holidays = new ArrayList<>();
        ticketprice = new ArrayList<>(); //new added
    }

}
