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
        IntFIFO f = new IntFIFO(3);
        f.insertElement(42);
        System.out.println(f.isEmpty());
        System.out.println(f.element());

    }


    @Override
    public boolean insertElement(Integer integer) {
        int old_cap = cap;
        boolean P = false;
        if((end) == begin){
            if(cap != 0){
                if(L[begin] != null){
                    P = true;
                }
            }
            else{
                P = true;
            }
            if(P){
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


        }
        L[(end) % cap] = integer;

        end = (end+1)%(cap);
        begin +=  (cap-old_cap) % cap;
        return true;

    }

    @Override
    public Integer element() {
        if(cap == 0){
            return null;
        }
        return L[begin];
    }

    @Override
    public Integer popElement() {
        Integer a = L[begin];
        if(L[begin] != null){
            L[begin] = null;
            begin = (begin+1)%cap;
        }

        return a;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public int size() {
        if(cap==0){
            return 0;
        }
        if(end == begin){
            if(L[begin] == null){
                return 0;
            }
            return cap;
        }
        else if(end > begin){
            return Math.abs(end - begin);
        }
        else{
            return cap - (begin-end);
        }
    }

    @Override
    public Iterator<Integer> iterator() {

        return new IntFIFOIterator();
    }

    class IntFIFOIterator implements Iterator<Integer>{
        private int i;
        private int b;
        private int e;
        private int cap1;
        private int s;
        private boolean t = false;

        IntFIFOIterator(){
            b = IntFIFO.this.begin;
            e = IntFIFO.this.end;
            cap1 = IntFIFO.this.cap;
            s = IntFIFO.this.size();
            i = b;
        }
        @Override
        public boolean hasNext() {
            if((i == e && t == true) || (i>e && i<b) || s == 0){
                return false;
            }
            return true;
        }

        @Override
        public Integer next() {
            Integer C = IntFIFO.this.L[i];
            if(i==cap1-1){
                t = true;
            }
            i = (i + 1)% cap1;

            return C;
        }
    }
}