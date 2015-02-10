import java.util.ArrayList;
import java.util.Collections;

public class Chute {
	private ArrayList<Card> crdCards;
	public enum Suit {Diamonds, Clubs, Hearts, Spades};
	private int nRemaining;
	
	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================
	public Chute(){
		//instantiate the arrayList
		crdCards = new ArrayList<Card>();
		
		nRemaining = 52;
		
		char[] cCards = { 
						  '2',
						  '3',
						  '4',
						  '5',
						  '6',
						  '7',
						  '8',
						  '9',
						  'T',
						  'J',
						  'Q',
						  'K',
						  'A'
						  };

		
		
		//load the chute
		
		for (Suit suit: Suit.values()) 
			for (int nD = 0; nD < cCards.length; nD++) 
				  add(new Card(cCards[nD], suit.toString()));
				

	}//end constructor
	
	//Suits
	//0 = Diamonds
	//1= Clubs
	//2= Hearts
	//3 = Spades
	
	
	// ===============================================
	// ==METHODS
	// ===============================================
	public ArrayList<Card> getCards() {
		return this.crdCards;
	}

	public void setCards(ArrayList<Card> crdCards) {
		this.crdCards = crdCards;
	}


	public void recapture(ArrayList<Card> crdParams){
		
		for (Card crd : crdParams) {
			crdCards.add(crd);
		}
	}


	private void add(Card crd){
		crdCards.add(crd);
		
	}
	public Card deal(){

		if (nRemaining == 0){
			shuffle();
			nRemaining = 52;
		} 
		
		    Card crd;
			crd = crdCards.remove(0);
			nRemaining--;
			return crd;
		

	}
	
	public int getNumCards(){
		return crdCards.size();
	}
	
	public int getNunCardsRemainingBeforeShuffle(){
		return nRemaining;
	}

	public void shuffle(){
		Collections.shuffle(crdCards);
	}
	

	
}