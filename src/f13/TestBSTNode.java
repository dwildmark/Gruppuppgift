package f13;

public class TestBSTNode {
    public static void main(String[] args) {
        BSTNode<Integer,String> n7 = new BSTNode<Integer,String>( new Integer(45), "fyrtiofem", null, null );
        BSTNode<Integer,String> n6 = new BSTNode<Integer,String>( new Integer(15), "femton", null, null );
        BSTNode<Integer,String> n5 = new BSTNode<Integer,String>( new Integer(31), "trettioett", null, null );
        BSTNode<Integer,String> n4 = new BSTNode<Integer,String>( new Integer(47), "fyrtiosju", n7, null );
        BSTNode<Integer,String> n3 = new BSTNode<Integer,String>( new Integer(19), "nitton", n6, null );
        BSTNode<Integer,String> n2 = new BSTNode<Integer,String>( new Integer(40), "fyrtio", n5, n4 );
        BSTNode<Integer,String> tree = new BSTNode<Integer,String>( new Integer(23), "tjugotre", n3, n2 );
        
        System.out.println( "Size = " + tree.size() + " Height = " + tree.height() );
        tree.showTree();
        
        System.out.println( "Size = " + n3.size() + " Height = " + n3.height() );
        n3.showTree();
        
        System.out.println( "Size = " + n2.size() + " Height = " + n2.height() );
        n2.showTree();

        tree.print();
    }
}
