# Escape Room: The Maze of Shadows
AP CSA PLTW 1.4.1 Java Project
By: Hrihaan, Avi, Rohan

## Project Overview

Escape Room: The Maze of Shadows is a grid-based adventure game built in Java for the AP Computer Science A (PLTW) 1.4.1 project.

The project has a bunch of java key concepts such as loops, methods, conditionals, user input, arrays, and classes.

The GUI is provided by PLTW through the GameGUI class.

## Gameplay Summary

You control a player inside a maze using text commands.
Your objective:

-Collect all prizes

-Avoid traps

-Strategically check or spring traps

-Navigate around walls

-Use single steps or jumps

-Reach the exit once all prizes are collected

You win when all prizes are collected.

If you step on a trap, you lose.


## How to Run the Game:
Compile and Run via Terminal in VSCode

javac EscapeRoom.java

java EscapeRoom.java 

## Gameplay Flow

Game starts and prints help menu

Player enters commands

Score and steps update after every action

If all prizes collected → player wins

Session high score updates

Player chooses whether to replay

Game continues until the player quits

## Functionalities and Contributions
Rohan:

My contributions focused on the project's data tracking (the high score and the change in score due to replay), improving the UX (user experience), and fixing all merger issues. I built the High Score Tracker, implementing the static variable required to save and display the single lowest score achieved across all game sessions. I also enhanced the player experience by adjusting the replay command so it now explicitly captures and reports the exact score penalty to the user, which makes the game cost mechanics due to replaying (if the player chooses to replay) clear. I integrated the step tracker for better real-time progress feedback. During the submission process, I managed the final integration, serving as the Integration Lead. This included merging my partners' code components with my own. I manually restored my functional code when it was accidentally removed. I ran the final tests to confirm the entire program was complete and runnable. Finally, I documented an earlier in-progress input approach in EscapeRoom2.java, although our group ultimately followed a different method for user input. 

## Presentation Link

https://www.canva.com/design/DAG4pbwihJY/PqG8Yj-tWEBNo2UdxcjgHA/view?utm_content=DAG4pbwihJY&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=he8d0f8265d
