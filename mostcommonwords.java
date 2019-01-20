class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = paragraph.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String w : words) if (!ban.contains(w)) count.put(w, count.getOrDefault(w, 0) + 1);
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}

Python Code
class Solution:
    def mostCommonWord(self, paragraph, banned):
        ban = set(banned)
        words = re.findall(r'\w+', paragraph.lower())
        return collections.Counter(w for w in words if w not in ban).most_common(1)[0][0]

P.S.

Python is faster. 

Fin.

            
            You can go the other way round. Replace everything that is not word characters, using negated character class:

message = message.replaceAll("[^\\w]", "");
or

message = message.replaceAll("\\W", "");
Both of them will replace the characters apart from [a-zA-Z0-9_]. If you want to replace the underscore too, then use:

[\\W_]
