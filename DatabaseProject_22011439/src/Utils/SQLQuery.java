package Utils;

public class SQLQuery {
	
	//------------------ CREATE -------------------------
    public static final String CREATE_MOVIE_TABLE = 
        "CREATE TABLE Movie (" +
        "movie_id int AUTO_INCREMENT not null, " +
        "movie_name varchar(30) not null, " +
        "running_time int, " +
        "film_rates varchar(30), " +
        "director_name varchar(30), " +
        "movie_info varchar(1000), " +
        "release_date date not null, " +
        "grade float, " +
        "screening_count INT not null, " +
        "primary key(movie_id), " +
        "CHECK (film_rates IN ('전체관람가', '12세 이상 관람가', '15세 이상 관람가', '청소년 관람 불가'))," +
        "CHECK (screening_count >= 1 AND screening_count <= 4)" +
        ");";

    public static final String CREATE_ACTOR_TABLE = 
        "CREATE TABLE Actor (" +
        "actor_id int AUTO_INCREMENT not null, " +
        "actor_name varchar(30) not null, " +
        "movie_id int, " +
        "movie_name varchar(30), " +
        "primary key(actor_id), " +
        "foreign key(movie_id) references Movie(movie_id)" +
        ");";

    public static final String CREATE_GENRE_TABLE = 
        "CREATE TABLE Genre (" +
        "genre_id int AUTO_INCREMENT not null, " +
        "genre_name varchar(30) not null, " +
        "movie_id int, " +
        "movie_name varchar(30), " +
        "primary key(genre_id), " +
        "foreign key(movie_id) references Movie(movie_id)" +
        ");";

    public static final String CREATE_THEATER_TABLE = 
        "CREATE TABLE Theater (" +
        "theater_id int AUTO_INCREMENT not null, " +
        "theater_name VARCHAR(30), " +
        "number_of_seats INT, " +
        "horizontal_seats INT not null, " +
        "vertical_seats INT not null, " +
        "is_in_use BOOLEAN, " +
        "primary key(theater_id)" +
        ");";

    public static final String CREATE_SCREENING_SCHEDULE_TABLE = 
        "CREATE TABLE ScreeningSchedule (" +
        "screening_schedule_id INT AUTO_INCREMENT not null, " +
        "movie_id INT not null, " +
        "theater_id INT not null, " +
        "screening_start_date DATE, " +
        "screening_day VARCHAR(5), " +
        "screening_round INT, " +
        "screening_start_time TIME, " +
        "primary key(screening_schedule_id), " +
        "FOREIGN KEY (movie_id) REFERENCES Movie(movie_id), " +
        "FOREIGN KEY (theater_id) REFERENCES Theater(theater_id)" +
        ");";

    public static final String CREATE_SEAT_TABLE = 
        "CREATE TABLE Seat (" +
        "seat_id int AUTO_INCREMENT not null, " +
        "seat_num int not null, " +
        "theater_id int not null, " +
        "is_in_use boolean, " +
        "primary key(seat_id), " +
        "foreign key(theater_id) references Theater(theater_id)" +
        ");";

    public static final String CREATE_MEMBER_TABLE = 
        "CREATE TABLE Member (" +
        "member_id varchar(30) not null, " +
        "member_name varchar(30), " +
        "phone_num int, " +
        "email varchar(100), " +
        "primary key(member_id)" +
        ");";

    public static final String CREATE_RESERVATION_TABLE = 
        "CREATE TABLE Reservation (" +
        "reservation_id int AUTO_INCREMENT not null, " +
        "payment_method varchar(30), " +
        "payment_status boolean default false, " +
        "payment_amount int, " +
        "member_id varchar(30) not null, " +
        "payment_date date default NULL, " +
        "primary key(reservation_id), " +
        "foreign key(member_id) references Member(member_id)" +
        ");";

    public static final String CREATE_TICKET_TABLE = 
        "CREATE TABLE Ticket (" +
        "ticket_id int AUTO_INCREMENT not null, " +
        "screening_schedule_id int not null, " +
        "theater_id int not null, " +
        "seat_id int not null, " +
        "reservation_id int not null, " +
        "is_ticketed boolean default false, " +
        "standard_price int, " +
        "sale_price int, " +
        "primary key(ticket_id), " +
        "foreign key(screening_schedule_id) references ScreeningSchedule(screening_schedule_id), " +
        "foreign key(seat_id) references Seat(seat_id), " +
        "foreign key(reservation_id) references Reservation(reservation_id)" +
        ");";
	//---------------------------------------------------

    
	//------------------ INSERT -------------------------
    public static final String INSERT_SAMPLE_DATA_MOVIE =
        "INSERT INTO Movie (movie_name, running_time, film_rates, director_name, movie_info, release_date, grade, screening_count) VALUES" +
        "('Movie A', 120, '전체관람가', 'Director A', 'Info A', '2024-01-01', 8.5, 4)," +
        "('Movie B', 150, '12세 이상 관람가', 'Director B', 'Info B', '2024-01-02', 7.8, 1)," +
        "('Movie C', 100, '15세 이상 관람가', 'Director C', 'Info C', '2024-01-03', 9.2, 1)," +
        "('Movie D', 90, '청소년 관람 불가', 'Director D', 'Info D', '2024-01-04', 6.5, 1)," +
        "('Movie E', 110, '전체관람가', 'Director E', 'Info E', '2024-01-05', 7.0, 1)," +
        "('Movie F', 130, '12세 이상 관람가', 'Director F', 'Info F', '2024-01-06', 8.0, 2)," +
        "('Movie G', 140, '15세 이상 관람가', 'Director G', 'Info G', '2024-01-07', 9.0, 1)," +
        "('Movie H', 105, '청소년 관람 불가', 'Director H', 'Info H', '2024-01-08', 6.8, 1)," +
        "('Movie I', 115, '전체관람가', 'Director I', 'Info I', '2024-01-09', 7.5, 1)," +
        "('Movie J', 125, '12세 이상 관람가', 'Director J', 'Info J', '2024-01-10', 8.3, 1)," +
        "('Movie K', 135, '15세 이상 관람가', 'Director K', 'Info K', '2024-01-11', 9.1, 1)," +
        "('Movie L', 95, '청소년 관람 불가', 'Director L', 'Info L', '2024-01-12', 6.9, 1);";

    public static final String INSERT_SAMPLE_DATA_ACTOR =
        "INSERT INTO Actor (actor_name, movie_id, movie_name) VALUES" +
        "('Actor A', 1, 'Movie A')," +
        "('Actor C', 1, 'Movie A')," +
        "('Actor F', 1, 'Movie A')," +
        "('Actor B', 2, 'Movie B')," +
        "('Actor C', 3, 'Movie C')," +
        "('Actor D', 4, 'Movie D')," +
        "('Actor E', 5, 'Movie E')," +
        "('Actor F', 6, 'Movie F')," +
        "('Actor G', 7, 'Movie G')," +
        "('Actor H', 8, 'Movie H')," +
        "('Actor A', 9, 'Movie I')," +
        "('Actor A', 10, 'Movie J')," +
        "('Actor A', 11, 'Movie K')," +
        "('Actor A', 12, 'Movie L');";

    public static final String INSERT_SAMPLE_DATA_GENRE =
        "INSERT INTO Genre (genre_name, movie_id, movie_name) VALUES" +
        "('Action', 1, 'Movie A')," +
        "('Comedy', 1, 'Movie A')," +
        "('Comedy', 2, 'Movie B')," +
        "('Drama', 3, 'Movie C')," +
        "('Horror', 4, 'Movie D')," +
        "('Action', 5, 'Movie E')," +
        "('Romance', 6, 'Movie F')," +
        "('Adventure', 7, 'Movie G')," +
        "('Thriller', 8, 'Movie H')," +
        "('Mystery', 9, 'Movie I')," +
        "('Fantasy', 10, 'Movie J')," +
        "('Animation', 11, 'Movie K')," +
        "('Documentary', 12, 'Movie L');";

    public static final String INSERT_SAMPLE_DATA_THEATER =
        "INSERT INTO Theater (theater_name, horizontal_seats, vertical_seats, is_in_use, number_of_seats) VALUES" +
        "('강변', 3, 5, true, 15)," +
        "('건대입구', 3, 3, true, 9)," +
        "('구로', 2, 3, true, 6)," +
        "('동대문', 25, 20, true, 500)," +
        "('명동', 10, 10, true, 100)," +
        "('미아', 15, 5, true, 75)," +
        "('불광', 20, 10, true, 200)," +
        "('송파', 25, 15, true, 375)," +
        "('신촌', 10, 15, true, 150)," +
        "('압구정', 15, 20, true, 300)," +
        "('왕십리', 20, 5, true, 100)," +
        "('천호', 25, 10, true, 250);";

    public static final String INSERT_SAMPLE_DATA_SCREENING_SCHEDULE =
        "INSERT INTO ScreeningSchedule (movie_id, theater_id, screening_start_date, screening_day, screening_round, screening_start_time) VALUES" +
        "(1, 1, '2024-06-01', 'Sat', 1, '10:00:00')," +
        "(1, 2, '2024-06-02', 'Sun', 2, '12:00:00')," +
        "(1, 3, '2024-06-03', 'Mon', 3, '14:00:00')," +
        "(1, 3, '2024-06-03', 'Mon', 4, '19:00:00')," +
        "(2, 2, '2024-06-04', 'Tue', 1, '16:00:00')," +
        "(3, 1, '2024-06-05', 'Wed', 1, '18:00:00')," +
        "(4, 3, '2024-06-03', 'Mon', 1, '14:00:00')," +
        "(5, 2, '2024-06-08', 'Sat', 1, '17:00:00')," +
        "(6, 1, '2024-06-06', 'Thu', 1, '20:00:00')," +
        "(7, 3, '2024-06-07', 'Fri', 1, '22:00:00')," +
        "(7, 2, '2024-06-03', 'Mon', 2, '13:00:00')," +
        "(8, 1, '2024-06-08', 'Sat', 1, '11:00:00')," +
        "(9, 1, '2024-06-09', 'Sun', 1, '13:00:00')," +
        "(10, 2, '2024-06-10', 'Mon', 1, '15:00:00')," +
        "(11, 3, '2024-06-11', 'Tue', 1, '17:00:00')," +
        "(12, 1, '2024-06-12', 'Wed', 1, '19:00:00');";

    public static final String INSERT_SAMPLE_DATA_SEAT =
        "INSERT INTO Seat (seat_num, theater_id, is_in_use) VALUES" +
        "(1, 1, true)," +
        "(2, 1, false)," +
        "(3, 1, true)," +
        "(4, 1, true)," +
        "(5, 1, true)," +
        "(6, 1, false)," +
        "(7, 1, true)," +
        "(8, 1, true)," +
        "(9, 1, true)," +
        "(10, 1, true)," +
        "(11, 1, true)," +
        "(12, 1, true)," +
        "(13, 1, true)," +
        "(14, 1, true)," +
        "(15, 1, true)," +
        "(1, 2, true)," +
        "(2, 2, true)," +
        "(3, 2, false)," +
        "(4, 2, true)," +
        "(5, 2, false)," +
        "(6, 2, true)," +
        "(7, 2, false)," +
        "(8, 2, true)," +
        "(9, 2, true)," +
        "(1, 3, true)," +
        "(2, 3, true)," +
        "(3, 3, true)," +
        "(4, 3, false)," +
        "(5, 3, true)," +
        "(6, 3, false);";

    public static final String INSERT_SAMPLE_DATA_MEMBER =
        "INSERT INTO Member (member_id, member_name, phone_num, email) VALUES" +
        "('member1', 'Member A', 1234567890, 'member1@example.com')," +
        "('member2', 'Member B', 1234567891, 'member2@example.com')," +
        "('member3', 'Member C', 1234567892, 'member3@example.com')," +
        "('member4', 'Member D', 1234567893, 'member4@example.com')," +
        "('member5', 'Member E', 1234567894, 'member5@example.com')," +
        "('member6', 'Member F', 1234567895, 'member6@example.com')," +
        "('member7', 'Member G', 1234567896, 'member7@example.com')," +
        "('member8', 'Member H', 1234567897, 'member8@example.com')," +
        "('member9', 'Member I', 1234567898, 'member9@example.com')," +
        "('member10', 'Member J', 1234567899, 'member10@example.com')," +
        "('member11', 'Member K', 1234567800, 'member11@example.com')," +
        "('user1', 'user1', 1234567801, 'user1@example.com');";

    public static final String INSERT_SAMPLE_DATA_RESERVATION =
        "INSERT INTO Reservation (payment_method, payment_status, payment_amount, member_id, payment_date) VALUES" +
        "('신용카드', true, 10000, 'user1', '2024-01-01')," +
        "('휴대폰결제', true, 20000, 'user1', '2024-01-02')," +
        "('무통장입금', false, 15000, 'user1', NULL)," +
        "('신용카드', true, 12000, 'user1', '2024-01-04')," +
        "('휴대폰결제', true, 18000, 'user1', '2024-01-05')," +
        "('무통장입금', false, 13000, 'member6', NULL)," +
        "('신용카드', true, 16000, 'member7', '2024-01-07')," +
        "('휴대폰결제', true, 17000, 'member8', '2024-01-08')," +
        "('무통장입금', false, 14000, 'member9', NULL)," +
        "('신용카드', true, 14000, 'member10', '2024-01-10')," +
        "('휴대폰결제', true, 11000, 'member11', '2024-01-11')," +
        "('무통장입금', false, 12000, 'user1', NULL);";

    public static final String INSERT_SAMPLE_DATA_TICKET =
        "INSERT INTO Ticket (screening_schedule_id, theater_id, seat_id, reservation_id, is_ticketed, standard_price, sale_price) VALUES" +
        "(1, 1, 2, 1, true, 15000, 10000)," +
        "(1, 1, 6, 2, true, 20000, 20000)," +
        "(2, 2, 20, 3, false, 15000, 15000)," +
        "(5, 2, 18, 4, true, 13000, 12000)," +
        "(7, 3, 28, 5, true, 18000, 14000)," +
        "(6, 6, 6, 6, false, 11000, 7000)," +
        "(7, 7, 7, 7, true, 16000, 13000)," +
        "(8, 8, 8, 8, true, 17000, 12000)," +
        "(9, 9, 9, 9, false, 13000, 10000)," +
        "(10, 10, 10, 10, true, 19000, 15000)," +
        "(11, 11, 11, 11, true, 14000, 11000)," +
        "(12, 12, 12, 12, false, 15000, 12000);";
	//---------------------------------------------------

    
}
