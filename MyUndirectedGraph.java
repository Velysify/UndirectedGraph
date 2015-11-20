//Viktor Bogren, vibo4548@student.su.se
import java.util.HashMap;
import java.util.ArrayList;

public class MyUndirectedGraph<T> implements UndirectedGraph<T> {
	
	public static class Node<T>{
		public Node(T t){
			element = t; 
			adjacencyList = new HashMap<T, Integer>();
			mtsAdjList = new HashMap<T, Integer>();
			known = false;
			p = this;
			d = -1;
			
		}
		public boolean known; 
		public T element;
		public int d = -1;
		public Node<T> p; 
		HashMap<T, Integer> adjacencyList;
		HashMap<T, Integer> mtsAdjList;
	}
	
	
	HashMap<T,Node<T>> graph = new HashMap<T,Node<T>> ();
	
	public boolean add(T newNode){
		if(graph.get(newNode) == null){
			Node<T> create = new Node<T>(newNode);
			graph.put(create.element,create);
			return true;
		}
		else{
			return false;
		}
	}
		
	public boolean connect(T node1, T node2, int cost){
		if(graph.get(node1) != null && graph.get(node2) != null){
			Node<T> firstNode = (Node<T>) graph.get(node1);
			Node<T> secondNode = (Node<T>)graph.get(node2);
			firstNode.adjacencyList.put(node2, cost);
			secondNode.adjacencyList.put(node1, cost);
			return true;
		}
		else{
			return false;
		}
	}
		
	public int getCost(T node1, T node2){
		if(graph.containsKey(node1) && graph.containsKey(node2)){
			Node<T> firstNode = graph.get(node1);
			if(firstNode.adjacencyList.containsKey(node2)){
			int cost = firstNode.adjacencyList.get(node2);
			return cost;
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}			
	}
	
	public UndirectedGraph<T> minimumSpanningTree(){
		MyUndirectedGraph<T> mst;
		HashMap<T, Node<T>> mtsgraph = new HashMap<T,Node<T>>();
		ArrayList<Node <T>> mstList = new ArrayList<Node <T>> (graph.values());
		while(true){
			Node<T> node = mstList.get(0);
			Node<T> nextNode = mstList.get(0);
			for(int index = 0; index < mstList.size(); index++ ){
				Node<T>	secondNode = mstList.get(0);
				node.known = true;
				int value;
				System.out.println(node.element);
				for(int secIndex = 0; secIndex < mstList.size(); secIndex++){
					if (node.adjacencyList.get(mstList.get(secIndex).element) != null){
						value = node.adjacencyList.get(mstList.get(secIndex).element);
						if(value != -1){
							secondNode = graph.get(mstList.get(secIndex).element);
							if(secondNode.known == false && secondNode.d > value || secondNode.known == false && secondNode.d == -1){
								secondNode.d = value;
								secondNode.p = node;
								System.out.println(node.element + " " + secondNode.element + " " + secondNode.d);

							}
						}
					}
				}
				int nextCost = Integer.MAX_VALUE;
				for(int thiIndex = 0; thiIndex < mstList.size(); thiIndex++){
					Node<T> getNode = mstList.get(thiIndex);
					if(getNode.d != -1 && getNode.d < nextCost && getNode.known == false){
						nextCost = getNode.d;
						nextNode = getNode;
					}
				}
				node = nextNode;
			}
			System.out.println();
			for(int index = 0; index < mstList.size(); index++){
				Node<T> currentNode = mstList.get(index);
				System.out.println(currentNode.element + " " + currentNode.p.element + " " + currentNode.d);
				currentNode.mtsAdjList.put(currentNode.p.element, currentNode.d);
				currentNode.p.mtsAdjList.put(currentNode.element, currentNode.d);
				mtsgraph.put(currentNode.element, currentNode);
			}
			for(int index = 0; index < mstList.size(); index++){
				Node<T> currentNode = mstList.get(index);
				currentNode.adjacencyList = currentNode.mtsAdjList;
			}
			graph = mtsgraph;
			mst = MyUndirectedGraph.this;
			
			break;
		}
		return mst;
	}
}
