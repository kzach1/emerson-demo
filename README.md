# Emerson Weather demo

Supported Environments
  This program was built and tested on a *nix system using Java 11 and Gradle 7.5
  
To run
  Open project in your favorite IDE and run DemoApplication.java
  Alternatively, run via command line:
    ./gradlew clean build bootRun
  For faster startup, run without tests
    ./gradlew clean build bootRun -x tests
    
Testing
  - incuded in this repo is a Postman workspace that covers the basic calls to this program
  - There is 1 endpoint to provide current weather, if run above that endpoint is
      localhost:8080/api/weather
  - You must provide at least 1 of either zip code or city in order to retrieve results
    - You may specify city by providing state and country as well
    
  Examples:
    - Get current weather by zip code
      localhost:8080/api/weather?zipCode=11940
    - Get current weather in New York City
      localhost:8080/api/weather?city=New York
    - Get current weather in London
      localhost:8080/api/weather?city=London
    - The above call is very naive and returns the first result from the OpenWeatherAPI (London, Great Britain). We can get more specific by specifying country
      localhost:8080/api/weather?city=London&country=CA

    
Assumptions
  - We only need to return current weather. Current weather is defined by the following: Current temperature, weather description, feels like temperature (if one exists)
  - Units are all imperial (Degrees F)
  - Open Weather API is fast and reliable. We don't need to store these in a db/cache in case the API goes down or we can't scale well.
  - City lookup is very naive. We'll display the weather for the first result of Open Weather API. 
    - Improvement could be using client's IP when location is ambiguous
    - Assume the City, State, Country are spelled correctly.
  - There are some cities that often go by local names which are not official names. An example: in NY there is "Town of Southampton" but locals call
    this "Southampton". "Southampton" returns Southampton in Great Britain. Assume the user will always enter the official name.
  - Integer values are precise enough for current weather.
  - Lombok is okay
