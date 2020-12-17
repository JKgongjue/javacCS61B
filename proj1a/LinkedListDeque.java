public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size = 0;
    private class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;
        public  IntNode(){
            next = this;
            prev = next;
        }
        public IntNode(IntNode p,T item, IntNode next){
            prev = p;
            this.item = item;
            this.next = next;
        }
        public T getItem(IntNode p,int index){
            if (index==0){
                return p.item;
            }
            index--;
            return getItem(p.next,index);
        }
    }
    public LinkedListDeque(){
        sentinel = new IntNode();
    }
    public LinkedListDeque(LinkedListDeque other){
        size = other.size();
        sentinel = new IntNode();
        IntNode p = sentinel;
        IntNode head = other.sentinel;
        while (head.next!=other.sentinel){
            p.next = new IntNode(p,head.next.item,null);
            p = p.next;
            head = head.next;
        }
        p.next = sentinel;
    }
    public void addFirst(T item){
        IntNode p = new IntNode(sentinel,item,sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }
    public void addLast(T item){
        IntNode p = new IntNode(sentinel.prev,item,sentinel);
        sentinel.prev = p;
        p.prev.next = p;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        IntNode head = sentinel;
        while(head.next!=sentinel){
            System.out.print(head.next.item+" ");
            head = head.next;
        }
        System.out.println("");
    }
    public T removeFirst(){
        if (!this.isEmpty()){
            T item = sentinel.next.item;
            sentinel.next.next.prev = this.sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return item;
        }
        return null;
    }
    public T removeLast(){
        if (!this.isEmpty()){
            T item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = this.sentinel;
            size--;
            return item;
        }
        return null;
    }
    public T get(int index){
        IntNode head = sentinel.next;
        int ind =0;
        while (ind!=index){
            head = head.next;
            ind++;
        }
        return head.item;
    }
    public T getRecursive(int index){
        IntNode p = sentinel.next;
        return p.getItem(p,index);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(0);
        list.addLast(1);
        list.addFirst(5);
        System.out.println(list.getRecursive(2));
        System.out.println(list.removeFirst());
    }
}
