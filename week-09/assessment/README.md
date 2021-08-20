
# Secured React Field Agent Assessment

## Tasks

_TODO_ Add time estimates to each of the top-level tasks

### Part 0: Set Up and Planning

* [ ] Continue working from last week's React Field Agent repository (#.# hours)
  * [ ] Update the README with the contents from this file

* [ ] Create a new branch for all work on the assessment
  * From within the local repo, create the branch by running the command `git checkout -b assessment-work` (this command creates a branch named `assessment-work` and makes the new branch the active branch)
  * Push the branch to the remote repo by running the command `git push --set-upstream origin assessment-work`
  * _Now just stay on the `assessment-work` branch and use the normal git workflow:_
    * Make code changes
    * Run `git status` to review changes
    * Run `git add .` to stage changes
    * Run `git commit -m "Some commit message"` to commit changes
    * Run `git push` to push changes to the remote repo
    * Rinse and repeat!

* [ ] Clone the [`secured-field-agent`](https://github.com/dev10-program/secured-field-agent) repo (#.# hours)
  * Review the repo's README file
  * Test starting the app by running the command `docker-compose up` from the root of the repo
  * **Note: Be sure that there aren't any other apps running in IntelliJ or from the terminal on ports 5000 and 8080.**
  * **Don't make any changes to the app**
  * **Don't copy the app into the assessment repo**
  * **Use the Secured Field Agent app instead of the original Field Agent API app for the Secured React Field Agent backend service**
  * Use the provided HTTP requests to test the provided User and Field Agent endpoints

* [ ] Review the requirements (#.# hours)

* [ ] Identify any research that I need to do (#.# hours)

---

_Note: The following sequence of tasks is focused on keeping refactoring to a minimum. To that end, tasks related to user login and registration are to be completed before any tasks related to updating the Agents CRUD UI. This is necessary because the Secured Field Agent app requires a valid auth token for all Field Agent API HTTP requests._

### Part 1: Client-Side Routes

* [ ] Implement the required client-side routes (#.# hours)
  * [ ] Install `react-router-dom`
  * [ ] Define the necessary client-side routes (see the list of routes below)
  * [ ] Stub out any components that are needed to support the client-side routes
    * _Note: Stub out the individual Agents CRUD UI components but hold off on moving any code from last week's monolithic Agents CRUD UI component to the individual components_
  * [ ] Display a "Not Found" message if a route doesn't match one of the defined routes

* [ ] Migrate HTML and CSS from Module 8 assessment (as needed) (#.# hours)
  * [ ] Home
  * [ ] User Login
  * [ ] User Registration

**Make sure that my GitHub repo is updated (i.e. push all commits to my local feature branch to the remote repo)!**

### Part 2: User Login and Registration

* [ ] Create a "Login" component (#.# hours)
  * [ ] Add a form with "Username" and "Password" fields
  * [ ] Use `fetch` to `POST` the user's information to the User API
  * [ ] Use an existing user's information to test that the API returns an auth token

* [ ] Add the necessary user state and helper functions to the App component (#.# hours)
  * [ ] Add a state variable to track the current user
  * [ ] Add a function to login a user
  * [ ] Add a function to logout a user
  * [ ] Collect the user state variable along with the login/logout helper functions into a single `auth` object

* [ ] Create a React context to track user auth (#.# hours)
  * [ ] Create the context in its own module
  * [ ] Update the App component to provide the context to the entire app
  * [ ] Set the context's `value` to the `auth` object

* [ ] Update the "Login" component (#.# hours)
  * [ ] Consume the user auth context
  * [ ] After a successful `POST` to the User API, call the auth context's login function and pass in the auth token and redirect the user to the "Home" route

* [ ] Update the header/navbar component (#.# hours)
  * [ ] Consume the user auth context
  * [ ] When there's a logged in user, display the user's username and "Logout" button
  * [ ] When there's not a logged in user, display links for the "Login" and "Register" routes
  * [ ] When the "Logout" button is clicked, call the auth context's logout function

* [ ] Protect each of the agent related routes (#.# hours)
  * [ ] When there's a logged in user, display the route's associated component
  * [ ] When there's not a logged in user, redirect the user to the login route

* [ ] Create a "Register" component (#.# hours)
  * [ ] Add a form with "Username", "Password", and "Confirm Password" fields
  * [ ] Use `fetch` to `POST` the user's information to the User API
  * [ ] After a successful `POST` to the User API, determine what to do next (i.e. automatically login the user and redirect them to the "Home" route or redirect them to the login route)

**Make sure that my GitHub repo is updated (i.e. push all commits to my local feature branch to the remote repo)!**

### Part 3: Agents CRUD UI Component Refactoring

* [ ] Update the "Agents" component to use the API (#.# hours)
  * [ ] Use `fetch` to `GET` a list of agents from the Field Agent API when the component is first loaded
  * [ ] Update the "Add Agent" button to redirect the user to the "Add Agent" route (if not already completed)
  * [ ] Update the individual agent "Edit" and "Delete" buttons to redirect the user to the appropriate routes (if not already implemented)

* [ ] Update the "Add Agent" component (#.# hours)
  * [ ] Move code from the "Agents" component into the "Add Agent" component
  * [ ] Use `fetch` to `POST` the new agent's information to the Field Agent API
  * [ ] Display any validation errors from the API in the UI
  * [ ] After a successful `POST` to the Field Agent API, redirect the user to the "Agents" route

* [ ] Update the "Edit Agent" component (#.# hours)
  * [ ] Move code from the "Agents" component into the "Edit Agent" component
  * [ ] Use the `useParams` hook to get the agent's ID from the route
  * [ ] Use `fetch` to `GET` the agent from the Field Agent API when the component is first loaded
  * [ ] Use `fetch` to `PUT` the updated agent's information to the Field Agent API
  * [ ] Display any validation errors from the API in the UI
  * [ ] After a successful `PUT` to the Field Agent API, redirect the user to the "Agents" route

* [ ] Update the "Delete Agent" component (if needed) (#.# hours)
  * [ ] Move code from the "Agents" component into the "Delete Agent" component
  * [ ] Use the `useParams` hook to get the agent's ID from the route
  * [ ] Use `fetch` to `GET` the agent from the Field Agent API when the component is first loaded
  * [ ] Use `fetch` to `DELETE` the agent from the Field Agent API
  * [ ] After a successful `DELETE` from the Field Agent API, redirect the user to the "Agents" route

**Make sure that my GitHub repo is updated (i.e. push all commits to my local feature branch to the remote repo)!**

### Part 4: Testing and Project Submission

* [ ] Use the provided test plan to manually test the application (#.# hours)

* [ ] Create a pull request in GitHub to facilitate code review (#.# hours)

---

## High-Level Requirements

Complete a secured React front-end for the Field Agent project.

* Use the Fetch API to send all CRUD operations to the back-end data service.
* Display all API validation errors in the React UI.
* Implement the required client-side routes.
* Display a "Not Found" message if a route doesn't match one of the defined routes.
* Create new React components as needed to support the required client-side routes.
* Implement user login and registration.
* Require a user to login to view the Agents CRUD UI.
* Display the logged in user's username in the header.
* Provide a way for the user to logout.

## Technical Requirements

* Use `fetch` for async HTTP.
* You are not allowed to change the Field Agent HTTP Service or database (unless there's a confirmed bug and your instructor approves).
* Use React Router to implement the client-side routes.
* Use React Context to share the current logged in user's information to any component that needs access to that information.
* Use the provided User API to support adding user login and registration.
* Use React Router's `useHistory` hook to programmatically redirect users.
* Use React Router's `useParams` hook to access parameters, paths, and other data.

## Client-Side Routes

* "Home" `/` - Renders a component that displays a welcome message and a link to the "Agents" route
  * Links to other parts of the website could be added in the future
* "Agents" `/agents` - Renders a component that displays a list of agents
* "Add Agent" `/agents/add` - Renders a component that displays a form to add an agent
* "Edit Agent" `/agents/edit/:id` - Renders a component that displays a form to edit the agent specified by the `:id` route parameter
* "Delete Agent" `/agents/delete/:id` (optional) - Renders a component that displays a confirmation message to delete the agent specified by the `:id` route parameter
  * _Note: If this route isn't implemented, handle agent deletion within the "Agents" route._
* "Login" `/login` - Renders a component that displays a form to login a user
* "Register" `/register` - Renders a component that displays a form to register a user
* "Not Found" - Renders a component that displays a friendly "not found" message if the requested route doesn't match one of the defined routes
