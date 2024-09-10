<a name="readme-top"></a>


<div align="center">

[![Contributors][contributors-shield]][contributors-url] [![LinkedIn][linkedin-shield]][linkedin-url]

</div>
<div align="center">

![conconnect_logo.png](src%2Fmain%2Fresources%2Fstatic%2Fconconnect_logo.png)
<br><i>The Modular Convention Platform</i>
</div>



  <div align="justify">
    Creation of Spring Boot web application utilizing Java. The purpose of this application is to create an open-source modular option for small conventions (such as comic conventions, gaming conventions, sci-fi, etc).
</div>

<hr>

### Current Status
- In Early Development (<i>as of March 8th, 2024</i>)

<hr>

### Project Roadmap
<div align="justify">
This section details the current (and not exhaustive) list of planned features. The core concept is to allow easy setup with scalability without needing to understand the inner workings.

#### Introduction
This section documents the roadmap, including initial features, plugins, and any additional add-ons in the works.

#### Overall Vision
The goal is to create an open-sourced modular convention application with plugins for conventions and award sites.

</div>

<hr>

#### Core Functionality

<details><summary>User Registration and Management</summary>

- [ ] **User Registration**
  - [ ] User Creation (via REST API: `POST /api/user`)
  - [ ] User Login (via REST API: `POST /api/auth/login`)
  - [ ] **User Password Encryption**
    - [ ] Custom Username: Check to ensure the username does not exist (via REST API: `GET /api/user/{username}/exists`).
    - [ ] Create user in the database (via REST API: `POST /api/user`).
    - [ ] Hash password/security features.
    - [ ] Include user data fields: First Name, Last Name, Pronouns, Email Address, Password (with confirmation), Mailing Address, Phone Number.
    - [ ] Sign-up button to submit registration (triggers the REST API).
- [ ] **User Profile Maintenance**
  - [ ] Profile landing page (via REST API: `GET /api/user/{userId}`).
  - [ ] Ability to change password, update pronouns, add/update profile picture (via REST API: `PUT /api/user/{userId}`).
  - [ ] Update mailing address and phone number (via REST API: `PUT /api/user/{userId}/update-address`).
- [ ] **User Roles**
  - [ ] Add custom roles (via REST API: `POST /api/user/roles`).
  - [ ] Set permissions of custom roles (via REST API: `PUT /api/user/roles/{roleId}/permissions`).
  - [ ] Dropdown with roles and associated permissions (via REST API: `GET /api/user/roles`).

</details>

<details><summary>Convention Organizer Management</summary>

- [ ] **Assign Locations to Events**
  - [ ] Manage and assign event locations (via REST API: `PUT /api/event/{eventId}/location`).
- [ ] **Assign Exhibitors to Booth Locations**
  - [ ] Assign exhibitors to specific booth locations (via REST API: `POST /api/exhibitor/{exhibitorId}/assign-booth`).
- [ ] **Approve Events**
  - [ ] Admin functionality for approving event requests (via REST API: `PUT /api/event/{eventId}/approve`).

</details>

#### Plugin Features

<details><summary>Events</summary>

- [ ] **Create Events** (via REST API: `POST /api/event`)
- [ ] **Modify Events** (via REST API: `PUT /api/event/{eventId}`)
- [ ] **Delete Events** (via REST API: `DELETE /api/event/{eventId}`)
- [ ] **Electronic Event Tickets** (via REST API: `GET /api/event/{eventId}/tickets`)
  - [ ] Attach electronic tickets to user badges (via REST API: `POST /api/tickets/{ticketId}/attach-to-badge`).
- [ ] **QR Codes for Event (Attendee)**
  - [ ] Generate and manage QR codes for electronic tickets (via REST API: `GET /api/event/{eventId}/qr-code`).
- [ ] **Printed Event Tickets**
  - [ ] Provide options for printing event tickets (via REST API: `GET /api/event/{eventId}/print-tickets`).
- [ ] **QR Code Scanner for Event (Organizers)**
  - [ ] Dashboard for organizers to scan QR codes; ability to see who is still missing in real-time (via REST API: `GET /api/event/{eventId}/attendees`).
- [ ] **Payments**
  - [ ] Integration with various payment systems for processing transactions (via REST API: `POST /api/payment`).

</details>

#### REST API Functionality
<details><summary>API Endpoints Overview</summary>

- [ ] **Users**
  - [ ] `GET /api/user` - Get all users.
  - [ ] `GET /api/user/{userId}` - Get user by ID.
  - [ ] `POST /api/user` - Create a new user.
  - [ ] `PUT /api/user/{userId}` - Update an existing user.
  - [ ] `DELETE /api/user/{userId}` - Delete a user.

- [ ] **Authentication**
  - [ ] `POST /api/auth/login` - Authenticate user credentials and return a token.
  - [ ] `POST /api/auth/logout` - Invalidate user session/token.

- [ ] **Events**
  - [ ] `GET /api/event` - Get all events.
  - [ ] `GET /api/event/{eventId}` - Get event details by ID.
  - [ ] `POST /api/event` - Create a new event.
  - [ ] `PUT /api/event/{eventId}` - Update an event.
  - [ ] `DELETE /api/event/{eventId}` - Delete an event.
  - [ ] `GET /api/search` - Search for events based on specific fields and criteria.
    - **Query Parameters**:
      - `entity`: The type of entity to search (e.g., `event`).
      - `field`: The field to search (e.g., `shortDescription`).
      - `contains`: The value to search for (e.g., part of the `shortDescription`).
    - **Example**: `/api/search?entity=event&field=shortDescription&contains=dog`


- [ ] **Tickets**
  - [ ] `GET /api/event/{eventId}/tickets` - Get tickets for an event.
  - [ ] `POST /api/tickets/{ticketId}/attach-to-badge` - Attach tickets to user badge.

- [ ] **Payments**
  - [ ] `POST /api/payment` - Process a payment for an event or service.

</details>

#### Database

<details><summary>Database</summary>

- [ ] **Firebase Plugin** (via REST API: interacts with Firebase for user and event data)
  - [ ] `GET /api/event` to retrieve events from Firebase.
  - [ ] `POST /api/event` to create a new event in Firebase.
- [ ] **MySQL Plugin** (via REST API: interacts with MySQL for user and event data)
  - [ ] Similar endpoints available for MySQL-based operations.

</details>

#### Exhibitors
<details><summary>Exhibitor Management</summary>

- [ ] **Automated QR Code Creation** (via REST API: `POST /api/exhibitor/{exhibitorId}/qr-code`)
- [ ] **QR Code Scanner** (via REST API: `GET /api/exhibitor/{exhibitorId}/scan`)
- [ ] **Exhibitor Profile**
  - [ ] View and manage exhibitor profiles (via REST API: `GET /api/exhibitor/{exhibitorId}`)
  - [ ] Update exhibitor details (via REST API: `PUT /api/exhibitor/{exhibitorId}`)
- [ ] Tools and systems for managing exhibitor information and activities (via REST API: `POST /api/exhibitor`).

</details>

#### Awards
<details><summary>Awards Management</summary>

- [ ] **Categories** (via REST API: `GET /api/awards/categories`)
- [ ] **Nominations** (via REST API: `POST /api/awards/{categoryId}/nomination`)
- [ ] **Voting** (via REST API: `POST /api/awards/{awardId}/vote`)

</details>



<hr>

#### How can I help?
<div align="justify">
If you would like to contribute or provide feedback: please reach out by email below.</div>

[![Gmail](https://img.shields.io/badge/Gmail-EA4335.svg?style=for-the-badge&logo=gmail&logoColor=white)](mailto:akiraka@gmail.com)

<hr>

### Built With


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![REST API](https://img.shields.io/badge/REST%20API-005571.svg?style=for-the-badge&logo=api&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28.svg?style=for-the-badge&logo=Firebase&logoColor=black)
![VS Code](https://img.shields.io/badge/Visual%20Studio%20Code-007ACC.svg?style=for-the-badge&logo=Visual-Studio-Code&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=for-the-badge&logo=Apache-Maven&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E.svg?style=for-the-badge&logo=JavaScript&logoColor=black)
![HTML5](https://img.shields.io/badge/HTML5-E34F26.svg?style=for-the-badge&logo=HTML5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6.svg?style=for-the-badge&logo=CSS3&logoColor=white)




[contributors-shield]: https://img.shields.io/github/contributors/mriffey1/ConventionWebApp.svg?style=for-the-badge
[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[contributors-url]: https://github.com/mriffey1/ConventionWebApp/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/mriffey1/ConventionWebApp.svg?style=for-the-badge
[forks-url]: https://github.com/mriffey1/ConventionWebApp/network/members
[stars-shield]: https://img.shields.io/github/stars/mriffey1/ConventionWebApp.svg?style=for-the-badge
[stars-url]: https://github.com/mriffey1/ConventionWebApp/stargazers
[issues-shield]: https://img.shields.io/github/issues/mriffey1/ConventionWebApp.svg?style=for-the-badge
[issues-url]: https://github.com/mriffey1/ConventionWebApp/issues
[license-shield]: https://img.shields.io/github/license/mriffey1/ConventionWebApp.svg?style=for-the-badge
[license-url]: https://github.com/mriffey1/ConventionWebApp/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/mriffey
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
