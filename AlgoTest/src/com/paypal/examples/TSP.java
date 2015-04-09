package com.paypal.examples;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TSP {
	
	Set<Point> travelCoordinates;
	Set<Point> unvisited;
	final double MAX_DIST = 100000;
	
	public TSP(Set<Point> points)
	{
		travelCoordinates = points;
		unvisited =  new HashSet<Point>(50);
		unvisited.addAll(travelCoordinates);

	}
	
	Point determineNextVisit(Point prevVisit)
	{
		Iterator iter = unvisited.iterator();
		
		double distance = MAX_DIST;
		
		Point rv = null;
		
		while(iter.hasNext()) {		
			Point pt = (Point)iter.next();
			
			if(prevVisit == null)
			{
				return pt;
			}
			
			double dist = pt.distance(prevVisit);
			if(dist < distance)
			{
				distance = dist;
				rv = pt;
			}
			
		}
		
		return rv;
	}
	
	public List<Point> TSPClosestNeighbour()
	{
		List<Point> path = new ArrayList<Point>(50);		
		
		Point prevVisit = null;
		while(!unvisited.isEmpty()) {
			Point pt = determineNextVisit(prevVisit);
			path.add(pt);
			unvisited.remove(pt);
			prevVisit = pt;
		}
		
		return path;
		
	}
	
	public static void main(String args[])
	{
		Set<Point> points = new HashSet<Point>(10);
		points.add(new Point(0,0));
		points.add(new Point(2,0));
		points.add(new Point(1,1));
		points.add(new Point(0,1));
		TSP tsp = new TSP(points);
		List<Point> path = tsp.TSPClosestNeighbour();
		
		Iterator<Point> it  = path.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
	}

}
