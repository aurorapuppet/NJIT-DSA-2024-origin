1.What is your hash function like for hash table solution (if you implemented hash table).
@Override
public int hashCode() {
int hash=0;
String fullName = getFullName();
for (int i = 0; i < fullName.length(); i++) {
hash=37*hash+fullName.charAt(i);
}
return Math.abs(hash);
}
2.For binary search trees (if you implemented it), how does your implementation get the top-100 list?
Sort the key-value pairs in the array alphabetically by key, and output the keys and corresponding values of the first 100 elements in a formatted manner.
Pair<String, Integer>[] sortedWords = words.toSortedArray();
for (int index = 0; index < Math.min(100, sortedWords.length); index++) {
String word = String.format("%-20s", sortedWords[index].getKey()).replace(' ', '.');
System.out.format("%4d. %s %6d%n", index + 1, word, sortedWords[index].getValue());
}
3.What can you say about the correctness of your implementation? Any issues, bugs or problems you couldn't solve? Any idea why the problem persists and what could perhaps be the solution?
In the process of completing the exercise, I met several problems. First, unlike Exercise 4, we need to compare value in this assignment, and we did not fully understand the meaning of doing so. Second, in the process of sorting words, the number of occurrences should be decreasing from the most to the least, but the running results seem to be inconsistent.
4.What can you say about the time complexity of your implementation? How efficient is the code in reading and managing the words and their counts? How efficient is your code in getting the top-100 list? Which sorting algorithm are you using? What is the time complexity of that algorithm?
Getting the top-100 list involves sorting the array of words and their counts, which is done using quickSort (fastSort method). Since quickSort has an average-case time complexity of O(n log n), it efficiently handles sorting large datasets.
5.What did you find the most difficult things to understand and implement in this programming task? Why?
This is the most fundamental requirement of the task, how to store words in the corresponding variables, such as the use of methods, how to split strings into words by letters, ignore punctuation and other non-alphabetic characters, and convert words to lowercase. These can only be found on the Internet.
private void processLine(String line){
for (String word : line.split("\\P{IsAlphabetic}+")) {
word = word.toLowerCase(Locale.ROOT);
if (word.length() > 1) {
addToWords(word);
}
}
}
6.What did you learn doing this?
Be good at using all resources to find information to solve problems。