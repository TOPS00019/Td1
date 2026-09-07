package container;

import java.util.Collection;
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
        IntFIFO q = new IntFIFO(3);

        q.insertElement(1);

        q.insertElement(2);

        q.insertElement(3);

        System.out.println(q.size());

        q.insertElement(4);
        System.out.println(q.size());

        //System.out.println(q.popElement());
        //System.out.println(q.popElement());

        //System.out.println(q.popElement());

    }


    @Override
    public boolean insertElement(Integer integer) {
        if(end+1 == begin){
            cap = (cap+1) * 2;
            Integer[] M = new Integer[cap];
            for(int i=0; i<cap-1; i++){
                if((i < end)){
                    M[i] = L[i];
                }
                else if(begin <= i){
                    M[i+1] = L[i];
                }
            }
            L = M;
            L[(end) % cap] = integer;
            end = (end+1)%(cap+1);
        }
        L[(end) % cap] = integer;
        end = (end+1)%(cap+1);
        return true;

    }

    @Override
    public Integer element() {
        return L[begin];
    }

    @Override
    public Integer popElement() {
        Integer a = new Integer(L[begin]);
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
        return Math.abs(end-begin);
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
