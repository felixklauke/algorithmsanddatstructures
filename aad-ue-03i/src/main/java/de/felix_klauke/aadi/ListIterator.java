package de.felix_klauke.aadi;

import java.util.Iterator;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ListIterator<ContentType> implements Iterator<ContentType> {

    private Node<ContentType> currentNode;

    ListIterator(Node<ContentType> currentNode) {
        this.currentNode = currentNode;
    }

    public boolean hasNext() {
        return currentNode != null;
    }

    public ContentType next() {
        ContentType content = currentNode.getContent();
        currentNode = currentNode.getNext();
        return content;
    }

    public void remove() {
        Node<ContentType> previous = currentNode.getPrevious();
        Node<ContentType> next = currentNode.getNext();

        previous.setNext(next);

        currentNode.setNext(null);
        currentNode.setPrevious(null);
    }
}
