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
- [ ] Users can Login and signup
- [ ] Exercises videos separated by categories
    - [ ] Yoga
    - [ ] Weight training
    - [ ] Stretching
    - [ ] Cardio
    - [ ] Sports
- [ ] Users can edit their own Profile information
- [ ] Users can follow a video from our app and do the workout

**Optional Nice-to-have Stories**
- [ ] Weekly Weight Tracker - can upload pictures of themselves
- [ ] Meal Recommendations
- [ ] Comment section/rating
- [ ] Point System
- [ ] Feature the workout of the day/week
- [ ] Friend List and comparing their workouts
- [ ] User feed to communicate their progress with other users

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
  | level           | String            | Level chosen by user that determines which words they are tested on |
  | gamesCount      | Number            | Number of games won and lost by user |
  | streakCount     | Number            | Number of consecutive games won by user  |
  | highScore       | Number            | Number of consecutive games won by user (multiplied by 100 points) |
  | favoritedWords  | Array of strings  | List of words favorited by user |
  | seenWords       | Dictionary        | Dictionary of words and how many times the word was shown to the user |
  | createdAt       | DateTime          | Date when user was created (default field) |
  | updatedAt       | DateTime          | Date when user was last updated (default field) |
  


### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
