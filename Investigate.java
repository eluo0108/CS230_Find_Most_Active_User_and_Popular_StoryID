/**
 * Create report content (driver) by
 * Creating a graph given the .csv file, then "asking questions" of this graph using the pre-mad emethods
 *
 * @Breanna W
 * @Esther L
 */
import java.util.*;
import java.io.*;

public class Investigate extends AdjListsGraph<String>
{
    AdjListsGraph graph;

    String mostActiveName = "";
    int highest = 0;

    /**
     * Constructor
     * look @ first character to see if it is story or user
     * read RATgraph.csv
     * produce adjlistsgraph object from csv using methods from adjlistsgraph (displayFile)
     */
    public Investigate(String RATgraph) 
    {
        graph = new AdjListsGraph<String>();

        //System.out.println(graph.arcs.size());

        File RATgraphFile = new File(RATgraph);
        graph.convertCSV(RATgraphFile);
        //System.out.println(graph.saveToTGF());
        //System.out.println(graph.saveToTGF()); 
    }

    /**
     * The length of a linked list that contains the MOST edges coming out of 
     * a node "a" which represents a userID 
     */
    public String findMostActiveUser()
    {        
        String str = "\"Most Active\" user is: ";
        for (int i = 0; i < graph.arcs.size(); i++)
        {

            int currentSize = graph.findListSize(graph.arcs.get(i));
            //if the size of the current user linked list is greater than the 
            //current highest number, then replace highest with the size of the
            //current list
            if (currentSize > highest)
            {
                highest = currentSize;
                mostActiveName = (String)graph.vertices.get(i);
            }
        }

        if (mostActiveName.equals(""))
        {
            return "No \"Most Active\" User";
        }
        else
        {
            return str + mostActiveName + "\n And it's size is " + highest;
        }
    }

    /**
     * The length of a linked list that contains the MOST edges coming out of
     * a node "a" which represents a storyID
     *
     * (Key (storyID), value (numOccurences))
     *
     **/
    public String findMostActiveStory()
    {
        String str = "\"Most Popular\" storyID is: ";
        String highestStory = "";
        int highestVal = 0;
        String currentStory;
        Integer currentVal; //num of occurences
        //string is storyID, Integer is number of occurences
        Hashtable<String, Integer> stories = new Hashtable<String, Integer>(1000);
        for (int i = 0; i < graph.arcs.size(); i++){ //iterate thru vector (users)
            for (int j = 1; j < graph.findListSize(graph.arcs.get(i)); i++){ //iterate thru linked list (storyIDS)
                currentStory = graph.findListElement(graph.arcs.get(i), j);

                if (currentStory.charAt(0) != 'u')
                {
                    if (stories.containsKey(currentStory))
                    {
                        currentVal = stories.get(currentStory);
                    }
                    else
                        currentVal = 0;

                    int futureVal = currentVal + 1;

                    stories.put(currentStory, futureVal);

                    if (futureVal > highestVal)
                    {                  
                        highestVal = futureVal;
                        highestStory = currentStory;
                    }
                }
            }
        }

        return str + highestStory + " ";
    }

    public static void main(String args[]) //argument will be read file
    {
        Investigate i1 = new Investigate("RATgraph.tsv");

        System.out.println(i1.findMostActiveUser());
        System.out.println(i1.findMostActiveStory());

    }
}
