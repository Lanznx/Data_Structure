// https://javarevisited.blogspot.com/2021/11/how-to-implement-skip-list-in-java.html#axzz7oSlT8cHz
import java.util.Random;

interface SkippableList<T extends Comparable<? super T>> {

    int LEVELS = 30;

    boolean delete(T target);
    void print();
    void insert(T data);
    SkipNode<T> search(T data);
}

class SkipNode<N extends Comparable<? super N>> {

    N data;
    @SuppressWarnings("unchecked")
    SkipNode<N>[] next = (SkipNode<N>[]) new SkipNode[SkippableList.LEVELS];

    SkipNode(N data) {
        this.data = data;
    }

    void refreshAfterDelete(int level) {
        SkipNode<N> current = this;
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).data == null) {
                SkipNode<N> successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }

    void setNext(SkipNode<N> next, int level) {
        this.next[level] = next;
    }

    SkipNode<N> getNext(int level) {
        return this.next[level];
    }

    SkipNode<N> search(N data, int level, boolean print) {
        if (print) {
            // System.out.print("Searching for: " + data + " at ");
            print(level);
        }

        SkipNode<N> result = null;
        SkipNode<N> current = this.getNext(level);
        while (current != null && current.data.compareTo(data) < 1) {
            if (current.data.equals(data)) {
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    void insert(SkipNode<N> skipNode, int level) {
        SkipNode<N> current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        if (skipNode.data.compareTo(current.data) < 1) {
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null 
                && current.data.compareTo(skipNode.data) < 1 &&
                current.getNext(level).data.compareTo(skipNode.data) < 1) {

            current = current.getNext(level);
        }

        SkipNode<N> successor = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successor, level);
    }

    void print(int level) {
        // System.out.print("level " + level + ": [ ");
        int length = 0;
        SkipNode<N> current = this.getNext(level);
        while (current != null) {
            length++;
            // System.out.print(current.data + " ");
            current = current.getNext(level);
        }

        // System.out.println("], length: " + length);
    }

}

public class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {

    private final SkipNode<T> head = new SkipNode<>(null);
    private final Random rand = new Random();

    @Override
    public void insert(T data) {
        SkipNode<T> skipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                //insert with prob = 1/(2^i)
                insert(skipNode, i);
            }
        }
    }

    @Override
    public boolean delete(T target) {
        System.out.println("Deleting " + target);
        SkipNode<T> victim = search(target, true);
        if (victim == null) return false;
        victim.data = null;

        for (int i = 0; i < LEVELS; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    @Override
    public SkipNode<T> search(T data) {
        return search(data, true);
    }

    @Override
    public void print() {
        for (int i = LEVELS-1; i >= 0 ; i--) {
            head.print(i);
        }
        System.out.println();
    }

    private void insert(SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private SkipNode<T> search(T data, boolean print) {
        SkipNode<T> result = null;
        for (int i = LEVELS-1; i >= 0; i--) {
            if ((result = head.search(data, i, print)) != null) {
                if (print) {
                    // System.out.println("Found " + data.toString() 
                    //     + " at level " + i + ", so stopped" );
                    System.out.println();
                }
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        for (int i = 10; i < 30; i++) {
            SkipList<Integer> sl = new SkipList<>();
            long insertStartTime = System.nanoTime();
            for (int j = 0; j < Math.pow(2, i); j++) {
                Random r = new Random();
                int upperBound = (int) Math.pow(2, 30);
                int ans = r.nextInt(upperBound);
                sl.insert(ans);
            }

            long insertEndTime = System.nanoTime();
            for (int j = 0; j < 100000; j++) {
                Random r = new Random();
                int upperBound = (int) Math.pow(2, 30);
                int ans = r.nextInt(upperBound);
                sl.search(ans);
            }

            long searchEndTime = System.nanoTime();
            System.out.printf("%d,%d\n",
                    insertEndTime - insertStartTime,
                    searchEndTime - insertEndTime);
        }
    }
}