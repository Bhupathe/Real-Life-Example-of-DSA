import java.util.Scanner;

class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        UndoRedo obj = new UndoRedo();
        System.out.print("Enter Text: "+obj.enteredText());
        obj.enterText(sc.nextLine());

        System.out.print("\nEnter Text: "+obj.enteredText());
        obj.enterText(sc.nextLine());

        System.out.print("\nEnter Text: "+obj.enteredText());
        obj.enterText(sc.nextLine());

        System.out.print("\nEnter Text: "+obj.enteredText());
        obj.enterText(sc.nextLine());

        System.out.println("\nUndo Mechanism: "+obj.undo());
        System.out.println("Undo Mechanism: "+obj.undo());

        System.out.println("Redo Mechanism: "+obj.redo());
        System.out.println("Redo Mechanism: "+obj.redo());
        //System.out.println("Undo Mechanism: "+obj.undo());

        sc.close();
    }
}

class UndoRedo{
    LinkedList undo_text;
    LinkedList redo_text;
    private String text="";

    UndoRedo(){
        undo_text = new LinkedList();
        redo_text = new LinkedList();
    }

    void enterText(String text){
        if(text==null){
            this.text = text;
        }else{
        this.text += text;
        }
        //System.out.println("Entering insert method.");
        undo_text.insert(this.text);
        //System.out.println("Exit from insert method.");
        redo_text.head.next = null;
    }

    String undo(){
        //System.out.println("Undo Peek: "+undo_text.peek());
        redo_text.insert(undo_text.pop());
        text = undo_text.peek();
        return text;
    }

    String redo(){
        /*System.out.println("Redo Peek: "+redo_text.peek());
        undo_text.insert(redo_text.pop());
        text = redo_text.peek();
        return text;*/

        String returnValue = redo_text.peek();
        undo_text.insert(returnValue);
        redo_text.pop();
        return returnValue;
    }

    String enteredText(){
        return text;
    }
}

class Node{
    String text;
    Node next;

    Node(){
        text = null;
        next = null;
    }

    Node(String text){
        this.text = text;
        next = null;
    }
}

class LinkedList{
    Node head;
    LinkedList(){
        head = new Node();
    }
    boolean isEmpty(){
        return head.next == null;
    }
    void insert(String text){
        Node newNode = new Node(text);
        if(isEmpty()){
            head.next = newNode;
            return;
        }
        Node temp = head;
        //System.out.println("Entering while loop");
        while(temp.next!=null){
            temp = temp.next;
        }
        //System.out.println("Exited from while loop.");
        temp.next = newNode;
        return;
    }

    String pop(){
        Node temp = head;
        Node prev = null;
        while(temp.next != null){
            prev = temp;
            temp = temp.next;
        }
        String returnValue = temp.text;
        prev.next = null;
        return returnValue;
    }

    String peek(){
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        return temp.text;
    }
}