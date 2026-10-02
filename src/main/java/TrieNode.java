import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private final Map<Character, TrieNode> children;
    private boolean endOfWord;

    public static TrieNode create() {
        return new TrieNode(new HashMap<>(), false);
    }

    private TrieNode(Map<Character, TrieNode> children, boolean endOfWord) {
        this.children = children;
        this.endOfWord = endOfWord;
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public TrieNode addChild(char c) {
        return children.computeIfAbsent(c, _c -> TrieNode.create());
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}
