package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrieSearch {

    static class Node {
        Node [] children = new Node[ALPHABET_SIZE];
        boolean isEndOfWord;

        public Node() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; ++i) {
                children[i] = null;
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "children:" + Arrays.toString(children) +
                    ", isEndOfWord:" + isEndOfWord +
                    '}';
        }
    }

    static final int ALPHABET_SIZE = 26;
    public static Node root;

    public static void insert(String key) {
        Node tmp = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i) - 'a';
            if (tmp.children[index] == null) {
                tmp.children[index] = new Node();
            }
            tmp = tmp.children[index];
        }
        tmp.isEndOfWord = true;
    }

    public static List<String> search(Node root, String key, String matched) {
        List<String> found = new ArrayList<>();
        StringBuilder matchedBuilder = new StringBuilder(matched);
        for (int i = 0; i < key.length(); ++i) {
            matched = matchedBuilder.toString();
            int index;
            if (key.charAt(i) == '.') {
                for(int j = 0; j < root.children.length; ++j) {
                    if (root.children[j] != null) {
                        found.addAll(search(root.children[j], key.substring(i + 1), matched + (char) ('a' + j)));
                    }
                }
                return found;
            }
            else {
                index = key.charAt(i) - 'a';
                if (root.children[index] == null)
                    return found;
                else {
                    matchedBuilder.append((char) ('a' + index));
                }
            }
            root = root.children[index];
        }
        if (root.isEndOfWord) {
            matched = matchedBuilder.toString();
            found.add(matched);
        }
        return found;
    }

    public static void main(String[] args) {
        root = new Node();
        String [] keys = {"bad", "mad", "dad", "dam", "ban", "bat", "add", "sum", "num"};
        for (String key : keys)
            insert(key);

        List<String> found = search(root, "da", "");
        System.out.println(found);
    }
}
