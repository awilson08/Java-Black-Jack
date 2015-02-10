import java.util.HashMap;
import java.util.Map;




public class Card {

	// ===============================================
	// ==FIELD
	// ===============================================
	/**
	 * @uml.property  name="face"
	 */
	

	private char cFace;
	private String cSuit;
	/**
	 * This map is static.
	 */
	private static Map<Character, Integer> map = new HashMap<Character, Integer>();
	static {
		
		map.put('2', 2);
		map.put('3', 3);
		map.put('4', 4);
		map.put('5', 5);
		map.put('6', 6);
		map.put('7', 7);
		map.put('8', 8);
		map.put('9', 9);
		map.put('T', 10);
		map.put('J', 10);
		map.put('Q', 10);
		map.put('K', 10);
		map.put('A', 11);
		
	}//end static
	
	
	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================
	public Card(char face, String suit) {
		this.cFace = face;
		this.cSuit = suit;
	}

	// ===============================================
	// ==METHODS
	// ===============================================

	/**
	 * This method lorem ipsum.
	 * @return 
	 * @uml.property  name="face"
	 * @return char representing the face of the card.
	 */
	public String getSuit(){
		return this.cSuit;
	}
	
	public char getFace() {
		return this.cFace;
	}

	public void setFace(char cFace) {
		this.cFace = cFace;
	}
	
	public int getValue(){
		return map.get(getFace());
	}
	
	@Override 
	public String toString(){
		return getFace() + ":" + getValue() + getSuit();
	}

	
}
