package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		runEngine();
		/*
		 * Map<String,Object> map = new HashMap<String, Object>(); map.put("input",
		 * "patar");
		 * 
		 * boolean match = MvelParser.parseMvelExpression("input == 'patar1'", map);
		 * 
		 * System.out.println(match); MvelParser.parseMvelExpression("input='data'",
		 * map); System.out.println(map);
		 */
		
		//generateLogicByGraph();
		//generateApplication();
	}
	
	
	public static void runEngine() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("input", "patar");
		
		Start start = new Start("Start");
		start.setInput(map);
		start.setLogic("System.out.println('patar2333')");
	
		Event event = new Event("Great Logic");
	    event.setLogic("system.out.println('patar2333')");
	}
	
	
	
	public Map<String,Object> runIt(String functionName,Start start, Map<String,Object> input,DirectedGraph<Process, ExecutorLine> directedGraph ){
		Boolean run = true;
		start.execute();
		Set<ExecutorLine> edges = directedGraph.outgoingEdgesOf(start);
		while(run && edges.size()>0) {
			List<ExecutorLine> list = new ArrayList<ExecutorLine>(edges);
				if(list.size()>0) {
				Collections.sort(list, new Comparator<ExecutorLine>() {
	
					@Override
					public int compare(ExecutorLine a, ExecutorLine b) {
						// TODO Auto-generated method stub
						int c = 0;
						if(a.getPriority()>b.getPriority()) {
							c = 1;
						}
						return c;
					}
				});
				
				for(ExecutorLine l:list) {
					if(l.isAllowedContinue()) {
						
						break;
					}
							
				}
			}
		}
		
		return input;
	}
	public static void generateLogicByGraph() {
		Start start = new Start("start");
		start.setLogic("");
		Event event = new Event("Cek Logic");
	    event.setLogic("i>200");
	    
	    Event event5 = new Event("Cek Logic 5");
	    event.setLogic("i>205");
	    DirectedGraph<Process, ExecutorLine> directedGraph 
		  = new DefaultDirectedGraph<>(ExecutorLine.class);
	    directedGraph.addVertex(start);
		directedGraph.addVertex(event);
		directedGraph.addVertex(event5);
		directedGraph.addEdge(start, event,new ExecutorLine(1));
		directedGraph.addEdge(start, event5,new ExecutorLine(2));
		Set<ExecutorLine> edges = directedGraph.outgoingEdgesOf(start);
		List<ExecutorLine> list = new ArrayList<ExecutorLine>(edges);
		Collections.sort(list, new Comparator<ExecutorLine>() {

			@Override
			public int compare(ExecutorLine a, ExecutorLine b) {
				// TODO Auto-generated method stub
				int c = 0;
				if(a.getPriority()>b.getPriority()) {
					c = 1;
				}
				return c;
			}
		});
		
		for(ExecutorLine l:list) {
			Process p =  directedGraph.getEdgeSource(l);
			System.out.println(p.isStart());
			System.out.println(l.getPriority());
		}
	}
	
	public static void generateApplication() {
		DirectedGraph<String, DefaultEdge> directedGraph 
		  = new DefaultDirectedGraph<>(DefaultEdge.class);
		directedGraph.addVertex("v1");
		directedGraph.addVertex("v2");
		directedGraph.addVertex("v3");
		directedGraph.addEdge("v1", "v2");
		directedGraph.addEdge("v1", "v3");
		directedGraph.addEdge("v2", "v1");
		
		
	    Set<DefaultEdge> edges = directedGraph.outgoingEdgesOf("v1");
	    Set<DefaultEdge> edges1 = directedGraph.incomingEdgesOf("v1");
	    System.out.println(edges.size());
	    System.out.println(edges1.size());
	}

}
