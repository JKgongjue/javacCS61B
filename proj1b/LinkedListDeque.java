public class LinkedListDeque<T> implements Deque<T>{
    private IntNode sentinel;
    private int size = 0;
    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;
        public  IntNode() {
            next = this;
            prev = next;
        }
        public IntNode(IntNode p, T item, IntNode next) {
            prev = p;
            this.item = item;
            this.next = next;
        }
        public T getItem(IntNode p, int index) {
            if (index == 0) {
                return p.item;
            }
            index--;
            return getItem(p.next, index);
        }
    }
    public LinkedListDeque() {
        sentinel = new IntNode();
    }
//    public LinkedListDeque(LinkedListDeque other){
//        size = other.size();
//        sentinel = new IntNode();
//        IntNode p = sentinel;
//        IntNode head = other.sentinel;
//        while (head.next!=other.sentinel){
//            p.next = new IntNode(p,head.next.item,null);
//            p = p.next;
//            head = head.next;
//        }
//        p.next = sentinel;
//    }
    @Override
    public void addFirst(T item) {
        IntNode p = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }
    @Override
    public void addLast(T item) {
        IntNode p = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = p;
        p.prev.next = p;
        size++;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        IntNode head = sentinel;
        while (head.next != sentinel) {
            System.out.print(head.next.item + " ");
            head = head.next;
        }
        System.out.println(" ");
    }
    @Override
    public T removeFirst() {
        if (!this.isEmpty()) {
            T item = sentinel.next.item;
            sentinel.next.next.prev = this.sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return item;
        }
        return null;
    }
    @Override
    public T removeLast() {
        if (!this.isEmpty()) {
            T item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = this.sentinel;
            size--;
            return item;
        }
        return null;
    }
    @Override
    public T get(int index) {
        IntNode head = sentinel.next;
        int ind = 0;
        while (ind != index) {
            head = head.next;
            ind++;
        }
        return head.item;
    }
    public T getRecursive(int index) {
        IntNode p = sentinel.next;
        return p.getItem(p, index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> list = new LinkedListDeque<>();
//        list.addLast(0);
//        list.addLast(1);
//        list.addFirst(5);
//        System.out.println(list.getRecursive(2));
//        System.out.println(list.removeFirst());
//    }
}
