
Special thanks to  [codingwithmitch.com](https://codingwithmitch.com/)

## Number Fact application
Get to know some amazing facts about

1. Date
2. Year
3. Number
4. Random facts
5. Trivia

# Model View Intent
MVI stands for Model-View-Intent. MVI is one of the newest architecture patterns for Android, inspired by the unidirectional and cyclical nature of the Cycle.js framework.

MVI works in a very different way compared to its distant relatives, MVC, MVP or MVVM. The role of each MVI components is as follows:

Model represents a state. Models in MVI should be immutable to ensure a unidirectional data flow between them and the other layers in your architecture.
Like in MVP, Interfaces in MVI represent Views, which are then implemented in one or more Activities or Fragments.
Intent represents an intention or a desire to perform an action, either by the user or the app itself. For every action, a View receives an Intent. The Presenter observes the Intent, and Models translate it into a new state.


# Dagger Hilt
Playground for learning how to use [Hilt](https://dagger.dev/hilt/). A new way to incorporate Dagger dependency injection into an Android application.

# Examples
Below is a list of examples.

## Hilt Basics

1. Preparing to use Hilt
	- Follow the setup procedure:
		- https://developer.android.com/training/dependency-injection/hilt-android
2. Field injecting a class with no dependencies. You own that class.
3. Scopes and the "tier-like" system
4. Dependencies that require dependencies (Concrete classes that you own)
5. Some things can't be constructor-injected. What is the solution?
6. Hilt Modules, Binds and Provides
7. Multiple Bindings of the same type

## Basic MVI Repository Pattern

1. Retrieve data from [Number Api](https://rapidapi.com/divad12/api/numbers-1?endpoint=53aa3b65e4b059614033fa2c) with [Retrofit](https://square.github.io/retrofit/)
2. Cache data with [Room](https://developer.android.com/topic/libraries/architecture/room)
3. Display cached data in UI

[Download APK](https://github.com/Tanmeet03/ModelViewIntent/blob/main/apk/app-debug.apk)



