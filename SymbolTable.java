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
        
        if (k.compareTo(parent.k) > 0)
        {
            return get(parent.right, k);
        }
        return get(parent.left, k);
    }
    
    public void put(Key k, Value v)
    {
        Node n = new Node(k, v);
        put(n, root);
    }
    
    private void put(Node newNode, Node parent)
    {
        
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
        if (c >= 0) {return floor(parent.left, k);}
        if (c < 0) 
        {
            Key potentialK = floor(parent.right, k);
            if (potentialK == null) {return parent.k;} //If not null, then this is best
            return potentialK;
        }
        
        return null;
    }
    
    public Key ceiling(Key k)
    {
        return ceiling(root, k);
    }
    
    private Key ceiling(Node parent, Key k)
    {
        if (parent == null) {return null;}
        
        int c = parent.k.compareTo(k);
        if (c <= 0) {return floor(parent.right, k);}
        if (c > 0) 
        {
            Key potentialK = floor(parent.left, k);
            if (potentialK == null) {return parent.k;} //If not null, then this is best
            return potentialK;
        }
        
        return null;
    }
    
    
    class Node 
    {
        Key k;
        Value v;
        
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
