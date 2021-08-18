# User-Defined Capstone

## Introduction

Use everything you've learned in this course to design, implement, and test a database-backed application with an HTML user interface delivered via HTTP. The Java Capstone assessment is an open-ended project in which you are responsible for every decision.

You must decide:
- the topic or domain of your application (What problem does it solve?)
- appropriate technologies (Do you build your UI with React or something else?)
- application architecture (Are the front-end and back-end separate projects? [They are.] Do you need something more or different than the course layers and patterns?)
- development strategy from planning to testing (When you say that you're 60% complete, how do you know?)

The Capstone has a formal start date and end date. You must be able to conceive, design, implement, and fully test the project in the allotted time. Don't tackle too much or too little.

<blockquote class="icon-block-exclamation-triangle">
You <strong>must</strong> work with your instructor to ensure the project is "right-sized". Don't make assumptions. Instructor approval is required. Don't start working without approval.
</blockquote>

In addition, you are absolutely forbidden to work on any capstone phase other than the idea phase before the official start date. That means no design work, no schema, and absolutely no code. You can *think* about your project as much as you like -- come up with ideas, reflect, and refine -- but do not create diagrams, create a task list, create a database schema, or write a single line of code before the start date.

<blockquote class="icon-block-sticky-note">
This requirement is designed to level the playing field for students. Your capstone is a concrete demonstration of what you can accomplish in a fixed amount of time. If you start before your classmates, the demonstration isn't fair or accurate.
<p>Don't test this requirement. If there's reason to believe you started early, your instructor will assign a fully specified final project in place of the capstone.</p>
</blockquote>

## Technical Requirements

Unless it's modified by a **stretch goal** (see below), the following are strict technical requirements.

1. Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
2. MySQL for data management
3. Spring Boot, MVC (@RestController), JdbcTemplate, Testing
4. An HTML and CSS UI that's built with React
5. Sensible layering and pattern choices
6. At least one UI secured by role
7. A full test suite that covers the domain and data layers

## Stretch Goal

The stretch goal demonstrates your ability to learn independently and makes your project unique. It is required.

Use a technology that wasn't covered in the course. The technology can be something that you already know. Keep time constraints firmly in mind. A stretch goal can be anything from a small enhancement to a completely different application approach.

Examples from previous capstones:

- JavaScript map API
- JavaScript productivity libraries: [Lodash](https://lodash.com/), [Dinero.js](https://dinerojs.com/), or even an existing but unfamiliar library like [Intl.DateTimeFormat](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat)
- alternative JavaScript front-end frameworks: Angular, Vue.js, Svelte
- TypeScript
- WebSocket communication: used Stomp, but Stomp isn't necessary
- animations: used GreenSock, but there are many alternatives
- SVG-enhanced UI
- Java productivity libraries: [Lombok](https://projectlombok.org/), [Mockito](https://github.com/mockito/mockito) (advanced usage), or [Guava](https://github.com/google/guava)
- in-browser audio
- JPA
- alternative data stores: PostgreSQL, MongoDB, Hadoop
- hardware-based projects: Raspberry Pi smart mirror, Arduino-based home security
- deployment: to a dedicated VM or the cloud
- modest machine learning: used an open-source chess move evaluator, be careful with this one -- it's not possible to learn even simple machine learning from scratch in a couple of weeks *while* you're building a substantial application
- web scraping

Less commonly, a stretch goal can be a modest increase in scope: a couple more data entities, nuanced domain rules, or solving a problem in uncharted territory.

Instructor approval is required for all stretch goals.

## Approach

Planning is absolutely essential for a project this large. Create a complete list of concrete tasks required to finish. Tasks should take no longer than 4 hours. Schedule each task for a specific day. At the end of each day, take stock. Are you ahead of schedule, on schedule, or behind? If ahead, how can you put the extra time to use? 

If behind, what do you have to adjust to complete the project? Don't just hope that things will improve. Take concrete steps to simplify or remove features.

Ask questions. Even though you control the specification, your classmates and instructors are invaluable resources. Ask clarifying questions. Don't make assumptions when things aren't clear.

Test as you go. If you wait to test until the end, three things happen:

1. Your tests aren't as good.
2. The last bit of development becomes a slog. No one loves 100% testing. Spread the testing out over several days.
3. You gain false confidence because you don't see domain failures that are prevented in the UI. Remember, if we throw away the UI, the domain should still prevent all invalid transactions.

## Deliverables

1. A schedule of concrete tasks (at most 4 hours per task) that represent all work required to finish your project along with task statuses
2. Diagrams: database schema, class, layer, flow
3. Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes.
4. A short presentation, 4 to 6 slides, describing who you are, how you found programming, and your project
5. Complete project source code free of compiler errors
6. A schema creation script along with any DML for data needed to run the application (security roles, default data, etc)
7. If it isn't straight-forward, instruction to set up and run your application
8. A complete test suite with all tests passing
