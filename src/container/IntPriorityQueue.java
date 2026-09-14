package container;

import java.util.Iterator;

public class IntPriorityQueue implements Queue<Integer> {
    private int cap;
    private Integer[] L;
    private int end = -1;


    public IntPriorityQueue (int capacity){
        this.cap = capacity;
        this.L = new Integer[cap];
        this.end = -1;

    }

    static void main() {
        IntPriorityQueue P = new IntPriorityQueue(3);
        System.out.println(P.element());
        P.insertElement(3);
        P.insertElement(2);
        P.insertElement(1);
        P.insertElement(0);
        P.popElement();

    }


    @Override
    public boolean insertElement(Integer integer) {
        int old_cap = cap;
        int li = 0;
        if(end == cap-1){
            cap = (cap+1) * 2;
            Integer[] M = new Integer[cap];
            for(int i=0; i<old_cap; i++){
                 M[i] = L[i];
            }
            L = M;
        }
        L[end+1] = integer;
        int k = end + 1;
        int parent = 0;
        while(k>0){
            parent = (int) Math.floor((k-1)/2);
            if(L[parent] < L[k]){
                int a  = L[parent];
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
    public Integer element() {
        return L[0];
    }

    @Override
    public Integer popElement() {
        int a = L[0];
        L[0] = L[end];
        L[end] = null;
        end--;
        int k = 0;
        while(k<end){
            int fils1 = 2*k+1;
            int fils2 = 2*k+2;
            if(fils2<=end){
                if(L[k] < L[fils1] || L[k] > L[fils1]){
                    if(L[fils1]>L[fils2]){
                        int b  = L[fils1];
                        L[fils1] = L[k];
                        L[k] = b;
                        k = fils1;
                    }
                    else{
                        int b  = L[fils2];
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
                if(L[k] < L[fils1]){
                    int b  = L[fils1];
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
        return end == -1;
    }

    @Override
    public int size() {
        return end+1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
