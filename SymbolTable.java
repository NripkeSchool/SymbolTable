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
