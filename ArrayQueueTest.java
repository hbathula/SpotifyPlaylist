// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import queue.EmptyQueueException;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** tests the ArrayQueue class
 * 
 * @author himabathula
 * @version Aug 9, 2024
 */

public class ArrayQueueTest
    extends TestCase

{

    private ArrayQueue<String> queue;
    private ArrayQueue<String> emptyQueue;

    /**
     * sets up the queue and queues some entries inside
     */
    @Override
    public void setUp()
    {
        queue = new ArrayQueue<>();
        emptyQueue = new ArrayQueue<>();
        queue.enqueue("Hima");
        queue.enqueue("Krisha");
        queue.enqueue("Siri");
    }


    /**
     * constructor is tested
     */

    public void testConstuctor()
    {
        ArrayQueue<String> defaultQueue = new ArrayQueue<>();
        assertNotNull(defaultQueue);
        assertEquals(21, defaultQueue.getLengthOfUnderlyingArray());
        assertEquals(0, defaultQueue.getSize());
        assertTrue(defaultQueue.isEmpty());
    }


    /**
     * size is tested after entires get added
     */

    public void testGetSize()
    {

        assertEquals(3, queue.getSize());
        queue.enqueue("Lakshmi");
        assertEquals(4, queue.getSize());
    }


    /**
     * length of array is returned after capacity is assigned the length is one
     * more than the capacity
     */

    public void testGetLengthOfUnderlyingArray()
    {
        ArrayQueue<String> smallQueue = new ArrayQueue<>(5);
        assertEquals(6, smallQueue.getLengthOfUnderlyingArray());
    }


    /**
     * checked whether queue size gets doubled once the maximum capacity gets
     * reached
     */

    public void testEnsureCapacity()
    {
        ArrayQueue<String> smallQueue = new ArrayQueue<>(5);
        for (int i = 0; i < 5; i++)
        {
            smallQueue.enqueue("Element " + i);
        }

        assertEquals(6, smallQueue.getLengthOfUnderlyingArray());
        smallQueue.enqueue("Overflow");
        assertEquals(12, smallQueue.getLengthOfUnderlyingArray());
    }


    /**
     * clears the queue
     */

    public void testClear()
    {
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
        assertEquals(21, queue.getLengthOfUnderlyingArray());
    }


    /**
     * tests whether array queue contents are correctly getting positioned
     */

    public void testToArray()
    {
        Object[] array = queue.toArray();
        assertEquals(3, array.length);
        assertEquals("Hima", array[0]);
        assertEquals("Krisha", array[1]);
        assertEquals("Siri", array[2]);

        Exception exception = null;
        try
        {
            emptyQueue.toArray();
        }

        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof EmptyQueueException);

    }


    /**
     * queue entries are concatenated
     */

    public void testToString()
    {
        assertEquals("[Hima, Krisha, Siri]", queue.toString());

        emptyQueue.enqueue("only one");
        assertEquals("[only one]", emptyQueue.toString());

        emptyQueue.clear();
        assertEquals("[]", emptyQueue.toString());
    }


    /**
     * equals method checks whether two array queues contain the same elements
     * in the same order
     */

    public void testEquals()
    {
        ArrayQueue<String> queue2 = new ArrayQueue<>();
        queue2.enqueue("Hima");
        queue2.enqueue("Krisha");
        queue2.enqueue("Siri");

        assertTrue(queue2.equals(queue2));

        assertTrue(queue.equals(queue2));

        queue2.enqueue("Another");
        queue.enqueue("nav");
        assertFalse(queue.equals(queue2));

        assertFalse(queue.equals(null));
        assertFalse(queue.equals("Not a queue"));

        queue2.enqueue("Another entry");
        assertFalse(queue.equals(queue2));
    }


    /**
     * tests whether queue entires can get dequeued and what happens when all
     * entries get dequeud
     */
    public void testDequeue()
    {
        assertEquals("Hima", queue.dequeue());
        assertEquals("Krisha", queue.dequeue());
        assertEquals("Siri", queue.dequeue());
        assertTrue(queue.isEmpty());

        Exception exception = null;
        try
        {
            queue.dequeue();
        }

        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof EmptyQueueException);
    }


    /**
     * checks what happens when entries are enqueued onto queue and also
     * triggers the enureCapacity method, which doubles the size
     */
    public void testEnqueue()
    {
        queue.enqueue("Lakshmi");
        assertEquals(4, queue.getSize());
        assertEquals("Hima", queue.getFront());

        ArrayQueue<String> smallQueue = new ArrayQueue<>(2);
        smallQueue.enqueue("A");
        smallQueue.enqueue("B");
        smallQueue.enqueue("C");
        assertEquals(6, smallQueue.getLengthOfUnderlyingArray());
    }


    /**
     * gets the front entry of the queue
     */
    public void testGetFront()
    {
        assertEquals("Hima", queue.getFront());
        queue.dequeue();
        assertEquals("Krisha", queue.getFront());

        Exception exception = null;
        try
        {
            emptyQueue.getFront();
        }

        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof EmptyQueueException);

    }


    /**
     * public testing method to check whether queue is full
     */

    public void testPublicIsFull()
    {
        ArrayQueue<String> smallQueue = new ArrayQueue<>(2);
        assertFalse(smallQueue.publicIsFull());
        smallQueue.enqueue("A");
        smallQueue.enqueue("B");
        assertTrue(smallQueue.publicIsFull());
        smallQueue.enqueue("C");
        assertFalse(smallQueue.publicIsFull());
    }


    /**
     * check whether queue is empty once it gets clear
     */

    public void testIsEmpty()
    {
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }

}
