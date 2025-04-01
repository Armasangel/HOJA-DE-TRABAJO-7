public class BinarySearchTree<E extends Comparable<E>> {
    protected BSTNode<E> root;

    protected static class BSTNode<E> {
        protected E data;
        protected BSTNode<E> left;
        protected BSTNode<E> right;

        public BSTNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insertar valor en el AB
     * @param value Valor a insertar
     */
    public void insert(E value) {
        root = insertRecursive(root, value);
    }

    private BSTNode<E> insertRecursive(BSTNode<E> current, E value) {
        if (current == null) {
            return new BSTNode<>(value);
        }

        int comparison = value.compareTo(current.data);
        
        if (comparison < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        }

        
        return current;
    }

    /**
     * Busca el valor en el AB
     * @param value El valor que busca
     * @return El valor encontrado o null si no existe
     */
    public E search(E value) {
        return searchRecursive(root, value);
    }

    private E searchRecursive(BSTNode<E> current, E value) {
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.data);
        
        if (comparison == 0) {
            return current.data;
        }
        
        if (comparison < 0) {
            return searchRecursive(current.left, value);
        } else {
            return searchRecursive(current.right, value);
        }
    }

    /**
     * In-order traversal of the BST
     * @param consumer Consumer function to process each node
     */
    public void inOrderTraversal(java.util.function.Consumer<E> consumer) {
        inOrderRecursive(root, consumer);
    }


    private void inOrderRecursive(BSTNode<E> current, java.util.function.Consumer<E> consumer) {
        if (current != null) {
            inOrderRecursive(current.left, consumer);
            consumer.accept(current.data);
            inOrderRecursive(current.right, consumer);
        }
    }

    /**
     * Recorrido inverso del AB (para orden descendente)
     * @param consumer Funcion de consumo para procesar cada nodo
     */
    public void reverseInOrderTraversal(java.util.function.Consumer<E> consumer) {
        reverseInOrderRecursive(root, consumer);
    }


    private void reverseInOrderRecursive(BSTNode<E> current, java.util.function.Consumer<E> consumer) {
        if (current != null) {
            reverseInOrderRecursive(current.right, consumer);
            consumer.accept(current.data);
            reverseInOrderRecursive(current.left, consumer);
        }
    }
}