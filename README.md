FORUM ENGINE

You ask, others google


Description:

Forum Engine is a state of the art web application, which aims to provide its users a plattform on which they can share and expand their knowledge.

You can browse through the different sections or topics(FORUMS) of the app, and even create your own! Each forum can contain multiple questions/anwsers regarding certain aspects of the overlaying topic.
Every registered User is given the possibility to ask questions concerning a topic, and receive help on the matter, by experts in the field who might be able to anwser his question.
Each User has their own User Profile Page with detailed stat-tracking about their posting activities, also theres an option to upload a profile picture on ur page.



Installation:

User:

1. To use the application urself all that is needed is a browser and an internet connection.


Developer:

Required Software:

    Browser
    Java JDK
    IntelliJ Ultimate
    Tomcat Application Server
    MySQL
	
	MySQL configuration:
		As standard the application uses a schema 'fe' and the user 'forumengine' to access the MySQL server
		If you would like to use this standard simply create the user and schema on your MySQL server
		
		If you would like to change this, create a user and schema and edit the application.properties file
		
		NOTE: 	The application uses a special account called 'koarl' as a sort of root user.
				It is required to create this user, preferably in the database by executing the query:
				INSERT INTO fe.user (username, is_banned, password, role) VALUES ('koarl', 0, '$2a$10$I5z.S/s1DioCmJmFxbvJkO4p8QDVK7SINQsZA.V3LaRoUE9yw24u.', 'ROLE_ADMIN');
				to create this user with the default password: Pa55w.rd




Usage:

Pretty much self explanatory. Its a super advanced forum, you can browse it, you can create content for it, you can expand your overall knowledge - AWESOME!!!.


Contributing:

Please do not try to contribute to this project. This is not open source, if you fiddle with our code we might sue you.


Credits:

Lead Developer:  Lorenz Possnig

Project Manager, Senior Developer : Mattlschweiger Valentin

Social Media Manager, Senior Developer : Maurer Karl


Licensing:

NO LICENSE
