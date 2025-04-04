# 💡 Idea
This is a composition of libraries, APIs and utilities for a minigame network called **Ranked Project**. They all work together, communicating, managing game servers and player data in order to provide smooth gameplay and effortless server deployment.

# 📋 Project Modules

**PrivateAPI**
- Used to manage game servers, player queues, player's data, its persistency and messaging between server instances. Provides REST API as a way to communicate with it

**Common**
- Used in every other module as an abstract layer to access and work with PrivateAPI more easily. Provides DTOs and Rest Client

# 💻 Curretly Utilizied Technologies
**Spring**: Mostly used for PrivateAPI which is orchestrating the whole network and managing servers and player data
