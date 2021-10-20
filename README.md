Encrypt-Decrypt any text from the command line using two algorithms:

1- Shift: it shifts each letter by the specified number according to its order in the alphabet in a circle
(i.e. if the letter z is shifted by 1, it becomes a).

2- Unicode: same as the above but doesn't keep the characters in circle.

# Usages:

After compiling the code, run this command (in the same directory as the *.class files):

> java Main -mode mode -data data -alg alg -key key -in file_in.txt -out file_out.txt

Where:

mode: can be either enc (encryption) or dec (decryption).

data: is the text to be encrypted/decrypted.

alg: is either Shift of Unicode (case-insensitive).

key: an integer value specifying how many cells should each letter in the input text moved.

file_in.txt: is the file to get data from (in case -data option was not specified).

file_out.txt: is the file to store the resulted text.

# Note:

-data overrides -in

If -out was not specified, the cipher text will be printed to the standard output.

# Example Usages:

Input #1:

> java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode

This command must get data from the file road_to_treasure.txt (already exists), encrypt the data with the key 5,
create a file called protected.txt and write the cipher text to it.

Input #2:

> java Main -mode enc -key 5 -data "Hello World, Java!" -alg unicode

Output #2:

Mjqqt%\twqi1%Of{f&

Input #3:

> java Main -key 5 -alg unicode -data "Mjqqt%\twqi1%Of{f&" -mode dec

Output #3:

Hello World, Java!

Input #4:

> java Main -key 5 -alg shift -data "Hello World, Java!" -mode enc

Output #4:

Mjqqt Btwqi, Ofaf!

Input #5:

> java Main -key 5 -alg shift -data "Mjqqt Btwqi, Ofaf!" -mode dec

Output #5:

Hello World, Java!