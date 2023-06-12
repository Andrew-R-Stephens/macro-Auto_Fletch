<div style="display:float" align="center">
    <img height="100%" src="https://upload.wikimedia.org/wikipedia/en/a/a7/Old_School_Runescape_Logo.png"/>
</div>
<div style="display:float" align="center">
    <img height="100%" src="https://oldschool.runescape.wiki/images/Fletching_icon_%28detail%29.png"/>
</div>
<div style="display:float" align="center">
    <h2>Fletching Macro</h2>
</div>

<br>

<div style="display:float" align="center">
    <img src="https://img.shields.io/badge/Java-JDK%2017-F80000?logo=oracle&logoColor=white&style=bold"/>
</div>

<br>
<br>

> Let's face it, Old School Runescape has its up's and down's. When it comes to skilling, we've all stared at the screen mindlessly clicking around, doomed to repeatedly execute the same task over and over again.

<br>

<h3>Overview</h3>
This is a solution (macro) which records cycles of click events. It then uses the recorded clicks to execute timed events for you. freeing your hands or freeing you from the computer altogether.
`This is not an injection, a hack, or anything malicious. It simply produces an interactive overlay over your entire screen.`

<br>
<br>

<h3>Top Level Design</h3>
-This macro records the location of click events and then automatically reproduces the click for you. More than one click event will trigger a recording of the time difference between clicks. A few key presses later and you'll be free to do as you wish with your hands.

-A recording cycle is a series of clicks which define a complete loop of actions. `(Opening the bank, withdrawing logs, exiting the bank, clicking knife, clicking logs, selecting fletch option, *waiting for fletching to finish*, then back to the beginning)`

-You may record more than one cycle. If a series of cycles is recorded, execution order will be randomized `Example:  A, B, C -> [B, C, A], [C, A, B], [A, B, C], [..etc] `.

-You must then manually entire the number of series of cycles that must be executed for your full task to complete.

<br>

<p>Due to human imperfection, the more cycles you add to your series, the harder it is for anti-cheat to catch you.</p>

<br>

<h3>Controls:</h3>

```sh
ESC: Terminates the overlay/program.
M1: Executes the mouse event.
SPACEBAR: Indicates that the cycles are prepped and opens cmd for user input.
```
