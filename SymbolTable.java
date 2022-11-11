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
        else if (c > 0) {parent.size++; parent.right = put(parent.right, k, v);}
        else {parent.v = v;}
        
        return parent;
    }
    
    public void delete(Key k)
    {
        root = delete(root, k);
    }
    
    private Node delete(Node parent, Key k)
    {
        if (parent == null) {return null;}
        int c = parent.k.compareTo(k);
        
        if (c < 0) {parent.left = delete(parent.left, k);}
        else if (c > 0) {parent.right = delete(parent.right, k);}
        else {
            if (parent.right == null) {return parent.left;}
            if (parent.left == null) {return parent.right;}
            Node del = parent;
            parent = min(del.right);
            //x.right = delMin(del.right);
        }
        return null;
    }
    
    public boolean contains(Key k)
    {
        return get(k) != null;
    }
    
    public Key floor(Key k)
    {
        return floor(root, k).k;
    }
    
    private Node floor(Node parent, Key k)
    {
        if (parent == null) {return null;}
        
        int c = parent.k.compareTo(k);
        if (c > 0) {return floor(parent.left, k);}
        if (c < 0) 
        {
            Node potentialK = floor(parent.right, k);
            if (potentialK == null) {return parent;} //If not null, then this is best
            return potentialK;
        }
        
        return parent;
    }
    
    public Key ceiling(Key k)
    {
        return ceiling(root, k).k;
    }
    
    private Node ceiling(Node parent, Key k)
    {
        if (parent == null) {return null;}
        
        int c = parent.k.compareTo(k);
        if (c < 0) {return ceiling(parent.right, k);}
        if (c > 0) 
        {
            Node potentialK = ceiling(parent.left, k);
            if (potentialK == null) {return parent;} //If not null, then this is best
            return potentialK;
        }
        
        return parent;
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
        return select(root, i).k;
    }
    
    private Node select(Node parent, int i)
    {
        if (parent == null) {return null;}
        int s = size(parent.left);
        
        if (s < i) {return select(parent.right, i-s-1);}
        if (s > i) {return select(parent.left, i);}
        return parent;
    }
    
    public int rank(Key k)
    {
        return rank(root, k);
    }
    
    private int rank(Node parent, Key k)
    {
        if (parent == null) {return 0;}
        int c = parent.k.compareTo(k);
        
        if (c > 0) {return rank(parent.left, k);}
        if (c < 0) {return 1+size(parent.left)+rank(parent.right, k);}
        return size(parent.left);
    }
    
    public Key min()
    {
        Node n = min(root);
        return n == null ? null : n.k;
    }
    
    private Node min(Node parent)
    {
        if (parent == null) {return null;}
        if (parent.left == null) {return parent;}
        return min(parent.left);
    }
    
    public Key max()
    {
        Node n = max(root);
        return n == null ? null : n.k;
    }
    
    private Node max(Node parent)
    {
        if (parent == null) {return null;}
        if (parent.right == null) {return parent;}
        return max(parent.right);
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
