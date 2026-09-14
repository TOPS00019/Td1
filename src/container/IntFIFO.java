package container;

import java.util.Iterator;

public class IntFIFO implements Queue<Integer> {
    private int cap;
    private Integer[] L;
    private int begin;
    private int end;

    IntFIFO(int capacity){
        cap = capacity;
        L = new Integer[cap];
        begin = 0;
        end = 0;

    }

    static void main() {


    }


    @Override
    public boolean insertElement(Integer integer) {
        int old_cap = cap;
        if((end) == begin && this.L[begin] != null){

            cap = (cap+1) * 2;

            Integer[] M = new Integer[cap];
            for(int i=0; i<old_cap; i++){
                if((i < end)){
                    M[i] = L[i];
                }
                else if(begin <= i){
                    M[i+(cap-old_cap)] = L[i];
                }
            }
            L = M;

        }
        L[(end) % cap] = integer;
        end = (end+1)%(cap);
        begin +=  (cap-old_cap);
        return true;

    }

    @Override
    public Integer element() {
        return L[begin];
    }

    @Override
    public Integer popElement() {
        Integer a = L[begin];
        L[begin] = null;
        begin = (begin+1)%cap;
        return a;
    }

    @Override
    public boolean isEmpty() {
        if(end==begin){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if(end == begin){
            if(L[begin] == null){
                return 0;
            }
            return cap;
        }
        else if(end > begin){
            return Math.abs(end-begin);
        }
        else{
            return cap - (begin-end);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}