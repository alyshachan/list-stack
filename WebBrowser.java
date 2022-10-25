package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * @author Alysha Chan and Chavo Salazar
 * 
 *         WebBrowser simulates how a web browser
 *         visits webpages, especially how the
 *         back button shows the previous webpage
 *         visited and how the forward button
 *         shows the next webpage.
 *         
 *         WebBrowser uses two private instances of
 *         LinkedListStack to keep track
 *         of the previous webpages visited (back) and one
 *         to keep track of the next webpages (forward).
 *         A private URL variable is used to keep track of
 *         the current page.
 */
public class WebBrowser{
		
	private LinkedListStack<URL> back;
	private LinkedListStack<URL> forward;
	private URL currentPage;
	
	
	/**
	 * WebBrowser constructor with no parameters
	 * creates a new web browser with no
	 * previously-visited webpages and no
	 * webpages to visit next.
	 */
	public WebBrowser(){
		back = new LinkedListStack<>();
		forward = new LinkedListStack<>();
		currentPage = null;
	}
	
	/**
	 * WebBrowser constructor with a SinglyLinkedList
	 * as the parameter creates a new web browser
	 * with a preloaded history of visited webpages,
	 * given as a list of URL objects. The first
	 * webpage in the list is the "current" page
	 * visited, and the remaining webpages are
	 * ordered from most recently visited
	 * to least recently visited.
	 * 
	 * @param history - a singly-linked list with pre-existing history for the web browser
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		back = new LinkedListStack<>();
		LinkedListStack<URL> temp = new LinkedListStack<>();
		
		for (int i = 1; i < history.size(); i++) {
			temp.push(history.get(i));
		}
		for (int i = 1; i < history.size(); i++) {
			back.push(temp.pop());
		}
		forward = new LinkedListStack<>();
		currentPage = history.get(0);
	}
	
	/**
	 * Visit() simulates visiting a webpage,
	 * given as a URL.  Calling this method
	 * clears the forward stack, and adds the 
	 * previously current page to back. This is just like a forward
	 * button (right facing arrow) on a real web browser.
	 * When you visit a link, the right-arrow is cleared.
	 * And the previous page is added to the back button.
	 * 
	 * @param webpage - a URL of a webpage to visit
	 */
	public void visit(URL webpage) {
		if (currentPage != null) {
			back.push(currentPage);
			currentPage = webpage;
			forward.clear();
		} else {
		currentPage = webpage;
		}
	}
	
	/**
	 * This method simulates using the back button,
	 * returning the URL visited. Forward will have
	 * the previously current page. NoSuchElementException
	 * is thrown if there is no previously-visited URL.
	 * 
	 * @return currentPage - the first page in the back stack
	 */
	public URL back() throws NoSuchElementException{
		if (back.isEmpty())
			throw new NoSuchElementException();
		forward.push(currentPage);
		currentPage = back.pop();
		return currentPage;
	}
	
	/**
	 * This method simulates using the forward button,
	 * returning the URL visited. Back will have
	 * the previously current page. NoSuchElementException
	 * is thrown if there is no URL to visit next.
	 * 
	 * @return currentPage - the first page in the forward stack
	 */
	public URL forward() throws NoSuchElementException{
		if (forward.isEmpty())
			throw new NoSuchElementException();
		back.push(currentPage);
		currentPage = forward.pop();
		return currentPage;
	}
	
	/**
	 * The history() method generates a history of URLs visited,
	 * as a list of URL objects ordered from most
	 * recently visited to least recently visited
	 * (including the "current" page visited), without
	 * altering subsequent behavior of this web browser.
	 * "Forward" links are not included.
	 * 
	 * The behavior of the method is O(N), where N is the number of URLs.
	 * 
	 *  @return urlHistory - a singly-linked list of the web brower's history
	 */
	public SinglyLinkedList<URL> history(){
		SinglyLinkedList<URL> urlHistory = new SinglyLinkedList<>();
		LinkedListStack<URL> temp = new LinkedListStack<>();
		int size = back.size();
		
		urlHistory.insertFirst(currentPage);

		for (int i = 0; i < size; i++) {
			urlHistory.insert(i + 1 , back.peek());
			temp.push(back.pop());
		}
		
		for (int i = 0; i < size; i++) {
			back.push(temp.pop());
		}
		return urlHistory;
	}
	
}
