# Tinyurl - URL Shortener
Tinyurl is a an example/clone for URL shortening service.

Created a REST API that provides the ability to convert a long URL to a shortened one.

By taking the long URL as input, a key is generated, using the SHA-256 hash function. This key is mapped to the long URL, and is used to form the shortened URL. Now, if the user makes a request using the shortened URL, will be redirected to the same page.

# Technologies used
- Spring Boot
- Postgres
- Javascript

# Functionality 
1. Add a new long URL, to generate a shortened URL.
   POST : http://localhost:8080/newUrl. The body content must be a JSON with the structure: {"url": "https://facebook.com"}.

   On success, the entered long URL, the generated key and the shortened URL are returned.

2. Obtain the content of the page from the long URL, using the generated key.
   GET : http://localhost:8080/{key}.

   Using the frontend, simply click on the shortened URL on the table to get redirected.
   If successful, it is redirected to the page with the long URL entered, displaying its content.

3. Delete the information associated with a key.
   DELETE : http://localhost:8080/{key}.
   Using the frontend, simply click on the delete button corresponding the URL on the table.
   If successful, the long URL, key and shortened URL (associated with the entered key) are deleted.

# Demo


https://github.com/shashwatidash/tinyurl/assets/42328420/dee181c0-551d-4b35-b18a-d0a9cf244014



# Learnings

- Learn more about hash functions . It has been taken into account how it affects the length of the key and the possible collisions that may occur.

- Practice with Spring Boot . A layered code organization has been followed, which facilitates development, maintenance and modularity.

Note : The file V1__initialization.sqlcontains the SQL queries that must be executed with what is necessary for the project's DB. They can be executed in a script, connecting to the DB.
