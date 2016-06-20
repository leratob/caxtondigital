## Caxton Digital assessment solution

This is Lerato Bodibe's solution to the assessment.
The application uses http port 8080. Before running the application, make sure nothing else is utilizing http port 8080.
Everything that the application needs to run, for the purpose of the assessment, has been embedded in project. The items below are embedded.

## Plug and play solution for the purpose of demo
    
**Database**
    
Instead of using Microsoft database as a datasource, H2 properties have been configured in the application.properties file so that the project is a plug and play.
    To connect the application to a microsoft sql server the database properties can be changec to point to microsoft sql server.
    When the application starts up, the data from the artists.xlsx file is imported into the H2 database. The data is imported using apache poi
    The database can be accessed from the endpoing http://localhost:8080/console
    
**Application server**
    
Instead of using an external tomcat, I am using spring boot with an embedded tomcat.
   

## Tools used in the solution

1.  Maven
2.  Spring REST SERVICES
3.  Spring boot with an embedded tomcat.
4.  projectlombok to deal with boiler plate code. eg getters and setters
5.  Spring MVC
6.  git repository.
7.  JAXB and XSD


## Installation

1.  Clone the project into your local machine.
2.  Run maven clean and build to generate classes from the metadata.xsd file
2.  Run the main function in src/main/java/za/co/caxtondigital/Application, this should start an embedded tomcat server on http port 8080.


## Available rest services operations

* **Search for artists** - *get* - http://localhost:8080/artist/search/{keyword}/{page_number}/{page_size}
* e.g http://localhost:8080/artist/search/joh/1/10
* Response data

    ```
    {
        artists: [
            {
                id: "b625448e-bf4a-41c3-a421-72ad46cdb831 ",
                name: "John Coltrane ",
                country: "US",
                aliases: "John Coltraine,John William Coltrane"
            }
        ],
        numberOfSearchResults: 6,
        page: 0,
        pageSize: 1,
        numberOfPages: 6
    }
    ```

* **Retrieve 10 albums** - *get* - http://localhost:8080/artist/{artist_id}/albums
* e.g http://localhost:8080/artist/144ef525-85e9-40c3-8335-02c32d0861f3/albums
* Response data

    ```
    [
        {
            releaseId: "bc290ee4-e571-4ac6-a0ec-2b444ee4dfb7",
            title: "Aware 8: The Compilation",
            status: "Official",
            label: null,
            numberOfTracks: 16,
            otherArtists: [
                {
                    id: "89ad4ac3-39f7-470e-963a-56509c546377",
                    name: "Various Artists",
                }
            ]
        }
    ]
    ```