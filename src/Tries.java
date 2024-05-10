import java.util.HashMap;

public class Tries {
    public static class Trie {
        TrieNode root = new TrieNode();

        // inserting string in trie
        public void insert(String word) {
            TrieNode curr = root;

            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }

                curr = curr.children.get(c);
            }

            curr.isWord = true;
        }

        // searching for a string
        public boolean search(String word) {
            TrieNode curr = root;

            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    return false;
                } else {
                    curr = curr.children.get(c);
                }
            }

            return curr.isWord;
        }

        // searching for a prefix
        public boolean searchPrefix(String prefix) {
            TrieNode curr = root;

            for (char c : prefix.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    return false;
                } else {
                    curr = curr.children.get(c);
                }
            }

            return true;
        }
    }

    public static class TrieNode {
        boolean isWord;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<Character,
                    TrieNode>();
            this.isWord = false;
        }
    }
}
