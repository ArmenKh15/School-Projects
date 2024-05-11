Introduction
The nefarious Dr. Evil has planted a slew of “binary bombs” on our class machines. 
A binary bomb is a program that consists of a sequence of phases. Each phase expects 
you to type a particular string on stdin. If you type the correct string, then the phase 
is defused and the bomb proceeds to the next phase. Otherwise, the bomb explodes by printing 
"BOOM!!!" and then terminating. The bomb is defused when every phase has been defused.

There are too many bombs for us to deal with, so we are giving each student a bomb to defuse. 
Your mission, which you have no choice but to accept, is to defuse your bomb before the due date. 
Good luck, and welcome to the bomb squad!


Defuse Your Bomb
Your job for this lab is to defuse your bomb.

You must do the assignment on rlogin. In fact, there is a rumor that Dr. Evil really is evil, and 
the bomb will always blow up if run elsewhere. There are several other tamper-proofing devices built 
into the bomb as well, or so we hear.

You can use many tools to help you defuse your bomb. Please look at the hints section for some tips and 
ideas. The best way is to use your favorite debugger to step through the disassembled binary.

Each time your bomb explodes it notifies the bomblab server, and you lose 1/4 point (up to a max of 20 
points) in the final score for the lab. So there are consequences to exploding the bomb. You must be careful!

The first four phases are worth 10 points each. Phases 5 is a little more difficult, so it is worth 15 
points each. So the maximum score you can get is 55 points from the first 5 phases. See the note at the 
top of the page about Phase 6/extra credit.

Although phases get progressively harder to defuse, the expertise you gain as you move from phase to phase 
should offset this difficulty. However, the last phase will challenge even the best students, so please don’t wait until the last minute to start.

The bomb ignores blank input lines. If you run your bomb with a command line argument, for example,

./bomb psol.txt

then it will read the input lines from psol.txt until it reaches EOF (end of file), and then switch over to stdin. 
In a moment of weakness, Dr. Evil added this feature so you don’t have to keep retyping the solutions to phases you have already defused.

NOTE: you must have a blank line at the end of the file for the solutions to be correctly read

To avoid accidentally detonating the bomb, you will need to learn how to single-step through the assembly code 
and how to set breakpoints. You will also need to learn how to inspect both the registers and the memory states. 
One of the nice side-effects of doing the lab is that you will get very good at using a debugger. This is a crucial 
skill that will pay big dividends the rest of your career.
