/**
 * 检查一下代码格式，再修改一下能拿2.5分，那就有39.265+2.5 = 41.765分
 *超过80%，其他的
 *我第一次的插入思路是插入时判断是否扩容，即没插之前，再判断，这会导致head和tail共用一格的问题，需要设置很多条件去处理
 *现在我直接在插入后判断时候扩容，即head==tail我就扩容，这样虽然会空一格出来，但不用处理没插满的问题。
 *显然后者思路更好
 * */



public class ArrayDeque<T> {
    private int size;
    private float usageFactor=0;
    private T[] items;
    private int head; //记录头部节点位置
    private int tail; //记录尾部节点位置

    private int getHead() {
        return head;
    }
    private int getTail() {
        return tail;
    }
    private T[] getItems() {
        return items;
    }
    private void doubleCapacity() {
        int n = items.length - head;
        int r = tail;
        T[] a = (T[]) new Object[items.length * 2];
        System.arraycopy(items, head, a, 0, n);
        System.arraycopy(items,0, a, n, r);
        tail = size + 1;
        items = a;
        head = 0;
    }
    private void divideCapacity() {
        T[] a = (T[]) new Object[items.length / 2];

        if (head <= tail) {
            System.arraycopy(items, head, a,0,tail - head);
            tail = tail - head;
            head = 0;
            items = a;
        }
        else {
            int r = items.length - head;
            System.arraycopy(items, head, a,0, r);
            System.arraycopy(items,0, a, r, tail);
            items = a;
        }
    }
    private float getUsageFactor() {
        return usageFactor;
    }
    private boolean isResize() {
        usageFactor = (float) size / items.length;
        if (items.length >= 16) {
            return usageFactor < 0.25;
        }
        if (items.length == 8) {
            return false;
        }
        return usageFactor < 0.2;
    }

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        head = 4;
        tail = 5;
    }
    public void addFirst(T item) {
        items[head] = item;
        head--;
        size++;
        if (head < 0) {
            head = items.length-1;
        }
        if (head == tail) {
            doubleCapacity();
        }
    }
    public void addLast(T item) {
        items[tail] = item;
        tail++;
        size++;
        if (tail >= items.length) {
            tail = 0;
        }
        if (head == tail) {
            doubleCapacity();
        }

    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        if (tail <= head){
            for (int i = head; i < items.length; i++) {
                System.out.print(items[i].toString() + " ");
            }
            for (int j = 0; j < tail; j++){
                System.out.print(items[j].toString() + " ");
            }
            System.out.println(" ");
        }
        else {
            for (int i = head; i < tail; i++){
                System.out.print(items[i].toString() + " ");
            }
            System.out.println(" ");

        }
    }
    public T removeFirst() {
        T item;
        if (size == 0) {
            return null;
        }
        int i = head + 1;
        if (i >= items.length) {
            i = 0;
        }
        item = items[i];
        items[i] = null;
        head = i;
        return item;
    }
    /**
     * 由于设置的时候tail为尾点，且数组未满时tail这个点指向的addlast将要加入的点。
     *所有的逻辑都基于tail指向的点是空。
     * 但是特殊情况下tail指向的点不一定为空
     * 所以这里要判断tail指向的点是否为空。
     * */
    public T removeLast() {
        T item;
        if (size == 0) {
            return null;
        }
        int i = tail - 1;
        if (i < 0) {
            i = items.length -1 ;
        }
        item = items[i];
        items[i] = null;
        tail = i;
        return item;
    }
    public T get(int index) {
        int r = head + 1 + index;
        if (r > items.length - 1) {
            r = r - items.length;
        }
        return items[r];
    }
}
