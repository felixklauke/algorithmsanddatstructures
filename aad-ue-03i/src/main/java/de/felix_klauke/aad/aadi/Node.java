package de.felix_klauke.aad.aadi;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class Node<ContentType> {

    private final ContentType content;
    private Node<ContentType> previous;
    private Node<ContentType> next;

    private Node(ContentType content, Node<ContentType> previous, Node<ContentType> next) {
        this.content = content;
        this.previous = previous;
        this.next = next;
    }

    public Node(ContentType content) {
        this(content, null, null);
    }

    public ContentType getContent() {
        return content;
    }

    public Node<ContentType> getNext() {
        return next;
    }

    public void setNext(Node<ContentType> next) {
        this.next = next;
    }

    public Node<ContentType> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<ContentType> previous) {
        this.previous = previous;
    }
}
