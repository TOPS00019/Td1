package container;

import java.util.Comparator;
import java.util.Iterator;

public class GenPriorityQueue<E extends Comparable<E>> implements Queue<E>,  Iterable<E> {
    private int cap;
    private Object[] L;
    private int end;


    public GenPriorityQueue (int capacity){
        this.cap = capacity;
        this.L = new Object[cap];
        this.end = -1;

    }

    static void main() {

    }

    @Override
    public boolean insertElement(E e) {
        int old_cap = cap;
        if(end == cap-1){
            cap = (cap+1) * 2;
            Object[] M = new Object[cap];
            for(int i=0; i<old_cap; i++){
                M[i] = L[i];
            }
            L = M;
        }
        L[end+1] = e;
        int k = end + 1;
        int parent = 0;
        while(k>0){
            parent = (int) Math.floor((k-1)/2);
            if(((E) L[parent]).compareTo((E) L[k]) < 0){
                Object a  = L[parent];
                L[parent] = L[k];
                L[k] = a;
                k = parent;
            }
            else{
                break;
            }

        }
        end++;
        return true;
    }

    @Override
    public E element() {
        if(cap == 0){
            return null;
        }
        return (E) L[0];
    }

    @Override
    public E popElement() {
        if(cap == 0){
            return null;
        }
        E a = (E) L[0];
        L[0] = L[end];
        L[end] = null;
        end--;
        int k = 0;
        while(k<end){
            int fils1 = 2*k+1;
            int fils2 = 2*k+2;
            if(fils2<=end){
                if(((E) L[k]).compareTo((E) L[fils1]) < 0 || ((E) L[k]).compareTo((E) L[fils2]) < 0){
                    if(((E) L[fils2]).compareTo((E) L[fils1]) < 0){
                        Object b  =  L[fils1];
                        L[fils1] = L[k];
                        L[k] = b;
                        k = fils1;
                    }
                    else{
                        Object b  = L[fils2];
                        L[fils2] = L[k];
                        L[k] = b;
                        k = fils2;
                    }
                }
                else{
                    break;
                }

            }
            else if(fils1<=end){
                if(((E) L[k]).compareTo((E) L[fils1]) < 0){
                    Object b  = L[fils1];
                    L[fils1] = L[k];
                    L[k] = b;
                    k = fils1;
                }
                else{
                    break;
                }

            }
            else{
                break;
            }
        }


        return a;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public int size() {
        return end+1;
    }

    @Override
    public Iterator<E> iterator() {

        return new GenPriorityQueueIterator();
    }


    class GenPriorityQueueIterator implements Iterator<E>{
        private int i;
        private int sizeP;
        GenPriorityQueueIterator(){
            i = 0;
            sizeP = GenPriorityQueue.this.size();
        }
        @Override
        public boolean hasNext() {
            return i<sizeP;
        }

        @Override
        public E next() {
            E C =  (E) GenPriorityQueue.this.L[i];
            i++;
            return C;
        }
    }

}
