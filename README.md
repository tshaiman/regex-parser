# regex-parser
Classic Interview Question involving writing a simple Reg Ex engine in java
use the Attached lib for the assertThat library.

in this exercise you have to write simple Reg-Ex parser that works with 2 major simbols :

* (Asterix) = > matches [0-Infinite] characters 
+ (Plus)    => matches [0-1] characters

for example for the input "abc" the following is reg-ex match :
"abc"
"ab+"
"ab*"
"abc*"
"+bc"
"*c"
"a+c"

but 
"a*d" is not a match.

The solution should not care about performance , run-time or memory .
