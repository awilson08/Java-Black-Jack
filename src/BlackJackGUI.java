import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BlackJackGUI implements ActionListener{

	private JFrame frmBlackjack;
	private static JLabel lblCashRemaining;
	private static JPanel playerCardsPanel;
	private static JPanel DealerCardsPanel;
	private JButton btnQuit;
	private static JButton btnStick;
	private static JButton btnHit;
	private static Player plyPlayer;
	private static Player plyDealer;
	private static Chute cht;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGUI window = new BlackJackGUI();
					window.frmBlackjack.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}//end main

	public BlackJackGUI() {
		initialize();
		startGame();
		playGame();
	}
	public static void startGame(){
		cht = new Chute();
		cht.shuffle();

		//get player's name
		String strName = JOptionPane.showInputDialog("What is your name?");
		
		//instantiate two players
		plyPlayer = new Player(strName, 1000.0);
		plyDealer = new Player("Dealer", 0.0);
		
	}
	
	public static void playGame(){
			//deal out the cards
			plyPlayer.hit(cht.deal());
			plyDealer.hit(cht.deal());
			plyPlayer.hit(cht.deal());			
			plyDealer.hit(cht.deal());
			
			
			//initial cards for player
			displayHand(plyPlayer);
			
			//initial cards for dealer - make sure that dealer's first card remains hidden
			plyDealer.setHidden(true);
			displayHand(plyDealer);
			
			
			//if both players have 21- tie!
			if (plyPlayer.getHandValue() == 21 && plyDealer.getHandValue() == 21 ){
				JOptionPane.showMessageDialog(null,"TIE");
				//show hands
				displayHand(plyPlayer);
				plyDealer.setHidden(false);
				displayHand(plyDealer);
				
				if (playAgain()){playGame();}
				else{/*clear screen*/
					playerCardsPanel.removeAll(); playerCardsPanel.repaint();
					DealerCardsPanel.removeAll(); DealerCardsPanel.repaint(); 
				}
				
					
			} 
			else if (plyDealer.getHandValue() == 21 ){
				JOptionPane.showMessageDialog(null,"DEALER HAS BLACKJACK");
				//show hands
				displayHand(plyPlayer);
				plyDealer.setHidden(false);
				displayHand(plyDealer);
				
				//player loses 100 dollars
				plyPlayer.setMoney(plyPlayer.getMoney() - 100);
				
				//reportBankStatus(plyPlayer); //update label
				lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
				lblCashRemaining.repaint();
				
				if (playAgain()){
					//get the cards back from the players and put them back into the chute
					cht.recapture(plyDealer.returnCards());
					cht.recapture(plyPlayer.returnCards());
					playGame();}
				else{/*clear screen*/
					playerCardsPanel.removeAll(); playerCardsPanel.repaint();
					DealerCardsPanel.removeAll(); DealerCardsPanel.repaint(); 
				}
				
			}	
			else if (plyPlayer.getHandValue() == 21 ){
				JOptionPane.showMessageDialog(null,"YOU HAVE BLACKJACK");
				//show hands
				displayHand(plyPlayer);
				plyDealer.setHidden(false);
				displayHand(plyDealer);

				//player gains 150 dollars
				plyPlayer.setMoney(plyPlayer.getMoney() + 150);
				
				//update label
				lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
				lblCashRemaining.repaint();
					
				if (playAgain()){
					//get the cards back from the players and put them back into the chute
					cht.recapture(plyDealer.returnCards());
					cht.recapture(plyPlayer.returnCards());
					playGame();}
				
				else{/*clear screen*/
					playerCardsPanel.removeAll(); playerCardsPanel.repaint();
					DealerCardsPanel.removeAll(); DealerCardsPanel.repaint(); 
				}
			}
			else {
				
				//enable hit and stick
				btnHit.setEnabled(true);
				btnStick.setEnabled(true);
			}

		
	
	}//end playGame
	

	private void hit() {
	
		if (plyPlayer.getHandValue() <= 21){
			plyPlayer.hit(cht.deal());
			displayHand(plyPlayer);
		}
		if (plyPlayer.getHandValue() > 21){
			JOptionPane.showMessageDialog(null,"YOU BUSTED");
			
			//show hands
			displayHand(plyPlayer);
			plyDealer.setHidden(false);
			displayHand(plyDealer);
		
			//update money
			plyPlayer.setMoney(plyPlayer.getMoney() - 100);
			lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
			lblCashRemaining.repaint();

	
			if (playAgain()){
				//get the cards back from the players and put them back into the chute
				cht.recapture(plyDealer.returnCards());
				cht.recapture(plyPlayer.returnCards());
				playGame();}
		}	
		
	}
	
	private void stick() {
		while (plyDealer.getHandValue() < 17){plyDealer.hit(cht.deal());}
		
		//dealer busted
		if (plyDealer.getHandValue() > 21){
			JOptionPane.showMessageDialog(null,"DEALER BUSTED");
			
			//show hands
			displayHand(plyPlayer);
			plyDealer.setHidden(false);
			displayHand(plyDealer);
			
			plyPlayer.setMoney(plyPlayer.getMoney() + 100);
			lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
			lblCashRemaining.repaint();
				
			if (playAgain()){
				//get the cards back from the players and put them back into the chute
				cht.recapture(plyDealer.returnCards());
				cht.recapture(plyPlayer.returnCards());
				playGame();}

		}
		else {
			//you lose
			 if (plyDealer.getHandValue() > plyPlayer.getHandValue()){
				 JOptionPane.showMessageDialog(null,"YOU LOSE");
					
				 	//show hands
					displayHand(plyPlayer);
					plyDealer.setHidden(false);
					displayHand(plyDealer);
					
					plyPlayer.setMoney(plyPlayer.getMoney() - 100);
					lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
					lblCashRemaining.repaint();
					
					if (playAgain()){
						//get the cards back from the players and put them back into the chute
						cht.recapture(plyDealer.returnCards());
						cht.recapture(plyPlayer.returnCards());
						playGame();}
				}
			 //you win
				else if (plyDealer.getHandValue() < plyPlayer.getHandValue()){
					JOptionPane.showMessageDialog(null,"YOU WIN");
					
					//show hands
					displayHand(plyPlayer);
					plyDealer.setHidden(false);
					displayHand(plyDealer);
					
					plyPlayer.setMoney(plyPlayer.getMoney() + 100);
					lblCashRemaining.setText("CASH REMAINING: " + reportBankStatus(plyPlayer));
					lblCashRemaining.repaint();
					
					if (playAgain()){
						//get the cards back from the players and put them back into the chute
						cht.recapture(plyDealer.returnCards());
						cht.recapture(plyPlayer.returnCards());
						playGame();}
				}
			
			 //tie
					else {
						JOptionPane.showMessageDialog(null,"TIE");
						
						//show hands
						displayHand(plyPlayer);
						plyDealer.setHidden(false);
						displayHand(plyDealer);
						
						if (playAgain()){
							//get the cards back from the players and put them back into the chute
							cht.recapture(plyDealer.returnCards());
							cht.recapture(plyPlayer.returnCards());
							playGame();}
					}
			
			}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("HIT")){
		
			hit();
		}
		
		if (e.getActionCommand().equals("STICK")){
			stick();
		}
		
		if (e.getActionCommand().equals("QUIT")){
			System.exit(0);
		}	
	}
	
	
	private static String reportBankStatus(Player ply) {
		DecimalFormat dfm = new DecimalFormat("$0.00");
		return dfm.format(ply.getMoney());
	}
	
	private static boolean playAgain(){
		int intAgain = JOptionPane.showConfirmDialog(null, "Play Again?", "Would you like to play again?", 
				JOptionPane.YES_NO_OPTION);
		
		if (intAgain==JOptionPane. 	YES_OPTION){
			displayHand(plyPlayer);
			plyDealer.setHidden(true);
			displayHand(plyDealer);
			return true;
		} 
		else {return false;}
	
	}
	
	public static void displayHand(Player X){
		int counter = 0;
		JLabel[] hand;
		
		//clear panels with cards each time this is updated
		if (X.getName() != "Dealer"){playerCardsPanel.removeAll();}
		else{DealerCardsPanel.removeAll();} 
	
		if (!X.getHand().isHidden()) {
			
			for (Card crd : X.getHand().crdCards){
				
			//create new J label array for card images
			hand = new JLabel[X.getHand().crdCards.size()];
			hand[counter] = new JLabel("");
			hand[counter].setHorizontalAlignment(SwingConstants.RIGHT);
			hand[counter].setIcon(new ImageIcon(BlackJackGUI.class.getResource("/resources/" + 
				crd.getFace() + crd.getSuit() + ".png")));
				
				if (X.getName() != "Dealer"){playerCardsPanel.add(hand[counter]);}
				else{DealerCardsPanel.add(hand[counter]);}
			counter++;
			}
		
		}
		else{
			for (int nC = 0; nC < X.getHand().crdCards.size(); nC++) {
				if (nC == 0){
					//first card return blank card image
					hand = new JLabel[X.getHand().crdCards.size()];
					hand[0] = new JLabel("");
					hand[0].setIcon(new ImageIcon(BlackJackGUI.class.getResource("/resources/CardBackRed.png")));
					
					if (X.getName() != "Dealer"){playerCardsPanel.add(hand[0]); }
					else{DealerCardsPanel.add(hand[0]);}
				} 
				else{
					hand = new JLabel[X.getHand().crdCards.size()];
					hand[nC] = new JLabel("");
					hand[nC].setHorizontalAlignment(SwingConstants.LEFT);
					hand[nC].setIcon(new ImageIcon(BlackJackGUI.class.getResource("/resources/" + 
							X.getHand().crdCards.get(nC).getFace() +X.getHand().crdCards.get(nC).getSuit() + ".png")));
					
					if (X.getName() != "Dealer"){playerCardsPanel.add(hand[nC]); }
					else{DealerCardsPanel.add(hand[nC]);} 
				}
			}//end for
			

		}//end else
		playerCardsPanel.revalidate();
		playerCardsPanel.repaint();
		DealerCardsPanel.revalidate();
		DealerCardsPanel.repaint();
	}//end display
	

	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBlackjack = new JFrame();
		frmBlackjack.setTitle("BlackJack 21");
		frmBlackjack.setBounds(100, 100, 500, 600);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MainPanel = new JPanel();
		frmBlackjack.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel SouthPanel = new JPanel();
		MainPanel.add(SouthPanel, BorderLayout.SOUTH);
		
		btnQuit = new JButton("QUIT");
		SouthPanel.add(btnQuit);
		btnQuit.addActionListener(this);
		
		btnStick = new JButton("STICK");
		btnStick.setEnabled(false);
		SouthPanel.add(btnStick);
		btnStick.addActionListener(this);
		
		btnHit = new JButton("HIT");
		btnHit.setEnabled(false);
		SouthPanel.add(btnHit);
		btnHit.addActionListener(this);
		
		JPanel TopPanel = new JPanel();
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		TopPanel.add(panel_1);
		
		lblCashRemaining = new JLabel("CASH REMAINING: $1,000.00");
		lblCashRemaining.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TopPanel.add(lblCashRemaining);
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBorder(new LineBorder(new Color(139, 69, 19), 10, true));
		CenterPanel.setBackground(new Color(0, 128, 0));
		CenterPanel.setForeground(Color.BLACK);
		MainPanel.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new BorderLayout(0, 0));
		
		DealerCardsPanel = new JPanel();
		DealerCardsPanel.setBackground(new Color(0, 128, 0));
		CenterPanel.add(DealerCardsPanel, BorderLayout.NORTH);
		DealerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		playerCardsPanel = new JPanel();
		playerCardsPanel.setBackground(new Color(0, 128, 0));
		CenterPanel.add(playerCardsPanel, BorderLayout.SOUTH);
		playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel DeckPanel = new JPanel();
		DeckPanel.setBorder(null);
		DeckPanel.setBackground(new Color(0, 128, 0));
		CenterPanel.add(DeckPanel, BorderLayout.EAST);
		DeckPanel.setLayout(new BoxLayout(DeckPanel, BoxLayout.X_AXIS));
		
		JLabel lblDeckSTATIC = new JLabel("");
		lblDeckSTATIC.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeckSTATIC.setIcon(new ImageIcon(BlackJackGUI.class.getResource("/resources/CardBackRed.png")));
		DeckPanel.add(lblDeckSTATIC);
	}







}//end class
