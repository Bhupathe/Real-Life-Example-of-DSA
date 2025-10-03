import java.util.*;

class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        MusicPlaylist obj = new MusicPlaylist();
        obj.add("Break the Cocoon (Soul Land)");
        obj.add("Battle Song (Soul Land)");
        obj.add("Blue Sea (Soul Land)");
        obj.add("Search of Soul");
        obj.add("Sword Art Online 2 Op 2");
        obj.add("Sword Art Online Original Soundtrack Vol 1 25 fight!");
        obj.add("SwordLand");
        obj.add("Sword Art Online: Alicization - War of Underworld OST - 23");
        obj.add("Sword Art Online: Alicization - War of Underworld OST - 26");
        obj.add("Back on Cerulean - Gundam Build Fighters Try OST");
        obj.add("[The Last One] Back-On Gundam Build Fighters Try OST");
        obj.add("Magic Time (Gundam Build Divers Re:Rise Ending)");

        //System.out.println("Your Playlist contains: ");
        //obj.print();

        System.out.print("\nSearch Song: ");
        String songSearch = sc.nextLine();
        obj.search(songSearch);   
        //System.out.println("Count: "+obj.size()); 

        //System.out.println(" ");
        for(int i = 0; i<obj.searchList.length;i++){
            System.out.println("("+(i+1)+") "+obj.searchList[i]+" ");
        }

        System.out.print("\n\nEnter the song number you want to play: ");
        int songNumber = sc.nextInt();
        System.out.println("Now Playing: "+obj.play(songNumber));

        sc.nextLine();
        System.out.println("\n\nEnter 'p' for previous song and 'n' for next song: ");
        String playSong = sc.nextLine();
        System.out.println("Now Playing: "+obj.prevOrNext(playSong));

        System.out.println("\n\nEnter 'p' for previous song and 'n' for next song: ");
        playSong = sc.nextLine();
        System.out.println("Now Playing: "+obj.prevOrNext(playSong));
    }
}

class Node{
    String name;
    Node prev=null;
    Node next=null;

    Node(String name){
        this.name = name;
    }

    Node(){
        name = null;
    }
}

class MusicPlaylist{
    Node head;
    String searchList[] = null;
    private int songNumber;

    MusicPlaylist(){
        head = new Node();
    }

    MusicPlaylist(String name){
        head = new Node(name);
    }

    boolean isEmpty(){
        return head.next == null;
    }

    void add(String songName){
        Node newNode = new Node(songName);
        Node temp = head;
        if(head.next == null){
            head.next = newNode;
            newNode.prev = head;
            return;
        }

        while(temp.next!=null)
            temp = temp.next;
        temp.next = newNode;
        newNode.prev = temp;
    }

    void print(){
        Node temp = head.next;
        if(isEmpty()){
            System.out.println("Your Playlist is Empty.");
            return;
        }
        while(temp!=null){
            System.out.println(temp.name);
            temp = temp.next;
        }
    }

    int size(){
        Node temp = head.next;
        int count = 0;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    void search(String name){
        String tempSearchList[] = new String[size()];
        if(isEmpty()){
            System.out.println("Playlist is Empty.");
            return;
        }
        Node temp = head.next;
        int i = 0;
        while(temp!=null){
            if((temp.name.toLowerCase()).contains(name.toLowerCase())){
                tempSearchList[i] = temp.name;
                i++;
                //System.out.println(temp.name);
            }
            temp = temp.next;
        }
        searchList = new String[i];
        for(int j=0;j<i;j++)
            searchList[j] = tempSearchList[j];
    }

    String play(int songNumber){
        this.songNumber = songNumber-1;
        return searchList[songNumber-1];
    }

    String prevOrNext(String code){
        switch(code){
            case "p": return previous();
            case "n": return next();
            default: return "Invalid Code.";
        }
    }

    String previous(){
        if(songNumber == 0){
            songNumber = searchList.length - 1;
            return searchList[songNumber];
        }
        songNumber = songNumber-1;
        return searchList[songNumber];
    }

    String next(){
        if(songNumber == (searchList.length-1)){
            songNumber = 0;
            return searchList[0];
        }
        songNumber = songNumber + 1;
        return searchList[songNumber];
    }
}