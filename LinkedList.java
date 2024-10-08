package LinkedList;

public class LinkedList {
    public static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){//O(1)
        //step1 = create new node
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        
        //step2 - newNode next = head
        newNode.next = head; //link
        //step3 - Head = new node
        head = newNode;
    }
    public void addLast(int data){//O(1)
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }
    public void print(){//O(n)
        if(head == null){
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+"-->");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void add(int idx,int data){
        if(idx == 0){
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i=0;

        while(i< idx-1){
            temp = temp.next;
            i++;
        }

        //i = idx-1; temp -> prev
        newNode.next = temp.next;
        temp.next = newNode;

    }
    public int removeFirst(){
        if(size == 0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        } else if(size==1){
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }
    public int removeLast(){
        if(size == 0){
            System.out.println("LL is empty");
            return Integer.MAX_VALUE;
        } else if(size == 1){
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        //prev : i = size - 2
        Node prev = head;
        for(int i=0; i<size-2; i++){
            prev = prev.next;
        }
        int val = prev.next.data; //tail data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }
    public int itrSearch(int key){//O(n)
        Node temp = head;
        int i=0;

        while(temp != null){
            if(temp.data == key){//key found
                return i;
            }
            temp = temp.next;
            i++;
        }

        //key not found
        return -1;
    }

    public int helper(Node head, int key){
        if(head == null){
            return -1;
        }

        if(head.data == key){
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx == -1){
            return -1;
        }

        return idx+1;
    }
    public int recSearch(int key){
        return helper(head,key);
    }



    public void reverse() { //O(n)
        // 3 variables prev,curr,next
        //while(curr!=null)..
        // 1.next = curr.next
        // 2.curr.next = prev
        // 3.prev = curr
        // 4.curr = next

        Node prev = null;
        Node curr = tail = head;
        Node next;

        while(curr != null){//ttr --> 3 var & 4 steps
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head=prev;
    }
    
    
    //remove Nth node from the End ie size-n+1 th node from the Start
    public void deleteNthfromEnd(int n){
        //Calculate size 
        int sz=0;
        Node temp = head;
        while(temp.next != null){
            sz++;
            temp = temp.next;
        }
        if(n==sz){
            head = head.next; //remove first
            return;
        }
        //sz-n
        int i=1;
        int itoFind = sz-n;
        Node prev = head;
        while(i < itoFind){
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
        
    }
    //Check if LL is a Palindrome
    //Step 1 --> Find MidNode
    //Step 2 --> Reverse 2nd Half
    //Step 3 --> Check if 1st Half = 2nd Half
    //Slow-Fast Approach

    //Step 1 --> Find MidNode
    public Node findMid(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;//+1
            fast = fast.next.next;//+2
        }
    return slow; //slow is my midNode
    }

    public boolean checkpalindrome(){
        if(head == null|| head.next == null){
            return true;
        }
        //Step 1 --> Find MidNode
        Node midNode = findMid(head);
        //Step 2 --> Reverse 2nd Half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
       

        Node right = prev; //right half head
        Node left = head;
        //Step 3 --> Check if 1st Half = 2nd Half
        while(right != null){
            if(left.data != right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static boolean isCycle(){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;//+1
            fast = fast.next.next;//+2
            if(slow == fast) {
                return true; //cycle exists
            }
        }
        return false; //cycle doesn't exist
    }

    public static void removeCycle(){
        //detect cycle
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while(fast != null && fast.next != null){
            slow = slow.next;//+1
            fast = fast.next.next;//+2
            if(slow == fast){
                cycle = true;
                break;
            }
        }
        if(cycle == false){
            return;
        }
        //find meeting point
        slow = head;
        Node prev = null;
        while(slow != fast){
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        //remove cycle -> last.next = null
        prev.next = null;
    }

    private Node getMid(Node head){
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;//mid node
    }
    public Node merge(Node head1, Node head2){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;

        while(head1 != null && head2 != null){
            if(head1.data <= head2.data){
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            }
            else{
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next; 
            }
        }
        while (head1 != null){
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null){
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergedLL.next;

    }
    public Node mergeSort(Node head){
        if(head == null || head.next == null){
            return head;
        }
        //find mid
        Node mid = getMid(head);
        //left & right MergeSort
        Node rigtHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rigtHead);
        
        //merge
        return merge(newLeft, newRight);
    }
    public static void delNNodesAfterMNodes(int n, int m){
        Node temp1 = head;
        int j=1;
        while(j!=m){
            temp1 = temp1.next;
            j++;
        }
        Node temp2 = temp1.next;
        int k = 0;
        while(k!=n){
            temp2 = temp2.next;
            k++;
        }
        temp1.next = temp2;
        return;
    }

    



    
    public static void main(String[] args) {
       LinkedList ll = new LinkedList();
       ll.addFirst(8);
       ll.addFirst(7);
       ll.addFirst(6);
       ll.addFirst(5);
       ll.addFirst(4);
       ll.addFirst(3);
       ll.addFirst(2);
       ll.addFirst(1);
       ll.print();
       ll.delNNodesAfterMNodes(2, 2);
       ll.print();
    }

    
}
