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

### Project Roadmapa
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

- [X] **User Registration**
  - [X] User Creation
  - [ ] User Login
  - [X] User Password Encryption
    - Custom Username: Check to ensure the username does not exist.
    - Create user in the database.
    - Hash password/security features.
    - Include user data fields: First Name, Last Name, Pronouns, Email Address, Password (with confirmation), Mailing Address, Phone Number.
    - Sign-up button to submit registration.
- [ ] **User Profile Maintenance**
  - [ ] Profile landing page
  - Ability to change password, update pronouns, add/update profile picture.
  - Update mailing address and phone number.
- [ ] **User Roles**
  - [ ] Add custom roles
  - [ ] Set permissions of custom roles
  - Dropdown with roles and associated permissions.

</details>

#### Plugin Features
<details><summary>Events</summary>

- [ ] **Electronic Event Tickets**: Attach electronic tickets to user badges.
- [ ] **QR Codes for Event (Attendee)**: Generate and manage QR codes for electronic tickets.
- [ ] **Printed Event Tickets**: Provide options for printing event tickets.
- [ ] **QR Code Scanner for Event (Organizers)**: Dashboard for organizers to scan QR codes; ability to see who is still missing in real-time.
- [ ] **Payment**: Integration with various payment systems for processing transactions.
- [ ] Create Events
- [ ] Modify Events
- [ ] Delete Events

</details>

<details><summary>Database</summary>

- [ ] **Firebase Plugin**
- [ ] **MySQL Plugin**
 
</details>
 
<details><summary>Exhibitors</summary>

- [ ] Automated QR Code Creation
- [ ] QR Code Scanner
- [ ] Exhibitor Profile
- [ ] Tools and systems for managing exhibitor information and activities.

</details>

<details><summary>Awards</summary>

- [ ] Categories
- [ ] Nominations
- [ ] Voting

</details>

#### Data Validation
<details><summary>Validation Across Features</summary>

- [ ] **Object Validation**
  - Ensure all data fields across Events, Users, and Database categories are validated and follow predefined rules and standards.

</details>


<hr>

#### How can I help?
<div align="justify">
If you would like to contribute or provide feedback: please reach out by email below.</div>

[![Gmail](https://img.shields.io/badge/Gmail-EA4335.svg?style=for-the-badge&logo=gmail&logoColor=white)](mailto:akiraka@gmail.com)

<hr>

### Built With


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
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
