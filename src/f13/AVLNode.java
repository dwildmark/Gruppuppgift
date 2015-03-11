package f13;

class AVLNode<K,V> {
    K key;
    V value;
    AVLNode<K,V> left;
    AVLNode<K,V> right;
    int height=0;
    
    public AVLNode( K key, V value, AVLNode<K,V> left, AVLNode<K,V> right ) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    public int height() {
        int leftD = -1, rightD = -1;
        if( left != null )
            leftD = left.height();
        if( right != null )
            rightD = right.height();
        return 1 + Math.max( leftD, rightD );
    }
    
    public int size() {
        int leftS = 0, rightS = 0;
        if( left != null )
            leftS = left.size();
        if( right != null )
            rightS = right.size();
        return 1 + leftS + rightS;
    }
    
    public void print() {
        if( left != null)
            left.print();
        System.out.println(key + ": " + value);
        if( right != null )
            right.print();
    }
    
    public void showAVL() {
        javax.swing.JOptionPane.showMessageDialog( null, new ShowAVL( this, 800,600 ), "Show tree", javax.swing.JOptionPane.PLAIN_MESSAGE );
    }
}
