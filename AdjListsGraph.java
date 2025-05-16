/**
 * AdjListsGraph 
 * @bwhite4
 * @yluo2
 * 
 */
import java.util.*;
import java.io.*;
import javafoundations.*;

public class AdjListsGraph<T> 
{
    //vector of generic kind of objects
    public Vector<T> vertices; //new Vector<T>()
    //vector of linked lists ( (adj vtx, adj vtx), (adj vtx, adj vtx) ...
    public Vector<LinkedList<T>> arcs;//how access elements within linkededlist
    
    private int numVertices;//create a counter to keep track of total vertices created
    
    /**
     * Constructor for AdjListsGraph
     */
    public AdjListsGraph()
    {
        //initialize variables
        numVertices = 0;
        vertices = new Vector<T>();
        arcs = new Vector<LinkedList<T>>();
    }

    /**
     * toString method
     */
    public String toString()
    {
        String str = "Vertices:\n";
        str += vertices.toString();
        str += "Edges:\n";
        for (int i = 0; i < arcs.size(); i++)
        {
            str += "from " + vertices.elementAt(i) + ": " + arcs.get(i).toString() + "\n";
            str += "Number of elements: " + arcs.get(i).size() + "\n";
        }

        return str;
    }

    /** 
     * Returns the number of vertices in this graph. 
     * 
     * @return the number of vertices in this graph
     */
    public int getNumVertices()
    {
        return vertices.size();
    }

    /** 
     * Returns the number of arcs in this graph.
     * An arc between Verteces A and B exists, if a direct connection
     * from A to B exists.
     * 
     * @return the number of arcs in this graph
     */
    public int getNumArcs()
    {
        return arcs.size();
    }

    /** 
     * Returns a boolean indicating whether this graph is empty or not.
     * A graph is empty when it contains no vertice,and of course, no edges.
     *  
     *  @return true if this graph is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        if ( (getNumVertices() == 0) && (getNumArcs() == 0) )
        {
            return true;
        }
        else
            return false;
    }

    /** 
     * Returns true if an arc (direct connection) exists 
     * from the first vertex to the second, false otherwise
     * 
     * @return true if an arc exists between the first given vertex (vertex1),
     * and the second one (vertex2),false otherwise
     * 
     *  */
    public boolean isArc (T vertex1, T vertex2)
    {
        int index = vertices.indexOf(vertex1);
        if (index == -1) return false;
        return arcs.get(index).contains(vertex2);
    }

    /** 
     * Returns true if an edge exists between two given vertices, i.e,
     * an arch exists from the first vertex to the second one, and an arc from
     * the second to the first vertex, false otherwise.
     *  
     * @return true if an edge exists between vertex1 and vertex2, 
     * false otherwise
     * 
     * */
    public boolean isEdge (T vertex1, T vertex2)
    {
        return (isArc (vertex1, vertex2) && isArc (vertex2, vertex1));
    }

    /** 
     * Returns true if the graph is undirected, that is, for every
     * pair of nodes i,j for which there is an arc, the opposite arc
     * is also present in the graph, false otherwise.  
     * 
     * @return true if the graph is undirected, false otherwise
     * */
    public boolean isUndirected(){
        for (int i = 0; i < arcs.size(); i++) {
            for (int j = 0; j < arcs.get(i).size(); j++) {
                T from = vertices.get(i);
                T to = arcs.get(i).get(j);
                if (!isArc(to, from)) return false;
            }
        }
        return true; 
    }

    /** 
     * Adds the given vertex to this graph
     * If the given vertex already exists, the graph does not change
     * 
     * @param The vertex to be added to this graph
     * */
    public void addVertex (T vertex)
    {
        if (!(this.vertices.contains(vertex)))
        {
            //add new vertex to Vector vertices
            this.vertices.add(vertex);
            
            //create new linked list in vector arcs
            LinkedList l1 = new LinkedList<T>();
            this.arcs.add(l1);
            
            //increment number of vertices
            numVertices++;
        }
        else{
            System.out.println("Vertex already exists");
        }
    }

    /** 
     * Removes the given vertex from this graph.
     * If the given vertex does not exist, the graph does not change.
     * 
     * @param the vertex to be removed from this graph
     *  */
    public void removeVertex (T vertex)
    {
        if (this.vertices.contains(vertex))
        {
            //remove vertex from Vector vertices
            this.vertices.remove(vertex);
            
            //remove linked list associated with vertex in vector arcs
            arcs.remove(vertices.indexOf(vertex));
            
            //decrease number of vertices
            numVertices--;
        }
        else{
            System.out.println("Vertex does not exist");
        }
    }

    /** 
     * Inserts an arc between two given vertices of this graph.
     * if at least one of the vertices does not exist, the graph 
     * is not changed.
     * 
     * public Vector<LinkedList<T>> arcs ( [1,2] , [1,3] )
     * [
     * 
     * @param the origin of the arc to be added to this graph
     * @param the destination of the arc to be added to this graph
     * 
     *  */
    public void addArc (T vertex1, T vertex2)
    {
        if ( (this.vertices.contains(vertex1)) && (this.vertices.contains(vertex2)) )
        {
            //index value is position on vertex 1 in the vertices vector
            int index = vertices.indexOf(vertex1);

            //current list being added to (each vertex has its own list of nodes connected to it)
            LinkedList currentList = this.arcs.get(index); 
            
            //add vertex2 to vertex1's linked list in the vector arcs
            currentList.add(vertex2); 
        }
        else{
            System.out.println("One of the vertices does not exist");
        }
    }

    /** 
     * Removes the arc between two given vertices of this graph.
     * If one of the two vertices does not exist in the graph,
     * the graph does not change.
     * 
     * @param the origin of the arc to be removed from this graph
     * @param the destination of the arc to be removed from this graph
     * 
     * */
    public void removeArc (T vertex1, T vertex2){
        if ( (this.vertices.contains(vertex1)) && (this.vertices.contains(vertex2)))
        {
            int index = vertices.indexOf(vertex1);
            
            this.arcs.get(index).remove(vertex2);
        }
        else{
            System.out.println("One of the vertices does not exist");
        }
    }

    /** 
     * Inserts the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be added to this graph
     * @param the destination of the edge to be added to this graph
     * 
     *  */
    public void addEdge (T vertex1, T vertex2){
        addArc (vertex1, vertex2);
        addArc (vertex2, vertex1);
    }

    /** 
     * Removes the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be removed from this graph
     * @param the destination of the edge to be removed from this graph
     * 
     */
    public void removeEdge (T vertex1, T vertex2){
        removeArc (vertex1, vertex2);
        removeArc (vertex2, vertex1);
    }

    /** 
     * Return all the vertices, in this graph, adjacent to the given vertex.
     * 
     * @param A vertex in th egraph whose successors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from the given vertex to x (vertex -> x).
     *
     * */
    public LinkedList<T> getSuccessors(T vertex){
        return arcs.get(vertices.indexOf(vertex));
    }

    /** 
     * Return all the vertices x, in this graph, that precede a given
     * vertex.
     * 
     * @param A vertex in the graph whose predecessors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from x to the given vertex (x -> vertex).
     * 
     * */
    public LinkedList<T> getPredecessors(T vertex){
        LinkedList predecessors = new LinkedList<T>();
        for (int i = 0; i < arcs.size(); i++){
            //check if the specific linked list in vector arcs contain the vertex
            if (arcs.get(i).contains(vertex)){
                //add the vertex that the linked list is referring to predecessors
                predecessors.add(vertices.get(i));
            }
        }
        return predecessors;
    }

    /** 
     * Return size of a given linked list.
     * This is a helper method for Investigate class.
     * 
     * @param l linkedlist (within the vector arcs)
     * @return int of list's size
     * 
     * */
    public int findListSize(T l)
    {
        LinkedList l1 = (LinkedList)(l);
        return (l1.size());
    }
    
    /** 
     * Return size of a given linked list. 
     * This is a helper method for Investigate class.
     * 
     * @param l linkedlist (within the vector arcs)
     * @param index the specific position the element is located in the linkedlist
     * @return int of list's size
     * 
     * */
     public String findListElement(T l, int index)
     {
         LinkedList l1 = (LinkedList)(l);
         return (String)l1.get(index);
        }

    /** 
     * Returns iterator depth-first-search
     * @param startIndex the position of the starting index
     * @return Iterator
     * */
    public Iterator iteratorDFS(int startIndex) 
    {
        int numVertices = vertices.size(); //mentioned without initialization in textbook
        int currentVertex = 0;

        //boolean[][] adjMatrix; mentioned without initialization in textbook, will use linkedlist implntn instead

        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayIterator<Integer> iter = new ArrayIterator<Integer>();

        boolean[] visited = new boolean[numVertices];
        boolean found = false;

        if (startIndex > vertices.size())
        {
            return iter;
        }

        for (int vertexIdx = 0; vertexIdx < numVertices; vertexIdx++)
        {
            visited[vertexIdx] = false;
        }

        traversalStack.push(startIndex);
        iter.add (startIndex);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty())
        {
            currentVertex = traversalStack.peek();
            found = false;
        }

            for (int vertexIdx = 0; vertexIdx < numVertices && !found; vertexIdx++)
            {
                if ((arcs.get(currentVertex).contains(vertexIdx)) && (!visited[vertexIdx]))
                {
                    traversalStack.push(vertexIdx);
                    iter.add(vertexIdx);
                    visited[vertexIdx] = true;
                    found = true;
                }

            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }

        while (iter.hasNext())
        {
            System.out.print("Test iterator printing: " + iter.next() + " ");
        }

        return iter;
    }

    /** 
     * Returns iterator bredth-first-search
     * @return Iterator
     * */
    public LinkedList<T> iteratorBFS(){
        //store starting point in a specific variable
        T start;
        
        //create traversalQueue to record the vertices being traversed
        ArrayQueue<T> traversalQueue = new ArrayQueue<T>();
        
        //create linked list to return at the end
        LinkedList<T> iter = new LinkedList<T>();
        
        //store current value being traversed
        T currentVal;
        
        //a list of boolean indicating which vertices we've visited
        boolean[] visited = new boolean[numVertices];
        
        //set defaults for visited list 
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        
        //set value of start
        start = vertices.get(0);
        
        //add start to the queue to prepare to traverse through the vertex linked list
        traversalQueue.enqueue(start);
        visited[0] = true;
        
        while (!traversalQueue.isEmpty()) {
            T currentVertex = traversalQueue.dequeue();
            iter.add(currentVertex);

            int index = vertices.indexOf(currentVertex);
            LinkedList<T> neighbors = arcs.get(index);

            for (int i = 0; i < neighbors.size(); i++) {
                T neighbor = neighbors.get(i);
                int neighborIndex = vertices.indexOf(neighbor);

                if (!visited[neighborIndex]) {
                    traversalQueue.enqueue(neighbor);
                    visited[neighborIndex] = true;
                }
            }
        }
        return iter;
    }
    
    /**
     * Method printList prints out elements from BFS
     *
     * @param iter Linked List that contains all the vertices being itered through
     * @return result in String format
     */
    public String BFSprintList(LinkedList iter)
    {
        String str = "Sorted by BFS:\n";
        for (int i = 0; i < iter.size(); i++)
        {
            str += "element " + i + ": " + iter.get(i) + "\n";
        }

        return str;
    }
    
    /** 
     * Turns graph into .tgf text (String)
     * 
     * @param graph
     * 
     * @return String
     *
     *1 First node (u)
     *2 Second node
     *#
     *1 2 Edge between the two
     */
    public String saveToTGF()
    {   
        //return string
        String str = "";

        //writing nodes (vertex) first (one node per line)
        for (int i = 0; i < vertices.size(); i++)
        {
            str += vertices.get(i) + "\n";
        }

        //hash mark
        str += "#\n";

        //writing edges first (each node)
        for (int i = 0; i < arcs.size(); i++)
        {
            for (int j = 0; j < arcs.get(i).size(); j++)
            {
                //System.out.println("i :" + i + " j: " + j);
                str += vertices.get(i) + " " + arcs.get(i).get(j) + "\n";
            }
        }

        return str;
    }

    /** 
     * put .csv values into an AdjListsGraph obj
     * .csv file to .tgf file 
     * .csv (user ID, all story IDs) -> .tgf (1 "userID" 2 "story ID" ... # 1 2 2 1 (1 connected to everything)
     * @param .csv file
     * 
     * @return .tgf format text (String)
     */
    public void convertCSV(File inFileName)
    {
        String str = "";

        String id = "";

        String storyID = "";

        try {
            Scanner fileScan = new Scanner (inFileName);
            //skip over first line .csv
            String line = fileScan.nextLine();

            while (fileScan.hasNextLine()) {

                line = fileScan.nextLine();
                
                //Find, add to vertices, and print userID
                String userid[] = line.split("\t");

                // ✅ Skip this line if it doesn't have at least 5 columns
                if (userid.length < 5) continue;
                
                id = "u" + userid[0];
                
                if (!(this.vertices.contains((T)id)))
                {
                    this.addVertex((T)id);
                }

                //new scanner of the entire element which is all of the stories 
                //under conditional that stops at nextLine "\n"
                Scanner storiesScan = new Scanner (userid[4]);
                //useDelimiter on commas
                storiesScan.useDelimiter(",");
                while (storiesScan.hasNext()) //stop when hits the \n new line 
                {
                    storyID = storiesScan.next();

                    if (!(this.vertices.contains((T)storyID
                        )))
                    {
                        this.addVertex((T)storyID);
                    }
                    this.addEdge((T)id, (T)storyID);
                }

            }

            this.saveToTGF();

        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    /**
     * Depth-first search main method (testing)
     * .csv reading method (testing)
     */
    public static void main(String args[]) //argument will be read file
    {
        AdjListsGraph<String> test = new AdjListsGraph<String>();

        // test.addVertex("1");
        // test.addVertex("3");
        // test.addVertex("5");
        // test.addArc("1", "3");
        // test.addArc("3", "1");
        // test.addArc("1", "5");
        // test.addArc("5", "1");
        // System.out.print(test);

        File ratGraph = new File("RATgraph.tsv");

        // convert .csv into adjListsGraph
        test.convertCSV(ratGraph);
        System.out.println(test.arcs.size());

        // convert this newly created adjlistsgraph to .tgf format
        System.out.println(test.saveToTGF()); 
        
       
        // test.addVertex(Integer.toString(1));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addVertex(Integer.toString(2));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addVertex(Integer.toString(3));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addVertex(Integer.toString(4));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addVertex(Integer.toString(5));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addVertex(Integer.toString(6));
        // System.out.println("Test add vertex:\n");
        // System.out.println(test.toString());

        // test.addEdge(Integer.toString(1), Integer.toString(2));
        // System.out.println("Test add edge:\n");
        // System.out.println(test.toString());

        // test.addEdge(Integer.toString(2), Integer.toString(3));
        // System.out.println("Test add edge:\n");
        // System.out.println(test.toString());

        // test.addEdge(Integer.toString(1), Integer.toString(4));
        // System.out.println("Test add edge:\n");
        // System.out.println(test.toString());

        // test.addEdge(Integer.toString(4), Integer.toString(5));
        // System.out.println("Test add edge:\n");
        // System.out.println(test.toString());

        // test.addEdge(Integer.toString(5), Integer.toString(6));
        // System.out.println("Test add edge:\n");
        // System.out.println(test.toString());

        // test.iteratorDFS(0);
        
        // //make an adjacent lists
        // AdjListsGraph a1 = new AdjListsGraph<String>();
        // a1.addVertex("1");
        // a1.addVertex("2");
        // a1.addVertex("3");
        // a1.addVertex("4");
        // a1.addVertex("5");
        // a1.addArc("1", "2");
        // a1.addArc("1", "3");
        // a1.addArc("2", "4");
        // a1.addArc("2","5");
        // System.out.println(a1);
        
        // LinkedList l1 = a1.iteratorBFS();
        // System.out.println(a1.BFSprintList(l1));
        // //sort by BFS

    }
}  
