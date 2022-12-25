// https://www.techiedelight.com/zh-tw/implementation-treap-data-structure-cpp-java-insert-search-delete/
import java.util.Random;

// 一個 Treap 節點
class TreapNode {
    int data;
    int priority;
    TreapNode left, right;

    // 構造函數
    TreapNode(int data) {
        this.data = data;
        this.priority = new Random().nextInt(Integer.MAX_VALUE);
        this.left = this.right = null;
    }
}

class Main {
    /*
     * 左旋轉給定treap的函數
     * 
     * r R
     * / \ 左旋轉 / \
     * L R ———> r Y
     * / \ / \
     * X Y L X
     */
    public static TreapNode rotateLeft(TreapNode root) {
        TreapNode R = root.right;
        TreapNode X = root.right.left;

        // 旋轉
        R.left = root;
        root.right = X;

        // 設置一個新的根
        return R;
    }

    /*
     * 向右旋轉給定的treap的函數
     * 
     * r L
     * / \ 向右旋轉 / \
     * L R ———> X r
     * / \ / \
     * X Y Y R
     */
    public static TreapNode rotateRight(TreapNode root) {
        TreapNode L = root.left;
        TreapNode Y = root.left.right;

        // 旋轉
        L.right = root;
        root.left = Y;

        // 設置一個新的根
        return L;
    }

    // 將具有優先級的給定鍵插入到treap中的遞歸的函數
    public static TreapNode insertNode(TreapNode root, int data) {
        // 基本情況
        if (root == null) {
            return new TreapNode(data);
        }
        // 如果數據小於根節點，則插入左子樹；
        // 否則，插入右子樹
        if (data < root.data) {
            root.left = insertNode(root.left, data);

            // 如果堆屬性被破壞，則向右旋轉
            if (root.left != null && root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else {
            root.right = insertNode(root.right, data);

            // 如果違反了堆屬性，則向左旋轉
            if (root.right != null && root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }

        return root;
    }

    // 遞歸的函數在給定的treap中搜索一個鍵
    public static boolean searchNode(TreapNode root, int key) {
        // 如果鍵不存在於樹中
        if (root == null) {
            return false;
        }

        // 如果找到密鑰
        if (root.data == key) {
            return true;
        }

        // 如果key小於根節點，則在左子樹中搜索
        if (key < root.data) {
            return searchNode(root.left, key);
        }

        // 否則，在右子樹中搜索
        return searchNode(root.right, key);
    }

    // 從給定的treap中刪除一個鍵的遞歸的函數
    public static TreapNode deleteNode(TreapNode root, int key) {
        // 基本情況：在樹中找不到鍵
        if (root == null) {
            return null;
        }

        // 如果key小於根節點，則遞歸左子樹
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        }

        // 如果key大於根節點，則遞歸到右子樹
        else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        }

        // 如果找到密鑰
        else {
            // 案例一：要刪除的節點沒有子節點(是葉子節點)
            if (root.left == null && root.right == null) {
                // 釋放內存並將root更新為null
                root = null;
            }

            // 情況2：要刪除的節點有兩個孩子
            else if (root.left != null && root.right != null) {
                // 如果左孩子的優先級低於右孩子
                if (root.left.priority < root.right.priority) {
                    // 在根上調用 `rotateLeft()`
                    root = rotateLeft(root);

                    // 遞歸刪除左孩子
                    root.left = deleteNode(root.left, key);
                } else {
                    // 在根上調用 `rotateRight()`
                    root = rotateRight(root);

                    // 遞歸刪除右孩子
                    root.right = deleteNode(root.right, key);
                }
            }

            // 案例3：要刪除的節點只有一個孩子
            else {
                // 選擇一個子節點
                TreapNode child = (root.left != null) ? root.left : root.right;
                root = child;
            }
        }
        return root;
    }

    // 實用函數來打印一個treap的二維視圖
    // 反向中序遍歷
    public static void printTreap(TreapNode root, int space) {
        final int height = 10;

        // 基本情況
        if (root == null) {
            return;
        }

        // 增加關卡之間的距離
        space += height;

        // 先打印右孩子
        printTreap(root.right, space);
        System.lineSeparator();

        // 用空格填充後打印當前節點
        for (int i = height; i < space; i++) {
            System.out.print(' ');
        }

        System.out.println(root.data + "(" + root.priority + ")");

        // 打印左孩子
        System.lineSeparator();
        printTreap(root.left, space);
    }

    public static void main(String[] args) {
        for (int i = 10; i < 30; i++) {
            TreapNode t = null;
            long insertStartTime = System.nanoTime();
            for (int j = 0; j < Math.pow(2, i); j++) {
                Random r = new Random();
                int upperBound = (int) Math.pow(2, 30);
                int ans = r.nextInt(upperBound);
                t = insertNode(t, ans);
            }

            long insertEndTime = System.nanoTime();
            for (int j = 0; j < 100000; j++) {
                Random r = new Random();
                int upperBound = (int) Math.pow(2, 30);
                int ans = r.nextInt(upperBound);
                searchNode(t, ans);
            }

            long searchEndTime = System.nanoTime();
            System.out.printf("%d,%d\n",
                    insertEndTime - insertStartTime,
                    searchEndTime - insertEndTime);
        }
    }
}
