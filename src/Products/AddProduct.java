package Products;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class AddProduct extends JFrame implements ActionListener {

	JTextField t1, t2 ,t3 ;
	JButton b1 , b2;
	RandomAccessFile raf;
	
	public AddProduct() {
		
		try {
			raf = new RandomAccessFile("data.txt","rw");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		setBounds(400 , 150 , 830 , 500);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel l0 = new JLabel("ADD NEW PRODUCT");
		l0.setBounds(120 , 10 , 300 , 18);
		l0.setFont(new Font("Tahoma", Font.BOLD,18));
		l0.setForeground(Color.RED);
		add(l0);
		
		JLabel l1 = new JLabel("Product ID:");
		l1.setBounds(30 , 90 , 150 , 25);
		l1.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(l1);
		
		t1 = new JTextField();
		t1.setBounds(220, 90, 200, 25);
		t1.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(t1);
		
		JLabel l2 = new JLabel("Product Name:");
		l2.setBounds(30 , 140 , 150 , 25);
		l2.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(l2);
		
		t3 = new JTextField();
		t3.setBounds(220, 140, 200, 25);
		t3.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(t3);
		
		
		JLabel l3 = new JLabel("Product Price:");
		l3.setBounds(30 , 190 , 150 , 25);
		l3.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(l3);
		
		t2 = new JTextField();
		t2.setBounds(220, 190, 200, 25);
		t2.setFont(new Font("Tahoma", Font.PLAIN,20));
		add(t2);
		
		b1 = new JButton("Add");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setBounds(70, 330, 150, 25);
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton("Back");
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.BLACK);
		b2.setBounds(240, 330, 150, 25);
		b2.addActionListener(this);
		add(b2);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource
				("Products/icon/add.png"));
		Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l4 = new JLabel(i3);
		l4.setBounds(450, 80, 300, 300);
		add(l4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == b1) {
			int id = Integer.parseInt(t1.getText());
			String name = t3.getText();
			double price = Double.parseDouble(t2.getText());
			
			String data = id + " " + name + " " + price;
			int l = data.length();
			int space = 100 - l;
			for(int i = 1 ; i <= space ; i++) {
				data = data + " ";
			}
			data = data + "\n";
			
			try {
				int size = (int) raf.length();
				raf.seek(size);
				raf.writeBytes(data);
				JOptionPane.showMessageDialog(null, "Product Added Successfully");

				
			} catch (IOException ae) {
				System.out.println("Product can't be added");
			}
		}else if(e.getSource() == b2) {
			dispose();
		}
		
	}
	
	public static void main(String[] args) {
		new AddProduct().setVisible(true);
	}
}
