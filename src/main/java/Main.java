import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;

import com.heroku.sdk.jdbc.DatabaseUrl;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");


    get("/", (request, response) -> {

      Map<String, Object> attributes = new HashMap<>();
      if ((request.queryParams("start")==null)||(request.queryParams("end")==null))
        attributes.put("message", "Choose stations and press submit");
      else
      attributes.put("message", Dijkstra.test(request.queryParams("start"),request.queryParams("end")));

      return new ModelAndView(attributes, "pathFinder.ftl");
    }, new FreeMarkerEngine());

    post("/", (request, response) -> {

      Map<String, Object> attributes = new HashMap<>();
      if ((request.queryParams("start")==null)||(request.queryParams("end")==null))
        attributes.put("message", "Choose stations and press submit");
      else
        attributes.put("message", Dijkstra.test(request.queryParams("start"),request.queryParams("end")));

      return new ModelAndView(attributes, "pathFinder.ftl");
    }, new FreeMarkerEngine());

  }



}

class Vertex implements Comparable<Vertex>
{
  public final String name;
  public Edge[] adjacencies;
  public double minDistance = Double.POSITIVE_INFINITY;
  public Vertex previous;
  public Vertex(String argName) { name = argName; }
  public String toString() { return name; }
  public int compareTo(Vertex other)
  {
    return Double.compare(minDistance, other.minDistance);
  }

}

class Edge
{
  public final Vertex target;
  public final double weight;
  public Edge(Vertex argTarget, double argWeight)
  { target = argTarget; weight = argWeight; }
}

class Dijkstra
{
  public static void computePaths(Vertex source)
  {
    source.minDistance = 0.;
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
      Vertex u = vertexQueue.poll();

      // Visit each edge exiting u
      for (Edge e : u.adjacencies)
      {
        Vertex v = e.target;
        double weight = e.weight;
        double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
          vertexQueue.remove(v);

          v.minDistance = distanceThroughU ;
          v.previous = u;
          vertexQueue.add(v);
        }
      }
    }
  }

  public static List<Vertex> getShortestPathTo(Vertex target)
  {
    List<Vertex> path = new ArrayList<Vertex>();
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
      path.add(vertex);

    Collections.reverse(path);
    return path;
  }

  public static String test(String pointA, String pointB)
  {
    if ((pointA.equals(""))||(pointB.equals(""))) return "Invalid input";

    System.out.println("pointA: " + pointA+"; pointB: " + pointB);
    Vertex stationA = null;
    Vertex stationB = null;
    // mark all the vertices
    Vertex A = new Vertex("A");
    Vertex B = new Vertex("B");
    Vertex C = new Vertex("C");
    Vertex D = new Vertex("D");
    Vertex E = new Vertex("E");

    if (pointA.equals("A")) stationA = A;
    else if (pointA.equals("B")) stationA = B;
    else if (pointA.equals("C")) stationA = C;
    else if (pointA.equals("D")) stationA = D;
    else if (pointA.equals("E")) stationA = E;

    if (pointB.equals("A")) stationB = A;
    else if (pointB.equals("B")) stationB = B;
    else if (pointB.equals("C")) stationB = C;
    else if (pointB.equals("D")) stationB = D;
    else if (pointB.equals("E")) stationB = E;

    // set the edges and weight
    A.adjacencies = new Edge[]{ new Edge(B, 3), new Edge(D,6) };
    B.adjacencies = new Edge[]{ new Edge(A, 3), new Edge(C,7) };
    C.adjacencies = new Edge[]{ new Edge(D, 8), new Edge(E,3) };
    D.adjacencies = new Edge[]{ new Edge(E, 9), new Edge(C,9), new Edge(B,5) };
    E.adjacencies = new Edge[]{ new Edge(D, 9) };

    computePaths(stationA); // run Dijkstra
    System.out.println("Distance to " + stationB + ": " + stationB.minDistance);
    List<Vertex> path = getShortestPathTo(stationB);
    System.out.println("Path: " + path);

    return "From " + pointA + " to " + pointB +" the best route is " + path.toString() + " and should take about "+ (int)stationB.minDistance + " minutes.";
  }
}
