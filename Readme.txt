The assignment is divided into following packages-

1. Model
	- Event class (MongoDB Document)
	- Session class
	- Language enum

2. Repository
	- EventRepository (interface for MongoRepository)

3. Controller
	- Controller class
		 Methods
			- getAllEvents					GET /events
			- createEvent					POST /events/{eventName}
			- createSession (in given event)		PUT /events/{eventName}/{session}
			- deleteSession (from given event)		DELETE events/{eventName}/{sessionName}

4. Main Class
	- ApApplication

5. MongoDB connection parameters provided in application.properties
		
		