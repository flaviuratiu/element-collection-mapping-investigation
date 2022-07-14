# The problem:
Incomplete mapping of join columns fails for manually inserted rows only.

# Prerequisites:
- mysql column names in snake_case
- an entity with an embedded id having property names in camelCase, without a `@Column(name = "some_id")` annotation
- an `@ElementCollection` with `@JoinColumn` specifying `name`, but not `referencedColumnName`

# Steps to reproduce:
1. Create the `column-mapping-test` database: 
```shell
docker run --name mysql-temp -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_DATABASE=column-mapping-test -p 127.0.0.1:3306:3306 -d mysql:5.7
```
2. Start the application.
3. Execute `GET http://localhost:8080/question-responses/create` to create an initial row in the main table. (`GET` has been used here just so I can easily test even from a browser)
4. Execute the following sql, to manually insert a row in the element collection table:
```sql 
INSERT INTO `column-mapping-test`.question_response_ratings
(user_id, task_id, option_id, rating_id)
SELECT
(SELECT user_id FROM `column-mapping-test`.question_response LIMIT 1),
(SELECT task_id FROM `column-mapping-test`.question_response LIMIT 1),
(SELECT user_id FROM `column-mapping-test`.question_response LIMIT 1),
(SELECT user_id FROM `column-mapping-test`.question_response LIMIT 1)
```
5. Execute `GET http://localhost:8080/question-responses` to read existing responses from the database.

# Expected result:
Step 5 should return the `QuestionResponse` (main entity) with the manually inserted ratings (`@ElementCollection`) included.

# Actual result:
At step 5, the main entity is returned not including the element collection.
Very interesting though, if one was to have the row inserted in the collection table by the service, the `GET` at step 5 would return the main entity with the element collection included.
This can be tested with an empty db, by uncommenting line 31 in `QuestionResponseController` and skipping step 4.

# The fix:
- the embedded id properties need to be annotated with `@Column(name = "some_id")`
- each `@JoinColumn` for the `@ElementCollection` should have both `name` and `referencedColumnName` specified.

The fix can be tested from the `correct-mappings` branch.
