public class SymbolTable<Key extends Comparable<Key>, Value>
{
    Node root;
    public Value get(Key k)
    {
        Node n = get(root, k);
        if (n == null) {return null;}
        return n.v;
    }
    
    private Node get(Node parent, Key k)
    {
        if (parent == null) {return null;}
        if (parent.k == k) {return parent;}
        if (k.compareTo(parent.k) > 0) {return get(parent.right, k);}
        
        return get(parent.left, k);
    }
    
    public void put(Key k, Value v)
    {
        root = put(root, k, v);
    }
    
    private Node put(Node parent, Key k, Value v)
    {
        if (parent == null) {return new Node(k, v);}
        int c = k.compareTo(parent.k);
        
        if (c < 0) {parent.size++; parent.left = put(parent.left, k, v);}
        if (c > 0) {parent.size++; parent.right = put(parent.right, k, v);}
        
        return parent;
    }
    
    public void remove(Key k)
    {
        
    }
    
    public boolean contains(Key k)
    {
        return get(k) != null;
    }
    
    public Key floor(Key k)
    {
        return floor(root, k);
    }
    
    private Key floor(Node parent, Key k)
    {
        if (parent == null) {return null;}
        
        int c = parent.k.compareTo(k);
        if (c > 0) {return floor(parent.left, k);}
        if (c < 0) 
        {
            Key potentialK = floor(parent.right, k);
            if (potentialK == null) {return parent.k;} //If not null, then this is best
            return potentialK;
        }
        
        return parent.k;
    }
    
    public Key ceiling(Key k)
    {
        return ceiling(root, k);
    }
    
    private Key ceiling(Node parent, Key k)
    {
        if (parent == null) {return null;}
        
        int c = parent.k.compareTo(k);
        if (c < 0) {return floor(parent.right, k);}
        if (c > 0) 
        {
            Key potentialK = floor(parent.left, k);
            if (potentialK == null) {return parent.k;} //If not null, then this is best
            return potentialK;
        }
        
        return parent.k;
    }
    
    public int size()
    {
        return size(root);
    }
    
    private int size(Node n)
    {
        if (n == null) {return 0;}
        return n.size;
    }
    
    public Key select(int i)
    {
        return select(root, i);
    }
    
    private Key select(Node parent, int i)
    {
        if (parent == null) {return null;}
        int s = size(parent.left);
        
        if (s < i) {return select(parent.right, i-s-1);}
        if (s > i) {return select(parent.left, i);}
        return parent.k;
    }
    
    public int rank(Key k)
    {
        Node n = get(root, k);
        return n == null ? -1 : size(n.left);
    }
    
    private class Node 
    {
        Key k;
        Value v;
        int size;
        
        //Left/Right nodes
        Node left;
        Node right;
        public Node(Key k, Value v)
        {
            this.k = k;
            this.v = v;
        }
    }
}
