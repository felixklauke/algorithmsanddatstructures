package de.felix_klauke.aadi;

import java.util.Iterator;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class List<ContentType> implements Iterable<ContentType> {

    private Node<ContentType> head;
    private Node<ContentType> tail;

    public List(Node<ContentType> head, Node<ContentType> tail) {
        this.head = head;
        this.tail = tail;
    }

    public List() {
    }

    public void add(ContentType content) {
        if (isEmpty()) {
            Node<ContentType> node = new Node<ContentType>(content);
            head = node;
            tail = node;
            return;
        }

        Node<ContentType> node = new Node<>(content);

        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public ContentType remove(ContentType content) {
        if (isEmpty()) {
            return null;
        }

        Iterator<ContentType> iterator = iterator();

        int removedElements = 0;

        while (iterator.hasNext()) {
            if (!iterator.next().equals(content)) {
                continue;
            }

            iterator.remove();
            removedElements++;
        }

        return removedElements == 0 ? null : content;
    }

    public boolean contains(ContentType content) {
        if (isEmpty()) {
            return false;
        }

        for (ContentType next : this) {
            if (content.equals(next)) {
                return true;
            }
        }

        return false;
    }

    public Iterator<ContentType> iterator() {
        return new ListIterator<ContentType>(head);
    }
}
