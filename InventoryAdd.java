import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class InventoryAdd extends JFrame implements ActionListener {
//declare components
	private JFrame inventoryFrame;
	private JLabel lblProdID, lblProdName, lblPrice, lblQuantity;
	private JTextField textProdID, textProdName, textPrice, textQuantity;
	private JPanel pnlData, pnlButton;
	private JButton btnAdd, btnBack;
	private JLabel label;

//setup GUI
	public InventoryAdd() {
		EmptyBorder padding = new EmptyBorder(5, 5, 5, 5);
		
		inventoryFrame = new JFrame();
        inventoryFrame.getContentPane();
		
		//setup label
		lblProdID = new JLabel("Product ID");
		lblProdName = new JLabel("Product Name");
		lblPrice = new JLabel("Price");
		lblQuantity = new JLabel("Quantity");
		
		//setup textfield
		textProdID = new JTextField(15);
		textProdName = new JTextField(15);
		textPrice = new JTextField(15);
		textQuantity = new JTextField(15);
		
		//setup jbutton
		btnAdd = new JButton("Add Product");
		btnAdd.addActionListener(this);
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		
		//setup panel
		pnlData = new JPanel();
		pnlData.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlData.setBorder(new TitledBorder(new EtchedBorder(), "Enter New Product Details", TitledBorder.CENTER, TitledBorder.TOP));
		
		//add component to panelData
        //label and textF for ProdID
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlData.add(lblProdID,gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlData.add(textProdID,gbc);
        
        //label and textF for ProdName
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlData.add(lblProdName,gbc); 
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlData.add(textProdName,gbc);
        
        //label and textF for Price
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlData.add(lblPrice,gbc);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        pnlData.add(textPrice,gbc);
        
        //label and textF for Qty
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlData.add(lblQuantity,gbc);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnlData.add(textQuantity,gbc);
        
        //setup panel for button
        pnlButton = new JPanel();
        pnlButton.setBorder(BorderFactory.createEmptyBorder(8, 5, 5, 5)); // Add padding
        pnlButton.setLayout(new BorderLayout());
        
        pnlButton.add(btnBack,BorderLayout.WEST);
        pnlButton.add(btnAdd,BorderLayout.EAST);
        
        
        inventoryFrame.getContentPane().setLayout(new BorderLayout());
        inventoryFrame.getContentPane().add(pnlData,BorderLayout.CENTER);
        inventoryFrame.getContentPane().add(pnlButton,BorderLayout.SOUTH);
		//GUI setting
		inventoryFrame.pack(); //let layout manager determine frame size
		inventoryFrame.setTitle("Add New Product");
		inventoryFrame.setLocationRelativeTo(null);
		inventoryFrame.setVisible(true);
		ImageIcon icon = new ImageIcon("icon.png"); // Instantiation icon
		inventoryFrame.setIconImage(icon.getImage()); // Set icon for frame
		inventoryFrame.setDefaultCloseOperation(inventoryFrame.DISPOSE_ON_CLOSE);//set to only close this window when click on X
		
	}

	//actionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd)
		{
			String prodID, prodName;
            double price;
			int quantity;
			
			try {
			prodID = textProdID.getText();
            prodName = textProdName.getText();
            price = Double.parseDouble(textPrice.getText());
            quantity = Integer.parseInt(textQuantity.getText());
	        // Check if any of the text fields are empty
	        if (textProdID.getText().isEmpty() || 
	            textProdName.getText().isEmpty() || 
	            textPrice.getText().isEmpty() || 
	            textQuantity.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "All fields must be filled out.");
	            return;
	        } else if (price < 0 || quantity < 0){ //check if input for price and quantity is less than 0
	        	JOptionPane.showMessageDialog(null, "Quantity and/or price must not be a negative value");
	        	return;
	        } else {
	            Dashboard.AddRowToTable(new Object[]{prodID, prodName, price, quantity, "", "Edit", "Delete"});
	            textProdID.setText("");
	    		textProdName.setText("");
	    		textPrice.setText("");
	    		textQuantity.setText("");
	        }
	            
			} catch (Exception e1){
				JOptionPane.showMessageDialog(null, "Invalid input/format. \nPlease check that you type in the right character/number for each boxes.\n\n" + e1);
			}//end of TryCatch BLOCK
            
			
			//action
		} else if (e.getSource() == btnBack)
		{
			//close this window if press on back button
			inventoryFrame.dispose();
		}//end of ifelse block
		
	}//end of actionListener

}
