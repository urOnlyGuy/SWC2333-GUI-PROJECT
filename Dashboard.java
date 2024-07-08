import javax.swing.*;
import java.awt.*;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.TableRowSorter;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JFormattedTextField;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Dashboard implements ActionListener{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Dashboard();
		});
	}

	private JFrame frame;
	private ImageIcon icon;
	private static JLabel lblWelcome, lblFilter, lblNoOfProd1, lblNoOfProd2 , lblLowStock1 , lblLowStock2, lblOutOfStock1, lblOutOfStock2, lblPanel2_1_2, lblPanel2_2_2;
	private static JTable tableInventory; //set static for use of other class and method
	public DefaultTableModel tableModelInventory, tableModelOrder;
	private JButton btnAdd, btnPanel2Add,btnPrintInventory,btnPrintRestock;
	private JRadioButton filterButton1 , filterButton2;
	private ButtonGroup group1;
	private static Action delete;
	private JTable tableOrder;
	private JComboBox comboBox;
	private TableRowSorter<DefaultTableModel> sorter;

	public Dashboard() {
		EmptyBorder padding = new EmptyBorder(5, 5, 5, 5);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0 };
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setSize(820, 580);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		icon = new ImageIcon("icon.png"); // Instantiation icon
		frame.setIconImage(icon.getImage()); // Set icon for frame
		frame.setTitle("AIMS - Arif's Inventory Management System (by Arif)");

		JPanel topMainPanel = new JPanel();
		FlowLayout fl_topMainPanel = (FlowLayout) topMainPanel.getLayout();
		fl_topMainPanel.setVgap(2);
		fl_topMainPanel.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_topMainPanel = new GridBagConstraints();
		gbc_topMainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topMainPanel.fill = GridBagConstraints.BOTH;
		gbc_topMainPanel.gridx = 0;
		gbc_topMainPanel.gridy = 0;
		frame.getContentPane().add(topMainPanel, gbc_topMainPanel);

		//add component to topMainPanel
		lblWelcome = new JLabel("Welcome, Admin");
		topMainPanel.add(lblWelcome);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		/*
		 * 
		 * Creation of 1st tab
		 * 
		 */
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.weighty = 50.0;
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 1;
		frame.getContentPane().add(tabbedPane_1, gbc_tabbedPane_1);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.PINK); //to see any empty area only
		tabbedPane_1.addTab("Inventory", panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		JPanel panel1_Top = new JPanel();
		FlowLayout fl_panel1_Top = (FlowLayout) panel1_Top.getLayout();
		fl_panel1_Top.setHgap(20);
		panel1.add(panel1_Top);

		GridLayout gl_panel1_Top_Noti1 = new GridLayout(2, 0);

		//panel for No of Product
		JPanel panel1_Top_Noti1 = new JPanel();
		panel1_Top_Noti1.setBackground(new Color(176, 224, 230));
		panel1_Top.add(panel1_Top_Noti1);
		panel1_Top_Noti1.setLayout(gl_panel1_Top_Noti1);

		//label for no of product
		lblNoOfProd1 = new JLabel("Number of Product");
		lblNoOfProd1.setBorder(padding);
		lblNoOfProd2 = new JLabel("0");
		lblNoOfProd2.setBorder(padding);

		panel1_Top_Noti1.add(lblNoOfProd1);
		panel1_Top_Noti1.add(lblNoOfProd2);

		//panel for lowstock
		JPanel panel1_Top_LowStockPnl = new JPanel();
		panel1_Top_LowStockPnl.setBorder(new MatteBorder(1, 5, 1, 1, (Color) new Color(255, 165, 0)));
		panel1_Top_LowStockPnl.setBackground(new Color(255, 239, 213));
		panel1_Top.add(panel1_Top_LowStockPnl);
		GridLayout gl_panel1_Top_LowStockPnl = new GridLayout(2, 0);
		panel1_Top_LowStockPnl.setLayout(gl_panel1_Top_LowStockPnl);

		//label for low stock
		lblLowStock1 = new JLabel("Low-in-Stock Product");
		lblLowStock1.setBorder(padding);
		lblLowStock2 = new JLabel("getNumberOfProdWithLowStockStatus");
		lblLowStock2.setBorder(padding);

		panel1_Top_LowStockPnl.add(lblLowStock1);
		panel1_Top_LowStockPnl.add(lblLowStock2);

		//panel for out of stock
		JPanel panel1_Top_OutOfStockPnl = new JPanel();
		panel1_Top_OutOfStockPnl.setBackground(new Color(255, 228, 225));
		panel1_Top_OutOfStockPnl.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red));
		GridLayout gl_panel1_Top_OutOfStockPnl = new GridLayout(2, 0);
		gl_panel1_Top_OutOfStockPnl.setHgap(5);
		panel1_Top_OutOfStockPnl.setLayout(gl_panel1_Top_OutOfStockPnl);
		panel1_Top.add(panel1_Top_OutOfStockPnl);

		//label for out of stock
		lblOutOfStock1 = new JLabel("Out of Stock Product");
		lblOutOfStock1.setBorder(padding);
		lblOutOfStock2 = new JLabel("getNumberOfProdWithOutOfStockStatus");
		lblOutOfStock2.setBorder(padding);

		panel1_Top_OutOfStockPnl.add(lblOutOfStock1);
		panel1_Top_OutOfStockPnl.add(lblOutOfStock2);

		//panel for add button and filter panel
		JPanel panel1_Btn = new JPanel();
		panel1_Btn.setLayout(new BorderLayout());
		panel1.add(panel1_Btn);

		//add button
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		panel1_Btn.add(btnAdd,BorderLayout.WEST);
		
		//filter panel
		JPanel pnlFilter1 = new JPanel();
		lblFilter = new JLabel("Set Filter: ");
		filterButton1 = new JRadioButton();
		filterButton1.addActionListener(this);
		filterButton1.setText("Show All");
		filterButton1.setSelected(true);
		filterButton2 = new JRadioButton();
		filterButton2.addActionListener(this);
		filterButton2.setText("Out-of-stock");
		group1 = new ButtonGroup(); 
		group1.add(filterButton1);
		group1.add(filterButton2);
		
		pnlFilter1.add(lblFilter);
		pnlFilter1.add(filterButton1);
		pnlFilter1.add(filterButton2);
		panel1_Btn.add(pnlFilter1,BorderLayout.EAST);
		

		// data for jtable
		String dataInventory[][] = { { "101", "Coke", "20.50", "100", "", "Edit", "Delete" },
				{ "102", "Pepsi", "20.00", "20", "", "Edit", "Delete" },
				{ "103", "Bread", "20.50", "0", "", "Edit", "Delete" }, 
				{ "104", "Cake", "30.00", "15", "", "Edit", "Delete" },
				{ "105", "Mineral Water", "15.00", "100", "", "Edit", "Delete" },
				{ "106", "Green Tea", "28.00", "255", "", "Edit", "Delete" },
				{ "107", "Milo", "35.00", "100", "", "Edit", "Delete" },
				{ "108", "Potato Bun", "20.00", "50", "", "Edit", "Delete" }, };
		String columnInventory[] = { "PRODUCT ID", "PRODUCT NAME", "PRICE", "QUANTITY", "TOTAL", "", "" };

		DefaultTableModel tableModelInventory = new DefaultTableModel(dataInventory, columnInventory) {
			@Override
			public Object getValueAt(int row, int column) {
				if (column == 4) {
					double price = Double.parseDouble(getValueAt(row, 2).toString());
					int quantity = Integer.parseInt(getValueAt(row, 3).toString());
					double total = price * quantity;
					return String.format("%.2f", total);
				}
				return super.getValueAt(row, column);
			}
		};
        sorter = new TableRowSorter<DefaultTableModel>(tableModelInventory);
		tableInventory = new JTable(tableModelInventory) {
			//override method to set colour of cell for quantity column
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
			{
				Component comp = super.prepareRenderer(renderer, row, col);
				if (col == 3 && !(comp instanceof JButton)) // check for 4th column
				{
					Object value = getModel().getValueAt(row, col);
					int intValue = Integer.parseInt(value.toString());
					if (intValue <= 0) 
					{
						comp.setBackground(new Color(255, 200, 200));
					} else if (intValue <= 20) 
					{
						comp.setBackground(new Color(255, 239, 213));
					}
				} else {
					comp.setBackground(null);
				}
				// Ensure that other rendering properties (like borders) remain consistent
		        if (isCellSelected(row, col)) {
		            comp.setForeground(getSelectionForeground());
		            comp.setBackground(getSelectionBackground());
		        } else {
		            comp.setForeground(getForeground());
		        }
				return comp;
			}
		};
		tableInventory.setRowSorter(sorter);
		tableInventory.setDefaultEditor(Object.class, null); // make table uneditable.

		JScrollPane scrollPane1 = new JScrollPane(tableInventory);

		panel1.add(scrollPane1);

		// 				scrollPane.setViewportView(table);

		tabbedPane_1.setSelectedIndex(0); // this must be set after Instantiation of tabpane


		// count and update number of product
		lblNoOfProd2.setText(String.valueOf(tableInventory.getRowCount()));
		
		int lowStockCount = 0;
		int outOfStockCount = 0;
		// count no of out of stock and low stock
		for (int i = 0; i < tableInventory.getRowCount(); i++) {
		    int quantity = Integer.parseInt(tableInventory.getValueAt(i, 3).toString());
		    if (quantity <= 0) {
		        outOfStockCount++;
		    } else if (quantity <= 20) {
		        lowStockCount++;
		    }
		}
		//update notice panel for low stock and out of stock
		lblLowStock2.setText(String.valueOf(lowStockCount));
		lblOutOfStock2.setText(String.valueOf(outOfStockCount));
		
		// DONE 1ST TAB
		/*
		 * 
		 *  CREATION OF 2ND TAB CONTAINER (ORDER)
		 *  
		 */
		//create panel and tab for 2nd tab
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GREEN);
		tabbedPane_1.addTab("Restock", panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel2_Top = new JPanel();
		JPanel panel2_Btn = new JPanel();
		panel2_Btn.setLayout(new BorderLayout());
		
		JScrollPane scrollPane2 = new JScrollPane();
		
		panel2.add(panel2_Top, BorderLayout.NORTH);
		panel2.add(panel2_Btn, BorderLayout.CENTER);
		panel2.add(scrollPane2, BorderLayout.SOUTH);
		
		//setup label
		JLabel lblPanel2_1_1 = new JLabel("Total Cost of All Order (RM): ");
		lblPanel2_1_2 = new JLabel("Null");	
		JLabel lblPanel2_2_1 = new JLabel("Total Quantity of Ordered Item: ");
		lblPanel2_2_2 = new JLabel("Null");
		
		//add label to top panel of tab2panel2_Top
		panel2_Top.add(lblPanel2_1_1);
		panel2_Top.add(lblPanel2_1_2);
		panel2_Top.add(lblPanel2_2_1);
		panel2_Top.add(lblPanel2_2_2);
		
		//setup button
		btnPanel2Add = new JButton("Add Order/Invoice");
		btnPanel2Add.addActionListener(this);
		panel2_Btn.add(btnPanel2Add, BorderLayout.WEST);
		
		///create array type string for table
		String dataOrder[][] = { { "inv012345", "Star Enterprise", "Milo", "10.50","20", "", "10/12/24", "Delete" }
								,{ "bill001", "Maju Sdn Bhd", "Coke", "10.50","15", "", "02/12/24", "Delete" }
								,{ "inv24/01", "Gardenia", "bread", "10.50","5", "", "01/11/24", "Delete" }};
		String columnOrder[] = { "ORDER/INVOICE NO", "SUPPLIER", "ORDERED ITEM", "PRICE", "QUANTITY", "TOTAL", "DATE","" };

		tableModelOrder = new DefaultTableModel(dataOrder, columnOrder){
			//override defaultTableModel to auto-populate total column
			@Override
			public Object getValueAt(int row, int column) {
				if (column == 5) {
					double price = Double.parseDouble(getValueAt(row, 3).toString());
					int quantity = Integer.parseInt(getValueAt(row, 4).toString());
					double total = price * quantity;
					return String.format("%.2f", total);
				}
				return super.getValueAt(row, column);
			}
		};
		
		tableOrder = new JTable(tableModelOrder);
		scrollPane2.setViewportView(tableOrder);
		
		// count total cost of order and total quantity of all order
		double ttlCost = 0;
		int ttlOrderQuantity = 0;
		for (int i = 0; i < tableOrder.getRowCount(); i++) {
		    int quantity = Integer.parseInt(tableOrder.getValueAt(i, 4).toString());
		    ttlOrderQuantity = quantity + ttlOrderQuantity;
		    double cost = Double.parseDouble(tableOrder.getValueAt(i, 5).toString());
		    ttlCost = cost + ttlCost;
		    }
		
		//update toppanel label
		lblPanel2_1_2.setText(String.valueOf(ttlCost));
		lblPanel2_2_2.setText(String.valueOf(ttlOrderQuantity));

		/*
		 * 
		 * creation of 3rd tab panel
		 * 
		 */
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.ORANGE);
		tabbedPane_1.addTab("Report", panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel3_Top = new JPanel ();
		FlowLayout flowLayout = (FlowLayout) panel3_Top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnPrintInventory = new JButton("Print Inventory Data");
		btnPrintInventory.addActionListener(this);
		btnPrintRestock = new JButton("Print Restock Data");
		btnPrintRestock.addActionListener(this);
		panel3_Top.add(btnPrintInventory);
		panel3_Top.add(btnPrintRestock);
		
		panel3.add(panel3_Top, BorderLayout.NORTH);

		
		
        // Create a split pane to hold both the pie chart and bar chart
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        // Create the pie chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("In Stock", tableInventory.getRowCount() - outOfStockCount - lowStockCount);
        pieDataset.setValue("Low Stock", lowStockCount);
        pieDataset.setValue("Out of Stock", outOfStockCount);

        JFreeChart pieChart = ChartFactory.createPieChart("Product Stock Status", pieDataset, true, true, false);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        splitPane.setTopComponent(pieChartPanel);

        // Create the bar chart
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        for (int i = 0; i < tableOrder.getRowCount(); i++) {
            String supplier = tableOrder.getValueAt(i, 1).toString();
            int quantity = Integer.parseInt(tableOrder.getValueAt(i, 4).toString());
            barDataset.addValue(quantity, "Quantity", supplier);
        }

        JFreeChart barChart = ChartFactory.createBarChart("Ordered Items by Supplier", "Supplier", "Quantity", barDataset);
        ChartPanel barChartPanel = new ChartPanel(barChart);
        splitPane.setBottomComponent(barChartPanel);

        panel3.add(splitPane, BorderLayout.CENTER);
		
		
		//abstractAction(delete) for JTable's button
		@SuppressWarnings("serial")
		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				Object delete = table.getModel().getValueAt(modelRow, 1);
				Window window = SwingUtilities.windowForComponent(table);

				// show confirmation dialog for deletion
				int result = JOptionPane.showConfirmDialog(window,
						"Are you sure you want to delete " + delete + "? \nThis cannot be undone.", "Delete Row Confirmation",
						JOptionPane.YES_NO_OPTION);

				// 
				if (result == JOptionPane.YES_OPTION) {
					//								System.out.println( "Deleting row: " + modelRow);
					((DefaultTableModel) table.getModel()).removeRow(modelRow);
					
					// recount no
					lblNoOfProd2.setText(String.valueOf(tableInventory.getRowCount()));
					
					// recount no of out of stock and low stock
					int lowStockCount = 0;
					int outOfStockCount = 0;
					for (int i = 0; i < tableInventory.getRowCount(); i++) {
					    int quantity = Integer.parseInt(tableInventory.getValueAt(i, 3).toString());
					    if (quantity <= 0) {
					        outOfStockCount++;
					    } else if (quantity <= 20) {
					        lowStockCount++;
					    }
					}
					//update notice pnlEdit for low stock and out of stock
					lblLowStock2.setText(String.valueOf(lowStockCount));
					lblOutOfStock2.setText(String.valueOf(outOfStockCount));
					
					// count total cost of order and total quantity of all order in Restock
					double ttlCost = 0;
					int ttlOrderQuantity = 0;
					for (int i = 0; i < tableOrder.getRowCount(); i++) {
					    int quantity = Integer.parseInt(tableOrder.getValueAt(i, 4).toString());
					    ttlOrderQuantity = quantity + ttlOrderQuantity;
					    double cost = Double.parseDouble(tableOrder.getValueAt(i, 5).toString());
					    ttlCost = cost + ttlCost;
					    }
					
					//update top panel label in Restock
					lblPanel2_1_2.setText(String.valueOf(ttlCost));
					lblPanel2_2_2.setText(String.valueOf(ttlOrderQuantity));
				}
			}
		};
		
		Action edit = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				Object edit = table.getModel().getValueAt(modelRow, 1);
				Window window = SwingUtilities.windowForComponent(table);

				// Create a JPanel with GridLayout for the input dialog
		        JPanel pnlEdit = new JPanel(new GridLayout(4, 2, 5, 5));
		        JLabel lblProdID = new JLabel("Product ID:");
		        JTextField txtProdID = new JTextField(10);
		        JLabel lblProdName = new JLabel("Product Name:");
		        JTextField txtProdName = new JTextField(10);
		        JLabel lblPrice = new JLabel("Price:");
		        JTextField txtPrice = new JTextField(10);
		        JLabel lblQuantity = new JLabel("Quantity:");
		        JTextField txtQuantity = new JTextField(10);

		        // Populate the text fields with the existing values
		        txtProdID.setText(table.getValueAt(modelRow, 0).toString());
		        txtProdName.setText(table.getValueAt(modelRow, 1).toString());
		        txtPrice.setText(table.getValueAt(modelRow, 2).toString());
		        txtQuantity.setText(table.getValueAt(modelRow, 3).toString());

		        pnlEdit.add(lblProdID);
		        pnlEdit.add(txtProdID);
		        pnlEdit.add(lblProdName);
		        pnlEdit.add(txtProdName);
		        pnlEdit.add(lblPrice);
		        pnlEdit.add(txtPrice);
		        pnlEdit.add(lblQuantity);
		        pnlEdit.add(txtQuantity);

		        // Show the input dialog
		        int result = JOptionPane.showConfirmDialog(window, pnlEdit, "Edit Product: " + edit, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                String prodId = txtProdID.getText();
		                String prodName = txtProdName.getText();
		                double price = Double.parseDouble(txtPrice.getText());
		                int quantity = Integer.parseInt(txtQuantity.getText());

		                if (price < 0 || quantity < 0) {
		                    JOptionPane.showMessageDialog(null, "Invalid input for price or quantity. Input must not be a negative value");
		                } else {
		                    // update value in table
		                    table.setValueAt(prodId, modelRow, 0);
		                    table.setValueAt(prodName, modelRow, 1);
		                    table.setValueAt(price, modelRow, 2);
		                    table.setValueAt(quantity, modelRow, 3);

		                    // Recount the number of products
		                    lblNoOfProd2.setText(String.valueOf(table.getRowCount()));

		                    int outOfStockCount = 0;
		                    int lowStockCount = 0;
		                    // Recount the number of out of stock and low stock products
		                    for (int i = 0; i < table.getRowCount(); i++) {
		                        int qty = Integer.parseInt(table.getValueAt(i, 3).toString());
		                        if (qty <= 0) {
		                            outOfStockCount++;
		                        } else if (qty <= 20) {
		                            lowStockCount++;
		                        }
		                    }
		                    lblLowStock2.setText(String.valueOf(lowStockCount));
		                    lblOutOfStock2.setText(String.valueOf(outOfStockCount));
		                    // refresh the "Total" column calculation
		                    ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, 4);
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input for price or quantity. Please enter numeric values.");
		            }
		        }
		    }
		};
		
		ButtonColumn btnColumn = new ButtonColumn(tableInventory, edit, 5); //add edit button to tableInventory
		btnColumn.setMnemonic(KeyEvent.VK_E);
		ButtonColumn btnColumn2 = new ButtonColumn(tableInventory, delete, 6); //add delete button to tableInventory
		btnColumn2.setMnemonic(KeyEvent.VK_D);
		
		ButtonColumn btnColumn3 = new ButtonColumn(tableOrder, delete, 7); //add delete button to tableOrder
		btnColumn3.setMnemonic(KeyEvent.VK_D);
		
	}//end of dashboard constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd)
		{
			InventoryAdd invAddFrame= new InventoryAdd();
		} else if (e.getSource() == btnPrintInventory) {
            try {
				tableInventory.print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(null, "ERROR \n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
        } else if (e.getSource() == btnPrintRestock) {
        	try {
				tableOrder.print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(null, "ERROR \n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
        } else if (e.getSource() == filterButton1) {
            filterTable("Show All");
        } else if (e.getSource() == filterButton2) {
            filterTable("Out-of-stock");
        }else if (e.getSource() == btnPanel2Add)
		{
			System.out.println("button add IN tab 2 clicked"); // test if actionlistener is functioning.
			
			//GUI Panel for adding item into Order Table
	        JPanel pnlAddOrder = new JPanel(new GridLayout(6, 2, 5, 5));
	        JLabel lblOrderNo = new JLabel("Supplier Order/Invoice No: ");
	        JTextField txtOrderNo = new JTextField(10);
	        JLabel lblSupplierName = new JLabel("Supplier Name: ");
	        JTextField txtSupplierName = new JTextField(10);
	        JLabel lblOrderItem = new JLabel("Item To Order: ");
	        comboBox = new JComboBox();
	        JLabel lblOrderPrice = new JLabel("Price:");
	        JTextField txtOrderPrice = new JTextField(10);
	        JLabel lblOrderQuantity = new JLabel("Quantity:");
	        JTextField txtOrderQuantity = new JTextField(10);
	        JLabel lblOrderDate = new JLabel("Date (dd/MM/yy):");
	        JFormattedTextField txtOrderDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yy"));
	        txtOrderDate.setColumns(10);
	        txtOrderDate.setValue(new Date()); // set current date as default
	        
	        //populate jcombobox item from tableInventory
	        int itemCount = tableInventory.getRowCount();
	        for (int i = 0; i < itemCount; i++) {
	            String item = (String) tableInventory.getValueAt(i, 1); // Assuming the item is in the second column
	            comboBox.addItem(item);
	        }

	        //add component into panelAddOrder
	        pnlAddOrder.add(lblOrderNo);
	        pnlAddOrder.add(txtOrderNo);
	        pnlAddOrder.add(lblSupplierName);
	        pnlAddOrder.add(txtSupplierName);
	        pnlAddOrder.add(lblOrderItem);
	        pnlAddOrder.add(comboBox);
	        pnlAddOrder.add(lblOrderPrice);
	        pnlAddOrder.add(txtOrderPrice);
	        pnlAddOrder.add(lblOrderQuantity);
	        pnlAddOrder.add(txtOrderQuantity);
	        pnlAddOrder.add(lblOrderDate);
	        pnlAddOrder.add(txtOrderDate);
			
	        // Show the input dialog
	        int result = JOptionPane.showConfirmDialog(frame, pnlAddOrder, "Add Order/Invoice ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	        if (result == JOptionPane.OK_OPTION) {
	                try {	    	          
		                String orderNo = txtOrderNo.getText();
		                String supplierName = txtSupplierName.getText();
		                String itemName = comboBox.getSelectedItem().toString();
		                double price = Double.parseDouble(txtOrderPrice.getText());
		                int quantity = Integer.parseInt(txtOrderQuantity.getText());
		                String orderDate = txtOrderDate.getText();
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		                sdf.setLenient(false);
		                Date date = sdf.parse(orderDate); // This will throw ParseException if the date is invalid
		                
	                	if (price <= 0 || quantity <= 0) {	//check if price/quantity is not negative
		                    JOptionPane.showMessageDialog(null, "Invalid input for price or quantity. Input must be a positive value");
		                } else 
		                {
		                	for (int i = 0; i < itemCount;i++) {
		                		String itemToCompare = tableInventory.getValueAt(i, 1).toString();
	                			System.out.println("Currently: "+i); 	//to show which row is being process
		                		if (itemToCompare.equalsIgnoreCase(itemName)) {
		                			int qtyBefore = Integer.parseInt(tableInventory.getValueAt(i, 3).toString());
		                			int qtyAfter = qtyBefore + quantity;
		                			String qtyAfterStr = Integer.toString(qtyAfter);
		                			tableInventory.setValueAt(qtyAfterStr,i,3);
		                			JOptionPane.showMessageDialog(null, "Reorder of "+ itemName+ " successful. ", itemName + "Restocked! ", JOptionPane.INFORMATION_MESSAGE);
		                			break;
		                		}
		                	}

		                	DefaultTableModel modelInventory = (DefaultTableModel)tableOrder.getModel();
		                	modelInventory.addRow(new Object[]{orderNo, supplierName, itemName , price, quantity, "", orderDate,"Delete"});
		                	
		                    // Recount the number of out of stock and low stock products
		                	int outOfStockCount = 0;
		                    int lowStockCount = 0;
		                    for (int i = 0; i < tableInventory.getRowCount(); i++) {
		                        int qty = Integer.parseInt(tableInventory.getValueAt(i, 3).toString());
		                        if (qty <= 0) {
		                            outOfStockCount++;
		                        } else if (qty <= 20) {
		                            lowStockCount++;
		                        }
		                    }
		                    lblLowStock2.setText(String.valueOf(lowStockCount));
		                    lblOutOfStock2.setText(String.valueOf(outOfStockCount)); 
		            		lblNoOfProd2.setText(String.valueOf(tableInventory.getRowCount()));// recount no of product update label
		            		
		            		// count total cost of order and total quantity of all order
		            		double ttlCost = 0;
		            		int ttlOrderQuantity = 0;
		            		for (int i = 0; i < tableOrder.getRowCount(); i++) {
		            		    int quantityOrder = Integer.parseInt(tableOrder.getValueAt(i, 4).toString());
		            		    ttlOrderQuantity = quantityOrder + ttlOrderQuantity;
		            		    double cost = Double.parseDouble(tableOrder.getValueAt(i, 5).toString());
		            		    ttlCost = cost + ttlCost;
		            		    }
		            		
		            		//update toppanel label
		            		lblPanel2_1_2.setText(String.valueOf(ttlCost));
		            		lblPanel2_2_2.setText(String.valueOf(ttlOrderQuantity));
		                	
		    	        }
	                	
	                }  catch (NumberFormatException e3) {
	                    JOptionPane.showMessageDialog(frame, "Invalid input for price or quantity. Input must be: \n-a numeric value \n-not a negative value." );
	                } catch (ParseException e3) {
	                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please enter the date in dd/MM/yy format.");
	                }             
	            }
	        }//end of action for btnPanel2Add
		 
	}//end of actionperformed
	
	//custom method/constructor to add row to Inventory JTable
    public static void AddRowToTable(Object[] dataRow)
    {
        int outOfStockCount = 0;
        int lowStockCount = 0;
        DefaultTableModel model = (DefaultTableModel)tableInventory.getModel();
        model.addRow(dataRow);
        
        //						ButtonColumn buttonColumn = new ButtonColumn(tableInventory, delete, 5); // table
		//						buttonColumn.setMnemonic(KeyEvent.VK_E);
        
        // Recount the number of out of stock and low stock products
        for (int i = 0; i < tableInventory.getRowCount(); i++) {
            int qty = Integer.parseInt(tableInventory.getValueAt(i, 3).toString());
            if (qty <= 0) {
                outOfStockCount++;
            } else if (qty <= 20) {
                lowStockCount++;
            }
        }
        lblLowStock2.setText(String.valueOf(lowStockCount));
        lblOutOfStock2.setText(String.valueOf(outOfStockCount)); 
		lblNoOfProd2.setText(String.valueOf(tableInventory.getRowCount()));// recount no of product update label
    }//end of constructor method to add row to JTable tableInventory
    
    //constructor for filter for tableinventory
    private void filterTable(String filterType) {
        RowFilter<DefaultTableModel, Object> rf = null;
        if (filterType.equals("Show All")) {
            sorter.setRowFilter(null); // Show all rows
        } else if (filterType.equals("Out-of-stock")) {
            RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
                public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    int quantity = Integer.parseInt(entry.getStringValue(3).toString());
                    return quantity <= 0;
                }
            };
            sorter.setRowFilter(filter);
        }
    }

}//end of class dashboard