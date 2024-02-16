package basic.tree.trie;

public class TrieNode {
    public int pass;
    public int end;
    //如果字符种类特别多的情况下可以使用HashMap<Char,Node>/TreeMap<Char,Node> 来表达。
    public TrieNode[] nexts;

    public TrieNode(){
        pass = 0;
        end = 0;
        /**
         * nexts[0] == null 没有走向'a'的路
         * nexts[0] != null 有走向'a'的路
         * ......
         * next[25] != null 有走向'z'的路
         */
        nexts = new TrieNode[26];
    }
}
