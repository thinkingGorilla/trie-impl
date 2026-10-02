import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Trie {

    private final TrieNode root = TrieNode.create();

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.addChild(c);
        }
        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord();
    }

    public boolean startsWith(String word) {
        TrieNode node = findNode(word);
        return node != null;
    }

    private TrieNode findNode(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.hasChild(c)) return null;
            current = current.getChild(c);
        }
        return current;
    }

    public List<String> autocomplete(String word) {
        TrieNode node = findNode(word);
        if (node == null) return Collections.emptyList();

        List<String> words = new ArrayList<>();
        collectAllWords(node, new StringBuilder(word), words);

        return words;
    }

    private void collectAllWords(TrieNode node, StringBuilder word, List<String> words) {
        if (node.isEndOfWord()) {
            words.add(word.toString());
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            char childChar = entry.getKey();
            TrieNode child = entry.getValue();

            word.append(childChar);
            collectAllWords(child, word, words);
            word.deleteCharAt(word.length() - 1);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("banana");
        trie.insert("bananamall");

        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("appl"));

        System.out.println(trie.autocomplete("app"));
        System.out.println(trie.autocomplete("banana"));
    }
}
