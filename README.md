Encrypt-Decrypt your data from the command line using two algorithms:

1- Shift: it shifts each letter by the specified number according to its order in the alphabet in a circle
(i.e. if the letter z is shifted by 1, it becomes a).

2- Unicode: same as the above but doesn't keep the characters in circle.

# Usages:

After compiling the code, run this command (in the same directory as the *.class files):

> java Main -mode mode -data data -alg alg -key key -in file_in -out file_out

Where:

mode can be either enc (encryption) or dec (decryption).

data is the text to be encrypted/decrypted.

alg is either Shift of Unicode (case-insensitive).

key an integer value specifying how many cells should each letter in the input text moved.

file_in is the file to get data from (in case -data option was not specified).

file_out is the file to store the resulted text.

# Note that:

-data overrides -in

If -out was not specified, print the resulted text to the standard output.

# Example Usages:

> java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode

This command must get data from the file road_to_treasure.txt, encrypt the data with the key 5,
create a file called protected.txt and write the resulted text to it.

> java Main -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode

Output:

\jqhtrj%yt%m~ujwxpnqq&

> java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec

Output:

Welcome to hyperskill!

> java Main -key 5 -alg shift -data "Welcome to hyperskill!" -mode enc

Output:

Bjqhtrj yt mdujwxpnqq!

> java Main -key 5 -alg shift -data "Bjqhtrj yt mdujwxpnqq!" -mode dec

Output:

Welcome to hyperskill!

