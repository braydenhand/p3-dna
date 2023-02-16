import java.net.IDN;

public class LinkStrand implements IDnaStrand {
    private class Node {
        String info;
        Node next;

        Node(String x) {
            info = x;
        }

        Node(String x, Node node) {
            info = x;
            next = node;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    public LinkStrand() {
        this("");
    }

    public LinkStrand(String string) {
        initialize(string);
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
        myAppends = 0;
        mySize = source.length();
        myLast = myFirst;
        return;

    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends += 1;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        Node myC = myFirst;
        LinkStrand revlist = new LinkStrand();
        while (myC != null) {
            StringBuilder t = new StringBuilder();
            t.append(myC.info);
            Node temp = new Node(t.reverse().toString());
            revlist.mySize += t.length();
            temp.next = revlist.myFirst;
            revlist.myFirst = temp;
            myC = myC.next;

        }
        return revlist;
    }

    @Override
    public int getAppendCount() {
        // TODO Auto-generated method stub
        return myAppends;
    }

    @Override
    public String toString() {
        StringBuilder myStr = new StringBuilder();
        Node myC = myFirst;
        while (myC != null) {
            myStr.append(myC.info);
            myC = myC.next;
        }
        return myStr.toString();
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException();
        }
        if (index <= myIndex) {
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while (index != myIndex) {
            myIndex += 1;
            myLocalIndex += 1;

            if (myCurrent.info.length() <= myLocalIndex && myCurrent.next != null) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);

    }
}
