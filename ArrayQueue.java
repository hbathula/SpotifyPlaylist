// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Hima Bathula (906421933)

package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * // -------------------------------------------------------------------------
 * /** Gives behavior for songs to get enqueued, dequeued, etc
 * 
 * @param <T>
 *            T represents generic, and in this case song objects
 * @author himabathula
 * @version Aug 9, 2024
 */

public class ArrayQueue<T>
    implements QueueInterface<T>
{
    /**
     * default capacity of queue set to be 20
     */

    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    /**
     * constructor taking default capacity
     */

    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }


    /**
     * queue has 20 entries front index and back index get initialized to 0
     * number of entires denoted by size is also 0
     * 
     * @param capacity
     *            20 is the capacity of the queue
     */

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        queue = (T[])new Object[capacity + 1];
        enqueueIndex = 0;
        dequeueIndex = 0;
        size = 0;

    }


    /**
     * @return returns the number of items stored in queue
     */

    public int getSize()
    {
        return size;
    }


    /**
     * @return length of underlying array in the queue
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    /**
     * doubles size of queue when maximum entries is reached
     */

    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        if ((enqueueIndex + 1) % queue.length == dequeueIndex)
        {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            queue = (T[])new Object[newSize];

            int j = dequeueIndex;
            for (int i = 0; i < size; i++)
            {
                queue[i] = oldQueue[j];
                j = (j + 1) % oldSize;
            }

            dequeueIndex = 0;
            enqueueIndex = size;
        }

    }


    /**
     * circular implementation when array is full
     */

    private int incramentIndex(int index)
    {
        return (index + 1) % queue.length;
    }


    /**
     * clears queue
     */

    @Override
    public void clear()
    {
        @SuppressWarnings("unchecked")
        T[] newQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = newQueue;
        enqueueIndex = 0;
        dequeueIndex = 0;
        size = 0;

    }


    /**
     * copies elements of queue into array and returns it throws an expetion if
     * queue is empty copies elements of q
     * 
     * @return the array format of queue
     */

    public Object[] toArray()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        @SuppressWarnings("unchecked")
        T[] resArray = (T[])new Object[size];
        for (int i = 0; i < size; i++)
        {
            resArray[i] = queue[(dequeueIndex + i) % queue.length];
        }

        return resArray;

    }


    /**
     * concatenates the contents of the queue
     */

    @Override
    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }

        StringBuilder string = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            string.append(queue[(dequeueIndex + i) % queue.length].toString());
            if (i < size - 1)
            {
                string.append(", ");
            }
        }

        string.append("]");
        return string.toString();
    }


    /**
     * checks whether two array queues are equal if the contain the same
     * elements in the same order
     */

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArrayQueue<T> other = (ArrayQueue<T>)obj;

        if (size != other.size)
        {
            return false;
        }
        for (int i = 0; i < size; i++)
        {
            T thisEntry = queue[(dequeueIndex + i) % queue.length];

            T otherEntry =
                other.queue[(other.dequeueIndex + i) % other.queue.length];

            if (!thisEntry.equals(otherEntry))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * removes entry from the queue
     */

    @Override
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        T front = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        dequeueIndex = incramentIndex(dequeueIndex);
        size--;
        return front;
    }


    /**
     * adds entry to queue
     */

    @Override
    public void enqueue(T newEntry)
    {
        ensureCapacity();
        queue[enqueueIndex] = newEntry;
        enqueueIndex = incramentIndex(enqueueIndex);
        size++;
    }


    /**
     * gets the front entry of the queue
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * @return returns whether queue gets filled if the last index and first
     *             index are equal
     */

    private boolean isFull()
    {
        return (enqueueIndex + 1) % queue.length == dequeueIndex;
    }


    /**
     * public reference to private variable above
     * 
     * @return the isFull() method for testing
     */

    public boolean publicIsFull()
    {
        return isFull();
    }


    /**
     * checks whether queue is empty
     */
    @Override
    public boolean isEmpty()
    {

        return size == 0;
    }

}
