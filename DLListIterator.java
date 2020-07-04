package hw4;

public class DLListIterator {
    private DLList.Node current;
    private DLList.Node previous;
    private DLList list;
    private Cat cat;

    public DLListIterator(DLList list){
        this.list = list;
        this.reset();
    }

    public void reset(){
        current = list.head;
        previous = null;
    }

    public Cat next(){
        Cat temp = current.c;
        previous = current;
        current = current.next;
        return temp;
    }

    public void toPrevious(){
        current = previous;
        previous = current.previous;
    }

    public void toNext(){
        previous = current;
        current = current.next;
    }

    public Cat getCurrent(){
        return current.c;
    }

    public boolean hasNext(){
        return current.next != null;
    }

    public boolean atEnd(){
        return current == list.tail;
    }

    public void insertBefore(Cat c){
        DLList.Node n = list.NodeNew(c);

        if (list.isEmpty()){
            list.push(c);
            current = n;
        } else {
            n.previous = current.previous;
            current.previous = n;
            if (previous != null) {
                n.next = previous.next;
                previous.next = n;
                previous = n;
            } else {
                n.next = list.head;
                list.head = n;
                previous = list.head;
            }
        }
        list.size++;
    }

    public void insertAfter(Cat c){
        DLList.Node n = list.NodeNew(c);

        if (list.isEmpty()){
            list.push(c);
            current = n;
        } else {
            if (hasNext()) {
                toNext();
                insertBefore(c);
                toPrevious();
                toPrevious();
            } else {
                n.previous = current;
                current.next = n;
            }
        }
        list.size++;
    }

    public Cat deleteCurrent(){
        Cat temp = current.c;
        if (hasNext()) {
            previous.next = current.next;
            current = current.next;
            current.previous = previous;
            list.size--;
            return temp;
        } else {
            current.previous = previous.previous;
            current = previous;
            current.next = null;
            previous = current.previous;
            list.size--;
            return temp;
        }
    }
}
