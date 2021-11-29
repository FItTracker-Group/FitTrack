FitTrack Project - README Template
===
# FitTrack

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A fitness application designed to help people learn about various exercises and keep track of their weight. The purpose of this app is to help create a workout community among friends or people with similar goals.

### App Evaluation
<!-- [Evaluation of your app across the following attributes] -->
- **Category:**
- **Mobile:**
- **Story:**
- **Market:**
- **Habit:**
- **Scope:**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
- [x] Users can Login and signup
- [x] Users can follow a video from our app and do the exercises separated by categories
    - [x] Yoga
    - [x] Weight training
    - [x] Stretching
    - [x] Cardio
- [x] Created Home Screen with exercise categories that lead to another detailed screen
- [x] Profile fragment
    - [x] Users can edit their own Profile information
    - [x] Users information is parsed and displayed
- [x] User feed to communicate their progress with other users
- [x] User can make a post to the feed

**Optional Nice-to-have Stories**
- [ ] Weekly Weight Tracker - can upload pictures of themselves
- [ ] Meal Recommendations
- [ ] Comment section/rating
- [ ] Point System
- [ ] Feature the workout of the day/week
- [ ] Friend List and comparing their workouts
- [x] User can time their workout or set a timer for rest periods
- [x] User can navigate workouts with a search bar
- [ ] Users can find personal training coaches

**Current Progress GIFS**

<img src='https://github.com/FItTracker-Group/FitTrack/blob/main/fittrack%20unit%2013.gif' width='200' alt='Video Walkthrough' />

### 2. Screen Archetypes

* [Login]
   * [Login]
   * [Register]
   * ...
* [Explore]
   * [Home Screen - Exercise categories]
   * ...
* [Stream]
   * [Feed - for users to share their workout videos/pictures/suggestions etc]
   * [Users can socialize and meet other people to workout with]
   * ...
* [Detail]
   * [Specific exercise video(s) the user wants to learn]
   * [Comment section for user feedback]
   * ...
* [Creation]
   * [Compose a post]
   * ...
* [Profile]
   * [Consists of the users username/name]
   * [includes the optional weight tracker]
   * [Friend List]
   * ...

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* [Exercises]
* [Feed]
* [Profile]

Optional/Extra:
* [Meal]

**Flow Navigation** (Screen to Screen)

* Launch Screen -> Login/Register
* Login -> Main Screen
* Exercises -> List exercise categories
* Feed-> jumps to community/friend feed/stream
* Profile -> personal information

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/FItTracker-Group/FitTrack/blob/main/Wireframe.png" width=''>

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/FItTracker-Group/FitTrack/blob/main/FitTrack.gif" width='250'>
### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]

### Models
[Add table of models]

##### User
  | Property        | Type              | Description |
  | --------------- | ----------------- | ------------|
  | objectId        | String            | Unique id for the user (default field) |
  | username        | String            | Username set by user |
  | password        | String            | Password set by user |
  | image           | File              | Profile image that user uploads |
  | friend_list     | ArrayList         | List of friends the user has |
  | Profile Description      | String   | User biography: user can write about themself |

##### Post
  | Property        | Type              | Description |
  | --------------- | ----------------- | ------------|
  | objectId        | String            | Unique id for the post (default field) |
  | image           | File              | Profile image that user uploads |
  | author      | Pointer to User            | Post created by the author |
  | description     | String            | Introduce the post  |
  | likesCount       | Number          | Number of likes for the post |
  | createdAt       | DateTime          | Date when user was created (default field) |
  | caption/titile       | String            | Title introducing what the post is about |
  
  ##### Exercise
  | Property        | Type              | Description |
  | --------------- | ----------------- | ------------|
  | objectId        | String            | Unique id for the user (default field) |
  | title        | String            | The description for the exercise |
  | type        | String            | The name of type the each exercise |
  | image           | File              | The picture to show the type of exercise |
  | level           | String            | The level type for each exercise(beginner, intermediate, advance) |

  ##### Video
  | Property        | Type              | Description |
  | --------------- | ----------------- | ------------|
  | objectId        | String            | Unique id for the user (default field) |
  | title(description) | String            | The description for the video |
  | likesCount       | Number          | Number of likes for the video | 
  | dislikesCount       | Number          | Number of likes for the video |  
  | url        | String            | The link from 3rd party resource |


### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]\

#### List of network requests by screen
- Login/Register Screen:
    * (Read/Get) Create a new user object with provided information
    ``` ParseUser user = new ParseUser();
        user.setEmail(etEmail.getText().toString());
        user.setUsername(etUsername.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.signUpInBackground(e -> {
            if (e != null) {
                Log.e(TAG, "Error signing up");
            } else {
                Log.i(TAG, "Sign up successful");
                goLoginActivity();
            }
        }); 

- Home screen:
    * (Read/Get) Query the video title, likes, and url
    * (Create/Post) adding a like to the video
 
- Feed screen:
    * (Create/Post) Create a post with a photo(optional), caption(optional), and description
    * (Create/Post) adding a like to the video

- Profile screen:
    * (Read/Get) Query logged in user object
    * (Update/PUT) Update user profile image
