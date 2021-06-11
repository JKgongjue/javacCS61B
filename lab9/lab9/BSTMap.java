package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {

        if(key == null) throw new UnsupportedOperationException();
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) return getHelper(key,p.left);
        else if (cmp > 0) return getHelper(key,p.right);
        else return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) throw new UnsupportedOperationException();
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
//        throw new UnsupportedOperationException();
        if (root == null) {
            return root = new Node(key,value);
        }
        if (p == null) {
            return new Node(key,value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0)  p.left = putHelper(key,value,p.left);
        else if (cmp > 0)  p.right = putHelper(key,value,p.right);
        else  p.value = value;
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
//        throw new UnsupportedOperationException();
        if (key == null) throw new UnsupportedOperationException();
        putHelper(key,value,root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.remove("hello");
    }



    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
//    删除最小值
    private Node removeMin(Node p) {
        if (p.left == null) return p.right;
        p.left = removeMin(p.left);
        size--;
        return p;
    }
    private Node getMin(Node p) {
        if (p.left == null) return p;
        return getMin(p.left);
    }

//    删除最大值
    private Node removeMax(Node p) {
        if (p.right == null) return p.left;
        p.right = removeMax(p.right);
        size--;
        return p;
    }
    private K getMax(Node p) {
        if (p.right == null) return p.key;
        return getMax(p.right);
    }
    private Node getMaxNode(Node p) {
        if (p.right == null) return p;
        return getMin(p.right);
    }

    private Node removehelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) p.left = removehelper(key,p.left);
        else if (cmp > 0) p.right = removehelper(key,p.right);
        else {
//            分3种情况删除
//            1.叶子结点
            if (p.left == null && p.right == null) {
                return null;
            }
//            2. 单结点
            if (p.left == null || p.right == null) {
                if (p.right != null) {
                    return p.left;
                }
                return p.right;
            }
//            3 两个子孩子结点
            Node maxNode = getMaxNode(p.left);
            p.key = maxNode.key;
            p.value = maxNode.value;
            removeMax(p.left);
            return p;
        }
        return p;
    }



    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */

    @Override
    public V remove(K key) {
        if (get(key) == null) return null;
        removehelper(key, root);
        return get(key);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) != value) return null;
        if (get(key) == null) return null;
        remove(key);
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
