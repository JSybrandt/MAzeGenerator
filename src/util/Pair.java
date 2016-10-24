/*
* Justin Sybrandt
*
* Description:
* The pair class stores at most two of the same type. Either left or right can be undefined and
* the pair structure will work fine. This was motivated by the symmetrical relationship between adjacent rooms.
*
* Important Values:
* equals - equality is defines if either a.left=b.left and a.right=b.right OR a.left=b.right and a.right=b.left
*           this means that if a is adjacent to b, then b is adjacent to a implicity.
* */

package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pair<T> {
    private Optional<T> left, right;
    public Pair(){
        this(null,null);
    }
    public Pair(T left){
        this(left,null);
    }
    public Pair(T left, T right){
        if(left != null)
            this.left = Optional.of(left);
        else
            this.left = Optional.empty();
        if(right != null)
            this.right = Optional.of(right);
        else
            this.right = Optional.empty();
    }
    public List<T> toList(){
        List<T> ret = new ArrayList<T>();
        if(left.isPresent())ret.add(left.get());
        if(right.isPresent())ret.add(right.get());
        return ret;
    }
    public Optional<T> getOther(T test) {
        if(left.isPresent() && test ==  left.get())
            return right;
        if(right.isPresent() && test == right.get())
            return left;
        return Optional.empty();
    }
    public Optional<T> getLeft(){return left;}
    public Optional<T> getRight(){return right;}

    @Override
    public boolean equals(Object other){
        if(other instanceof Pair<?>) {
            Pair o = (Pair) other;
            return (left.equals(o.left) && right.equals(o.right)) ||
                    (left.equals(o.right) && right.equals(o.left));
        }
        else
            return false;
    }
    public boolean bothPresent(){
        return left.isPresent() && right.isPresent();
    }

}
