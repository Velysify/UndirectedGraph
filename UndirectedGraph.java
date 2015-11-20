
public interface UndirectedGraph<T> {

	/**
	 * L�gger till en ny nod i grafen.
	 * 
	 * @param newNode
	 *            datat f�r den nya noden som ska l�ggas till i grafen.
	 * @return false om noden redan finns.
	 */
	boolean add(T newNode);

	/**
	 * Kopplar samman tv� noder i grafen. Eftersom grafen �r oriktad s� spelar
	 * det ingen roll vilken av noderna som st�r f�rst. Det �r ocks�
	 * fullst�ndigt okej att koppla ihop en nod med sig sj�lv. D�remot till�ts
	 * inte multigrafer. Om tv� noder f�rs�ks kopplas ihop som redan �r
	 * ihopkopplade uppdateras bara deras kostnadsfunktion.
	 * 
	 * @param node1
	 *            den ena noden.
	 * @param node2
	 *            den andra noden.
	 * @param cost
	 *            kostnaden f�r att ta sig mellan noderna.
	 * @return true om b�gge noderna finns i grafen och kan kopplas ihop.
	 */
	boolean connect(T node1, T node2, int cost);

	/**
	 * Returnerar kostnaden f�r att ta sig mellan tv� noder.
	 * 
	 * @param node1
	 *            den ena noden.
	 * @param node2
	 *            den andra noden.
	 * @return kostnaden f�r att ta sig mellan noderna.
	 */
	int getCost(T node1, T node2);

	/**
	 * Returnerar en ny graf som utg�r ett minimalt sp�nnande tr�d till grafen.
	 * 
	 * @return en graf som representerar ett minimalt sp�nnande tr�d.
	 */
	UndirectedGraph<T> minimumSpanningTree();
}