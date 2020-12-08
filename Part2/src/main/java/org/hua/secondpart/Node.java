/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.secondpart;

import java.io.Serializable;

/**
 *
 * @author KillB
 */
public class Node implements Comparable<Node>, Serializable {

    private final char character;
    private final int frequency;
    private final Node left;
    private final Node right;

    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node n) {
        return this.frequency - n.frequency;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftChild() {
        return left;
    }

    public Node getRightChild() {
        return right;
    }

}
