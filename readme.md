# 1. PROJECT DESCRIPTION

Front-end created in Vaadin for the back-end part of the final project.

# 2. DEMO

REST mechanics are presented via front-end project: https://floating-lake-32865.herokuapp.com/
You may happen to see "application error" at first. Just reload the page.

# 3. BACK-END REPOSITORY
Due to not being an accepted version, code is in the working branch, not master.

https://github.com/kszubra/kodilla-fin-backend/tree/Back-end_Final

# 4. RUNNING PROJECT LOCALLY

1. To run project locally altogether with back-end application, you need to change the default port application starts on.
In application.properties uncomment starting port 8081. To run it locally with back-end running on heroku you don't need
to do it.
2. In same file you need to set the address on which back-end application is running. 

# 5. BUGS AND ISSUES  

Adding new notification preference requires matching it with user id. Due to the fact that back-end had been created
before font-end technology was known, it does not contain endpoint checking user's existence by id. As consequence
front does not check validity of input data. 