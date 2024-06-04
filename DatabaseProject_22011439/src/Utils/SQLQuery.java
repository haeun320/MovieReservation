package Utils;

public class SQLQuery {
    public static final String CREATE_MOVIE_TABLE = 
        "CREATE TABLE IF NOT EXISTS Movie (" +
        "movie_id int AUTO_INCREMENT not null, " +
        "movie_name varchar(30), " +
        "running_time int, " +
        "film_rates varchar(30), " +
        "director_name varchar(30), " +
        "movie_info varchar(1000), " +
        "release_date date, " +
        "grade float, " +
        "screening_count INT, " +
        "primary key(movie_id), " +
        "CHECK (screening_count >= 1 AND screening_count <= 4)" +
        ");";

    public static final String CREATE_ACTOR_TABLE = 
        "CREATE TABLE IF NOT EXISTS Actor (" +
        "actor_id int AUTO_INCREMENT not null, " +
        "actor_name varchar(30), " +
        "movie_id int, " +
        "movie_name varchar(30), " +
        "primary key(actor_id), " +
        "foreign key(movie_id) references Movie(movie_id)" +
        ");";

    public static final String CREATE_GENRE_TABLE = 
        "CREATE TABLE IF NOT EXISTS Genre (" +
        "genre_name varchar(30) not null, " +
        "movie_id int, " +
        "movie_name varchar(30), " +
        "primary key(genre_name), " +
        "foreign key(movie_id) references Movie(movie_id)" +
        ");";

    public static final String CREATE_THEATER_TABLE = 
        "CREATE TABLE IF NOT EXISTS Theater (" +
        "theater_id int AUTO_INCREMENT not null, " +
        "number_of_seats INT, " +
        "horizontal_seats INT, " +
        "vertical_seats INT, " +
        "is_in_use BOOLEAN, " +
        "primary key(theater_id)" +
        ");";

    public static final String CREATE_SCREENING_SCHEDULE_TABLE = 
        "CREATE TABLE IF NOT EXISTS ScreeningSchedule (" +
        "screening_schedule_id INT AUTO_INCREMENT not null, " +
        "movie_id INT, " +
        "theater_id INT, " +
        "screening_start_date DATE, " +
        "screening_day VARCHAR(5), " +
        "screening_round INT, " +
        "screening_start_time TIME, " +
        "primary key(screening_schedule_id), " +
        "FOREIGN KEY (movie_id) REFERENCES Movie(movie_id), " +
        "FOREIGN KEY (theater_id) REFERENCES Theater(theater_id)" +
        ");";

    public static final String CREATE_SEAT_TABLE = 
        "CREATE TABLE IF NOT EXISTS Seat (" +
        "seat_id int not null, " +
        "theater_id int not null, " +
        "is_in_use boolean, " +
        "primary key(seat_id, theater_id), " +
        "foreign key(theater_id) references Theater(theater_id)" +
        ");";

    public static final String CREATE_MEMBER_TABLE = 
        "CREATE TABLE IF NOT EXISTS Member (" +
        "member_id varchar(30) not null, " +
        "member_name varchar(30), " +
        "phone_num int, " +
        "email varchar(100), " +
        "primary key(member_id)" +
        ");";

    public static final String CREATE_RESERVATION_TABLE = 
        "CREATE TABLE IF NOT EXISTS Reservation (" +
        "reservation_id int AUTO_INCREMENT not null, " +
        "payment_method varchar(50), " +
        "payment_status boolean, " +
        "payment_amount int, " +
        "member_id varchar(30), " +
        "payment_date date, " +
        "primary key(reservation_id), " +
        "foreign key(member_id) references Member(member_id)" +
        ");";

    public static final String CREATE_TICKET_TABLE = 
        "CREATE TABLE IF NOT EXISTS Ticket (" +
        "ticket_id int AUTO_INCREMENT not null, " +
        "screening_schedule_id int, " +
        "theater_id int, " +
        "seat_id int, " +
        "reservation_id int, " +
        "is_ticketed boolean, " +
        "standard_price int, " +
        "sale_price int, " +
        "primary key(ticket_id), " +
        "foreign key(screening_schedule_id) references ScreeningSchedule(screening_schedule_id), " +
        "foreign key(seat_id, theater_id) references Seat(seat_id, theater_id), " +
        "foreign key(reservation_id) references Reservation(reservation_id)" +
        ");";
}
