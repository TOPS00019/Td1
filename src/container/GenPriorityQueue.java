package container;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;





public class GenPriorityQueue<E extends Comparable<? super E>> implements Queue<E>,  Iterable<E> {
    private int cap;
    private E L[];
    private int end;
    private Comparator<? super E> compar;


    public GenPriorityQueue (int capacity, Comparator<? super E> comparator){
        if(capacity<0){
            throw new NegativeArraySizeException();
        }
        this.compar = comparator;
        this.cap = capacity;
        this.L = (E[]) new Comparable[cap];
        this.end = -1;

    }

    static void main() {

    }
    private void resize(){
        int old_cap = cap;
        if(end == cap-1){
            cap = (cap+1) * 2;
            E[] M = (E[]) new Comparable[cap];
            for(int i=0; i<old_cap; i++){
                M[i] = L[i];
            }
            L = M;
        }
    }

    @Override
    public boolean insertElement(E e) {
        this.resize();
        L[end+1] = e;
        int k = end + 1;
        int parent = 0;
        while(k>0){
            parent = (int) Math.floor((k-1)/2);
            if(this.compar.compare(L[parent], L[k]) < 0){
                E a  = L[parent];
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
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return (E) L[0];
    }

    @Override
    public E popElement() {
        if(isEmpty()){
            throw new NoSuchElementException();
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
                if(this.compar.compare(L[k], L[fils1]) < 0 || this.compar.compare(L[k], L[fils2]) < 0){
                    if(this.compar.compare(L[fils2], L[fils1]) < 0){
                        E b  =  L[fils1];
                        L[fils1] = L[k];
                        L[k] = b;
                        k = fils1;
                    }
                    else{
                        E b  = L[fils2];
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
                if(this.compar.compare(L[k], L[fils1]) < 0){
                    E b  = L[fils1];
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
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            E C =  (E) GenPriorityQueue.this.L[i];
            i++;
            return C;
        }
    }

}
