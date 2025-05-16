/**
 * depth first search (with stack)
 *
 * @bwhite4
 * @v1
 */
import java.util.*;
import javafoundations.*;

public class iteratorDFS<T>
{ 
    int numVertices; //mentioned without initialization in textbook
    int currentVertex;

    public T[] vertices; //mentioned without initialization in textbook
    public boolean[][] adjMatrix; //mentioned without initialization in textbook

    LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
    ArrayIterator<T> iter = new ArrayIterator<T>();

    boolean[] visited = new boolean[numVertices];
    boolean found;
    
    //helper method indexIsValid (mentioned without initialization in textbook)
    public boolean indexIsValid(int i)
    {
        if (i > vertices.length)
        {
            return false;
        }
        else
            return true;
    }

    public Iterator<T> iteratorDFS(int startIndex)
    {
      if (!indexIsValid(startIndex))
            return iter;
        
        for (int vertexIdx = 0; vertexIdx < numVertices; vertexIdx++)
        {
            visited[vertexIdx] = false;
        }

        traversalStack.push(startIndex);
        iter.add (vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty())
        {
            currentVertex = traversalStack.peek();
            found = false;
        }

        for (int vertexIdx = 0; vertexIdx < numVertices && !found; vertexIdx++)
        {
            if ((adjMatrix[currentVertex][vertexIdx]) && (!visited[vertexIdx]))
            {
                traversalStack.push(vertexIdx);
                iter.add(vertices[vertexIdx]);
                visited[vertexIdx] = true;
                found = true;
            }

            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }

        return iter;
    }
}
