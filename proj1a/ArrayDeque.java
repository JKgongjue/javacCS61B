public class ArrayDeque<T> {
    private int size;
    private float UsageFactor=0;
    private T[] items;
    private int head ; //记录头部节点位置
    private int tail ; //记录尾部节点位置

    private int getHead() {
        return head;
    }
    private int getTail() {
        return tail;
    }

    private T[] getItems() {
        return items;
    }

    private float getUsageFactor() {
        return UsageFactor;
    }

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        head = 4;
        tail = 5;
    }
    private void doubleCapacity(){
        int n = items.length-head;
        int r = tail;
        T[] a = (T[]) new Object[items.length*2];
        System.arraycopy(items,head,a,0,n);
        System.arraycopy(items,0,a,n,r);
        head = 0;
        tail = items.length;
        items = a;

    }
    private void divideCapacity(){
        T[] a = (T[]) new Object[items.length/2];

        if (head<=tail){
            System.arraycopy(items,head,a,0,tail-head);
            tail = tail-head;
            head = 0;
            items = a;
        }
        else {
            int r = items.length - head;
            System.arraycopy(items,head,a,0,r);
            System.arraycopy(items,0,a,r,tail);
            items = a;
        }
    }
    private boolean isResize(){
        UsageFactor = (float) size/items.length;
        if (items.length>=16){
            return UsageFactor < 0.25;
        }
        if (items.length==8) return false;
        return UsageFactor <0.2;
    }
    public void addFirst(T item){
        if (size==0){
            items[head] = item;
            size++;
        }
        else  {
            if (head!=tail){
                if (head!=0){
                    items[--head] = item;
                }
                else {
                    head = items.length-1;
                    items[head] = item;
                }
            }
            else {
                doubleCapacity();
                head = items.length-1;
                items[head] = item;
            }
            size++;
        }
    }
    public void addLast(T item){
        if (head!=tail){
            if (tail!=items.length-1){
                items[tail] = item;
                tail++;
            }
            else {

                items[items.length-1] = item;
                tail = 0;
            }
        }
        else {
            if (items[head]==null){
                items[tail] = item;
            }
            else {
                doubleCapacity();
                items[tail] = item;
                tail++;
            }
        }
        size++;

    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if (tail<=head){
            for (int i = head; i < items.length; i++) {
                System.out.print(items[i]+" ");
            }
            for (int j = 0; j < tail; j++){
                System.out.print(items[j]+" ");
            }
            System.out.println(" ");
        }
        else {
            for (int i = head; i < tail; i++){
                System.out.print(items[i]+" ");
            }
            System.out.println(" ");

        }
    }
    public T removeFirst(){
        if (size==0) return null;
        T item = items[head];
        items[head] = null;
        head++;
        if (head>=items.length){
            head = head-items.length;
       }
        size--;
        if (isResize()){
            divideCapacity();
        }
        return item;
    }
    /**
     * 由于设置的时候tail为尾点，且数组未满时tail这个点指向的addlast将要加入的点。
     *所有的逻辑都基于tail指向的点是空。
     * 但是特殊情况下tail指向的点不一定为空
     * 所以这里要判断tail指向的点是否为空。
     * */
    public T removeLast(){
        if (size==0) return null;
        T item;
        if (tail==0){
            tail = items.length -1;
            item = items[tail];
            items[tail] = null;
        }
        else {
            if (items[tail]==null){
                tail--;
                item = items[tail];
            }
            else {
                item = items[tail];
                items[tail] = null;
            }

        }
        size--;
        if (isResize()){
            divideCapacity();
        }
        return item;
    }
    public T get(int index){
        int i = index+head;
        if (i>items.length){
            i = i - items.length-1;
        }
        return items[i];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
//        arrayDeque.addLast(1);
//        arrayDeque.addLast(2);
//        arrayDeque.addLast(3);
//        arrayDeque.addLast(4);
//        arrayDeque.addLast(5);
//        arrayDeque.addLast(6);
//        arrayDeque.addLast(7);
//        arrayDeque.addLast(0);
//        int t = arrayDeque.get(0);
//        System.out.println(t);
//
//    }
}
